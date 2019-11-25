/*
 * AffichageTournee
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package Vue;

import java.util.ArrayList;
import modele.Chemin;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;

public class AffichageTournee {

    private Tournee tournee;
    private Fenetre fenetre;

    public AffichageTournee(Tournee tournee, Fenetre f) {
        this.tournee = tournee;
        this.fenetre = f;
    }

    public void afficherTournee() {
        System.out.println("AfficherTournee dan sAffichageTournee");
        ArrayList<PointInteret> successionPointsInteret = tournee.getSuccessionPointsInteret();
        String nomRue = "";
        String heureArrivee = "";
        String type = "";
        String duree = "";

        if (successionPointsInteret != null) {
            for (PointInteret pt : successionPointsInteret) {
                Chemin c = pt.getCheminDepart();
                Troncon t = c.getSuccessionTroncons().get(0);
                nomRue = t.getNomRue();

                if (pt.isEstEnlevement()) {
                    type = "Enlèvement";
                } else {
                    type = "Livraison";
                }

                duree = pt.getDuree().toString();
                heureArrivee = pt.getHeureArrivee().toString();

                System.out.println("nomRue : " + nomRue + "type : " + type + "heure Arrivee : " + heureArrivee + "duree : " + duree);
            }
        }

    }
}
