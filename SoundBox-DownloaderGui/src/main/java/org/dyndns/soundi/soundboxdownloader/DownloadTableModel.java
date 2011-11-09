/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloader;

/**
 *
 * @author oli
 */
import java.util.Vector;
import javax.swing.table.*;


import javax.swing.JProgressBar;
import org.dyndns.soundi.portals.interfaces.Song;

public class DownloadTableModel extends AbstractTableModel {

    private String headers[];// = {"ContentId", "Source", "Filename", "Size", "Progress"};
    private Class columnClasses[];// = {Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, JProgressBar.class, GroovesharkSong.class};
    private Vector dataVector;// = new Object[2][2];//{{"", "", "", "", "", "", "", ""}};

    public DownloadTableModel() {

        headers = new String[]{"Song", "Artist", "Album", "Length", "RawSong", "State", "Control"};
        columnClasses = new Class[]{String.class, String.class, String.class, Long.class, Song.class, JProgressBar.class, Object.class};
        dataVector = new Vector();//{{"", "", "", "", "", "", "", ""}};
        
    }

    public void removeRow(int row) {
        dataVector.remove(row);
        fireTableRowsDeleted(row, row);
    }

    @Override
    public int getRowCount() {
        return dataVector.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }

    @Override
    public String getColumnName(int c) {
        return headers[c];
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return false;
    }

    @Override
    public Object getValueAt(int r, int c) {

        return ((Vector) dataVector.get(r)).get(c);

    }
// Ok, do something extra here so that if we get a String object back (from a
// text field editor) we can still store that as a valid Volume object.  If
// it's just a string, then stick it directly into our data array.

    @Override
    public void setValueAt(Object value, int r, int c) {
        if (c == 7) {
            JProgressBar bar = (JProgressBar) ((Vector) dataVector.get(r)).get(c);
            Double d = (Double) value;
            bar.setValue(d.intValue());
        } else {
            ((Vector) dataVector.get(r)).set(c, value);
        }
        fireTableCellUpdated(r, c);


    }
// A quick debugging utility to dump out the contents of our data structure

    /* public void dump() {
    for (int i = 0; i < dataVector.length; i++) {
    System.out.print("|");
    for (int j = 0; j < dataVector[0].length; j++) {
    System.out.print(dataVector[i][j] + "|");
    }
    System.out.println();
    }
    }
     */
    private JProgressBar createProgressBar() {
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        return progressBar;
    }

    /**
     * Inserts a new row into the table.
     * 
     * @param row the row index.
     * @param rowData the row data.
     */
    public void insertRow(int row, Vector rowData) {
        dataVector.add(row, rowData);
        fireTableRowsInserted(row, row);
    }

    /**
     * Inserts a new row into the table.
     * 
     * @param row the row index.
     * @param rowData the row data.
     */
    public void insertRow(int row, Object[] rowData) {
        insertRow(row, convertToVector(rowData));
    }

    protected static Vector convertToVector(Object[] data) {
        if (data == null) {
            return null;
        }
        Vector vector = new Vector(data.length);
        for (int i = 0; i < data.length; i++) {
            vector.add(data[i]);
        }
        return vector;
    }
}