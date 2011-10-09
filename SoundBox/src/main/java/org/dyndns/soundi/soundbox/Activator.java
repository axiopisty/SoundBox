package org.dyndns.soundi.soundbox;

import java.util.Hashtable;
import java.util.Map;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogReaderService;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator, EventHandler {

    @Override
    public void start(BundleContext context) throws Exception {

        //register the soundbox to _all_ events, we use it as debugging facility
        //TODO: nothing will get logged because null is not accepted - fix this!
        context.registerService(EventHandler.class.getName(), this, null);

        ServiceReference ref = context.getServiceReference(LogReaderService.class.getName());
        if (ref != null) {
            LogReaderService reader = (LogReaderService) context.getService(ref);
            reader.addLogListener(new Logger());
        }

        //set the browser to visible
        ref = context.getServiceReference(EventAdmin.class.getName());
        EventAdmin eventAdmin = (EventAdmin) context.getService(ref);
        Map crap = new Hashtable(); //only neccessary because there's no constructor like Event(String topic, null); :/
        Event reportGeneratedEvent = new Event(CommunicationAction.SETBROWSERVISIBLE.toString(), crap);
        eventAdmin.sendEvent(reportGeneratedEvent);

        //get the logger, thats where the debugging infos will go to...
        ServiceTracker logServiceTracker = new ServiceTracker(context, org.osgi.service.log.LogService.class.getName(), null);
        logServiceTracker.open();
        LogService logservice = (LogService) logServiceTracker.getService();

        if (logservice != null) {
            logservice.log(LogService.LOG_INFO, "hey, I logged that!");
            System.out.println("sent a log");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

    @Override
    public void handleEvent(Event event) {
        System.out.println("Retrieved event: " + event.getTopic());
    }
}
