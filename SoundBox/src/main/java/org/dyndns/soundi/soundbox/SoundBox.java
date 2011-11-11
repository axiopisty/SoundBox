/**
 *
 */
package org.dyndns.soundi.soundbox;

import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
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
     * Default constructor for the SoundBox.
     * @param cont The OSGi BundleContext, mostly from the Activator.
     */
    SoundBox(final BundleContext cont) {
        context = cont;
    }

    /**
     * This method initializes the SoundBox. In general it checks if the
     * event admin is installed and tries to set up the UI.
     */
    protected final void init() {

        new Thread() {

            @Override
            public void run() {
                //get the eventadmin and the event message
                ServiceReference ref = context.getServiceReference(
                        EventAdmin.class.getName());
                final EventAdmin eventAdmin = (EventAdmin)
                        context.getService(ref);
                if(eventAdmin == null) {
                    Util.sendMessage(Component.CORE, "Sorry, no event admin "
                            + "installed. No EventAdmin, no communication, no "
                            + "SoundBox.");
                    System.exit(-1);
                }
                
                //now get the browser interface
                ref = context.getServiceReference(IBrowserGui.class.getName());
                while (ref == null) {
                    try {
                        Thread.currentThread().sleep(SLEEP_TIMEOUT);
                    } catch (InterruptedException ex) {
                        java.util.logging.Logger.getLogger(SoundBox.class.
                                getName()).log(Level.SEVERE, null, ex);
                    }
                    ref = context.getServiceReference(IBrowserGui.class.getName());
                    Util.sendMessage(Component.CORE,
                            "waiting for the gui registration...");
                }
                //last but not least, send it
                final Map crap = new Hashtable();
                final Event event = new Event(
                        CommunicationAction.SETBROWSERVISIBLE.toString(), crap);
                eventAdmin.sendEvent(event);
            }
        }.start();
    }
}
