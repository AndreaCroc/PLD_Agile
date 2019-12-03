/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author acer
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

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        System.out.println("ediror");
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
        ecouteurBoutonTab.setColonne(column);
        ecouteurBoutonTab.setLigne(row);
        ecouteurBoutonTab.setTable(table);
        System.out.println("getTableCellEditorComponent");
        
        if(value!=null){
            bouton.setText(value.toString());
            System.out.println("value not null");
        }else{
            bouton.setText("");
            System.out.println("VALUE NULL");
        }
        return bouton;

    }
}
