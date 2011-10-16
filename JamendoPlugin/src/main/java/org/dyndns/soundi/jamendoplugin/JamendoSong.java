/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.jamendoplugin;

import org.dyndns.soundi.portals.interfaces.Song;

/**
 *
 * @author oli
 */
public class JamendoSong extends Song {

    JamendoSong(Object obj) {
        System.out.println("debug");
    }

    @Override
    public String getAlbumID() {
        return "";
    }
    
}
