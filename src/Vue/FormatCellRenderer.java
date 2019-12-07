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

    private int indexPI;
    private int indexPIDep;
    private int tableau;

    public FormatCellRenderer(int indexPI, int indexPIDep, int table) {
        this.indexPI = indexPI;
        this.indexPIDep = indexPIDep;
        this.tableau = table;
    }

    public int getIndexPI() {
        return indexPI;
    }

    public void setIndexPI(int indexPI) {
        this.indexPI = indexPI;
    }

    public int getIndexPIDep() {
        return indexPIDep;
    }

    public void setIndexPIDep(int indexPIDep) {
        this.indexPIDep = indexPIDep;
    }  

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        //Si on veut encadrer une ligne du tableauPIs
        if (tableau == 1) {
            //Si on a clique sur un point d interet
            if (indexPI != -1) {
                if (row == this.indexPI || row == this.indexPI - table.getRowCount()) {
                    if (column == 0) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.red));
                    } else if (value instanceof JTextArea) {
                        JTextArea textArea = (JTextArea) value;
                        textArea.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.red));
                        return textArea;
                    } else {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.red));
                    }
                }
            }
            
            if (indexPI != 0 && indexPIDep != -1) {
                if (row == this.indexPIDep || row == this.indexPIDep - table.getRowCount()) {
                    if (column == 0) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.ORANGE));
                    } else if (value instanceof JTextArea) {
                        JTextArea textArea = (JTextArea) value;
                        textArea.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.ORANGE));
                        return textArea;
                    } else {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.ORANGE));
                    }
                }
            }

            if (value instanceof JTextArea) {
                JTextArea textArea = (JTextArea) value;
                textArea.setBorder(null);
                return textArea;
            }
            //Si on veut encadrer une ligne du tableauEtapes
        } else {
            if (indexPI != -1) {
                int nbLignes = table.getRowCount() - 1;
                //Encadrer chaque cellule de la ligne associee au point d interet
                if (row == this.indexPI || row - nbLignes == indexPI || row == indexPI - nbLignes) {
                    if (column == 0) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.red));
                    } else if (column == table.getColumnCount() - 1) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.red));
                    } else {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.red));
                    }
                }
            }
            if (indexPI != 0 && indexPIDep != -1) {
                if (row == this.indexPIDep) {
                    if (column == 0) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.ORANGE));
                    }else {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.ORANGE));
                    }
                }
            }

        }

        //Poisitionner les elements textes au centre de la case
        this.setHorizontalAlignment(JLabel.CENTER);
        return this;
    }
}
