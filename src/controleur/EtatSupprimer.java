/*
 * EtatSupprimer
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 *
 * Classe EtatSupprimer quand on veut supprimer un point d interet
 */
public class EtatSupprimer implements Etat {

    /**
     * Supprimer un point d interet de la tournee et son point correspondant
     * Dans tous les cas, on repasse a l etat EtatTournee
     *
     * @param controleur
     * @param fenetre
     * @param carte
     * @param tournee
     * @param index
     */
    @Override
    public void supprimer(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee, int index) {
        PointInteret ptI = new PointInteret();
        System.out.println("index : " + index);
        int option = 1;
        boolean suppOk = false;

        if (index != 0) {
            //Recuperer le point d interet que l utilisateur veut supprimer
            //ptI = tournee.getSuccessionPointsInteret().get(index);
            System.out.println("index supprimer : " + index);
            System.out.println("supprimer : " + carte.getListePointsInteretActuelle());
            ptI = carte.getListePointsInteretActuelle().get(index);
            //Afficher un popup de confirmation de suppression
            option = fenetre.afficherPopSuppression(ptI);
            if (option == JOptionPane.OK_OPTION) {
                System.out.println("Point d'interet supprime");

                suppOk = carte.supprimerPointInteret(ptI);
                tournee = carte.getTournee();

                fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
                fenetre.setTournee(tournee);
                controleur.setTournee(tournee);
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                if (suppOk) {
                    System.out.println("if soopok");
                    fenetre.afficherEtapesTour(suppOk);
                    fenetre.afficherPanneauPI(suppOk);
                    fenetre.afficherBoutonSupprimer();
                } else {
                    System.out.println("else suppOk");
                    fenetre.cacherPanneauEtapesEtTour();
                    fenetre.cacherPanneauPI();
                    /*fenetre.cacherTableEtapes();
                    fenetre.cacherTablePI();
                    fenetre.afficherOuCacherMessageLivraison(true);
                    fenetre.afficherOuCacherMessageTournee(true);*/
                }

                fenetre.repaint();

            }
        } else {
            //Afficher popup d erreur car pas possible de supprimer l entrepot
            fenetre.afficherPopSuppressionErreur();
            fenetre.afficherBoutonSupprimer();
        }
        
        controleur.setEtat(controleur.etatTournee);
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
    public void annuler(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }

}
