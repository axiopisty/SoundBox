/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.groovesharkplugin;

import java.io.Serializable;
import org.dyndns.soundi.portals.interfaces.Artist;
/**
 * 
 * @author oli
 */
public class GroovesharkArtist extends Artist implements Serializable {
  private int _rank;

  private int _albumClicks;

  private String _avgRating;

  private String _estimatedDuration;

  private String _albumVerified;

  private int _songPlays;

  private String _dsName;

  private String _year;

  private String _songVerified;

  private String _artistName;

  private String _albumID;

  private String _trackNum;

  private String _danName;

  private String _artistID;

  private double _score;

  private String _isLowBitrateAvailable;

  private String _popularity;

  private String _songName;

  private String _dalName;

  private int _artistClicks;

  private int _songClicks;

  private String _avgDuration;

  private String _coverArtFilename;

  private int _queryAlbumClicks;

  private String _artistVerified;

  private int _quertyArtistClicks;

  private String _isVerified;

  private int _querySongClicks;

  private int _sphinxWeight;

  private String _albumName;

  private String _flags;

  private int _tsAddedInt;

  private int _artistPlays;

  private String _tsAdded;

  public GroovesharkArtist(String id, String name) {
        this._artistName = name;
        this._artistID = id;
  }

  /**
   * @return the _rank
   */
  public int getRank() {
        return _rank;
  }

  /**
   * @param rank the _rank to set
   */
  public void setRank(int rank) {
        this._rank = rank;
  }

  /**
   * @return the _albumClicks
   */
  public int getAlbumClicks() {
        return _albumClicks;
  }

  /**
   * @param albumClicks the _albumClicks to set
   */
  public void setAlbumClicks(int albumClicks) {
        this._albumClicks = albumClicks;
  }

  /**
   * @return the _avgRating
   */
  public String getAvgRating() {
        return _avgRating;
  }

  /**
   * @param avgRating the _avgRating to set
   */
  public void setAvgRating(String avgRating) {
        this._avgRating = avgRating;
  }

  /**
   * @return the _estimatedDuration
   */
  public String getEstimatedDuration() {
        return _estimatedDuration;
  }

  /**
   * @param estimatedDuration the _estimatedDuration to set
   */
  public void setEstimatedDuration(String estimatedDuration) {
        this._estimatedDuration = estimatedDuration;
  }

  /**
   * @return the _albumVerified
   */
  public String getAlbumVerified() {
        return _albumVerified;
  }

  /**
   * @param albumVerified the _albumVerified to set
   */
  public void setAlbumVerified(String albumVerified) {
        this._albumVerified = albumVerified;
  }

  /**
   * @return the _songPlays
   */
  public int getSongPlays() {
        return _songPlays;
  }

  /**
   * @param songPlays the _songPlays to set
   */
  public void setSongPlays(int songPlays) {
        this._songPlays = songPlays;
  }

  /**
   * @return the _dsName
   */
  public String getDsName() {
        return _dsName;
  }

  /**
   * @param dsName the _dsName to set
   */
  public void setDsName(String dsName) {
        this._dsName = dsName;
  }

  /**
   * @return the _year
   */
  public String getYear() {
        return _year;
  }

  /**
   * @param year the _year to set
   */
  public void setYear(String year) {
        this._year = year;
  }

  /**
   * @return the _songVerified
   */
  public String getSongVerified() {
        return _songVerified;
  }

  /**
   * @param songVerified the _songVerified to set
   */
  public void setSongVerified(String songVerified) {
        this._songVerified = songVerified;
  }

  /**
   * @return the _artistName
   */
  public String getArtistName() {
        return _artistName;
  }

  /**
   * @param artistName the _artistName to set
   */
  public void setArtistName(String artistName) {
        this._artistName = artistName;
  }

  /**
   * @return the _albumID
   */
  public String getAlbumID() {
        return _albumID;
  }

  /**
   * @param albumID the _albumID to set
   */
  public void setAlbumID(String albumID) {
        this._albumID = albumID;
  }

  /**
   * @return the _trackNum
   */
  public String getTrackNum() {
        return _trackNum;
  }

  /**
   * @param trackNum the _trackNum to set
   */
  public void setTrackNum(String trackNum) {
        this._trackNum = trackNum;
  }

  /**
   * @return the _danName
   */
  public String getDanName() {
        return _danName;
  }

  /**
   * @param danName the _danName to set
   */
  public void setDanName(String danName) {
        this._danName = danName;
  }

  /**
   * @return the _artistID
   */
  public String getArtistID() {
        return _artistID;
  }

  /**
   * @param artistID the _artistID to set
   */
  public void setArtistID(String artistID) {
        this._artistID = artistID;
  }

  /**
   * @return the _score
   */
  public double getScore() {
        return _score;
  }

  /**
   * @param score the _score to set
   */
  public void setScore(double score) {
        this._score = score;
  }

  /**
   * @return the _isLowBitrateAvailable
   */
  public String getIsLowBitrateAvailable() {
        return _isLowBitrateAvailable;
  }

