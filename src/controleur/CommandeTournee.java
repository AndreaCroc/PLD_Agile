
package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.Tournee;

/**
 * CommandeTournee regroupe les commandes possibles
 * Inspirer de l application PlaCo
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public interface CommandeTournee {
    
    /**
     * Realiser la commande
     * @param carte
     * @param tournee
     * @param fenetre
     * @param controleur
     */
    public void doCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur);
    
    /**
     * Realiser la commande inverse
     * @param carte
     * @param tournee
     * @param fenetre
     * @param controleur
     */
    public void undoCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur);
}
