/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.communicationaction;

/**
 *
 * @author oli
 */
public final class Portals {

    private Portals() {
    }

    public static enum Requests {
    }

    public static enum Responses {

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
         * This is a response for GETSTREAMFROMSONGFORPLAYER. It includes the InputStream in
         * its payload.
         * (TODO: document the event api and its payloads).
         */
        STREAMFROMSONGFORPLAYER {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/portals/STREAMFROMSONGFORPLAYER";
            }
        },
        /**
         * This is a response for GETSTREAMFROMSONGFORDOWNLOADER. It includes the InputStream in
         * its payload.
         * (TODO: document the event api and its payloads).
         */
        STREAMFROMSONGFORDOWNLOADER {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/portals/STREAMFROMSONGFORDOWNLOADER";
            }
        },}
}
