package org.dyndns.soundi.portals.interfaces;

/**
 * This is the class (enum) which represents the state of a plugin, either 
 * activated or deactivated.
 * 
 * @version 0.0.1
 * @author oli
 */

public enum State {

    /**
     * The plugin is initalized.
     */
    INITIALIZED {

        @Override
        public String toString() {
            return "Initialized";
        }
    },
    /**
     * After initialisation, the plugin can be activated. If it is activated, 
     * all requests and responses this plugin has subscriptioned will be delivered
     */
    ACTIVATED {

        @Override
        public String toString() {
            return "Activated";
        }
    },
    /**
     * If it is deactivated, it wont use any resources nor retrieve any event.
     */
    DEACTIVATED {

        @Override
        public String toString() {
            return "Deactivated";
        }
    }
};
