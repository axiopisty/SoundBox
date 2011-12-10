package org.dyndns.soundi.soundboxdownloadengine;

import java.util.Dictionary;
import java.util.Hashtable;
import static org.dyndns.soundi.communicationaction.downloader.Requests.DOWNLOADSONG;
import static org.dyndns.soundi.communicationaction.portals.Responses.STREAMFROMSONGFORDOWNLOADER;
import org.dyndns.soundi.gui.interfaces.IDownloaderEngine;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        IDownloaderEngine engine = new DefaultDownloadEngine(context);
        
        //register the downloader gui to all important events 
        String[] topics = new String[]{
            DOWNLOADSONG.toString(), STREAMFROMSONGFORDOWNLOADER.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        context.registerService(new String[]{EventHandler.class.getName(), IDownloaderEngine.class.getName()}, engine, props);   
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
