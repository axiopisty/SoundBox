/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.portals.interfaces;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
/**
 *
 * @author oli
 */
public abstract class IPortal implements EventHandler {
 
    private PluginInformation infos;
    private State state = State.DEACTIVATED;
    
    public abstract boolean init();
    public abstract Object searchSong(String searchString);
    public abstract void showPluginInformation();
    public abstract void showConfig();

    @Override
    public abstract void handleEvent(Event event);
    

    /**
     * @return the infos
     */
    public PluginInformation getInfos() {
        return infos;
    }

    /**
     * @param infos the infos to set
     */
    public void setInfos(PluginInformation infos) {
        this.infos = infos;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }
    
}
