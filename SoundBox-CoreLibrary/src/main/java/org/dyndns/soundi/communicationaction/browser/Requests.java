/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.communicationaction.browser;

/**
 *
 * @author oli
 */
public enum Requests {

    /**
     * This request is send when the user hits "search" in the UI and the default
     * search is executed or "song" is explicitly chosen (standard in the default
     * browser gui).
     */
    SEARCHSONGFORBROWSER {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/SEARCHSONGFORBROWSER";
        }
    },
    /**
     * This request is send when the user hits "search" in the UI and the combo-
     * box is changed to search only for albums.
     */
    SEARCHALBUMFORBROWSER {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/SEARCHALBUMFORBROWSER";
        }
    },
    /**
     * This request is send when the user hits "search" in the UI and the
     * combobox is set to artist in the browser gui.
     */
    SEARCHARTISTFORBROWSER {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/SEARCHARTISTFORBROWSER";
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
    PLAYSONGFROMBROWSER {

        @Override
        public String toString() {
            return "org/dyndns/soundi/soundbox/gui/browser/PLAYSONGFROMBROWSER";
        }
    },
}
