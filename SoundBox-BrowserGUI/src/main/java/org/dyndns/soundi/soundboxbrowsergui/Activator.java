package org.dyndns.soundi.soundboxbrowsergui;

import java.util.Dictionary;
import java.util.Hashtable;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.soundbox.core.gui.BrowserFrame;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator {

    @Override
    public void start(final BundleContext context) throws Exception {
        
        IBrowserGui gui = (IBrowserGui) new BrowserFrame(context);
        context.getBundles();
        context.registerService(IBrowserGui.class.getName(), gui, null);
        System.out.println("Standard Gui (Browser) registered.");
        
        // now add it to the notification list if something found a song, as we want to retrieve events 
        // regarding to portal plugins
        String[] topics = new String[]{
            CommunicationAction.FOUNDSONG.toString(), CommunicationAction.SETBROWSERVISIBLE.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        context.registerService(EventHandler.class.getName(), gui, props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
