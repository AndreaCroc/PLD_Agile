
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * EtatSupprimer
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */


public class EtatSupprimer implements Etat {
/**
 *
 * Classe EtatSupprimer quand on veut supprimer un point d interet
 */

    /**
     * Supprimer un point d interet de la tournee et son point correspondant
     * On repasse soit a l etat tournee soit a l etat de base
     *
     * @param controleur
     * @param fenetre
     * @param carte
     * @param tournee
     * @param index
     */
    @Override
    public void supprimer(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee, int index) {
        System.out.println("index : " + index);

        if (index != 0) {
            ArrayList<PointInteret> listePIs = carte.getListePointsInteretActuelle();
            //Recuperer le point d interet que l utilisateur veut supprimer
            System.out.println("index supprimer : " + index);
            System.out.println("supprimer : " + listePIs);
            int option = 1;
            boolean suppOk = false;
            PointInteret ptI = new PointInteret();
            //Si il y a plus d un element dans la liste de la carte
            if (listePIs.size() > 1) {
                ptI = listePIs.get(index);
                //Afficher un popup de confirmation de suppression
                option = fenetre.afficherPopSuppression(ptI);

                //Si on confirme la suppression
                if (option == JOptionPane.OK_OPTION) {
                    System.out.println("Point d'interet supprime");

                    suppOk = carte.supprimerPointInteret(ptI);
                    tournee = carte.getTournee();
                    listePIs = carte.getListePointsInteretActuelle();

                    if (listePIs.size() == 1 && suppOk) {
                        tournee = null;
                        carte.setUneTournee(null);
                    }
                    fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre, fenetre.getPanneauCarte().getZoom()));
                    fenetre.setTournee(tournee);
                    controleur.setTournee(tournee);
                    fenetre.viderPanneauEtapes();
                    fenetre.viderPanneauPIs();
                    fenetre.afficherBoutonSupprimer();
                    fenetre.griserBoutonCalcul();
                    fenetre.repaint();

                    //Plus que l entrepot dans la liste des points d interets de la carte
                    if (listePIs.size() == 1 && suppOk) {
                        fenetre.cacherPanneauEtapesEtTour();
                        fenetre.cacherPanneauPI();
                        //Afficher popup plus d elements liste
                        fenetre.afficherPopSuppressionVide();
                        controleur.setEtat(controleur.etatDeBase);
                    } else {
                        fenetre.afficherEtapesTour(true);
                        fenetre.afficherPanneauPI(true);
                        controleur.setEtat(controleur.etatTournee);
                        //Si suppression pas effectuee a cause d une erreur
                        if (!suppOk) {
                            //Afficher popup suppression annulee
                            fenetre.afficherPopSuppressionAnnulee();
                        }

                    }

                } else {
                    fenetre.afficherBoutonSupprimer();
                    fenetre.griserBoutonCalcul();
                    controleur.setEtat(controleur.etatTournee);
                }
            }

        } else {
            //Afficher popup d erreur car pas possible de supprimer l entrepot
            fenetre.afficherPopSuppressionErreur();
            fenetre.afficherBoutonSupprimer();
            fenetre.griserBoutonCalcul();
            controleur.setEtat(controleur.etatTournee);
        }
        fenetre.repaint();
        fenetre.setClicSupp(false);

    }

    /**
     * Annuler le mode suppression d un point d interet On repasse dans l etat
     * EtatTournee
     *
     * @param controleur
     * @param fenetre
     */
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre
    ) {
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }

}
