/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.jamendoplugin;

import org.dyndns.soundi.portals.interfaces.Artist;
import org.dyndns.soundi.portals.interfaces.Song;
import org.json.simple.JSONObject;

/**
 *
 * @author oli
 */
public class JamendoSong extends Song {

    JamendoSong(JSONObject obj) {
        this._id = obj.get("id").toString();
        String artistName = obj.get("artist_name").toString();
        this._artist = new Artist(artistName);
        String songName = obj.get("name").toString();
        this._songName = songName;
        this._albumName = obj.get("album_name").toString();
    }

    @Override
    public String getAlbumID() {
        return "";
    }
    
}
