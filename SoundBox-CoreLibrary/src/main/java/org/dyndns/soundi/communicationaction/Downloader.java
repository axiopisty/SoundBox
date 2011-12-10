/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.communicationaction;

/**
 *
 * @author oli
 */
public final class Downloader {

    private Downloader() {
    }

    public static enum Requests {

        GETSTREAMFROMSONGFORDOWNLOADER {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/gui/downloader/GETSTREAMFROMSONGFORDOWNLOADER";
            }
        },
        //no matter where it comes from, just download it ...
        DOWNLOADSONG {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/DOWNLOADSONG";
            }
        },
        //if something is transfered, error occured etc.
        DOWNLOADSTATECHANGED {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/DOWNLOADSTATECHANGED";
            }
        },
        //if the song has been finished
        DOWNLOADSONGFINISHED {

            @Override
            public String toString() {
                return "org/dyndns/soundi/soundbox/DOWNLOADSONG";
            }
        },}

    public static enum Responses {
    }
}
