package org.dyndns.soundi.soundboxdownloader;


import java.util.Dictionary;
import java.util.Hashtable;
import org.dyndns.soundi.gui.interfaces.IDownloaderGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        
        IDownloaderGui frame = new DownloadFrame(context);
        
        //register the downloader gui to all important events 
        String[] topics = new String[]{
            CommunicationAction.ADDSONGTODOWNLOADQUEUE.toString(), CommunicationAction.DOWNLOADSTATECHANGED.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        context.registerService(new String[]{EventHandler.class.getName(), IDownloaderGui.class.getName()}, frame, props);   
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
