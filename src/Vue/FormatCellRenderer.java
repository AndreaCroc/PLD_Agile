
package Vue;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * FormatCellRenderer permet de mettre tous les elements du tableau
 * au centre de la cellule et d encadrer en rouge la ligne en cas de clic 
 * sur un point d interet
 *
 * @version Version 1
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class FormatCellRenderer extends DefaultTableCellRenderer {

    private int indexPI; //Index du point dinteret selectionne
    private int indexPIDep; //Index du poitn d interet dependant
    private int tableau; //Connaitre le tableau 

    /**
     * Constructeur de la classe FormatCellRenderer
     * @param indexPI index du point dinteret
     * @param indexPIDep index du point d interet dependant
     * @param table numero du tableua
     */
    public FormatCellRenderer(int indexPI, int indexPIDep, int table) {
        this.indexPI = indexPI;
        this.indexPIDep = indexPIDep;
        this.tableau = table;
    }

    /**
     * Recuperer lindex du point d interet
     * @return index point interet
     */
    public int getIndexPI() {
        return indexPI;
    }

    /**
     * Modifier l index du point d interet
     * @param indexPI nouvel index
     */
    public void setIndexPI(int indexPI) {
        this.indexPI = indexPI;
    }

    /**
     * Recuperer lindex du point dinteret dependant
     * @return index point dependant
     */
    public int getIndexPIDep() {
        return indexPIDep;
    }

    /**
     * Modifier lindex du point dependant
     * @param indexPIDep nouvel index
     */
    public void setIndexPIDep(int indexPIDep) {
        this.indexPIDep = indexPIDep;
    }  

    /**
     * Modifier l affichage d une cellule du tableau
     * @param table tableau 
     * @param value valeur de la cellule
     * @param isSelected indique si la case est selectionnee
     * @param hasFocus indique si la cellule est focalisee
     * @param row indique la ligne de la cellule
     * @param column indique la colonne de la cellule
     * @return nouveauu composant de la cellule
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        //Si on veut encadrer une ligne du tableauPIs
        if (tableau == 1) {
            //Encadrer la ligne correspondante au point dinteret selectionne
            if (indexPI != -1) {
                if (row == this.indexPI || row == this.indexPI 
                                                - table.getRowCount()) {
                    if (column == 0) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 
                                                                0, Color.blue));
                    //Si la cellule est de type textArea
                    } else if (value instanceof JTextArea) {
                        JTextArea textArea = (JTextArea) value;
                        textArea.setBorder(BorderFactory.createMatteBorder(2, 0, 
                                                             2, 2, Color.blue));
                        return textArea;
                    } else {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 
                                                                0, Color.blue));
                    }
                }
            }
            
            //Encadrer la ligne ou se trouve le point dinteret dependant
            if (indexPI != 0 && indexPIDep != -1) {
                if (row == this.indexPIDep || row == this.indexPIDep 
                                                    - table.getRowCount()) {
                    if (column == 0) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 
                                                              0, Color.ORANGE));
                    } else if (value instanceof JTextArea) {
                        JTextArea textArea = (JTextArea) value;
                        textArea.setBorder(BorderFactory.createMatteBorder(2, 0, 
                                                         2, 2, Color.ORANGE));
                        return textArea;
                    } else {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 
                                                        0, Color.ORANGE));
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
                //Encadrer chaque cellule de la ligne a
                //ssociee au point d interet
                if (row == this.indexPI || row - nbLignes == indexPI 
                        || row == indexPI - nbLignes) {
                    if (column == 0) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 
                                                                0, Color.blue));
                    } else if (column == table.getColumnCount() - 1) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 
                                                                2, Color.blue));
                    } else {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 
                                                                0, Color.blue));
                    }
                }
            }
            if (indexPI != 0 && indexPIDep != -1) {
                if (row == this.indexPIDep) {
                    if (column == 0) {
                        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 
                                                            0, Color.ORANGE));
                    }else {
                        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 
                                                            0, Color.ORANGE));
                    }
                }
            }

        }

        //Poisitionner les elements textes au centre de la case
        this.setHorizontalAlignment(JLabel.CENTER);
        return this;
    }
}
