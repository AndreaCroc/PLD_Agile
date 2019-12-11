package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * CdeSupprime
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class CdeSupprime implements CommandeTournee {

    private PointInteret pi;
    private PointInteret pD;
    private PointInteret pAvantEnlevement;
    private PointInteret pAvantLivraison;
    private int index;


    public CdeSupprime(PointInteret pi, PointInteret pD, 
                       PointInteret pAvantEnlevement,
                       PointInteret pAvantLivraison, int index) {
        this.pi = pi;
        this.pD = pD;
        this.pAvantEnlevement = pAvantEnlevement;
        this.pAvantLivraison = pAvantLivraison;
        this.index = index;
    }
    
    
    /**
     * Revient à supprimer le point
     * @param carte la carte courante
     * @param tournee la tournée courante
     * @param fenetre la fenêtre courante
     * @param controleur le controleur courant
     */
    @Override
    public void doCde(Carte carte, Tournee tournee, Fenetre fenetre, 
                      Controleur controleur) {
        
        //On supprime le point
        boolean suppOk = carte.supprimerPointInteret(pi);
        
        //On met a jour la fenetre
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
        fenetre.griserBoutonCalcul();
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

    /**
     * Revient à ajouter le point
     * @param carte la carte courante
     * @param tournee la tournée courante
     * @param fenetre la fenêtre courante
     * @param controleur le contrôleur courant
     */
    @Override
    public void undoCde(Carte carte, Tournee tournee, Fenetre fenetre, 
                        Controleur controleur) {
        PointInteret pointEnlevement;
        PointInteret pointLivraison;
        int dureeEnlevement;
        int dureeLivraison;
        
        if (pi.isEnlevement()) {
            carte.ajouterLivraison(pi, pD, pAvantEnlevement, pAvantLivraison);
//            dureeEnlevement = pi.getDuree();
//            dureeLivraison = pD.getDuree();
//            pointEnlevement = new PointInteret(pi.getIntersection(), dureeEnlevement);
//            pointLivraison = new PointInteret(pD.getIntersection(), dureeLivraison);
            
        } else {
            carte.ajouterLivraison(pD, pi, pAvantEnlevement, pAvantLivraison);
//            dureeEnlevement = pD.getDuree();
//            dureeLivraison = pi.getDuree();
//            pointEnlevement = new PointInteret(pD.getIntersection(), dureeEnlevement);
//            pointLivraison = new PointInteret(pi.getIntersection(), dureeLivraison);
        }
//        pointEnlevement.setEnlevement(true);
//        pointEnlevement.setEntrepot(false);
//        pointEnlevement.setNumeroDemande(pi.getNumeroDemande());
//        pointLivraison.setEnlevement(false);
//        pointLivraison.setEntrepot(false);
//        pointLivraison.setNumeroDemande(pD.getNumeroDemande());
//        
//        carte.ajouterLivraison(pointEnlevement, pointLivraison, pAvantEnlevement, pAvantLivraison);
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

}
