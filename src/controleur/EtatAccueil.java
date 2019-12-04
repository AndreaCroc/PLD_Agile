/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;

/**
 *
 * @author labou
 */
public class EtatAccueil implements Etat {
    /**
     *
     * @param controleur
     * @param fenetre
     */
    @Override
    public void test2(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherMessageErreur3("c");
        //controleur.setEtatCourant(controleur.etatCercle1);
    }
    
}
