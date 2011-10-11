package org.dyndns.soundi.soundbox.core.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;
import org.dyndns.soundi.gui.interfaces.IBrowserGui;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.portals.interfaces.IPortal;
import org.dyndns.soundi.portals.interfaces.Song;
import org.dyndns.soundi.soundboxbrowsergui.Activator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 *
 * @author oli
 */
public class BrowserFrame extends JFrame implements IBrowserGui {

    private final BundleContext cx;
    Set portals;
    /**
     * 
     */
    protected final JFrame browser = this;

    /** Creates new form MainFrame
     * @param cx 
     */
    public BrowserFrame(BundleContext cx) {
        this.cx = cx;
        portals = new HashSet<IPortal>();
        initComponents();
        pluginListener();
    }

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
                        references = cx.getServiceReferences(IPortal.class.getName(), null);
                    } catch (InvalidSyntaxException ex) {
                        Logger.getLogger(Activator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (references != null) {
                        for (ServiceReference reference : references) {
                            final IPortal portal = (IPortal) cx.getService(reference);
                            if (!portals.contains(portal)) {
                                System.out.println("Registered new portal (" + portal.getInfos().getPluginName() + ")!");
                                if (portal.getState() == org.dyndns.soundi.portals.interfaces.State.ACTIVATED) {
                                    portal.init();
                                }

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

                                portals.add(portal);

                            }
                        }
                    }
                }
            }
        }.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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

        jLabel1.setText("Keyword");

        jTextField1.setText("Search for...");

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Songname", "Artist", "Album", "Length", "RawSong"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Object.class
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton2.setText("Download");

        jButton3.setText("Add to Player");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        configurationMenu.setText("Configuration");
        jMenuBar1.add(configurationMenu);

        jMenu3.setText("Help");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
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
        System.out.println("button hit: " + jTextField1.getText());
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
        this.setVisible(true);
        System.out.println("display set to visible!");
    }

    @Override
    public void searchArtist(String artist) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param event
     */
    @Override
    public void handleEvent(Event event) {
        
        if (event.getTopic().equals(CommunicationAction.SETBROWSERVISIBLE.toString())) {
            setVisible(true);
        } else if (event.getTopic().equals(CommunicationAction.FOUNDSONG.toString())) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                model.removeRow(i);
            }
            jTable1.setModel(model);
            ArrayList<Song> l = (ArrayList) event.getProperty("songList");
            for (Song s : l) {
                addSongToTable(s);
            }
        }
    }

    private void addSongToTable(Song s) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{s.getSongName(), s.getArtist().getArtistName(), s.getAlbumName(), s.getTimeInSeconds(), s});
        //TODO as model is a reference, im pretty sure thats not neccessary...
        jTable1.setModel(model);
    }
}
