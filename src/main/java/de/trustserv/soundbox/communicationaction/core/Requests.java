package de.trustserv.soundbox.communicationaction.core;

/**
 * Helper class for the long String (Topic).
 *
 * @author Oliver Zemann
 */
class StringPath {

    /**
     * The path of the topic string.
     */
    public static final String PATH = "org/dyndns/soundi/soundbox/core/";

    /**
     * Dont allow subclassing.
     */
    private StringPath() {
    }
}

/**
 * This enum defines all requests send by the soundbox core.
 *
 * @author Oliver Zemann
 */
public enum Requests {

    /**
     * This is a request from the SoundBox Core to set the Browser UI visible.
     */
    SETBROWSERVISIBLE {
                @Override
                public String toString() {
                    return StringPath.PATH + "SETBROWSERVISIBLE";
                }
            },
    /**
     * This is a request from the SoundBox Core to set the Browser UI invisible.
     */
    SETBROWSERINVISIBLE {
                @Override
                public String toString() {
                    return StringPath.PATH + "SETBROWSERINVISIBLE";
                }
            },
    /**
     * This is a request from the SoundBox Core to set the Downloader UI
     * visible.
     */
    SETDOWNLOADERVISIBLE {
                @Override
                public String toString() {
                    return StringPath.PATH + "SETDOWNLOADERVISIBLE";
                }
            },
    /**
     * This is a request from the SoundBox Core to set the Downloader UI
     * invisible.
     */
    SETDOWNLOADERINVISIBLE {
                @Override
                public String toString() {
                    return StringPath.PATH + "SETDOWNLOADERINVISIBLE";
                }
            },
    /**
     * This is a request from the SoundBox Core to set the Player UI visible.
     */
    SETPLAYERVISIBLE {
                @Override
                public String toString() {
                    return StringPath.PATH + "SETPLAYERVISIBLE";
                }
            },
    /**
     * This is a request from the SoundBox Core to set the Player UI invisible.
     */
    SETPLAYERINVISIBLE {
                @Override
                public String toString() {
                    return StringPath.PATH + "SETPLAYERINVISIBLE";
                }
            },
    /**
     * This is a request from the PluginRegistry that a (new) portal is
     * registered.
     */
    REGISTERPORTAL {
                @Override
                public String toString() {
                    return StringPath.PATH + "REGISTERPORTAL";
                }
            },
    /**
     * This is a request from the PluginRegistry that a portal is unregistered.
     */
    UNREGISTERPORTAL {
                @Override
                public String toString() {
                    return StringPath.PATH + "UNREGISTERPORTAL";
                }
            },
    /**
     * This informs all listeners to shutdown now. If it is send the all bundles
     * have a few seconds time to do what they need to "save" their state.
     */
    CLOSE {
                @Override
                public String toString() {
                    return StringPath.PATH + "CLOSE";
                }
            },
    /**
     * This is a message from the Core Library. If you want to get infos about
     * something around, handle it! Everything that uses the
     * Util.getInstance().sendMessage() method will create an Event with a
     * Message object, containing the sender and the message.
     */
    MESSAGE {
                @Override
                public String toString() {
                    return StringPath.PATH + "MESSAGE";
                }
            },
    /**
     *
     */
    GETGLOBALCONFIG {
                @Override
                public String toString() {
                    return StringPath.PATH + "GETGLOBALCONFIG";
                }
            }
}
