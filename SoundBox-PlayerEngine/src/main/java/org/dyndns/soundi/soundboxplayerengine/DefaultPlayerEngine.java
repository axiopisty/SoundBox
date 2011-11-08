/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxplayerengine;

import java.io.InputStream;
import org.dyndns.soundi.gui.interfaces.IPlayerEngine;
import org.osgi.framework.BundleContext;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
