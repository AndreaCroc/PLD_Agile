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


    public CdeSupprime(PointInteret pi, PointInteret pD, PointInteret pAvantEnlevement, PointInteret pAvantLivraison) {
        this.pi = pi;
        this.pD = pD;
        this.pAvantEnlevement = pAvantEnlevement;
        this.pAvantLivraison = pAvantLivraison;
    }
    
    

    @Override
    public void doCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur) {
        
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

    @Override
    public void undoCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur) {
        Double latitudeEnlvt;
        Double longitudeEnlvt;
        Double latitudeLivr;
        Double longitudeLivr;
        int dureeEnlevement;
        int dureeLivraison;
        
        if (pi.isEnlevement()) {
            latitudeEnlvt = pi.getIntersection().getLatitude();
            longitudeEnlvt = pi.getIntersection().getLongitude();
            latitudeLivr = pD.getIntersection().getLatitude();
            longitudeLivr = pD.getIntersection().getLongitude();
            dureeEnlevement = pi.getDuree();
            dureeLivraison = pD.getDuree();
        } else {
            latitudeEnlvt = pD.getIntersection().getLatitude();
            longitudeEnlvt = pD.getIntersection().getLongitude();
            latitudeLivr = pi.getIntersection().getLatitude();
            longitudeLivr = pi.getIntersection().getLongitude();
            dureeEnlevement = pD.getDuree();
            dureeLivraison = pi.getDuree();
        }
        
        
        
        
    }

}
