/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxplayerengine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import org.dyndns.soundi.gui.interfaces.IPlayerEngine;
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
public class DefaultPlayerEngine implements IPlayerEngine {

    private BundleContext cx;
    private boolean stopped = false;
    private final Lock lock = new ReentrantLock();

    DefaultPlayerEngine(BundleContext cx) {
        this.cx = cx;
    }

    @Override
    public void play(final InputStream is, final Song song) {
        try {
            /*AudioInputStream m_audioInputStream = null;
            try {
            m_audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(DefaultPlayerEngine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(DefaultPlayerEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            AudioFormat baseFormat = m_audioInputStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
            baseFormat.getSampleRate(),
            16,
            baseFormat.getChannels(),
            baseFormat.getChannels() * 2,
            baseFormat.getSampleRate(),
            false);
            AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, m_audioInputStream);*/
            //rawplay(decodedFormat, din, song);
            //rawplay2(is, song);
            //final AdvancedPlayer p = new AdvancedPlayer(is);
            final ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

            new Thread() {

                @Override
                public void run() {
                    try {
                        Player p = new Player(is) {

                            @Override
                            public void play() throws JavaLayerException {
                                try {
                                    if (lock.tryLock(1, TimeUnit.MILLISECONDS)) {
                                        new Thread() {

                                            @Override
                                            public void run() {
                                                while (!isComplete()) {
                                                    EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
                                                    Dictionary properties = new Hashtable();
                                                    properties.put("song", song);
                                                    properties.put("position", getPosition());
                                                    Event reportGeneratedEvent = new Event(CommunicationAction.PLAYBACKSTATECHANGED.toString(), properties);
                                                    eventAdmin.sendEvent(reportGeneratedEvent);
                                                    try {
                                                        Thread.currentThread().sleep(1000);
                                                    } catch (InterruptedException ex) {
                                                        Logger.getLogger(DefaultPlayerEngine.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
                                            }
                                        }.start();
                                    }
                                } catch (Exception ex) {
                                } finally {
                                    super.play();
                                    lock.unlock();
                                }
                            }
                        };
                        /*p.setPlayBackListener(new PlaybackListener() {
                        
                        @Override
                        public void playbackFinished(PlaybackEvent pe) {
                        super.playbackFinished(pe);
                        stopped = true;
                        }
                        
                        @Override
                        public void playbackStarted(PlaybackEvent pe) {
                        super.playbackStarted(pe);
                        final ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());
                        
                        if (ref != null) {
                        stopped = false;
                        while (!stopped) {
                        try {
                        Thread.currentThread().sleep(1000);
                        } catch (InterruptedException ex) {
                        Logger.getLogger(DefaultPlayerEngine.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        new Thread() {
                        
                        @Override
                        public void run() {
                        EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
                        Dictionary properties = new Hashtable();
                        properties.put("song", song);
                        // properties.put("seconds", p.)
                        Event reportGeneratedEvent = new Event(CommunicationAction.PLAYBACKSTATECHANGED.toString(), properties);
                        eventAdmin.sendEvent(reportGeneratedEvent);
                        }
                        }.start();
                        try {
                        Thread.currentThread().sleep(1000);
                        } catch (InterruptedException ex) {
                        Logger.getLogger(DefaultPlayerEngine.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                        }
                        }
                        });*/

                        p.play();
                    } catch (Exception ex) {
                    }
                }
            }.start();



        } catch (Exception ex) {
            Logger.getLogger(DefaultPlayerEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void stop() {
    }

    //TODO: use the eventadmin and not the play() method inside playergui
    @Override
    public void handleEvent(Event event) {
    }

    private void rawplay2(InputStream is, Song song) {
        Player p = null;
        try {
            p = new Player(is);
        } catch (JavaLayerException ex) {
            Logger.getLogger(DefaultPlayerEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            p.play();
        } catch (JavaLayerException ex) {
            Logger.getLogger(DefaultPlayerEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void rawplay(AudioFormat decodedFormat, AudioInputStream din, Song song) {
        byte[] data = new byte[4096];

        try {
            SourceDataLine line = getLine(decodedFormat);

            if (line != null) {
                // Start
                line.start();
                int nBytesRead = 0;

                while (nBytesRead != -1) {

                    Map properties = ((javazoom.spi.PropertiesContainer) din).properties();

                    String key = "mp3.position.microseconds";
                    long val = (Long) properties.get(key) / 1000000;
                    long val2 = (Long) properties.get("mp3.position.byte");


                    nBytesRead = din.read(data, 0, data.length);

                    if (nBytesRead != -1) {
                        line.write(data, 0, nBytesRead);
                    }
                }

                // Stop
                line.drain();
                line.stop();
                line.close();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        if (res != null) {
            res.open(audioFormat);
        }
        return res;
    }
}
