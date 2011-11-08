package org.dyndns.soundi.soundbox.core.gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.dyndns.soundi.portals.interfaces.Song;
import org.dyndns.soundi.soundboxbrowsergui.Activator;
import org.dyndns.soundi.utils.Util;
import org.dyndns.soundi.utils.Util.Component;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 *
 * @author oli
 */
public final class BrowserFrame extends JFrame implements IBrowserGui {

    private final BundleContext cx;
    private boolean initialized = false;
    Set portals;
    /**
     *
     */
    protected final JFrame browser = this;

    /**
     * Creates new form MainFrame
     *
     * @param cx
     */
    public BrowserFrame(final BundleContext cx) {
        this.cx = cx;
        portals = new HashSet<IPortal>();
        Bundle[] bundles = cx.getBundles();

        if (!initialized) {
            initComponents();
            initialized = true;
        }

        display();
        pluginListener();

    }

    private void addPortalToGui(final IPortal portal) {
        JMenu submenu = new JMenu(portal.getInfos().getPluginName());

        final JCheckBoxMenuItem item;
        item = new JCheckBoxMenuItem(portal.getState().toString(),
                portal.getState().toString().equals(org.dyndns.soundi.portals.interfaces.State.ACTIVATED.toString()) ? true : false);

        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (portal.getState()) {
                    case ACTIVATED:
                        portal.setState(org.dyndns.soundi.portals.interfaces.State.DEACTIVATED);
                        break;
                    case DEACTIVATED:
                        portal.setState(org.dyndns.soundi.portals.interfaces.State.ACTIVATED);
                        break;
                    default:
                        portal.setState(org.dyndns.soundi.portals.interfaces.State.DEACTIVATED);
                        break;
                }
                item.setText(portal.getState().toString());
            }
        });

        JMenuItem configItem = new JMenuItem(portal.getInfos().getPluginName() + " configuration");
        JMenuItem aboutItem = new JMenuItem("About " + portal.getInfos().getPluginName());
        configItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                portal.showConfig();
            }
        });
        aboutItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                portal.showPluginInformation();
            }
        });
        submenu.add(configItem);
        submenu.add(aboutItem);
        submenu.add(item);
        configurationMenu.add(submenu);
    }

    /**
     * TODO: this method should be in the soundbox, not in the gui!!
     */
    private void pluginListener() {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BrowserFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    ServiceReference[] references = null;
                    try {
                        //fetch all portals
                        references = cx.getServiceReferences(IPortal.class.getName(), null);
                    } catch (InvalidSyntaxException ex) {
                        Logger.getLogger(Activator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //if we found at least one...
                    if (references != null) {
                        //loop through it/them
                        for (ServiceReference reference : references) {
                            //cast it to an instance so we can use it (fetch informations from it)
                            final IPortal portal = (IPortal) cx.getService(reference);
                            //check if it is listed in our portals list
                            if (!portals.contains(portal)) {
                                //if not, call init() on the portals implemented interface
                                System.out.println("Registered new portal (" + portal.getInfos().getPluginName() + ")!");
                                if (portal.getState() == org.dyndns.soundi.portals.interfaces.State.ACTIVATED) {
                                    portal.init();
                                }
                                addPortalToGui(portal);
                                portals.add(portal);
                            }
                            //check if a portal has been removed 
                            if (portals.size() != references.length) {
                                //something changed... as we already registered every new portal, it must be a deletion 
                                System.out.println("a plugin has been removed!");
                            }
                        }
                    }

                    if (references == null && portals.size() == 1) //there's still one portal registered, but it has been removed
                    {
                        IPortal portal = (IPortal) portals.iterator().next();
                        removePortalFromGui(portal);
                        System.out.println("Plugin " + portals.iterator().next());
                        portals.clear();
                    }
                }
            }
        }.start();
    }

    private void removePortalFromGui(IPortal portal) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        configurationMenu = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jLabel1.setText("Keyword");

        jTextField1.setText("Enter a keyword...");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });

        jButton1.setText("Go!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jProgressBar1.setValue(60);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Song", "Artist", "Album" }));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Songname", "Artist", "Album", "Length", "RawSong"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setDoubleBuffered(true);
        jTable1.setMaximumSize(new java.awt.Dimension(375, 32));
        jTable1.setMinimumSize(new java.awt.Dimension(375, 32));
        jTable1.setOpaque(false);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton2.setText("Download");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Add to Player");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        configurationMenu.setText("Configuration");
        jMenuBar1.add(configurationMenu);

        jMenu3.setText("Help");

        jMenuItem1.setText("Online Help");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem3.setText("Help");
        jMenu3.add(jMenuItem3);

        jMenuItem2.setText("About");
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Util.sendMessage(Component.BROWSER, "Searching for `" + jTextField1.getText() + "`");
        searchSong(jTextField1.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() >= 2) {
            Song s = (Song) jTable1.getValueAt(jTable1.getSelectedRow(), 4);
            ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

            if (ref != null) {
                EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
                Dictionary properties = new Hashtable();
                properties.put("song", s);
                Event reportGeneratedEvent = new Event(CommunicationAction.STARTPLAYERFROMSONG.toString(), properties);
                eventAdmin.sendEvent(reportGeneratedEvent);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Song s = (Song) jTable1.getValueAt(jTable1.getSelectedRow(), 4);
        ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
            Dictionary properties = new Hashtable();
            properties.put("song", s);
            Event reportGeneratedEvent = new Event(CommunicationAction.ADDSONGTODOWNLOADQUEUE.toString(), properties);
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Desktop desktop = null;
        // Before more Desktop API is used, first check 
        // whether the API is supported by this particular 
        // virtual machine (VM) on this particular host.
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI("http://soundbox.origo.ethz.ch/forum"));
                } catch (Exception ex) {
                    Logger.getLogger(BrowserFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu configurationMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void changeConfiguration() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void display() {
        if (!initialized) {
            initComponents();
            initialized = true;
        }
        this.setVisible(true);
        System.out.println("display set to visible!");
    }

    @Override
    public void searchArtist(String artist) {
        ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
            Dictionary properties = new Hashtable();
            properties.put("artistName", artist);
            Event reportGeneratedEvent = new Event(CommunicationAction.SEARCHARTISTFORBROWSER.toString(), properties);
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
    }

    @Override
    public void searchSong(String song) {
        ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
            Dictionary properties = new Hashtable();
            properties.put("songTitle", song);
            Event reportGeneratedEvent = new Event(CommunicationAction.SEARCHSONGFORBROWSER.toString(), properties);
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
    }

    @Override
    public void searchAlbum(String album) {
        ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

        if (ref != null) {
            EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
            Dictionary properties = new Hashtable();
            properties.put("albumTitle", album);
            Event reportGeneratedEvent = new Event(CommunicationAction.SEARCHALBUMFORBROWSER.toString(), properties);
            eventAdmin.sendEvent(reportGeneratedEvent);
        }
    }

    /**
     *
     * @param event
     */
    @Override
    public void handleEvent(final Event event) {

        if (event.getTopic().equals(CommunicationAction.SETBROWSERVISIBLE.toString())) {
            setVisible(true);
        } else if (event.getTopic().equals(CommunicationAction.FOUNDSONG.toString())) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);

                    ArrayList<Song> l = (ArrayList) event.getProperty("songList");
                    for (Song s : l) {
                        addSongToTable(s);
                    }
                }
            });
        }
    }

    private void addSongToTable(Song s) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int minutes = 0, seconds = 0;
        String duration;
        minutes = s.getTimeInSeconds() / 60;
        seconds = s.getTimeInSeconds() % 60;
        duration = "" + minutes + ":";
        if (seconds < 9) {
            duration += "0";
        }
        duration += "" + seconds + " minutes";

        model.addRow(new Object[]{s.getSongName(), s.getArtist().getArtistName(), s.getAlbumName(), duration, s});
        model.fireTableDataChanged();

    }
}
