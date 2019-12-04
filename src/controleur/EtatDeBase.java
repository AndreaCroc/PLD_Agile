/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import modele.Carte;
import modele.Tournee;


public class EtatDeBase implements Etat {
    
    @Override
    public void changerCarte(Controleur controleur, Fenetre fenetre, Carte carte){
        
        boolean changerCarte = false;

        try {
            //Choix du fichier XML
            changerCarte = carte.chargerCarte(true);

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre et on affiche la carte
            // Dans tous les cas, on reste dans le meme etat
            if (changerCarte) {
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                fenetre.cacherPanneauEtapesEtTour();
                fenetre.cacherPanneauPI();
                fenetre.griserBoutonCalcul();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.repaint();
                fenetre.afficherConteneur2();
                fenetre.retireMessageErreur3();

            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur3("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            //fenetre.afficherMessageErreur3("Erreur lors de la sélection du fichier");
        }
        
    }
    
    @Override
    public void chargerLivraison(Controleur controleur, Fenetre fenetre, Carte carte){
        
        boolean chargerLivraison = false;

        try {
            //Choix du fichier XML
            chargerLivraison = carte.chargerLivraison();

            //Si le chargement des livraisons s est bien passe,
            // on affiche les livraisons et on change d etat
            if (chargerLivraison) {
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                fenetre.cacherPanneauEtapesEtTour();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.repaint();
                fenetre.afficherConteneur2();
                fenetre.afficherBoutonCalcul();
                fenetre.afficherPanneauPI();
                controleur.setEtat(controleur.etatLivraison);
            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur2("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            //fenetre.afficherMessageErreur2("Erreur lors de la sélection du fichier");
        }
    }
    

}
