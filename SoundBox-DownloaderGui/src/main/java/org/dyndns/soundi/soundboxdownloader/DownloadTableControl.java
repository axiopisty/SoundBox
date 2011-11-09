/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.soundi.soundboxdownloader;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager2;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author oli
 */
public class DownloadTableControl extends JPanel implements TableCellRenderer {

    private JButton pause;
    private JButton abort;
    private JButton retry;
    
    public DownloadTableControl() {
        super(new FlowLayout());
        pause = new JButton("p");
        abort = new JButton("a");
        retry = new JButton("r");
        add(pause);
        add(abort);
        add(retry);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
    
}
