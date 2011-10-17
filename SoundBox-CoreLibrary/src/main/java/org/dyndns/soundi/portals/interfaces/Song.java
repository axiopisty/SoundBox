package org.dyndns.soundi.portals.interfaces;

import java.io.Serializable;

/**
 * This is the class which contains all informations about a specific plugin.
 * 
 * @version 0.0.1
 * @author oli
 */

public abstract class Song implements Serializable {

    /**
     * The name of the song.
     */
    protected String _songName = "";
    /**
     * The ID of this song. Mostly used for the portals to unique identify a song.
     */
    protected String _id = "";
    /**
     * The artist of this song.
     */
    protected Artist _artist;
    /**
     * The name of the album of this song.
     */
    protected String _albumName = "";
    /**
     * The size of this song in byte.
     */
    protected long _contentLength;
    /**
     * The time in seconds of this song.
     */
    protected int _timeInSeconds;

    /**
     * Default constructor.
     */
    public Song() {
    }

    /**
     * 
     * @param id
     * @param songName
     * @param artist
     * @param albumName
     * @param albumID
     * @param timeInSeconds
     */
    public Song(String id, String songName, Artist artist, String albumName, String albumID, int timeInSeconds) {
        this._songName = songName;
        this._id = id;
        this._artist = artist;
        this._albumName = albumName;
        this._timeInSeconds = timeInSeconds;
    }

    /**
     * @return the _songName
     */
    public String getSongName() {
        return _songName;
    }

    /**
     * @param songName the _songName to set
     */
    public void setSongName(String songName) {
        this._songName = songName;
    }

    /**
     * @return the _id
     */
    public String getId() {
        return _id;
    }

    /**
     * @param id the _id to set
     */
    public void setId(String id) {
        this._id = id;
    }

    /**
     * @return the _artist
     */
    public Artist getArtist() {
        return _artist;
    }

    /**
     * @param artist the _artist to set
     */
    public void setArtist(Artist artist) {
        this._artist = artist;
    }

    /**
     * @return the _albumName
     */
    public String getAlbumName() {
        return _albumName;
    }

    /**
     * @param albumName the _albumName to set
     */
    public void setAlbumName(String albumName) {
        this._albumName = albumName;
    }

    /**
     * @return the _length
     */
    public int getTimeInSeconds() {
        return _timeInSeconds;
    }

    /**
     * @param timeInSeconds 
     */
    public void setLength(int timeInSeconds) {
        this._timeInSeconds = timeInSeconds;
    }

    /**
     * @return the _contentLength
     */
    public long getContentLength() {
        return _contentLength;
    }

    /**
     * @param contentLength the _contentLength to set
     */
    public void setContentLength(long contentLength) {
        this._contentLength = contentLength;
    }

    /**
     * @param timeInSeconds the _timeInSeconds to set
     */
    public void setTimeInSeconds(int timeInSeconds) {
        this._timeInSeconds = timeInSeconds;
    }

    /**
     * 
     * @return
     */
    public abstract String getAlbumID();

    
    //TODO i dont know if there could be a reference problem in the browser/player/downloader
    //     when a song goes over the EventAdmin 
    /*  
    @Override
    public int hashCode() {
    return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
    if (obj == null) {
    return false;
    }
    if (getClass() != obj.getClass()) {
    return false;
    }
    final Song other = (Song) obj;
    if ((this._songName == null) ? (other._songName != null) : !this._songName.equals(other._songName)) {
    return false;
    }
    if ((this._id == null) ? (other._id != null) : !this._id.equals(other._id)) {
    return false;
    }
    if (this._artist != other._artist && (this._artist == null || !this._artist.equals(other._artist))) {
    return false;
    }
    if ((this._albumName == null) ? (other._albumName != null) : !this._albumName.equals(other._albumName)) {
    return false;
    }
    return true;
    }
    
     */
}
