/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

/**
 *
 * @author acer
 */
public class EcouteurBoutonsTable implements ActionListener{
    private int colonne;
    private int ligne;
    private JTable table;
    
    public EcouteurBoutonsTable(){
        System.out.println("EcouteurBoutonsTable cree");
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
        
    @Override
    public void actionPerformed(ActionEvent evt){
        System.out.println("Clic sur bouton : ");
    }
}
