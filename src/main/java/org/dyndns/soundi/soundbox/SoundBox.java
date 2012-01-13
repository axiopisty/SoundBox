package org.dyndns.soundi.soundbox;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dyndns.soundi.communicationaction.core.Requests;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.utils.Util;
import org.dyndns.soundi.utils.Util.Component;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 * This is the main class of SoundBox Core. SoundBox Core starts the UI and
 * checks if the EventAdmin is registered in the OSGi environment.
 *
 * @author Oliver Zemann
 */
public class SoundBox {

    /**
     * The Event Admin instance to communicate with other bundles.
     */
    private transient EventAdmin eventAdmin;
    /**
     * Defines the polling intervall for checking if a UI is registered.
     */
    private static final int SLEEP_TIMEOUT = 1000;
    /**
     * The BundleContext, given by the Activator.
     */
    private final transient BundleContext context;

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

        /*
         * get the eventadmin and the event message
         */
        ServiceReference ref = null;

        while (ref == null) {
            ref = context.getServiceReference(
                    EventAdmin.class.getName());
            if (ref == null) {
                Util.sendMessage(Component.CORE,
                        "Waiting for the event admin...");
                try {
                    Thread.currentThread().sleep(SLEEP_TIMEOUT);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SoundBox.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        }

        eventAdmin = (EventAdmin) context.getService(ref);
        if (eventAdmin == null) {
            final String msg = "Sorry, no event admin "
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
                Requests.SETBROWSERVISIBLE.toString(), (Dictionary) crap);
        eventAdmin.sendEvent(event);
        pluginListener();

    }

    /**
     * This method starts the listener thread which checks the ./load directory.
     */
    private void pluginListener() {
        final Thread listener = new Thread(new PluginListenerThread(context),
                "Plugin Listener");
        listener.start();
    }

    /**
     * This method sends the CLOSE-event to all bundles that are registered to
     * the shutdown hook.
     */
    protected final void close() {
        final Map crap = new Hashtable();
        final Event event = new Event(
                Requests.CLOSE.toString(), (Dictionary) crap);
        eventAdmin.sendEvent(event);
    }
}
