package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * CdeAjout
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class CdeAjout implements CommandeTournee {

    private PointInteret pointEnlevement;
    private PointInteret pointLivraison;
    private PointInteret pointAvantLivr; // point d'intérêt après lequel on 
    // souhaite placer le point de livraison
    private PointInteret pointAvantEnlvt; // point d'intérêt après lequel 
    //on souhaite placer le point d'enlèvement

    public CdeAjout(PointInteret pointEnlevement, PointInteret pointLivraison, 
                    PointInteret pointAvantLivr, PointInteret pointAvantEnlvt) {
        this.pointEnlevement = pointEnlevement;
        this.pointLivraison = pointLivraison;
        this.pointAvantLivr = pointAvantLivr;
        this.pointAvantEnlvt = pointAvantEnlvt;
    }

    /**
     * Revient à ajouter le point
     * @param carte est la carte courante
     * @param tournee est la tourneée courante
     * @param fenetre est la fenêtre courante
     * @param controleur est le controleur courant
     */
    @Override
    public void doCde(Carte carte, Tournee tournee, Fenetre fenetre, 
                      Controleur controleur) {
        carte.ajouterLivraison(pointEnlevement, pointLivraison, 
                               pointAvantLivr, pointAvantEnlvt);
        tournee = carte.getTournee();
        fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
        fenetre.setTournee(tournee);
        controleur.setTournee(tournee);
        fenetre.viderPanneauEtapes();
        fenetre.viderPanneauPIs();
        fenetre.afficherBoutonSupprimer();
        fenetre.repaint();
        fenetre.afficherEtapesTour(true);
        fenetre.afficherPanneauPI(true);
        fenetre.repaint();
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }

    /**
     * Le undo revient a supprimer le point ajouter
     * @param carte est la carte courante
     * @param tournee est la tournée courante
     * @param fenetre est la fenêtre courante
     * @param controleur est le controleur courant
     */
    @Override
    public void undoCde(Carte carte, Tournee tournee, Fenetre fenetre, 
                        Controleur controleur) {
        boolean suppOk = carte.supprimerPointInteret(pointEnlevement);
        tournee = carte.getTournee();
        ArrayList<PointInteret> listePIs = carte.getListePointsInteretActuelle();

        if (listePIs.size() == 1 && suppOk) {
            tournee = null;
            carte.setUneTournee(null);
        }
        fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
        fenetre.setTournee(tournee);
        controleur.setTournee(tournee);
        fenetre.viderPanneauEtapes();
        fenetre.viderPanneauPIs();
        fenetre.afficherBoutonSupprimer();
        fenetre.repaint();

        //Plus que l entrepot dans la liste des points d interets de la carte
        if (listePIs.size() == 1 && suppOk) {
            fenetre.cacherPanneauEtapesEtTour();
            fenetre.cacherPanneauPI();
            //Afficher popup plus d elements liste
            fenetre.afficherPopSuppressionVide();
            controleur.setEtat(controleur.etatDeBase);
        } else {
            fenetre.afficherEtapesTour(true);
            fenetre.afficherPanneauPI(true);
            controleur.setEtat(controleur.etatTournee);
            //Si suppression pas effectuee a cause d une erreur
            if (!suppOk) {
                //Afficher popup suppression annulee
                fenetre.afficherPopSuppressionAnnulee();
            }

        }
    }

}
