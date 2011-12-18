/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soundbox.starter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oli
 */
public class SoundBoxStarter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> cmds = new ArrayList();
        cmds.add("java");
        cmds.add("-jar");
        cmds.add("bin" + System.getProperty("file.separator") + "felix.jar");
        ProcessBuilder pb = new ProcessBuilder(cmds);
        Process p = null;
        try {
            p = pb.start();
        } catch (IOException ex) {
            Logger.getLogger(SoundBoxStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream is = new BufferedInputStream(p.getInputStream());
        Scanner scanner;

        scanner = new Scanner(is);
        while(scanner.hasNext())
        {
            System.out.println(scanner.nextLine());
        }
    }
}
