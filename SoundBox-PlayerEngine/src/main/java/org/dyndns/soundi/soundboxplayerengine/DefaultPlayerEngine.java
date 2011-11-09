/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxplayerengine;

import java.io.InputStream;
import org.dyndns.soundi.gui.interfaces.IPlayerEngine;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;

/**
 *
 * @author oli
 */
public class DefaultPlayerEngine implements IPlayerEngine{

    private BundleContext cx;
    
    DefaultPlayerEngine(BundleContext cx)
    {
        this.cx = cx;
    }
    
    @Override
    public void play(InputStream is) {
        
    }

    @Override
    public void pause() {
       
    }

    @Override
    public void stop() {
       
    }

    @Override
    public void handleEvent(Event event) {
        
    }
    
}
