/**
 * This is the default activator for the main application SoundBox OSGi.
 *
 * @since 1.0
 */
package org.dyndns.soundi.soundbox;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * This is the main class which must be the first one that gets called - as osgi
 * bundle of course. This class provides the updater, the showGui call, and the
 * exit function of the application.
 *
 * @version 0.0.1
 * @author Oliver Zemann
 */
public class Activator implements BundleActivator {

    /**
     * The SoundBox instance.
     */
    private transient SoundBox box;

    @Override
    public final void start(final BundleContext context) {
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
