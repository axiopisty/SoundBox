package com.mycompany.beemp3plugin;

import java.util.Dictionary;
import java.util.Hashtable;
import static org.dyndns.soundi.communicationaction.browser.Requests.SEARCHSONGFORBROWSER;
import static org.dyndns.soundi.communicationaction.downloader.Requests.GETSTREAMFROMSONGFORDOWNLOADER;
import static org.dyndns.soundi.communicationaction.player.Requests.GETSTREAMFROMSONGFORPLAYER;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
        
        
public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        // register the plugin to the osgi framework
        BeeMp3Plugin plugin = new BeeMp3Plugin(context);
        plugin.init();

        // now add it to the notification list if someone enters something in the browser, as we want to retrieve events 
        // regarding to portal plugins
        String[] topics = new String[]{
          
            SEARCHSONGFORBROWSER.toString(), GETSTREAMFROMSONGFORPLAYER.toString(), GETSTREAMFROMSONGFORDOWNLOADER.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        //uncomment the next line to activate the plugin, disabled for testing
        context.registerService(new String[]{EventHandler.class.getName(), IPortal.class.getName()}, plugin, props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
