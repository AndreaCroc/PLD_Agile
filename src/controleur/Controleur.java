/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;

/**
 *
 * @author slabouchei
 */
public class Controleur {

    private Etat etatCourant;
    private Fenetre fenetre;

    public Controleur() {
        //etatCourant = etatInit;
        fenetre = new Fenetre(); //lui passer this
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

        System.out.println("Je lance le chargement d'une carte");

    }

    public void chargerLivraison() {

        System.out.println("Je lance le chargement d'une livraison");

    }

    public void calculerTournee() {

        System.out.println("Je lance le calcul d'une tournee");

    }

}
