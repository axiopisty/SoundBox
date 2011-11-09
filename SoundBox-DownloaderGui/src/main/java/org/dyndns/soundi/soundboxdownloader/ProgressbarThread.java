/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloader;

import javax.swing.JTable;

/**
 *
 * @author oli
 */
public class ProgressbarThread implements Runnable {
    
    private final int row;
    private final int percent;
    private final JTable table;
    
    public ProgressbarThread(int row, int percent, JTable table)
    {
        this.row = row;
        this.percent = percent;
        this.table = table;
    }

    @Override
    public void run() {
        if(row == -1)
            return;
        table.setValueAt(percent, row, 5);
    }
    
}
