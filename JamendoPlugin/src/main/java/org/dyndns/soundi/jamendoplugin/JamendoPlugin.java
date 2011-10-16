/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.jamendoplugin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dyndns.soundi.portals.interfaces.Author;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.dyndns.soundi.portals.interfaces.License;
import org.dyndns.soundi.portals.interfaces.PluginInformation;
import org.dyndns.soundi.portals.interfaces.Song;
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

    public JamendoPlugin(BundleContext cx) {
        this.cx = cx;
    }

    @Override
    public boolean init() {
        setInfos(new PluginInformation("Jamendo Plugin " + _version, new Author("Oliver", "Zemann"), new License("lgpl 1.0")));
        System.out.println("Initialized Jamendo Plugin " + getVersion());
        return true;
    }

    @Override
    public Object searchSong(String searchString) {
        String link = "http://api.jamendo.com/get2/id+name+album_name+artist_name/track/plain/track_album+album_artist?order=searchweight_desc&n=20&searchquery=" + searchString;
        HttpGet get = new HttpGet(link);
        DefaultHttpClient client = new DefaultHttpClient();
        Map l = new Hashtable<Object, Object>();
        ArrayList songArrayList = new ArrayList();
        JSONParser parser = new JSONParser();
        Object obj1 = null;
        Object obj = null;
        try {
            obj = parser.parse(EntityUtils.toString(client.execute(get).getEntity()));
        } catch (Exception ex) {
            Logger.getLogger(JamendoPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray songs = (JSONArray) obj;
        Iterator iter = songs.iterator();
        while (iter.hasNext()) {
            try {
                obj1 = parser.parse(iter.next().toString());
            } catch (Exception ex) {
                Logger.getLogger(JamendoPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject rawSong = (JSONObject) obj1;
            Song song = new JamendoSong(obj);
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
        JFrame frame = new JFrame("Some nice frame..");
        JLabel label = new JLabel("some text...");
        JLabel label2 = new JLabel("author: " + infos.getAuthor().getForename() + " " + infos.getAuthor().getLastname());
        JLabel label3 = new JLabel("Plugin Name: " + infos.getPluginName());
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void showConfig() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void handleEvent(Event event) {
        System.out.println("retrieved event: " + event.getTopic());
        System.out.println("contenct: " + event.toString());
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
}
