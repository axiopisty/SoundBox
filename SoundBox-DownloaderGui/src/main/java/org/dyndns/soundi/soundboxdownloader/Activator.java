package org.dyndns.soundi.soundboxdownloader;

import java.beans.EventHandler;
import java.util.Dictionary;
import java.util.Hashtable;
import org.dyndns.soundi.gui.interfaces.IDownloaderGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        
        IDownloaderGui frame = new DownloadFrame(context);
        
        //register the downloader gui to all important events 
        String[] topics = new String[]{
            CommunicationAction.ADDSONGTODOWNLOADQUEUE.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        context.registerService(EventHandler.class.getName(), frame, props);
        //last but not least register it as service so we know that its given
        //as osgi bundle
        context.registerService(IDownloaderGui.class.getName(), frame, null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
