
package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.Tournee;

/**
 * CommandeTournee regroupe les commandes possibles
 * Inspire de l application PlaCo
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public interface CommandeTournee {
    
    /**
     * Realiser la commande
     * @param carte carte courante
     * @param tournee tournee courante
     * @param fenetre fenetre courante
     * @param controleur controleur courant
     */
    public void doCde(Carte carte, Tournee tournee, Fenetre fenetre, 
                      Controleur controleur);
    
    /**
     * Realiser la commande inverse
     * @param carte carte courante
     * @param tournee tournee courantet
     * @param fenetre fenetre courante
     * @param controleur controleur courant
     */
    public void undoCde(Carte carte, Tournee tournee, Fenetre fenetre, 
                        Controleur controleur);
}
