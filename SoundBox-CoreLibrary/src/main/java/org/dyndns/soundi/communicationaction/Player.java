/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.communicationaction;

/**
 *
 * @author oli
 */
public final class Player {

    private Player() {
    }

    public static enum Requests {

        /**
         * This is a request (usually for a specific portal) that a stream from a
         * specific song is requests. The song must be in the payload.
         * (TODO: document the event api and its payloads).
         */
        GETSTREAMFROMSONGFORPLAYER {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/gui/player/GETSTREAMFROMSONGFORPLAYER";
            }
        },
        PLAYSONGFROMPLAYER {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/gui/player/PLAYSONGFROMPLAYER";
            }
        },
        //must be from the player
        PAUSESONG {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/gui/player/PAUSESONG";
            }
        },
        //must be from the player
        STOPSONG {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/gui/player/STOPSONG";
            }
        },
        /**
         * If the playback state has changed (every frame..)
         */
        PLAYBACKSTATECHANGED {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/defaultplayerengine/PLAYBACKSTATECHANGED";
            }
        },
        /**
         * This method is send from the Player GUI when the user stops the song
         */
        STOPPLAYBACKFROMPLAYER {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/gui/player/STOPPLAYBACKFROMPLAYER";
            }
        }
    }

    public static enum Responses {
    }
}
