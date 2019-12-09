
package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.Tournee;

/**
 * CommandeTournee regroupe les commandes possibles
 * Inspirer de l application PlaCo
 *
 * @version Version 1
 *
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public interface CommandeTournee {
    
    /**
     * Realiser la commande
     */
    public void doCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur);
    
    /**
     * Realiser la commande inverse
     */
    public void undoCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur);
}
