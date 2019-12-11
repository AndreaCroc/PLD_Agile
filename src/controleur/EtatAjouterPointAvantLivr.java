
package controleur;

import Vue.Fenetre;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;

/**
 * EtatAjouterPointAvantLivr permettant d ajouter un point de la tournee avant 
 * le point de livraison choisi à integrer a la tournee
 * Code inspire de l application PlaCo
 *
 * @version Version 1
 *
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class EtatAjouterPointAvantLivr implements Etat {

    /**
     * Ajouter un point avant le nouveau point de livraison
     * @param controleur controleur
     * @param fenetre fenetre
     * @param carte carte
     * @param index indice du point precedent le nouveau point de livraison
     */
    @Override
    public void ajouterPointAvantLivraison(Controleur controleur, Fenetre fenetre, Carte carte, int index){
        System.out.println("Etat Ajout Pt Avant Livr");
     int value = JOptionPane.showConfirmDialog(fenetre, "Confirmer le choix du point ?");
        if (value == JOptionPane.YES_OPTION) {
            // Enregistrer le point precedent l enlevement dans la Fenetre
            fenetre.setAvantPLParIndex(index);
            controleur.setEtat(controleur.etatAjouter);
            controleur.ajouter();
        } 
    };
    
    /**
     * Annuler tous les ajouts deja effectues et retourner a l'etat tournee
     * @param controleur controleur
     * @param fenetre fenetre
     */
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }

    /**
     * Mettre en surbrillance une ligne du tableau d etapes de la tournee et du
     * tableau d informations generales sur un point d interet
     *
     * @param fenetre
     * @param ptI point d interet selectionne
     */
    @Override
    public void surbrillerTables(Fenetre fenetre, PointInteret ptI) {
        fenetre.surbrillerLigneTabPI(ptI);
        fenetre.surbrillerLigneTabEtapes(ptI);
        fenetre.repaint();
    }

    /**
     * Encadrer un point d interet present sur la carte
     *
     * @param fenetre
     * @param p point d interet selectionne
     */
    @Override
    public void surbrillerPI(Fenetre fenetre, PointInteret p) {
        fenetre.surbrillerPI(p);
        fenetre.repaint();
    }
}
