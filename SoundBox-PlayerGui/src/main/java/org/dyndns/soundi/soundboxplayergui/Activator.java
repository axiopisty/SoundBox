package org.dyndns.soundi.soundboxplayergui;

//~--- non-JDK imports --------------------------------------------------------
import java.util.Dictionary;
import java.util.Hashtable;
import static org.dyndns.soundi.communicationaction.browser.Requests.ADDSONGSTOPLAYERQUEUE;
import static org.dyndns.soundi.communicationaction.browser.Requests.STARTPLAYERFROMSONG;
import static org.dyndns.soundi.communicationaction.core.Requests.SETPLAYERINVISIBLE;
import static org.dyndns.soundi.communicationaction.core.Requests.SETPLAYERVISIBLE;
import static org.dyndns.soundi.communicationaction.playerengine.Responses.PLAYBACKSTATECHANGED;
import static org.dyndns.soundi.communicationaction.portals.Responses.STREAMFROMSONGFORPLAYER;
import org.dyndns.soundi.gui.interfaces.IPlayerGui;
import org.dyndns.soundi.soundbox.core.gui.PlayerFrame;
import org.dyndns.soundi.utils.Util;
import org.dyndns.soundi.utils.Util.Component;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;


public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {

        IPlayerGui gui = (IPlayerGui) new PlayerFrame(context);

        Util.sendMessage(Component.PLAYER, "Standard Gui (Player) registered.");
        // now add it to the notification list if something found a song, as we want to retrieve events 
        // regarding to portal plugins
        String[] topics = new String[]{
            STARTPLAYERFROMSONG.toString(), ADDSONGSTOPLAYERQUEUE.toString(),
            STREAMFROMSONGFORPLAYER.toString(), SETPLAYERINVISIBLE.toString(), SETPLAYERVISIBLE.toString(),
            PLAYBACKSTATECHANGED.toString()
        };

        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        context.registerService(new String[]{EventHandler.class.getName(), IPlayerGui.class.getName()}, gui, props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
