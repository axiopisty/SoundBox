/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloadengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dyndns.soundi.gui.interfaces.IDownloaderEngine;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.portals.interfaces.Song;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 *
 * @author oli
 */
public class DefaultDownloadEngine implements IDownloaderEngine {

    private BundleContext cx;
    private HashMap<Song, InputStream> songList;

    DefaultDownloadEngine(BundleContext context) {
        this.cx = context;
        songList = new HashMap();
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getTopic().equals(CommunicationAction.DOWNLOADSONG.toString())) {
            Song s = (Song) event.getProperty("song");
            songList.put(s, null);
            download(s);
        } else if (event.getTopic().equals(CommunicationAction.STREAMFROMSONGFORDOWNLOADER.toString())) {
            songList.put((Song)event.getProperty("song"), (InputStream)event.getProperty("stream"));
            rawDownload((Song)event.getProperty("song"));
        }
    }

    @Override
    public void download(Song song) {
        ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
            Dictionary properties = new Hashtable();
            properties.put("song", song);
            Event reportGeneratedEvent = new Event(CommunicationAction.GETSTREAMFROMSONGFORDOWNLOADER.toString(), properties);
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
    }

    private void rawDownload(Song song) {
        try {
            File dir = new File(song.getArtist().getArtistName() + System.getProperty("line.seperator") + song.getAlbumName() + System.getProperty("line.seperator"));
            dir.mkdirs();
            FileOutputStream fos = new FileOutputStream(new File(dir.getAbsolutePath() + song.getSongName()+".mp3"));
            InputStream is = songList.get(song);
            byte b[] = new byte[4096];
            while(is.read(b) != -1)
                fos.write(b);
            fos.flush();
            fos.close();
            if(is.available() != 0)
                is.close();
        } catch (Exception ex) {
            Logger.getLogger(DefaultDownloadEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
