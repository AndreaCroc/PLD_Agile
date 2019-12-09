package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * CdeModif
 *
 * @version Version 1
 *
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class CdeModif implements CommandeTournee {

    private PointInteret ptI;
    private int decalage;

    public CdeModif(PointInteret ptI, int decalage) {
        this.ptI = ptI;
        this.decalage = decalage;
    }

    @Override
    public void doCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur) {
        boolean modifOk = carte.deplacerPointInteret(ptI, decalage);
        tournee = carte.getTournee();

        fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre, fenetre.getPanneauCarte().getZoom()));
        fenetre.setTournee(tournee);
        controleur.setTournee(tournee);
        fenetre.viderPanneauEtapes();
        fenetre.viderPanneauPIs();
        fenetre.afficherBoutonSupprimer();
        fenetre.griserBoutonCalcul();
        fenetre.repaint();

        fenetre.afficherEtapesTour(true);
        fenetre.afficherPanneauPI(true);

        //Si modification effectuee mais non respect contrainte
        if (!modifOk) {
            //Afficher popup pour prevenir modification ne respecte pas contrainte
            fenetre.afficherPopPrevenirModification();
        }

    }

    @Override
    public void undoCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur) {
        
        boolean modifOk = carte.deplacerPointInteret(ptI, decalage*(-1));
        tournee = carte.getTournee();

        fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre, fenetre.getPanneauCarte().getZoom()));
        fenetre.setTournee(tournee);
        controleur.setTournee(tournee);
        fenetre.viderPanneauEtapes();
        fenetre.viderPanneauPIs();
        fenetre.afficherBoutonSupprimer();
        fenetre.griserBoutonCalcul();
        fenetre.repaint();

        fenetre.afficherEtapesTour(true);
        fenetre.afficherPanneauPI(true);

        //Si modification effectuee mais non respect contrainte
        if (!modifOk) {
            //Afficher popup pour prevenir modification ne respecte pas contrainte
            fenetre.afficherPopPrevenirModification();
        }
        
    }

}
