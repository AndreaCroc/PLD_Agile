package controleur;

import Vue.Fenetre;
import modele.PointInteret;

/**
 * EtatModifier
 *
 * Version 1
 *
 *
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne MAGNIEN,
 * Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatModifier implements Etat {

    /**
     * Modifier l ordre de passage d un point d interet dans la tournee
     * 
     * @param controleur
     * @param fenetre
     * @param index
     */
    @Override
    public void modifier(Controleur controleur, Fenetre fenetre, int index) {
        fenetre.afficherBoutonSupprimer();
        fenetre.griserBoutonCalcul();
        controleur.setEtat(controleur.etatTournee);
        fenetre.setClicModif(false);
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

    /**
     * Annuler le mode modification d un point d interet On repasse dans l etat
     * EtatTournee
     *
     * @param controleur
     * @param fenetre
     */
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherBoutonSupprimer();
        fenetre.griserBoutonCalcul();
        controleur.setEtat(controleur.etatTournee);
    }
}
