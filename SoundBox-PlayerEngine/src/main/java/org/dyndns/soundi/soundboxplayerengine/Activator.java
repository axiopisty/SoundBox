package org.dyndns.soundi.soundboxplayerengine;

import java.util.Dictionary;
import java.util.Hashtable;
import org.dyndns.soundi.gui.interfaces.IPlayerEngine;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.utils.Util;
import org.dyndns.soundi.utils.Util.Component;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        IPlayerEngine engine = new DefaultPlayerEngine(context);
            
        String[] topics = new String[]{
            CommunicationAction.PLAYSONGFROMBROWSER.toString(), CommunicationAction.PLAYSONGFROMPLAYER.toString(), 
            CommunicationAction.STOPPLAYBACKFROMPLAYER.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        context.registerService(new String[]{EventHandler.class.getName(), IPlayerEngine.class.getName()}, engine, props);
        Util.sendMessage(Component.PLAYERENGINE, "Default player engine registered!");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
