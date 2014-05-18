package org.dyndns.soundi.soundbox;

import ch.trustserv.soundbox.abstr.communication.message.IMsgIdentifier;
import ch.trustserv.soundbox.abstr.communication.message.MsgCloseSoundBox;
import ch.trustserv.soundbox.abstr.communication.message.MsgSendGlobalConfig;
import ch.trustserv.soundbox.abstr.communication.message.MsgSetMainWindowVisible;
import ch.trustserv.soundbox.interfaces.gui.IBrowserGui;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;
import org.slf4j.LoggerFactory;

/**
 * This is the main class of SoundBox Core. SoundBox Core starts the UI and
 * checks if the EventAdmin is registered in the OSGi environment.
 *
 * @author Oliver Zemann
 */
public class SoundBox implements EventHandler {

    static final org.slf4j.Logger logger = LoggerFactory.getLogger(SoundBox.class);
    /**
     * The Event Admin instance to communicate with other bundles.
     */
    private transient EventAdmin eventAdmin;
    /**
     * Defines the polling intervall for checking if a UI is registered.
     */
    private static final int SLEEP_TIMEOUT = 1_000;
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
    protected void init() {

        /*
         * get the eventadmin and the event message
         */
        ServiceReference ref = null;

        while (ref == null) {
            ref = context.getServiceReference(
                    EventAdmin.class.getName());
            if (ref == null) {
                logger.warn("Waiting for the event admin...");
                try {
                    Thread.sleep(SLEEP_TIMEOUT);
                } catch (InterruptedException ex) {
                    logger.error(ex.getLocalizedMessage());
                }
            }
        }

        logger.warn("Registering context in Util class.");

        eventAdmin = (EventAdmin) context.getService(ref);
        if (eventAdmin == null) {
            final String msg = "Sorry, no event admin "
                    + "installed. No EventAdmin, no communication, no "
                    + "SoundBox.";
            logger.warn(msg);
            throw new RuntimeException(msg);
        }

        /*
         * now get the browser interface
         */
        ref = context.getServiceReference(IBrowserGui.class.getName());
        while (ref == null) {
            try {
                Thread.sleep(SLEEP_TIMEOUT);
            } catch (InterruptedException ex) {
                logger.error(ex.getLocalizedMessage());
            }
            ref = context.getServiceReference(IBrowserGui.class.getName());
            logger.warn("waiting for the gui registration...");
        }
        /*
         * last but not least, send it
         */
        new MsgSetMainWindowVisible().send(eventAdmin);
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
    protected void close() {
        new MsgCloseSoundBox().send(eventAdmin);
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getTopic().equals(IMsgIdentifier.GETGLOBCONFIG)) {
            new MsgSendGlobalConfig().send(eventAdmin);
        }
    }
}
