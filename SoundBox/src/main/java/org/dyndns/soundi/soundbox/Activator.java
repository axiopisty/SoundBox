package org.dyndns.soundi.soundbox;

import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.portals.interfaces.PortalRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogReaderService;

/**
 * This is the main class which must be the first one that gets called - as osgi
 * bundle of course. This class provides the updater, the showGui call, and the
 * exit function of the application.
 * 
 * @version 0.0.1
 * @author oli
 */
public class Activator implements BundleActivator {

    private PortalRegistry portalRegistry;
    
    @Override
    public void start(final BundleContext context) throws Exception {

        SoundBox box = new SoundBox(context);
        box.init();
        box.start();
        
        ServiceReference ref = context.getServiceReference(LogReaderService.class.getName());
        if (ref != null) {
            LogReaderService reader = (LogReaderService) context.getService(ref);
            reader.addLogListener(new Logger());
        }

        //todo: call the updater ... but at least code it :>

        

        //get the logger, thats where the debugging infos will go to...
        /*ServiceTracker logServiceTracker = new ServiceTracker(context, org.osgi.service.log.LogService.class.getName(), null);
        logServiceTracker.open();
        LogService logservice = (LogService) logServiceTracker.getService();
        
        if (logservice != null) {
        logservice.log(LogService.LOG_INFO, "hey, I logged that!");
        System.out.println("sent a log");
        }*/
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // maybe there should be an CommunicationAction.MAINAPPCLOSED message
        // so that a nice "close" of the application can be made...
    }
}
