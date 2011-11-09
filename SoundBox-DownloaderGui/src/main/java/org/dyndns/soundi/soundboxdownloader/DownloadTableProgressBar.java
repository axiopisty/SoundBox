/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloader;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author oli
 */
public class DownloadTableProgressBar extends JProgressBar implements TableCellRenderer {

    int initValue = 0;
    
    public DownloadTableProgressBar() {
        super(SwingConstants.HORIZONTAL);
        setStringPainted(true);
        setSize(115, 15);
    }
    
    public DownloadTableProgressBar(int i) {     
        super(SwingConstants.HORIZONTAL);
        initValue = i;
        setStringPainted(true);
        setSize(115, 15);
    }

    @Override
    public Component getTableCellRendererComponent(
            final JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (value == null) {
            return this;
        }
        if (value instanceof DownloadTableProgressBar) {
            setValue(((DownloadTableProgressBar) value).getValue());
        } else {
            setValue(0);
        }
        return this;
    }
}
