package org.dyndns.soundi.portals.interfaces;

/**
 * This is the class (enum) where the communication fields are declared.
 * You must use them for communication with the soundbox and its bundles.
 * 
 * @version 0.0.1
 * @author oli
 */
public enum CommunicationAction {

    //TODO split requests and responses for better understanding/division on what is what
    // please use the sender as string and not the one who should be informed
    // eg. use "soundbox/gui/browser/SEARCHSONG" when the browser sends a notification
    // that a song should be searched, and NOT "soundbox/gui/portals" because the
    // portals search for that song...
    /**
     * This request is send when the user hits "search" in the UI and the default
     * search is executed or "song" is explicitly chosen (standard in the default
     * browser gui).
     */
    SEARCHSONGFORBROWSER {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/SEARCHSONG";
        }
    },
    /**
     * This response is send from the portals when a song is found (or many songs).
     * Note: this is a list of songs, not only one song, in the payload of this event.
     * (TODO: document the event api and its payloads).
     */
    FOUNDSONG {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/portals/FOUNDSONG";
        }
    },
    /**
     * This is a request, send by the browser UI, that a song should be added to
     * the download queue. The song must be in the payload.
     */
    ADDSONGTODOWNLOADQUEUE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/ADDSONGTODOWNLOADQUEUE";
        }
    },
    /**
     * This is a request, send by the browser UI, that a song should be added to
     * the player queue. The song must be in the payload.
     */
    ADDSONGSTOPLAYERQUEUE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/ADDSONGSTOPLAYERQUEUE";
        }
    },
    /**
     * This is a request, send by the browser UI, that a song should be played.
     * The song must be in the payload.
     */
    STARTPLAYERFROMSONG {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/STARTPLAYERFROMSONG";
        }
    },
    /**
     * This is a request (usually for a specific portal) that a stream from a 
     * specific song is requests. The song must be in the payload.
     * (TODO: document the event api and its payloads).
     */
    GETSTREAMFROMSONG {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/player/GETSTREAMFROMSONG";
        }
    },
    /**
     * This is a response for GETSTREAMFROMSONG. It includes the InputStream in
     * its payload.
     * (TODO: document the event api and its payloads).
     */
    STREAMFROMSONG {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/portals/STREAMFROMSONG";
        }
    },
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
    }
}
