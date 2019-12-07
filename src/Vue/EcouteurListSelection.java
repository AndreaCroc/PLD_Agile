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
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 *
 * Classe EcouteurListSelection permettant de recuperer les evenements associes
 * a la selection d une ligne d un tableau
 *
 */
public class EcouteurListSelection implements ListSelectionListener {

    private Controleur controleur;
    private Fenetre fenetre;

    public EcouteurListSelection(Controleur controleur, Fenetre fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("ecouteurlis selection");
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
                    //Si on a clique sur tableauPI
                    if (lsm == fenetre.getListSelectModelPI()) {
                        Carte carte = fenetre.getCarte();
                        if (carte != null) {
                            ArrayList<PointInteret> listePiCarte = carte.getListePointsInteretActuelle();
                            if (listePiCarte != null && !listePiCarte.isEmpty()) {
                                System.out.println("ligne cliquee : " + i);
                                if (i < listePiCarte.size()) {
                                    System.out.println("if");
                                    PointInteret pi = listePiCarte.get(i);
                                    //Afficher la point d interet correspond a la ligne en surbrillance
                                    this.controleur.surbrillerPI(pi);
                                    this.controleur.surbrillerTables(pi);
                                    lsm.clearSelection();
                                }

                            }
                        }

                    } else {
                        Tournee tournee = fenetre.getTournee();
                        if (tournee != null) {
                            ArrayList<PointInteret> listePiTournee = tournee.getSuccessionPointsInteret();
                            if (listePiTournee != null && !listePiTournee.isEmpty()) {
                                System.out.println("ligne cliquee : " + i);
                                PointInteret pi = new PointInteret();
                                if (i < listePiTournee.size()) {
                                    System.out.println("if");
                                    pi = listePiTournee.get(i);
                                }
                                if (i == listePiTournee.size()) {
                                    pi = listePiTournee.get(0);
                                }
                                //Afficher la point d interet correspond a la ligne en surbrillance
                                this.controleur.surbrillerPI(pi);
                                this.controleur.surbrillerTables(pi);
                                lsm.clearSelection();
                            }
                        }

                    }

                }
            }

        }
    }
}
