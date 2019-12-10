
package controleur;

import Vue.Fenetre;
import java.util.ArrayList;
import modele.Carte;
import modele.Tournee;

/**
 * ListeCdesTournee permet de savegarder les commandes faites
 * Pris de l application PlaCo
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class ListeCdesTournee {
    
    private ArrayList<CommandeTournee> listeCommandes;
    private int indiceActuel;
    private Controleur controleur;
    private Carte carte;
    private Tournee tournee;
    private Fenetre fenetre;

    public ListeCdesTournee(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur) {
        listeCommandes = new ArrayList();
        indiceActuel = -1;
        this.carte = carte;
        this.tournee = tournee;
        this.fenetre = fenetre;
        this.controleur = controleur;
    }
    
    /**
    * Ajout de la commande a la liste
    * @param commande la commande a ajouter
    * Source : PlaCo
    */
    public void ajouterCommande(CommandeTournee commande){
        int i = indiceActuel+1;
        while(i<listeCommandes.size()){
            listeCommandes.remove(i);
        }
        indiceActuel++;
        listeCommandes.add(indiceActuel, commande);
        //commande.doCde(carte, tournee, fenetre, controleur);
    }
    
    /**
    * Annule temporairement la derniere commande ajoutee 
    * Source : PlaCo
    */
    public void undo(){
        if (indiceActuel >= 0){
                CommandeTournee cde = listeCommandes.get(indiceActuel);
                indiceActuel--;
                cde.undoCde(carte, tournee, fenetre, controleur);
        }
    }
    
    /**
    * Remet dans la liste la derniere commande annulee avec undo
    * Source : PlaCo
    */
    public void redo(){
        if (indiceActuel < listeCommandes.size()-1){
                indiceActuel++;
                CommandeTournee cde = listeCommandes.get(indiceActuel);
                cde.doCde(carte, tournee, fenetre, controleur);
        }
    }
    
    /**
     * Annule les commandes faites
     */
    public void annulerAnciennesCommandes() {
        listeCommandes.clear();
        indiceActuel = -1;
    }
}
