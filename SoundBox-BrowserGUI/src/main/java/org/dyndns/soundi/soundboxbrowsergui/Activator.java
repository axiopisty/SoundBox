package org.dyndns.soundi.soundboxbrowsergui;

import java.util.Dictionary;
import java.util.Hashtable;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.soundbox.core.gui.BrowserFrame;
import org.dyndns.soundi.utils.Util;
import org.dyndns.soundi.utils.Util.Component;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator {

    @Override
    public void start(final BundleContext context) throws Exception {
        
        IBrowserGui gui = (IBrowserGui) new BrowserFrame(context);
        
        // now add it to the notification list if something found a song, as we want to retrieve events 
        // regarding to portal plugins
        String[] topics = new String[]{
            CommunicationAction.FOUNDSONG.toString(), CommunicationAction.SETBROWSERVISIBLE.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        //last but not least register the class in the osgi environment
        context.registerService(IBrowserGui.class.getName(), gui, props);
        Util.sendMessage(Component.BROWSER, "Standard Gui (Browser) registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
