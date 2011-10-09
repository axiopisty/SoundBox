package org.dyndns.soundi.gui.interfaces;

import org.osgi.service.event.EventHandler;

/**
 * This is the interface which must be implemented by the UI for SoundBox.
 * 
 * @version 0.0.1
 * @author oli
 */
public interface IBrowserGui extends EventHandler {
    
    /**
     * This method will get executed when the user tries to change the 
     * configuration (eg. "configuration" clicked).
     */
    void changeConfiguration();
    /**
     * This method gets executed when the user tries to search for an artist.
     * In the default gui, this happens when the combobox displays "Artist" and
     * the search button gets clicked.
     * @param artist The artist name to search for.
     */
    void searchArtist(String artist);
    /**
     * This method gets executed when the user tries to search for a song.
     * In the default gui, this happens when the combobox displays "Song" and
     * the search button gets clicked.
     * @param song The song to search for.
     */
    void searchSong(String song);
    /**
     * This method gets executed when the user tries to search for an album.
     * In the default gui, this happens when the combobox displays "Album" and
     * the search button gets clicked.
     * @param album The album name to search for.
     */
    void searchAlbum(String album);
    /**
     * This method gets executed when the UI is created.
     */
    void init();
    /**
     * This method gets executed when the soundbox is up and running, so that 
     * the user can communicate with the gui (in swing this would be setVisible(true))
     */
    void display();
    
}
