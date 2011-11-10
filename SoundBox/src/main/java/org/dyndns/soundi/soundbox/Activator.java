package org.dyndns.soundi.soundbox;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * This is the main class which must be the first one that gets called - as osgi
 * bundle of course. This class provides the updater, the showGui call, and the
 * exit function of the application.
 *
 * @version 0.0.1
 * @author oli
 */
public class Activator implements BundleActivator {

    @Override
    public final void start(final BundleContext context) {

        final SoundBox box = new SoundBox(context);
        box.init();
        box.start();
    }

    @Override
    public void stop(final BundleContext context) {
        // maybe there should be an CommunicationAction.MAINAPPCLOSED message
        // so that a nice "close" of the application can be made...
    }
}
