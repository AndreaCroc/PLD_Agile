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
package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.Tournee;

/**
 * Interface Etat permettant de gerer les differents etats de lapplication
 * 
 */
public interface Etat {
        
    public default void chargerPageInit(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee){};
    public default void chargerPageDeBase(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee){};
    public default void changerCarte(Controleur controleur, Fenetre fenetre, Carte carte){};
    public default void chargerLivraison(Controleur controleur,Fenetre fenetre, Carte carte){};
    public default void calculerTournee(Controleur controleur,Fenetre fenetre, Carte carte, Tournee tournee){};
    public default void modifier(Controleur controleur, int bouton, int index){};
    public default void supprimer(Controleur controleur,Fenetre fenetre, Carte carte, Tournee tournee, int index){};
    public default void annuler (Controleur controleur,Fenetre fenetre){};
    public default void ajouter(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee){};
    
}
