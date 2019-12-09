
package controleur;

/**
 * CommandeTournee
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
    public void doCde();
    
    /**
     * Realiser la commande inverse
     */
    public void undoCde();
}