  /**
   * @param isLowBitrateAvailable the _isLowBitrateAvailable to set
   */
  public void setIsLowBitrateAvailable(String isLowBitrateAvailable) {
        this._isLowBitrateAvailable = isLowBitrateAvailable;
  }

  /**
   * @return the _popularity
   */
  public String getPopularity() {
        return _popularity;
  }

  /**
   * @param popularity the _popularity to set
   */
  public void setPopularity(String popularity) {
        this._popularity = popularity;
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
   * @return the _dalName
   */
  public String getDalName() {
        return _dalName;
  }

  /**
   * @param dalName the _dalName to set
   */
  public void setDalName(String dalName) {
        this._dalName = dalName;
  }

  /**
   * @return the _artistClicks
   */
  public int getArtistClicks() {
        return _artistClicks;
  }

  /**
   * @param artistClicks the _artistClicks to set
   */
  public void setArtistClicks(int artistClicks) {
        this._artistClicks = artistClicks;
  }

  /**
   * @return the _songClicks
   */
  public int getSongClicks() {
        return _songClicks;
  }

  /**
   * @param songClicks the _songClicks to set
   */
  public void setSongClicks(int songClicks) {
        this._songClicks = songClicks;
  }

  /**
   * @return the _avgDuration
   */
  public String getAvgDuration() {
        return _avgDuration;
  }

  /**
   * @param avgDuration the _avgDuration to set
   */
  public void setAvgDuration(String avgDuration) {
        this._avgDuration = avgDuration;
  }

  /**
   * @return the _coverArtFilename
   */
  public String getCoverArtFilename() {
        return _coverArtFilename;
  }

  /**
   * @param coverArtFilename the _coverArtFilename to set
   */
  public void setCoverArtFilename(String coverArtFilename) {
        this._coverArtFilename = coverArtFilename;
  }

  @Override
  public String toString() {
        throw new UnsupportedOperationException("dont toString an artist!");
  }

  /**
   * @return the _queryAlbumClicks
   */
  public int getQueryAlbumClicks() {
        return _queryAlbumClicks;
  }

  /**
   * @param queryAlbumClicks the _queryAlbumClicks to set
   */
  public void setQueryAlbumClicks(int queryAlbumClicks) {
        this._queryAlbumClicks = queryAlbumClicks;
  }

  /**
   * @return the _artistVerified
   */
  public String getArtistVerified() {
        return _artistVerified;
  }

  /**
   * @param artistVerified the _artistVerified to set
   */
  public void setArtistVerified(String artistVerified) {
        this._artistVerified = artistVerified;
  }

  /**
   * @return the _quertyArtistClicks
   */
  public int getQuertyArtistClicks() {
        return _quertyArtistClicks;
  }

  /**
   * @param quertyArtistClicks the _quertyArtistClicks to set
   */
  public void setQuertyArtistClicks(int quertyArtistClicks) {
        this._quertyArtistClicks = quertyArtistClicks;
  }

  /**
   * @return the _isVerified
   */
  public String getIsVerified() {
        return _isVerified;
  }

  /**
   * @param isVerified the _isVerified to set
   */
  public void setIsVerified(String isVerified) {
        this._isVerified = isVerified;
  }

  /**
   * @return the _querySongClicks
   */
  public int getQuerySongClicks() {
        return _querySongClicks;
  }

  /**
   * @param querySongClicks the _querySongClicks to set
   */
  public void setQuerySongClicks(int querySongClicks) {
        this._querySongClicks = querySongClicks;
  }

  /**
   * @return the _sphinxWeight
   */
  public int getSphinxWeight() {
        return _sphinxWeight;
  }

  /**
   * @param sphinxWeight the _sphinxWeight to set
   */
  public void setSphinxWeight(int sphinxWeight) {
        this._sphinxWeight = sphinxWeight;
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
   * @return the _flags
   */
  public String getFlags() {
        return _flags;
  }

  /**
   * @param flags the _flags to set
   */
  public void setFlags(String flags) {
        this._flags = flags;
  }

  /**
   * @return the _tsAddedInt
   */
  public int getTsAddedInt() {
        return _tsAddedInt;
  }

  /**
   * @param tsAddedInt the _tsAddedInt to set
   */
  public void setTsAddedInt(int tsAddedInt) {
        this._tsAddedInt = tsAddedInt;
  }

  /**
   * @return the _artistPlays
   */
  public int getArtistPlays() {
        return _artistPlays;
  }

  /**
   * @param artistPlays the _artistPlays to set
   */
  public void setArtistPlays(int artistPlays) {
        this._artistPlays = artistPlays;
  }

  /**
   * @return the _tsAdded
   */
  public String getTsAdded() {
        return _tsAdded;
  }

  /**
   * @param tsAdded the _tsAdded to set
   */
  public void setTsAdded(String tsAdded) {
        this._tsAdded = tsAdded;
  }

}
