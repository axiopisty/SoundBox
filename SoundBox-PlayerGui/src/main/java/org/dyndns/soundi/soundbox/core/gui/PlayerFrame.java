/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on Sep 23, 2011, 1:17:58 PM
 */
package org.dyndns.soundi.soundbox.core.gui;

import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;
import org.dyndns.soundi.gui.interfaces.IPlayerGui;
import org.dyndns.soundi.gui.interfaces.IPlayerEngine;
import org.dyndns.soundi.portals.interfaces.CommunicationAction;
import org.dyndns.soundi.portals.interfaces.Song;
import org.dyndns.soundi.utils.Util;
import org.dyndns.soundi.utils.Util.Component;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

/**
 *
 * @author oli
 */
public class PlayerFrame extends javax.swing.JFrame implements IPlayerGui {

    private BundleContext cx = null;
    private IPlayerEngine playerEngine = null;

    /** Creates new form MainFrame */
    public PlayerFrame(BundleContext cx) {
        this.cx = cx;
        initComponents();
        setVisible(true);
        initPlayerEngine();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jButton2.setText("pause");

        jButton3.setText("stop");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addComponent(jButton2)
                        .addGap(93, 93, 93)
                        .addComponent(jButton3)
                        .addGap(392, 392, 392))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                        .addGap(89, 89, 89))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         if (evt.getClickCount() >= 2) {             Song s = (Song) jTable1.getValueAt(jTable1.getSelectedRow(), 4);             ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());              if (ref != null) {                 EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);                 Dictionary properties = new Hashtable();                 properties.put("song", s);                 Event reportGeneratedEvent = new Event(CommunicationAction.STARTPLAYERFROMSONG.toString(), properties);                 eventAdmin.sendEvent(reportGeneratedEvent);             }         }     }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Song s = (Song) jTable1.getValueAt(jTable1.getSelectedRow(), 4);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

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

    @Override
    public void handleEvent(Event event) {


        System.out.println("retrieved event: " + event.getTopic());
        if (event.getTopic().equals(CommunicationAction.STARTPLAYERFROMSONG.toString())) {
            Song s = (Song) event.getProperty("song");
            addSongToTable(s);
            ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());

            if (ref != null) {
                EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
                Dictionary properties = new Hashtable();
                properties.put("song", s);
                Event reportGeneratedEvent = new Event(CommunicationAction.GETSTREAMFROMSONGFORPLAYER.toString(), properties);
                eventAdmin.sendEvent(reportGeneratedEvent);
            }
        } else if (event.getTopic().equals(CommunicationAction.STREAMFROMSONGFORPLAYER.toString())) {
            InputStream is = (InputStream) event.getProperty("stream");
            Song song = (Song) event.getProperty("song");
            play(is, song);
        } else if (event.getTopic().equals(CommunicationAction.ADDSONGSTOPLAYERQUEUE.toString())) {
            
            Song song = (Song) event.getProperty("song");
            addSongToTable(song);
            ServiceReference ref = cx.getServiceReference(EventAdmin.class.getName());
            //remove that, only for testing purposes!
            
            if (ref != null) {
                EventAdmin eventAdmin = (EventAdmin) cx.getService(ref);
                Dictionary properties = new Hashtable();
                properties.put("song", song);
                Event reportGeneratedEvent = new Event(CommunicationAction.GETSTREAMFROMSONGFORPLAYER.toString(), properties);
                eventAdmin.sendEvent(reportGeneratedEvent);
            }
        }
    }

    /**
     * This method sends the stream to the PlayerEngine for playback.
     * @param is The InputStream from the song that should be played.
     */
    private void play(InputStream is, Song song) {
        playerEngine.play(is, song);
    }

    private void initPlayerEngine() {
        Runnable waitJob = new Runnable() {

            @Override
            public void run() {
                ServiceReference ref = null;

                while (ref == null) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException ex) {
                        java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ref = cx.getServiceReference(IPlayerEngine.class.getName());
                    Util.sendMessage(Component.PLAYER, "waiting for a player engine...");
                }
                playerEngine = (IPlayerEngine) cx.getService(ref);
                Util.sendMessage(Component.PLAYER, "registered a player engine");
            }
        };
        new Thread(waitJob).start();
    }
}
