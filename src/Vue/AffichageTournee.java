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

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }
    
    

    
    public void afficherTournee() {
        System.out.println("laaaaaa");
        ArrayList<PointInteret> successionPointsInteret = tournee.getSuccessionPointsInteret();
//        Intersection inter = new Intersection();
//        PointInteret p1 = new PointInteret(inter, 10);
//        PointInteret p2 = new PointInteret(inter, 25);
//        PointInteret p3 = new PointInteret(inter, 15);
//        PointInteret p4 = new PointInteret(inter, 10);
//        PointInteret p5 = new PointInteret(inter, 25);
//        PointInteret p6 = new PointInteret(inter, 15);
//        
//        Chemin chemin1 = new Chemin ();
//        Troncon troncon1 = new Troncon();
//        troncon1.setNomRue("Lyon");
//        chemin1.ajouterTroncon(troncon1);
//        p1.setEstEnlevement(false);
//        p1.setHeureArrivee(9);
//        p1.setCheminDepart(chemin1);
//        
//        Chemin chemin2 = new Chemin ();
//        Troncon troncon2 = new Troncon();
//        troncon2.setNomRue("Paris");
//        chemin2.ajouterTroncon(troncon2);
//        p2.setEstEnlevement(true);
//        p2.setHeureArrivee(10);
//        p2.setCheminDepart(chemin2);
//        
//        Chemin chemin3 = new Chemin ();
//        Troncon troncon3 = new Troncon();
//        troncon3.setNomRue("Sainte Maxime");
//        chemin3.ajouterTroncon(troncon3);
//        p3.setEstEnlevement(false);
//        p3.setHeureArrivee(11);
//        p3.setCheminDepart(chemin3);
//        
//        Chemin chemin4 = new Chemin ();
//        Troncon troncon4 = new Troncon();
//        troncon4.setNomRue("Sainte Maxime");
//        chemin4.ajouterTroncon(troncon4);
//        p4.setEstEnlevement(false);
//        p4.setHeureArrivee(11);
//        p4.setCheminDepart(chemin4);
//        
//        Chemin chemin5 = new Chemin ();
//        Troncon troncon5 = new Troncon();
//        troncon5.setNomRue("Sainte Maxime");
//        chemin5.ajouterTroncon(troncon5);
//        p5.setEstEnlevement(false);
//        p5.setHeureArrivee(11);
//        p5.setCheminDepart(chemin5);
//        
//        Chemin chemin6 = new Chemin ();
//        Troncon troncon6 = new Troncon();
//        troncon6.setNomRue("Sainte Maxime");
//        chemin6.ajouterTroncon(troncon6);
//        p6.setEstEnlevement(false);
//        p6.setHeureArrivee(11);
//        p6.setCheminDepart(chemin6);
//        
//        ArrayList<PointInteret> successionPointsInteret = new ArrayList();
//        successionPointsInteret.add(p1);
//        successionPointsInteret.add(p2);
//        successionPointsInteret.add(p3);
//        successionPointsInteret.add(p4);
//        successionPointsInteret.add(p5);
//        successionPointsInteret.add(p6);
        
        String nomRue = "";
        String heureArrivee = "";
        String type = "";
        int duree = 0;
        int dureeTotPrevue = 0;
        DateFormat format = new SimpleDateFormat("HH:mm");
        String heureDeb = "";
        heureDeb = format.format(new Date());
        heureDeb = heureDeb.replace(":", "h");
        String heureFin = "";
        
        if (successionPointsInteret != null && !successionPointsInteret.isEmpty()) {
            PointInteret pti = successionPointsInteret.get(successionPointsInteret.size() - 1);
            heureFin = pti.getHeureArrivee().toString();
            for (PointInteret pt : successionPointsInteret) {
                System.out.println("boucle for");
                int index = successionPointsInteret.indexOf(pt)+1;
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
