package controleur;

import Vue.Fenetre;
import java.util.ArrayList;
import modele.Carte;
import modele.Tournee;

/**
 * ListeCdesTournee permet de savegarder les commandes faites Pris de l
 * application PlaCo
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class ListeCdesTournee {

    //Liste des commandes deja effectuees
    private ArrayList<CommandeTournee> listeCommandes;  
    private int indiceActuel;       //indice de la commande actuelle
    private Controleur controleur;  //Controleur
    private Carte carte;            //carte courante
    private Tournee tournee;        //Tournee courante
    private Fenetre fenetre;        //Fenetre courante

    /**
     * Constructeur de la classe ListeCdesTournee
     * @param carte carte courante
     * @param tournee tournee courante
     * @param fenetre fenetre courante
     * @param controleur controleur
     */
    public ListeCdesTournee(Carte carte, Tournee tournee, Fenetre fenetre, 
                            Controleur controleur) {
        listeCommandes = new ArrayList<CommandeTournee>();
        indiceActuel = -1;
        this.carte = carte;
        this.tournee = tournee;
        this.fenetre = fenetre;
        this.controleur = controleur;
    }

    /**
     * Ajout de la commande a la liste
     * Source : PlaCo
     * @param commande la commande a ajouter 
     * 
     */
    public void ajouterCommande(CommandeTournee commande) {
        int i = indiceActuel + 1;
        while (i < listeCommandes.size()) {
            listeCommandes.remove(i);
        }
        indiceActuel++;
        listeCommandes.add(indiceActuel, commande);
    }

    /**
     * Annule temporairement la derniere commande ajoutee 
     * Source : PlaCo
     */
    public void undo() {
        if (indiceActuel >= 0) {
            CommandeTournee cde = listeCommandes.get(indiceActuel);
            indiceActuel--;
            cde.undoCde(carte, tournee, fenetre, controleur);
        }
    }

    /**
     * Remet dans la liste la derniere commande annulee avec undo 
     * Source : PlaCo
     */
    public void redo() {
        if (indiceActuel < listeCommandes.size() - 1) {
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
