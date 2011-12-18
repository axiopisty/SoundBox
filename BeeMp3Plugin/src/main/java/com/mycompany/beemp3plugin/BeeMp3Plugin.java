/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beemp3plugin;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
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
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 *
 * @author oli
 */
public class BeeMp3Plugin extends IPortal {

    private BundleContext cx;

    BeeMp3Plugin(BundleContext context) {
        this.cx = context;
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public Object searchSong(String searchString) {
        String url = "http://beemp3.com/index.php?q=" + searchString + "&fl=tc128";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        HttpResponse resp = null;
        try {
            resp = client.execute(get);
        } catch (Exception ex) {
            Logger.getLogger(BeeMp3Plugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String toString = EntityUtils.toString(resp.getEntity());
            System.out.println(toString);
        } catch (Exception ex) {
            Logger.getLogger(BeeMp3Plugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dictionary l = new Hashtable<Object, Object>();
        l.put("songList", null);
        l.put("portalName", "BeeMP3");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showConfig() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getTopic().equals(SEARCHSONGFORBROWSER.toString())) {
            searchSong(event.getProperty("songTitle").toString());
        } else if (event.getTopic().equals(SEARCHALBUMFORBROWSER.toString())) {
            searchAlbum(event.getProperty("albumTitle").toString());
        } else if (event.getTopic().equals(SEARCHARTISTFORBROWSER.toString())) {
            searchArtist(event.getProperty("artistName").toString());
        } else if (event.getTopic().equals(GETSTREAMFROMSONGFORPLAYER.toString())) {
            Object song = event.getProperty("song");
            //if (song instanceof BeeMP3Song) {
            //    getStreamFromSong((JamendoSong) song, GETSTREAMFROMSONGFORPLAYER);
            //}
        } else if (event.getTopic().equals(GETSTREAMFROMSONGFORDOWNLOADER.toString())) {
            Object song = event.getProperty("song");
            //if (song instanceof JamendoSong) {
            //    getStreamFromSong((JamendoSong) song, GETSTREAMFROMSONGFORDOWNLOADER);
            //}
        }
    }

    @Override
    public PluginInformation getInfos() {
        return new PluginInformation("BeeMP3", new Author("Oliver", "Zemann"), new License("MIT"));
    }

    @Override
    public void setInfos(PluginInformation infos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void searchAlbum(String toString) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void searchArtist(String toString) {
        
    }
}
