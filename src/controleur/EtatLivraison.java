/*
 * EtatLivraison
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
import modele.Tournee;

/**
 *
 * Classe EtatLivraison
 */
public class EtatLivraison implements Etat {

    /**
     * Calculer une tournee
     * 
     * @param controleur
     * @param fenetre
     * @param carte
     * @param tournee 
     */
    @Override
    public void calculerTournee(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee) {

        fenetre.viderPanneauEtapes();
        fenetre.afficherMessageErreur3("");
        tournee = carte.calculerTournee();
        fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
        fenetre.setTournee(tournee);
        controleur.setTournee(tournee);
        fenetre.repaint();
        fenetre.afficherOuCacherMessageTournee(false);
        fenetre.afficherEtapesTour();
        fenetre.griserBoutonCalcul();
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }

    /**
     * Charger un fichier de livraisons
     * 
     * @param controleur
     * @param fenetre
     * @param carte 
     */
    @Override
    public void chargerLivraison(Controleur controleur, Fenetre fenetre, Carte carte) {

        boolean chargerLivraison = false;
        String cheminFichier = fenetre.getInputChargeCarte();
        
        fenetre.afficherMessageErreur3("");
        try {
            //Choix du fichier XML
            chargerLivraison = carte.chargerLivraison();

            //Si le chargement des livraisons s est bien passe,
            // on affiche les livraisons et on reste dans le meme etat
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
            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur2("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            //fenetre.afficherMessageErreur2("Erreur lors de la sélection du fichier");
        }
    }

    /**
     * Changer la carte deja chargee
     * 
     * @param controleur
     * @param fenetre
     * @param carte 
     */
    @Override
    public void changerCarte(Controleur controleur, Fenetre fenetre, Carte carte) {

        boolean changerCarte = false;

        try {
            //Choix du fichier XML
            changerCarte = carte.chargerCarte(true);

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre et on affiche la carte et on change detat
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
                controleur.setEtat(controleur.etatDeBase);

            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur3("Erreur lors du chargement du fichier");
                carte.setDemandesLivraisons(null);
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                fenetre.cacherPanneauEtapesEtTour();
                fenetre.cacherPanneauPI();
                fenetre.griserBoutonCalcul();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.repaint();
                controleur.setEtat(controleur.etatDeBase);

            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            //fenetre.afficherMessageErreur3("Erreur lors de la sélection du fichier");
            /*
            carte.setDemandesLivraisons(null);
            fenetre.viderPanneauEtapes();
            fenetre.viderPanneauPIs();
            fenetre.cacherPanneauEtapesEtTour();
            fenetre.cacherPanneauPI();
            fenetre.griserBoutonCalcul();
            fenetre.setTournee(null);
            carte.setUneTournee(null);
            fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
            fenetre.repaint();
            controleur.setEtat(controleur.etatDeBase);*/
        }

    }

}
