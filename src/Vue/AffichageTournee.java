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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modele.Chemin;
import modele.Intersection;
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
        System.out.println("AfficherTournee dans AffichageTournee");
        ArrayList<PointInteret> successionPointsInteret = tournee.getSuccessionPointsInteret();
        Intersection inter = new Intersection();
        PointInteret p1 = new PointInteret(inter, 10);
        PointInteret p2 = new PointInteret(inter, 25);
        PointInteret p3 = new PointInteret(inter, 15);
        
        //ArrayList<PointInteret> successionPointsInteret;
        
        String nomRue = "";
        String heureArrivee = "";
        String type = "";
        int duree = 0;
        int dureeTotPrevue = 0;
        DateFormat format = new SimpleDateFormat("HH:mm");
        String heureDeb = "";
        heureDeb = format.format(new Date());
        String heureFin = "";
        
        if (successionPointsInteret != null && !successionPointsInteret.isEmpty()) {
            PointInteret pti = successionPointsInteret.get(successionPointsInteret.size() - 1);
            heureFin = pti.getHeureArrivee().toString();
            for (PointInteret pt : successionPointsInteret) {

                int index = successionPointsInteret.indexOf(pt);
                Chemin c = pt.getCheminDepart();
                Troncon t = c.getSuccessionTroncons().get(0);
                nomRue = t.getNomRue();

                if (pt.isEstEnlevement()) {
                    type = "Enlèvement";
                } else {
                    type = "Livraison";
                }
                
                duree = pt.getDuree();
                dureeTotPrevue += duree;
                
                heureArrivee = pt.getHeureArrivee().toString();

                System.out.println("nomRue : " + nomRue + "type : " + type + "heure Arrivee : " + heureArrivee + "duree : " + duree);
                fenetre.setPanneauEtapes(index,type,nomRue,heureArrivee, duree);
                
            }
            fenetre.setPanneauTournee(heureDeb,heureFin,dureeTotPrevue);
        }

    }
}
