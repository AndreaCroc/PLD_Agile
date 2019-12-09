
package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * Etat - Interface Etat permettant de gerer les differents etats de 
 * lapplication : Code inspire de l application PlaCo
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
    public default void modifier(Controleur controleur, Fenetre fenetre,Tournee tournee, Carte carte, int index, ListeCdesTournee listeCommandes){};
    public default void supprimer(Controleur controleur,Fenetre fenetre, Carte carte, Tournee tournee, int index, ListeCdesTournee listeCommandes){};
    public default void annuler (Controleur controleur,Fenetre fenetre){};
    public default void ajouter(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee){};
    public default void surbrillerTables(Fenetre fenetre, PointInteret ptI){};
    public default void surbrillerPI(Fenetre fenetre, PointInteret p) {};
    public default void undo(ListeCdesTournee liste){};
    public default void redo(ListeCdesTournee liste){};
    
}
