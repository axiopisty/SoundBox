/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.gui.interfaces;

import org.dyndns.soundi.portals.interfaces.Song;
import org.osgi.service.event.EventHandler;

/**
 *
 * @author oli
 */
public interface IDownloaderEngine extends EventHandler {
    public void download(Song song);
}
