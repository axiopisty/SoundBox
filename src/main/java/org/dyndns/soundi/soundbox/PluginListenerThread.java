package org.dyndns.soundi.soundbox;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.dyndns.soundi.portals.interfaces.PluginInformation;
import static org.dyndns.soundi.portals.interfaces.State.ACTIVATED;
import static org.dyndns.soundi.communicationaction.core.Requests.REGISTERPORTAL;
import static org.dyndns.soundi.communicationaction.core.Requests.UNREGISTERPORTAL;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 * This is the implementation of the Thread that listens for changes in the
 * ./load directory. If a new Portal has been found, the SoundBox gets informed.
 * The SoundBox core sends a Message to all listeners with the required Infor-
 * mations.
 *
 * @author oli
 */
public class PluginListenerThread implements Runnable {

    /**
     * Creates a new Runnable which handles registering/unregistering plugins.
     * If a plugin is registered, a new event is fired to inform all listeners.
     * If a plugin is unregistered, there will be also an event, informing all
     * listeners which plugin has been removed.
     *
     * @param context
     */
    PluginListenerThread(BundleContext context) {
        this.context = context;
        ref = context.getServiceReference(EventAdmin.class.getName());
    }
    /**
     * Defines the polling intervall for checking if a UI is registered.
     */
    private static final int SLEEP_TIMEOUT = 1000;
    /**
     * The main context from the osgi framework.
     */
    private final BundleContext context;
    /**
     * Contains all registered Portals. This is the "model"-part.
     */
    private HashSet<IPortal> portals = new HashSet<IPortal>();
    /**
     *
     */
    private ServiceReference ref = null;
    /**
     *
     */
    ServiceReference[] refs = null;

    @Override
    public void run() {
        while (true) {

            try {
                /*
                 * fetch all portals
                 */
                refs = context.getServiceReferences(IPortal.class.getName(),
                        null);
            } catch (InvalidSyntaxException ex) {
                Logger.getLogger(SoundBox.class.getName()).
                        log(Level.SEVERE, null, ex);
            }

            /*
             * if we found at least one...
             */
            if (refs != null) {
                /*
                 * loop through it/them
                 */
                for (ServiceReference reference : refs) {
                    /*
                     * cast it to an instance so we can use it (fetch
                     * informations from it)
                     */
                    final IPortal portal =
                            (IPortal) context.getService(reference);
                    /*
                     * check if it is listed in our portals list
                     */
                    if (!portals.contains(portal)) {
                        /*
                         * if not, call init() on the portals implemented
                         * interface
                         */
                        PluginInformation infos = portal.getInfos();
                        if (infos != null) {
                            System.out.println("Registered new portal ("
                                    + infos.getPluginName() + ")!");
                        }
                        if (portal.getState() == ACTIVATED) {
                            portal.init();
                        }
                        addPortal(portal);
                        portals.add(portal);
                    }
                    /*
                     * check if a portal has been removed
                     */
                    if (portals.size() != refs.length) {
                        /*
                         * something changed... as we already registered every
                         * new portal, it must be a deletion
                         */
                        System.out.println("a plugin has been removed!");
                    }
                }
            }

            if (refs == null && portals.size() == 1) /*
             * there's still one portal registered, but it has been removed
             */ {
                IPortal portal = (IPortal) portals.iterator().next();
                removePortal(portal);
                System.out.println("Plugin " + portals.iterator().next());
                portals.clear();
            }

            try {
                Thread.currentThread().sleep(SLEEP_TIMEOUT);
            } catch (InterruptedException ex) {
                Logger.getLogger(SoundBox.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is invoked when a Portal is registered via the load
     * directory.
     *
     * @param portal The portal which has been found in ./load
     */
    private void addPortal(IPortal portal) {

        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) context.getService(ref);
            Dictionary properties = new Hashtable();
            properties.put("portal", portal);
            Event reportGeneratedEvent = new Event(REGISTERPORTAL.toString(),
                    properties);
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
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
    private void removePortal(IPortal portal) {

        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) context.getService(ref);
            Dictionary properties = new Hashtable();
            properties.put("portal", portal);
            Event reportGeneratedEvent = new Event(UNREGISTERPORTAL.toString(),
                    properties);
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
    }
}
