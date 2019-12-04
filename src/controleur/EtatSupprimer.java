/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;
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
        PointInteret ptI;
        System.out.println("index : "+index);
        if(index!=0){
            //Recuperer le point d interet que l utilisateur veut supprimer
            //ptI = tournee.getSuccessionPointsInteret().get(index);
            //Afficher un popup de confirmation de suppression
            fenetre.afficherPopSuppression();
        }else{
            //Afficher popup d erreur car pas possible de supprimer l entrepot
        }
        
        
    }

}
