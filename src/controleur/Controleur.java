package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import modele.Carte;
import modele.Intersection;

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

    public Controleur() {
        //etatCourant = etatInit;
        fenetre = new Fenetre(this); //lui passer this
        carte = new Carte();
    }

    /**
     * Change l'etat courant du controleur
     *
     * le nouvel etat courant
     */
    public Carte getCarte() {
        return this.carte;
    }

    public void setCarte(Carte nCarte) {
        this.carte = nCarte;
    }

    public void chargerCarte() {
        //Appeler methode affichage carte + ...
        boolean chargerCarte = true;

        try {

            carte.chargerCarte();
            if (chargerCarte) {
                fenetre.setPanneauCarte(new JCarte(this));
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
                fenetre.setPanneauCarte(new JCarte(this));
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

        boolean calculTournee = true;
        fenetre.afficherEtapesTour(calculTournee);
        System.out.println("Je lance le calcul d'une tournee");

    }

}
