/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.portals.interfaces;

/**
 *
 * @author oli
 */
public enum CommunicationAction {

    //TODO split requests and responses for better understanding/division on what is what
    //req:
    SEARCHSONGFORBROWSER {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/SEARCHSONG";
        }
    },
    //resp:
    FOUNDSONG {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/portals/FOUNDSONG";
        }
    },
    //req:
    ADDSONGTODOWNLOADQUEUE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/downloader/ADDSONGTODOWNLOADQUEUE";
        }
    },
    //req:
    ADDSONGSTOPLAYERQUEUE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/player/ADDSONGSTOPLAYERQUEUE";
        }
    },
    //req:
    STARTPLAYERFROMSONG {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/player/STARTPLAYERFROMSONG";
        }
    },
    //req:
    GETSTREAMFROMSONG {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/player/GETSTREAMFROMSONG";
        }
    },
    //resp:
    STREAMFROMSONG {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/player/STREAMFROMSONG";
        }
    },
    SETBROWSERVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETBROWSERVISIBLE";
        }
    },
    SETBROWSERINVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETBROWSERINVISIBLE";
        }
    },
    SETDOWNLOADERVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETDOWNLOADERVISIBLE";
        }
    },
    SETDOWNLOADERINVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETDOWNLOADERINVISIBLE";
        }
    },
    SETPLAYERVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETPLAYERVISIBLE";
        }
    },
    SETPLAYERINVISIBLE {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/SETPLAYERINVISIBLE";
        }
    }
,   ADDSONGSTODOWNLOADENGINE}
