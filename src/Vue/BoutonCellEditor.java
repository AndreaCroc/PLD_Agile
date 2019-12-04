/*
 * BoutonCellEditor
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
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * Classe BoutonCellEditor permet de recuperer les objets boutons
 * d un tableau et de leur rajouter une evenements sur le clic
 */
public class BoutonCellEditor extends DefaultCellEditor {

    private JButton bouton;
    private EcouteurBoutonsTable ecouteurBoutonTab;

    public BoutonCellEditor(JCheckBox checkBox,EcouteurBoutonsTable ecouteurBoutonTab) {
        super(checkBox);

        bouton = new JButton();
        bouton.setOpaque(true);
        this.ecouteurBoutonTab = ecouteurBoutonTab;
        bouton.addActionListener(this.ecouteurBoutonTab);
        System.out.println("BoutonCellEditor constructeur");
    }

    /**
     * Modifier l evenement sur un bouton
     * 
     * @param table : tableau qui appel la fonction
     * @param value : valeur de l'objet
     * @param isSelected : vrai si la ligne est selectionnee
     * @param row : index ligne
     * @param column : index colonne
     * @return 
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        System.out.println("ediror");
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
        //Recuperer la colonne sur laquelle on a clique
        ecouteurBoutonTab.setColonne(column);
        //Recuperer la ligne sur laquelle se trouve le bouton
        ecouteurBoutonTab.setLigne(row);
        //Recuperer le tableau dans lequel se trouve les elements
        ecouteurBoutonTab.setTable(table);
        System.out.println("getTableCellEditorComponent");
        
        if(value!=null){
            //Recuperer la valeur du bouton qui a declenche l appel
            bouton.setText(value.toString());
            System.out.println("value not null");
        }else{
            bouton.setText("");
            System.out.println("VALUE NULL");
        }
        return bouton;

    }
}
