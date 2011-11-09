package org.dyndns.soundi.gui.interfaces;

import java.io.InputStream;
import org.osgi.service.event.EventHandler;

/**
 * This is the interface which must be implemented by the player engine (mp3, 
 * flac, ogg and so on...).
 * 
 * @version 0.0.1
 * @author oli
 */
public interface IPlayerEngine extends EventHandler {
    /**
     * This method gets executed when a song should be played.
     * @param song The song that should be played. 
     */
    void play(InputStream is);
    
    void pause();
    
    void stop();
    
}
