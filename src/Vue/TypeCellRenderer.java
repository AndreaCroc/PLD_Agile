/*
 * TypeCellRenderer
 *
 * Version 1
 * 
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package Vue;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * Classe TypeCellRenderer permet d afficher un element objet du tableau
 * tel qu un bouton ou un textarea en prenant en compte le type de l objet
 */
public class TypeCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        if (value instanceof JTextArea) {
            return (JTextArea) value;
        }
        return this;
    }
}
