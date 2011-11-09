/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloader;

/**
 *
 * @author oli
 */
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.*;


import javax.swing.JProgressBar;
import org.dyndns.soundi.portals.interfaces.Song;

public class DownloadTableModel extends AbstractTableModel {

    private String headers[];
    private Class columnClasses[];
    private Vector dataVector;

    public DownloadTableModel() {
        headers = new String[]{"Song", "Artist", "Album", "Length", "RawSong", "State", "Control"};
        columnClasses = new Class[]{String.class, String.class, String.class, Long.class, Song.class, DownloadTableProgressBar.class, DownloadTableControl.class};
        dataVector = new Vector();
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
        return true;
    }

    @Override
    public Object getValueAt(int r, int c) {
         return ((Vector) dataVector.get(r)).get(c);
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if (column == 5) {
            DownloadTableProgressBar bar = (DownloadTableProgressBar) ((Vector) dataVector.get(row)).get(column);    
            Integer i = (Integer) value;
            bar.setValue(i);
        } else if(column == 6) {
            DownloadTableControl ctrl = (DownloadTableControl) value;
            
        }else {
            ((Vector) dataVector.get(row)).set(column, value);
        }
        fireTableCellUpdated(row, column);
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
        vector.addAll(Arrays.asList(data));
        return vector;
    }
}