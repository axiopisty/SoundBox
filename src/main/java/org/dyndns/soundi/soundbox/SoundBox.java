package org.dyndns.soundi.soundbox;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.dyndns.soundi.communicationaction.core.Requests.SETBROWSERVISIBLE;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.dyndns.soundi.portals.interfaces.PluginInformation;
import static org.dyndns.soundi.portals.interfaces.State.ACTIVATED;
import org.dyndns.soundi.utils.Util;
import org.dyndns.soundi.utils.Util.Component;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 * This is the main class of SoundBox Core. SoundBox Core starts the UI and
 * checks if the EventAdmin is registered in the OSGi environment.
 *
 * @author oli
 */
public class SoundBox {

    /**
     * Defines the polling intervall for checking if a UI is registered.
     */
    private static final int SLEEP_TIMEOUT = 1000;
    /**
     *
     */
    private final transient BundleContext context;
    /**
     * Contains all registered Portals. This is the "model"-part.
     */
    private HashSet<IPortal> portals = new HashSet<IPortal>();

    /**
     * Default constructor for the SoundBox.
     *
     * @param cont The OSGi BundleContext, mostly from the Activator.
     */
    SoundBox(final BundleContext cont) {
        context = cont;
    }

    /**
     * This method initializes the SoundBox. In general it checks if the event
     * admin is installed and tries to set up the UI.
     */
    protected final void init() {

        new Thread() {

            @Override
            public void run() {
                /*
                 * get the eventadmin and the event message
                 */
                ServiceReference ref = context.getServiceReference(
                        EventAdmin.class.getName());
                final EventAdmin eventAdmin = (EventAdmin) context.getService(ref);
                if (eventAdmin == null) {
                    String msg = "Sorry, no event admin "
                            + "installed. No EventAdmin, no communication, no "
                            + "SoundBox.";
                    Util.sendMessage(Component.CORE, msg);
                    throw new RuntimeException(msg);
                }

                /*
                 * now get the browser interface
                 */
                ref = context.getServiceReference(IBrowserGui.class.getName());
                while (ref == null) {
                    try {
                        Thread.currentThread().sleep(SLEEP_TIMEOUT);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SoundBox.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                    ref = context.getServiceReference(IBrowserGui.class.getName());
                    Util.sendMessage(Component.CORE,
                            "waiting for the gui registration...");
                }
                /*
                 * last but not least, send it
                 */
                final Map crap = new Hashtable();


                final Event event = new Event(
                        SETBROWSERVISIBLE.toString(), (Dictionary) crap);
                eventAdmin.sendEvent(event);
            }
        }.start();
        pluginListener();

    }

    /**
     * TODO: this method should be in the soundbox, not in the gui!!
     */
    private void pluginListener() {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.currentThread().sleep(SLEEP_TIMEOUT);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SoundBox.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }

                    ServiceReference[] references = null;
                    try {
                        /*
                         * fetch all portals
                         */
                        references = context.getServiceReferences(IPortal.class.getName(), null);
                    } catch (InvalidSyntaxException ex) {
                        Logger.getLogger(SoundBox.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }

                    /*
                     * if we found at least one...
                     */
                    if (references != null) {
                        /*
                         * loop through it/them
                         */
                        for (ServiceReference reference : references) {
                            /*
                             * cast it to an instance so we can use it (fetch
                             * informations from it)
                             */
                            final IPortal portal = (IPortal) context.getService(reference);
                            /*
                             * check if it is listed in our portals list
                             */
                            if (!portals.contains(portal)) {
                                /*
                                 * if not, call init() on the portals
                                 * implemented interface
                                 */
                                PluginInformation infos = portal.getInfos();
                                if (infos != null) {
                                    System.out.println("Registered new portal (" + infos.getPluginName() + ")!");
                                }
                                if (portal.getState() == ACTIVATED) {
                                    portal.init();
                                }
                                addPortalToGui(portal);
                                portals.add(portal);
                            }
                            /*
                             * check if a portal has been removed
                             */
                            if (portals.size() != references.length) {
                                /*
                                 * something changed... as we already registered
                                 * every new portal, it must be a deletion
                                 */
                                System.out.println("a plugin has been removed!");
                            }
                        }
                    }

                    if (references == null && portals.size() == 1) /*
                     * there's still one portal registered, but it has been
                     * removed
                     */ {
                        IPortal portal = (IPortal) portals.iterator().next();
                        removePortalFromGui(portal);
                        System.out.println("Plugin " + portals.iterator().next());
                        portals.clear();
                    }
                }
            }
        }.start();
    }

    /**
     * This method removes a portal from the internal list of portals. An event
     * to all listeners will be send via EventAdmin. Further informations about
     * the Event can be found in the communication API in
     *
     * @see org.dyndns.soundi.communicationaction This event is fired when a
     * portal has been removed from the ./load directory.
     * @param portal The portal that should be removed.
     */
    private void removePortalFromGui(final IPortal portal) {
    }

    /**
     * This method is invoked when a Portal is registered via the load
     * directory.
     *
     * @param portal The portal which has been found in ./load
     */
    private void addPortalToGui(final IPortal portal) {
    }
}
