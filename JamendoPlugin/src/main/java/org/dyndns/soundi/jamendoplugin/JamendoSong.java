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

    public JamendoSong(JSONObject obj) {
        this._id = obj.get("id").toString();
        String artistName = obj.get("artist_name").toString();
        this._artist = new Artist(artistName);
        String songName = obj.get("name").toString();
        this._songName = songName;
        this._albumName = obj.get("album_name").toString();
        this._timeInSeconds = Integer.parseInt(obj.get("duration").toString());
    }
    public JamendoSong(String id, Artist artist, String songName, String albumName, String timeInSeconds)
    {
        this._id = id;
        this._artist = artist;
        this._songName = songName;
        this._albumName = albumName;
        this._timeInSeconds = Integer.parseInt(timeInSeconds);
    }
}
