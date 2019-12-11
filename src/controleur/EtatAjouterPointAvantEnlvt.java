package controleur;

import Vue.Fenetre;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;

/**
 * EtatAjouterPointAvantEnlvt permettant d ajouter un point de la tournee avant
 * le nouveau point d'enlevement
 * Code inspire de l application PlaCo
 *
 * @version Version 1
 *
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatAjouterPointAvantEnlvt implements Etat {

    /**
     * Ajouter un point d'enlevement a la tournee
     * @param controleur controleur
     * @param fenetre fenetre
     * @param carte carte
     * @param index  indice du point precedent le nouveau point d'enlevement
     */
    @Override
    public void ajouterPointAvantEnlevement(Controleur controleur, Fenetre fenetre, Carte carte, int index) {
        System.out.println("Etat Ajout point avant enlevement");
        int value = JOptionPane.showConfirmDialog(fenetre, "Confirmer le choix du point ?");
        if (value == JOptionPane.YES_OPTION) {
            // Enregistrer le point precedent l enlevement dans la Fenetre
            fenetre.setAvantPEParIndex(index);
            controleur.setEtat(controleur.etatAjouterPtLivraison);
            JOptionPane.showMessageDialog(fenetre, "Merci de choisir un emplacement pour la livraison");
        } 

    }

    /**
     * Annuler l'ajout du point d'enlevement et revenir a l'etatTournee
     * @param controleur
     * @param fenetre 
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
