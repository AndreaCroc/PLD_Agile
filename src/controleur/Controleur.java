package controleur;

import Vue.Fenetre;
import Vue.JCarte;

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

    private Etat etatCourant;
    private Fenetre fenetre;

    public Controleur() {
        //etatCourant = etatInit;
        fenetre = new Fenetre(this,false); //lui passer this
    }

    /**
     * Change l'etat courant du controleur
     *
     * @param etat le nouvel etat courant
     */
    protected void setEtatCourant(Etat etat) {
        etatCourant = etat;
    }

    public void chargerCarte() {
        //Appeler methode affichage carte + ...
        boolean chargerCarte = true;
        fenetre.afficherConteneur2(chargerCarte);
        System.out.println("Je lance le chargement d'une carte");
        
    }

    public void chargerLivraison() {

        boolean chargerLivraison = true;
        
        //JCarte nouvelleCarte=new JCarte(fenetre.initTestCarte(),fenetre.initTestLivraisons());
        fenetre.dispose();
        fenetre=new Fenetre(this, true);
        chargerCarte();

        fenetre.afficherBoutonCalcul(chargerLivraison);
        
        System.out.println("Je lance le chargement d'une livraison");

    }

    public void calculerTournee() {

        boolean calculTournee = true;
        fenetre.afficherEtapesTour(calculTournee);
        System.out.println("Je lance le calcul d'une tournee");

    }

}