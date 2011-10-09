/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.portals.interfaces;

/**
 *
 * @author oli
 */
public enum State {

    /**
     * 
     */
    INITIALIZED {

        @Override
        public String toString() {
            return "Initialized";
        }
    },
    /**
     * 
     */
    ACTIVATED {

        @Override
        public String toString() {
            return "Activated";
        }
    },
    /**
     * 
     */
    DEACTIVATED {

        @Override
        public String toString() {
            return "Deactivated";
        }
    }
};
