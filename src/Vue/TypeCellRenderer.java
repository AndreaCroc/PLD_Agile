
package Vue;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * TypeCellRenderer permet d afficher un element objet du tableau
 * tel qu un bouton ou un textarea en prenant en compte le type de l objet
 *
 * @version Version 1
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class TypeCellRenderer extends DefaultTableCellRenderer {

    /**
     * Afficher une case du tableau en appliquant un format
     * 
     * @param table tableau 
     * @param value valeur dune cellule
     * @param isSelected vrai si la case est selectionnee
     * @param hasFocus vrai si la souris est focalisee sur la cellule
     * @param row ligne ou se trouve la cellule
     * @param column colonne ou se trouve la cellule
     * @return composant avec ,ouveau format
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        //Si la case est de type TextArea
        if (value instanceof JTextArea) {
            return (JTextArea) value;
        }
        return this;
    }
}
