/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 *
 * @author acer
 */
public class EtatSupprimer implements Etat {

    @Override
    public void supprimer(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee, int index) {
        PointInteret ptI = new PointInteret();
        System.out.println("index : " + index);
        int option = 1;
        if (index != 0) {
            //Recuperer le point d interet que l utilisateur veut supprimer
            ptI = tournee.getSuccessionPointsInteret().get(index);
            //Afficher un popup de confirmation de suppression
            option = fenetre.afficherPopSuppression(ptI);
            if (option == JOptionPane.OK_OPTION) {
                System.out.println("Point d'interet supprime");

                //carte.supprimerPointInteret(ptI);
            }
        } else {
            //Afficher popup d erreur car pas possible de supprimer l entrepot
            fenetre.afficherPopSuppressionErreur();
        }
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
        fenetre.setClicSupp(false);

    }

    @Override
    public void annuler(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }

}
