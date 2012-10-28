/**
 * This is the default activator for the main application SoundBox OSGi.
 *
 * @since 1.0
 */
package org.dyndns.soundi.soundbox;

import java.util.logging.Level;
import org.dyndns.soundi.utils.Util;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the main class which must be the first one that gets called - as osgi
 * bundle of course. This class provides the updater, the showGui call, and the
 * exit function of the application.
 *
 * @version 0.0.1
 * @author Oliver Zemann
 */
public class Activator implements BundleActivator {

    static final Logger logger = LoggerFactory.getLogger(Activator.class);
    /**
     * The SoundBox instance.
     */
    private transient SoundBox box;

    @Override
    public final void start(final BundleContext context) {
        if (logger.isTraceEnabled()) {
            logger.trace("start(" + context + ")");
        }
        try {
            Util.getProxyList();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Activator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        logger.info("Started the SoundBox :) - visit me on www.l33tbox.de");
        final Thread thread = new Thread() {
            @Override
            public void run() {
                box = new SoundBox(context);
                box.init();
            }
        };
        thread.start();
    }

    @Override
    public void stop(final BundleContext context) {
        box.close();
    }
}
