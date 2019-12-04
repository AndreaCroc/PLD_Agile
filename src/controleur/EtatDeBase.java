/*
 * EtatDeBase
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
import modele.Carte;

/**
 * Classe EtatDeBase
 * 
 */
public class EtatDeBase implements Etat {
    
    /**
     * Changer la carte deja charge
     * @param controleur
     * @param fenetre
     * @param carte 
     */
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
    
    /**
     * Charger une livraison lorsqu une carte est chargee
     * 
     * @param controleur
     * @param fenetre
     * @param carte 
     */
    @Override
    public void chargerLivraison(Controleur controleur, Fenetre fenetre, Carte carte){
        
        boolean chargerLivraison = false;
        String cheminFichier = fenetre.getInputChargeLiv();
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
                fenetre.afficherOuCacherMessageLivraison(false);
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
