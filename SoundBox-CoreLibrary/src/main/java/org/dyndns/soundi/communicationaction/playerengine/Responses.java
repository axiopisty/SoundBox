/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.communicationaction.playerengine;

/**
 *
 * @author oli
 */
public enum Responses {
    /**
     * If the playback state has changed (every frame..)
     */
    PLAYBACKSTATECHANGED {
        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/playerengine/PLAYBACKSTATECHANGED";
        }
        
    }
}
