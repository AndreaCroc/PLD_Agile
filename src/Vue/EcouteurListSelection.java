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
    private Fenetre fenetre;

    public EcouteurListSelection(Controleur controleur,Fenetre fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        //Quand une ligne du tableau a ete selectionnee
        if (!lsm.isSelectionEmpty() && !e.getValueIsAdjusting()) {
            //Trouver le premier index selectionne
            int minIndex = lsm.getMinSelectionIndex();
            //Trouver le dernier index selectionne
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                //Trouver l unique ligne qui a ete selectionnee
                if (lsm.isSelectedIndex(i)) {
                    //Indiquer qu une ligne a ete selectionee
                    this.controleur.setFenetreSurbrillance(true);
                    //Afficher la point d interet correspond a la ligne en surbrillance
                    this.controleur.surbrillancePI(i);
                    this.controleur.surbrillanceTableau(i);
                    if(this.fenetre.getClicSupp()){
                        this.controleur.supprimer(i);
                    }
                }
            }
        }

    }

}
