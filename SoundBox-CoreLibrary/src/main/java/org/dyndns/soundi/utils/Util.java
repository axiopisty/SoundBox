/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.utils;

/**
 *
 * @author oli
 */
public class Util {

    public static enum Component {

        CORE, BROWSER, PLAYER, EVENTADMIN, DOWNLOADER, DOWNLOADQUEUE, PLAYERQUEUE, UNSPECIFIED
    }
    
    public static void sendMessage(Component comp, String message)
    {
        System.out.println("["+comp.toString()+"]: " + message);
    }
}
