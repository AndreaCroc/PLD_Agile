/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;

/**
 *
 * @author DELL
 */
public class EtatAjouterPointAvantLivr implements Etat {
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
