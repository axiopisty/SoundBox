/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.jamendoplugin;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import static org.dyndns.soundi.communicationaction.browser.Requests.*;
import static org.dyndns.soundi.communicationaction.downloader.Requests.GETSTREAMFROMSONGFORDOWNLOADER;
import static org.dyndns.soundi.communicationaction.player.Requests.GETSTREAMFROMSONGFORPLAYER;
import static org.dyndns.soundi.communicationaction.portals.Responses.*;
import org.dyndns.soundi.portals.interfaces.Author;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.dyndns.soundi.portals.interfaces.License;
import org.dyndns.soundi.portals.interfaces.PluginInformation;
import org.dyndns.soundi.utils.Util;
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
            link = new URI("http://api.jamendo.com/get2/id+name+album_name+artist_name+duration/track/json/track_album+album_artist?order=searchweight_desc&n=all&searchquery=" + searchString.replaceAll(" ", "%20"));
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
            Event reportGeneratedEvent = new Event(FOUNDSONG.toString(), l);

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

        System.out.println("(plugin): retrieved: " + event.getTopic());

        if (event.getTopic().equals(SEARCHSONGFORBROWSER.toString())) {
            searchSong(event.getProperty("songTitle").toString());
        } else if (event.getTopic().equals(SEARCHALBUMFORBROWSER.toString())) {
            searchAlbum(event.getProperty("albumTitle").toString());
        } else if (event.getTopic().equals(SEARCHARTISTFORBROWSER.toString())) {
            searchArtist(event.getProperty("artistName").toString());
        } else if (event.getTopic().equals(GETSTREAMFROMSONGFORPLAYER.toString())) {
            Object song = event.getProperty("song");
            if (song instanceof JamendoSong) {
                getStreamFromSong((JamendoSong) song, GETSTREAMFROMSONGFORPLAYER);
            }
        } else if (event.getTopic().equals(GETSTREAMFROMSONGFORDOWNLOADER.toString())) {
            Object song = event.getProperty("song");
            if (song instanceof JamendoSong) {
                getStreamFromSong((JamendoSong) song, GETSTREAMFROMSONGFORDOWNLOADER);
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

    private void getStreamFromSong(JamendoSong jamendoSong, Enum reciever) {
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
                URLConnection con = streamLink.openConnection();
                jamendoSong.setContentLength(con.getContentLength());
                properties.put("stream", new BufferedInputStream(con.getInputStream()));
                properties.put("song", jamendoSong);
            } catch (IOException ex) {
                Logger.getLogger(JamendoPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
            Event reportGeneratedEvent = null;
            if (reciever == GETSTREAMFROMSONGFORDOWNLOADER) {
                reportGeneratedEvent = new Event(STREAMFROMSONGFORDOWNLOADER.toString(), properties);
            } else if (reciever == GETSTREAMFROMSONGFORPLAYER) {
                reportGeneratedEvent = new Event(STREAMFROMSONGFORPLAYER.toString(), properties);
            }
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
    }
}
