package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import modele.Carte;
import modele.Tournee;

/*
 * Controleur
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Controleur {

    private Fenetre fenetre;
    private Carte carte;
    private Tournee tournee;

    public Controleur() {
        carte = new Carte();
        tournee = new Tournee();
        fenetre = new Fenetre(this, carte, tournee); //lui passer this
        
    }

    /**
     * Change l'etat courant du controleur
     *
     * le nouvel etat courant
     */

    public void chargerCarte() {
        //Appeler methode affichage carte + ...
        boolean chargerCarte = true;

        try {

            carte.chargerCarte();
            if (chargerCarte) {
                fenetre.setPanneauCarte(new JCarte(this, carte));
                fenetre.repaint();
                fenetre.afficherConteneur2();
            }else{
                fenetre.afficherMessageErreur1("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            fenetre.afficherMessageErreur1("Erreur lors de la sélection du fichier");
        }

        System.out.println("Je lance le chargement d'une carte");

    }

    public void chargerLivraison() {

        boolean chargerLivraison = true;

        try {

            carte.chargerLivraison();
            if (chargerLivraison) {
                fenetre.setPanneauCarte(new JCarte(this, carte));
                fenetre.repaint();
                fenetre.afficherConteneur2();
                fenetre.afficherBoutonCalcul();
            }else{
                fenetre.afficherMessageErreur2("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            fenetre.afficherMessageErreur2("Erreur lors de la sélection du fichier");
        }    

        System.out.println("Je lance le chargement d'une livraison");

    }

    public void calculerTournee() {

        //Appeler methode calculerTournee de Tournee : tournee.calculerTourner();
        fenetre.afficherEtapesTour();
        System.out.println("Je lance le calcul d'une tournee");

    }

}
