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

public class EcouteurListSelection implements ListSelectionListener {

    /**
     *
     * Classe EcouteurListSelection permettant de recuperer les evenements
     * associes a la selection d une ligne d un tableau
     *
     */

    private Controleur controleur; //permet d appeler les traitements suivant les actions
    private Fenetre fenetre; //Fenetre depuis laquelle les actions sont faites

    /**
     * Constructeur de la classe EcouteurListSelection
     *
     * @param controleur //Controleur pour traier les actions
     * @param fenetre //Fenetre de l application
     */
    public EcouteurListSelection(Controleur controleur, Fenetre fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;
    }

    /**
     * Traiter les selections d une ligne d un tableau
     *
     * @param e evenement
     */
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
                    //Si on a clique sur tableauPI
                    if (lsm == fenetre.getListSelectModelPI()) {
                        Carte carte = fenetre.getCarte();
                        if (carte != null) {
                            //Recuperer la liste des points dinteret actuelle
                            ArrayList<PointInteret> listePiCarte = carte.getListePointsInteretActuelle();
                            if (listePiCarte != null && !listePiCarte.isEmpty()) {
                                if (i < listePiCarte.size()) {
                                    PointInteret pi = listePiCarte.get(i);
                                    //Afficher la point d interet correspondant a la ligne en surbrillance
                                    this.controleur.surbrillerPI(pi);
                                    this.controleur.surbrillerTables(pi);
                                    lsm.clearSelection();
                                }

                            }
                        }

                    } else {
                        Tournee tournee = fenetre.getTournee();
                        if (tournee != null) {
                            //Recuperer les points dinteret de la tournee
                            ArrayList<PointInteret> listePiTournee = tournee.getSuccessionPointsInteret();
                            if (listePiTournee != null && !listePiTournee.isEmpty()) {
                                PointInteret pi = new PointInteret();
                                if (i < listePiTournee.size()) {
                                    pi = listePiTournee.get(i);
                                }
                                //Si la derniere ligne du tableau est cliquee
                                if (i == listePiTournee.size()) {
                                    //Prendre la ligne du point d interet de l entrepot
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
