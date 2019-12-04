/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.Tournee;

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
        
    public default void chargerPageInit(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee){};
    public default void chargerPageDeBase(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee){};
    public default void changerCarte(Controleur controleur, Fenetre fenetre, Carte carte){};
    public default void chargerLivraison(Controleur controleur,Fenetre fenetre, Carte carte){};
    public default void calculerTournee(Controleur controleur,Fenetre fenetre, Carte carte, Tournee tournee){};
    public default void supprimer(Controleur controleur,Fenetre fenetre, Carte carte, Tournee tournee, int index){};
    
}
