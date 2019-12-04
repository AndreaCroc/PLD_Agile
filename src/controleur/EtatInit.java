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
public class EtatInit implements Etat {

    /**
     *
     * @param controleur
     * @param fenetre
     */
    @Override
    public void test1(Controleur controleur, Fenetre fenetre) {
        fenetre.retireMessageErreur3();
        //controleur.setEtatCourant(controleur.etatCercle1);
    }

}
