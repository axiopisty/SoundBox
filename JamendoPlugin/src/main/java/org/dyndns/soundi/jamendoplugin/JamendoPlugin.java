/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.jamendoplugin;

import org.dyndns.soundi.portals.interfaces.Author;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.dyndns.soundi.portals.interfaces.License;
import org.dyndns.soundi.portals.interfaces.PluginInformation;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;

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

    public boolean init() {
        setInfos(new PluginInformation("Jamendo Plugin " + _version, new Author("Oliver", "Zemann"), new License("lgpl 1.0")));
        System.out.println("Initialized Jamendo Plugin " + getVersion());        
        return true;
    }

    public Object searchSong(String searchString) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void showPluginInformation() {
        
    }

    public void showConfig() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void handleEvent(Event event) {
        System.out.println("retrieved event: " + event.getTopic());
        System.out.println("contenct: " + event.toString());
    }

    private String getVersion() {
        return _version;
    }

    public PluginInformation getInfos() {
        return infos;
    }

    public void setInfos(PluginInformation infos) {
        this.infos = infos;
    }
}
