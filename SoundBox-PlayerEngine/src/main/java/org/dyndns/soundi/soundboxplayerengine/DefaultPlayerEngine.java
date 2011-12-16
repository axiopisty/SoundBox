/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxplayerengine;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import javax.sound.sampled.*;
import static org.dyndns.soundi.communicationaction.player.Requests.STOPPLAYBACKFROMPLAYER;
import static org.dyndns.soundi.communicationaction.playerengine.Responses.PLAYBACKSTATECHANGED;
import org.dyndns.soundi.gui.interfaces.IPlayerEngine;
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
    private boolean stop = false;

    DefaultPlayerEngine(BundleContext cx) {
        this.cx = cx;
    }

    @Override
    public void play(final InputStream is, final Song song) {
        try {
            AudioInputStream m_audioInputStream = null;
            try {
                m_audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is)); //-->> EXCEPTION, line 45
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            AudioFormat baseFormat = m_audioInputStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
            AudioInputStream din =
                    AudioSystem.getAudioInputStream(decodedFormat, m_audioInputStream);
            rawplay(decodedFormat, din, song);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void stop() {
        stop = true;
    }

    //TODO: use the eventadmin and not the play() method inside playergui
    @Override
    public void handleEvent(Event event) {
        if (event.getTopic().equals(STOPPLAYBACKFROMPLAYER.toString())) {
            this.stop();
        }
    }

    private void rawplay(AudioFormat decodedFormat, AudioInputStream din, Song song) {
        byte[] data = new byte[4096];
//can be removed if it is correctly osgi'd as we probably make a singleton of this class
        stop = false;
        try {
            SourceDataLine line = getLine(decodedFormat);

            if (line != null) {
                // Start
                line.start();
                int nBytesRead = 0;

                while (nBytesRead != -1 && !stop) {

                    Map properties = ((javazoom.spi.PropertiesContainer) din).properties();

                    String key = "mp3.position.microseconds";
                    long val = (Long) properties.get(key) / 1000000;
                    long val2 = (Long) properties.get("mp3.position.byte");


                    nBytesRead = din.read(data, 0, data.length);

                    if (nBytesRead != -1) {
                        line.write(data, 0, nBytesRead);
                    }

                    ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());
                    if (ref != null) {
                        EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
                        Dictionary props = new Hashtable();
                        props.put("seconds", val);
                        props.put("bytePosition", val2);
                        props.put("song", song);
                        Event reportGeneratedEvent = new Event(PLAYBACKSTATECHANGED.toString(), props);
                        eventAdmin.sendEvent(reportGeneratedEvent);
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

    private static SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        if (res != null) {
            res.open(audioFormat);
        }
        return res;
    }
}
