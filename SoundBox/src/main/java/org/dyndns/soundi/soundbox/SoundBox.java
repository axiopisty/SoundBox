/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundbox;

import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 *
 * @author oli
 */
public class SoundBox {

    private BundleContext context;

    SoundBox(BundleContext context) {
        this.context = context;
    }

    void init() {
        new Thread() {

            @Override
            public void run() {
                //get the eventadmin and the event message
                ServiceReference ref = context.getServiceReference(EventAdmin.class.getName());
                final EventAdmin eventAdmin = (EventAdmin) context.getService(ref);
                if(eventAdmin == null)
                {
                    System.out.println("Sorry, no event admin installed. No EventAdmin, no communication, no SoundBox.");
                    System.exit(-1);
                }
                Map crap = new Hashtable(); //only neccessary because there's no constructor like Event(String topic, null); :/
                final Event reportGeneratedEvent = new Event(CommunicationAction.SETBROWSERVISIBLE.toString(), crap);

                //now get the browser interface
                ref = context.getServiceReference(IBrowserGui.class.getName());
                while (ref == null) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException ex) {
                        java.util.logging.Logger.getLogger(SoundBox.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ref = context.getServiceReference(IBrowserGui.class.getName());
                }
                //last but not least, send it
                eventAdmin.sendEvent(reportGeneratedEvent);
            }
        }.start();
    }

    void start() {
    }
}
