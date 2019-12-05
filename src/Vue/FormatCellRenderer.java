/*
 * FormatCellRenderer
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

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * Classe FormatCellRenderer permettant de mettre tous les elements du tableau
 * au centre de la cellule et d encadrer en rouge la ligne en cas de clic sur un
 * point d interet
 */
public class FormatCellRenderer extends DefaultTableCellRenderer {

    private int index;
    private int tableau;

    public FormatCellRenderer(int index, int table) {
        this.index = index;
        this.tableau = table;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        //Si on a clique sur un point d interet
        if (index != -1) {
            //Si on veut encadrer une ligne du tableauPIs
            if (tableau == 1) {
                if (row == this.index) {
                    this.setBorder(BorderFactory.createLineBorder(Color.red));
                }
                if (value instanceof JTextArea) {
                    return (JTextArea) value;
                }
                //Si on veut encadrer un eligne du tableauEtapes
            } else {
                int nbLignes = table.getRowCount() - 1;
                //Encadrer chaque cellule de la ligne associee au point d interet
                if (row == this.index || row - nbLignes == index) {
                    this.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }

        }

        //Poisitionner les elements textes au centre de la case
        this.setHorizontalAlignment(JLabel.CENTER);
        return this;
    }
}
