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
package controleur;

import Vue.Fenetre;

public class Controleur {

    private Etat etatCourant;
    private Fenetre fenetre;

    public Controleur() {
        //etatCourant = etatInit;
        fenetre = new Fenetre(this); //lui passer this
    }

    /**
     * Change l'etat courant du controleur
     *
     * @param etat le nouvel etat courant
     */
    protected void setEtatCourant(Etat etat) {
        etatCourant = etat;
    }

    /**
     * Charge une carte donnee et l'affiche dans la fnetre
     *
     * @param etat le nouvel etat courant
     */
    public void chargerCarte() {

        System.out.println("Je lance le chargement d'une carte");

    }

    /**
     * Change un livraison donnee
     *
     * @param etat le nouvel etat courant
     */
    public void chargerLivraison() {

        System.out.println("Je lance le chargement d'une livraison");

    }

    /**
     * Calcul une tournee et affiche les informations correspondantes
     * dans la fenetre
     *
     * @param etat le nouvel etat courant
     */
    public void calculerTournee() {

        System.out.println("Je lance le calcul d'une tournee");

    }

}
