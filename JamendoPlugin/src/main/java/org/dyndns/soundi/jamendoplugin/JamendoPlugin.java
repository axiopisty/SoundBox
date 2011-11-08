/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.jamendoplugin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dyndns.soundi.portals.interfaces.*;
import org.dyndns.soundi.utils.Util;
import org.dyndns.soundi.utils.Util.Component;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 *
 * @author oli
 */
public class JamendoPlugin extends IPortal {

    private String _version = "0.0.0";
    private BundleContext cx;
    PluginInformation infos;
    private Date lastTimeCommandSend;

    public JamendoPlugin(BundleContext cx) {
        this.cx = cx;
    }

    @Override
    public boolean init() {
        setInfos(new PluginInformation("Jamendo Plugin " + _version, new Author("Oliver", "Zemann"), new License("LGPL 1.0")));
        Util.sendMessage(this.getClass(), "Initialized Jamendo Plugin");
        return true;
    }

    @Override
    public Object searchSong(String searchString) {

        URI link = null;
        try {
            link = new URI("http://api.jamendo.com/get2/id+name+album_name+artist_name+duration/track/json/track_album+album_artist?order=searchweight_desc&n=all&searchquery=" + searchString);
        } catch (URISyntaxException ex) {
            Logger.getLogger(JamendoPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpGet get = new HttpGet(link);
        HttpClient client = new DefaultHttpClient();
        Dictionary l = new Hashtable<Object, Object>();
        ArrayList songArrayList = new ArrayList();
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            HttpEntity entity = client.execute(get).getEntity();
            String entityAsString = EntityUtils.toString(entity);
            entity.getContent().close();
            if (entityAsString.isEmpty()) // something went wrong...
            {
                return null;
            }
            obj = parser.parse(entityAsString);

        } catch (Exception ex) {
            Logger.getLogger(JamendoPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray songs = (JSONArray) obj;
        Iterator iter = songs.iterator();
        JSONObject rawSong = null;
        while (iter.hasNext()) {
            try {
                rawSong = (JSONObject) parser.parse(iter.next().toString());
            } catch (Exception ex) {
                Logger.getLogger(JamendoPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }

            JamendoSong song = new JamendoSong(rawSong);
            songArrayList.add(song);
        }

        l.put("songList", songArrayList);
        l.put("portalName", "Jamendo");
        ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());
        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
            Event reportGeneratedEvent = new Event(CommunicationAction.FOUNDSONG.toString(), l);

            eventAdmin.sendEvent(reportGeneratedEvent);
        }
        return null;
    }

    @Override
    public void showPluginInformation() {
        new PluginInformationFrame(this).setVisible(true);
    }

    @Override
    public void showConfig() {
        ConfigurationFrame frame = new ConfigurationFrame();
        frame.setVisible(true);
    }

    @Override
    public void handleEvent(Event event) {

        if (event.getTopic().equals(CommunicationAction.SEARCHSONGFORBROWSER.toString())) {
            searchSong(event.getProperty("songTitle").toString());
        } else if (event.getTopic().equals(CommunicationAction.SEARCHALBUMFORBROWSER.toString())) {
            searchAlbum(event.getProperty("albumTitle").toString());
        } else if (event.getTopic().equals(CommunicationAction.SEARCHARTISTFORBROWSER.toString())) {
            searchArtist(event.getProperty("artistName").toString());
        } else if (event.getTopic().equals(CommunicationAction.GETSTREAMFROMSONG.toString())) {
            Object song = event.getProperty("song");
            if (song instanceof JamendoSong) {
                getStreamFromSong((JamendoSong) song);
            }
        }
    }

    private String getVersion() {
        return _version;
    }

    @Override
    public PluginInformation getInfos() {
        return infos;
    }

    @Override
    public void setInfos(PluginInformation infos) {
        this.infos = infos;
    }

    private void searchAlbum(String toString) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void searchArtist(String toString) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void getStreamFromSong(JamendoSong jamendoSong) {
        URL streamLink = null;
        try {
            streamLink = new URL("http://api.jamendo.com/get2/stream/track/redirect/?id=" + jamendoSong.getId() + "&streamencoding=mp31");
        } catch (MalformedURLException ex) {
            Logger.getLogger(JamendoPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
            Dictionary properties = new Hashtable();
            try {
                properties.put("stream", streamLink.openStream());
            } catch (IOException ex) {
                Logger.getLogger(JamendoPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
            Event reportGeneratedEvent = new Event(CommunicationAction.STREAMFROMSONG.toString(), properties);
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
    }
}
