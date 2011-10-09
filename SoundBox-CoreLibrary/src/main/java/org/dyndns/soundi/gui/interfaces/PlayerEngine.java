package org.dyndns.soundi.gui.interfaces;

import org.dyndns.soundi.portals.interfaces.Song;

/**
 * This is the interface which must be implemented by the player engine (mp3, 
 * flac, ogg and so on...).
 * 
 * @version 0.0.1
 * @author oli
 */
public interface PlayerEngine {
    /**
     * This method gets executed when a song should be played.
     * @param song The song that should be played. 
     */
    void play(Song song);
}
