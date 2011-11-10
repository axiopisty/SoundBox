/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloadengine;

import java.io.File;
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
            songList.put((Song) event.getProperty("song"), (InputStream) event.getProperty("stream"));
            rawDownload((Song) event.getProperty("song"));
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
            File dir = new File(song.getArtist().getArtistName() + System.getProperty("file.separator") + song.getAlbumName() + System.getProperty("file.separator"));
            dir.mkdirs();
            FileOutputStream fos = new FileOutputStream(new File(dir.getAbsolutePath() + System.getProperty("file.separator") + song.getSongName() + ".mp3"));
            InputStream is = songList.get(song);
            byte b[] = new byte[8096];
            ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());
            EventAdmin eventAdmin = null;
            Dictionary properties = new Hashtable();
            properties.put("song", song);
            if (ref != null) {
                eventAdmin = (EventAdmin) cx.getService(ref);
            }
            long bytesRead = 0;
            int tmp = 0;
            while ((tmp = is.read(b)) != -1) {
                fos.write(b, 0, tmp);
                bytesRead += tmp;      
                properties.put("writtenBytes", bytesRead);
                Event reportGeneratedEvent = new Event(CommunicationAction.DOWNLOADSTATECHANGED.toString(), properties);
                eventAdmin.sendEvent(reportGeneratedEvent);
            }
            //Event reportGeneratedEvent = new Event(CommunicationAction.DOWNLOADSONGFINISHED.toString(), properties);
            //eventAdmin.sendEvent(reportGeneratedEvent);
            fos.flush();
            fos.close();
            if (is.available() != 0) {
                is.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(DefaultDownloadEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
