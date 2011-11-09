/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloader;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author oli
 */
public class DownloadTableProgressBar extends JProgressBar implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            final JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        
        this.add(new JProgressBar(0,100));
        return this;
    }
}
