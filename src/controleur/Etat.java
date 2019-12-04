/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;

/*
 * Etat
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public interface Etat {
    
    public default void test1(Controleur controleur, Fenetre fenetre){};
    public default void test2(Controleur controleur, Fenetre fenetre){};

    
}
