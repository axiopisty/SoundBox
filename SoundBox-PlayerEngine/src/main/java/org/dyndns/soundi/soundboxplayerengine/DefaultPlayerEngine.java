/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxplayerengine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
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
import org.dyndns.soundi.gui.interfaces.IPlayerEngine;
import org.dyndns.soundi.portals.interfaces.Song;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;

/**
 *
 * @author oli
 */
public class DefaultPlayerEngine implements IPlayerEngine {

    private BundleContext cx;

    DefaultPlayerEngine(BundleContext cx) {
        this.cx = cx;
    }

    @Override
    public void play(InputStream is, Song song) {
        try {
            AudioInputStream m_audioInputStream = null;
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
            AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, m_audioInputStream);
            rawplay(decodedFormat, din, song);
            //rawplay2(is, song);
            //Player p = new Player(is);
            //p.play();
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

    private void rawplay2(InputStream is, Song song)
    {
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
