package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * EtatAjouter permettant d ajouter un point dinteret a la tournee Code inspire
 * de l application PlaCo
 *
 * @version Version 1
 *
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatAjouter implements Etat {

    @Override
    public void ajouter(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee) {
        System.out.println("Etat ajouter");
        if (carte.ajouterLivraison(fenetre.getPE(), fenetre.getPL(), fenetre.getAvantPE(), fenetre.getAvantPL())) {
            tournee = carte.getTournee();
            fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
            fenetre.setTournee(tournee);
            controleur.setTournee(tournee);
            fenetre.viderPanneauEtapes();
            fenetre.viderPanneauPIs();
            fenetre.afficherEtapesTour(true);
            fenetre.afficherPanneauPI(true);

        } else {
            JOptionPane.showMessageDialog(fenetre, " La contrainte de "
                    + "précédence n'est pas respectée.\n Les points n'ont "
                    + "pas été ajoutés à la tournée.");
        }
        fenetre.repaint();
        fenetre.afficherBoutonSupprimer();
        fenetre.griserBoutonCalcul();
        controleur.setEtat(controleur.etatTournee);
    }

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
