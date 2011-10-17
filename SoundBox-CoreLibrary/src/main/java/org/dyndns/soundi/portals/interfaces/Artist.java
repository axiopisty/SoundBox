package org.dyndns.soundi.portals.interfaces;

/**
 * This is the class that represents an artist.
 * 
 * @version 0.0.1
 * @author oli
 */
public class Artist {
    private String artistName;

    public Artist(String artistName) {
        this.artistName = artistName;
    }

    /** Get the artist name of this artist.
     * @return the artist name
     */
    public String getArtistName() {
        return artistName;
    }

    /** Set the artist name of this artist.
     * @param artistName the artist name to set
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
  
}
