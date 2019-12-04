/*
 * EcouteurListSelection
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

import controleur.Controleur;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * Classe EcouteurListSelection permettant de recuperer les evenements
 * associes a la selection d une ligne d un tableau
 * 
 */
public class EcouteurListSelection implements ListSelectionListener {

    private Controleur controleur;

    public EcouteurListSelection(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        //Quand une ligne du tableau a ete selectionnee
        if (!lsm.isSelectionEmpty() && !e.getValueIsAdjusting()) {
            //Trouver l index min d une ligne du tableau
            int minIndex = lsm.getMinSelectionIndex();
            //Trouver l index max d une ligne du tableau
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                //Trouver l unique ligne qui a ete selectionnee
                if (lsm.isSelectedIndex(i)) {
                    //Indiquer qu une ligne a ete selectionee
                    this.controleur.setFenetreSurbrillance(true);
                    //Afficher la point d interet correspond a la ligne en surbrillance
                    this.controleur.surbrillancePI(i);
                }
            }
        }

    }

}
