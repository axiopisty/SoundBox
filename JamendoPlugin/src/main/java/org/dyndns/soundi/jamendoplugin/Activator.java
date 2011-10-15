package org.dyndns.soundi.jamendoplugin;

import java.util.Dictionary;
import java.util.Hashtable;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        // register the plugin to the osgi framework
        JamendoPlugin plugin = new JamendoPlugin(context);
        plugin.init();
        context.registerService(IPortal.class.getName(), plugin, null);

        // now add it to the notification list if someone enters something in the browser, as we want to retrieve events 
        // regarding to portal plugins
        String[] topics = new String[]{
            CommunicationAction.SEARCHSONGFORBROWSER.toString(), CommunicationAction.GETSTREAMFROMSONG.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        //uncomment the next line to activate the plugin, disabled for testing
        context.registerService(EventHandler.class.getName(), plugin, props);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
