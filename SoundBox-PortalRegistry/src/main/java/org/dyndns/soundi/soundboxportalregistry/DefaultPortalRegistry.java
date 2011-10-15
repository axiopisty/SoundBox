package org.dyndns.soundi.soundboxportalregistry;

import java.io.File;
import java.io.FilenameFilter;
import org.dyndns.soundi.portals.interfaces.PortalRegistry;
import org.osgi.service.event.Event;


/**
 *
 * @author oli
 */
public class DefaultPortalRegistry extends PortalRegistry implements Runnable {

    private final String directory = "c:\\plugins";

    public DefaultPortalRegistry() {
        super();
    }

    public void init() {
        /*System.out.println("Watching in " + new File(directory).getAbsolutePath());
        File jarFiles[] = new File(directory).listFiles(new FilenameFilter() {
        
        public boolean accept(File dir, String name) {
        if(name.endsWith(".jar"))
        return true;
        else return false;
        }
        });*/
        //String key = "felix.fileinstall.dir";
        //String value = directory;
        //System.setProperty(key, value);
    }

    public void run() {
        init();
        
        
    }

    public void handleEvent(Event event) {
    }
}
