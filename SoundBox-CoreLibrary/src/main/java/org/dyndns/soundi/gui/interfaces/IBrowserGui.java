/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.gui.interfaces;

import org.osgi.service.event.EventHandler;

/**
 *
 * @author oli
 */
public interface IBrowserGui extends EventHandler {
    
    void changeConfiguration();
    void searchArtist(String artist);
    void searchSong(String song);
    void searchAlbum(String album);
    void init();
    void display();
    
}
