/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import controleur.Controleur;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author acer
 */
public class EcouteurListSelection implements ListSelectionListener {

    private Controleur controleur;

    public EcouteurListSelection(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            System.out.println("ligne cliquee");
            System.out.println("index ligne : "+e.getLastIndex());
            controleur.surbrillancePI(e.getLastIndex());
        }

    }

}
