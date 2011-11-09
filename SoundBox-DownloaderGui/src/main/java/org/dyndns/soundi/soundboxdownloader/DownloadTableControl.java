/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloader;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author oli
 */
public class DownloadTableControl extends JPanel implements TableCellRenderer {

    public DownloadTableControl() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        add(new JButton("a"));
        add(new JButton("b"));
        add(new JButton("c"));
        return this;
    }
    
}
