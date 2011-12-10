/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.communicationaction.core;

/**
 *
 * @author oli
 */


public enum Requests {

    /**
     * This is a request from the SoundBox Core to set the Browser UI visible.
     */
    SETBROWSERVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETBROWSERVISIBLE";
        }
    },
    /**
     * This is a request from the SoundBox Core to set the Browser UI invisible.
     */
    SETBROWSERINVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETBROWSERINVISIBLE";
        }
    },
    /**
     * This is a request from the SoundBox Core to set the Downloader UI visible.
     */
    SETDOWNLOADERVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETDOWNLOADERVISIBLE";
        }
    },
    /**
     * This is a request from the SoundBox Core to set the Downloader UI invisible.
     */
    SETDOWNLOADERINVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETDOWNLOADERINVISIBLE";
        }
    },
    /**
     * This is a request from the SoundBox Core to set the Player UI visible.
     */
    SETPLAYERVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETPLAYERVISIBLE";
        }
    },
    /**
     * This is a request from the SoundBox Core to set the Player UI invisible.
     */
    SETPLAYERINVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETPLAYERINVISIBLE";
        }
    },
    /**
     * This is a request from the PluginRegistry that a (new) portal is registered.
     * (TODO: document the event api and its payloads).
     */
    REGISTERPORTAL {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/pluginregistry/REGISTERPORTAL";
        }
    },
    /**
     * This is a request from the PluginRegistry that a portal is unregistered.
     * (TODO: document the event api and its payloads).
     */
    UNREGISTERPORTAL {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/pluginregistry/UNREGISTERPORTAL";
        }
    },

}