package org.dyndns.soundi.soundboxportalregistry;

import java.util.Dictionary;
import java.util.Hashtable;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.portals.interfaces.PortalRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        PortalRegistry registry = new DefaultPortalRegistry();
        // store the service registry in the OSGi EventAdmin
        context.registerService(PortalRegistry.class.getName(), registry, null);
        System.out.println("Standard Plugin Registry registered.");
        Thread t = new Thread((DefaultPortalRegistry)registry, "PluginRegistry");
        t.start();
        // now add it to the notification list if something changed in the plugins, as we want to retrieve events 
        // regarding to portal plugins
        String[] topics = new String[]{
            CommunicationAction.REGISTERPORTAL.toString(), CommunicationAction.UNREGISTERPORTAL.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        context.registerService(EventHandler.class.getName(), registry, props);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
