package org.dyndns.soundi.portals.interfaces;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * This is the interface which must be implemented by all portals.
 * 
 * @version 0.0.1
 * @author oli
 */
public abstract class IPortal implements EventHandler {
 
    private State state = State.DEACTIVATED;
    
    /**
     * This method initializes the portal (eg. get a session id from the portals
     * website etc.)
     * @return true if everything is ok, false is something went wrong.
     */
    public abstract boolean init();
    /**
     * This method is send to all portals when the Browser UI gets the user input
     * (action) to search for a song.
     * @param searchString The keywords to search for.
     * @return true if at least one song is found, false if nothing is found or
     * something went wrong.
     */
    public abstract Object searchSong(String searchString);
    /**
     * This method gets executed when the user hits the "about" dialog in the 
     * specific portal.
     */
    public abstract void showPluginInformation();
    /**
     * This method gets executed when the user hits the "configuration" dialog in the 
     * specific portal.
     */
    public abstract void showConfig();

    /**
     * This method retrieves the events. You must register the portal to the 
     * EventAdmin listener class.
     * @param event The event.
     */
    @Override
    public abstract void handleEvent(Event event);
    

    /**
     * This method returns the PluginInformation class, which represents all
     * informations regarding this plugin (eg. License, Author, ...)
     * @return the infos of this plugin.
     */
    public abstract PluginInformation getInfos();

    /**
     * This method sets the infos for this plugin.
     * @param infos the infos to set
     */
    public abstract void setInfos(PluginInformation infos);

    /**
     * This method returns the current state of this plugin.
     * Either "Activated" or "Deactivated".
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * This method sets the current state of the plugin.
     * Either "Activated" or "Deactivated".
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }
    
}
