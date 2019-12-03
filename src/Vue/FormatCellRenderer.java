/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author acer
 */
public class FormatCellRenderer extends DefaultTableCellRenderer {

    private int index;

    public FormatCellRenderer(int index) {
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        if (index != -1) {
            int nbLignes = table.getRowCount() - 1;
            if (row == this.index || row - nbLignes == index) {
                this.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }

        this.setHorizontalAlignment(JLabel.CENTER);
        return this;
    }
}
