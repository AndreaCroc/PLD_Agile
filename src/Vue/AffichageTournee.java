/*
 * AffichageTournee
 *
 * Version 1
 * 
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package Vue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import modele.PointInteret;
import modele.Tournee;

/**
 *
 * Classe AffichageTournee permettant d'afficher les donnes globales d'une
 * tournee
 */
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

    /**
     * Affichage des heures de debut, fin et duree de la tournee
     */
    public void afficherTournee() {
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
//        p1.setEnlevement(false);
//        p1.setHeureArrivee(9);
//        p1.setCheminDepart(chemin1);
//        
//        Chemin chemin2 = new Chemin ();
//        Troncon troncon2 = new Troncon();
//        troncon2.setNomRue("Paris");
//        chemin2.ajouterTroncon(troncon2);
//        p2.setEnlevement(true);
//        p2.setHeureArrivee(10);
//        p2.setCheminDepart(chemin2);
//        
//        Chemin chemin3 = new Chemin ();
//        Troncon troncon3 = new Troncon();
//        troncon3.setNomRue("Sainte Maxime");
//        chemin3.ajouterTroncon(troncon3);
//        p3.setEnlevement(false);
//        p3.setHeureArrivee(11);
//        p3.setCheminDepart(chemin3);
//        
//        Chemin chemin4 = new Chemin ();
//        Troncon troncon4 = new Troncon();
//        troncon4.setNomRue("Sainte Maxime");
//        chemin4.ajouterTroncon(troncon4);
//        p4.setEnlevement(false);
//        p4.setHeureArrivee(11);
//        p4.setCheminDepart(chemin4);
//        
//        Chemin chemin5 = new Chemin ();
//        Troncon troncon5 = new Troncon();
//        troncon5.setNomRue("Sainte Maxime");
//        chemin5.ajouterTroncon(troncon5);
//        p5.setEnlevement(false);
//        p5.setHeureArrivee(11);
//        p5.setCheminDepart(chemin5);
//        
//        Chemin chemin6 = new Chemin ();
//        Troncon troncon6 = new Troncon();
//        troncon6.setNomRue("Sainte Maxime");
//        chemin6.ajouterTroncon(troncon6);
//        p6.setEnlevement(false);
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

        int duree = 0;
        String dureeMin = "";
        String dureeTotPrevue = "";
        String heureDeb = "";
        String heureFin = "";
        int index = 0;

        //S assurer que la liste contient des points d'interet
        if (successionPointsInteret != null && !successionPointsInteret.isEmpty()) {
            DecimalFormat df = new DecimalFormat("0.00");
            dureeTotPrevue = tournee.getDuree();
            dureeTotPrevue = dureeTotPrevue.substring(0, dureeTotPrevue.lastIndexOf(":"));
            dureeTotPrevue = dureeTotPrevue.replace(":", "h");

            //Recuperer le point d'interet correspondant a l'entrepot
            heureDeb = successionPointsInteret.get(0).getHeureDepart();
            heureDeb = heureDeb.substring(0, heureDeb.lastIndexOf(":"));
            heureDeb = heureDeb.replace(":", "h");
            heureFin = successionPointsInteret.get(0).getHeureArrivee();
            heureFin = heureFin.substring(0, heureFin.lastIndexOf(":"));
            heureFin = heureFin.replace(":", "h");

            //Afficher le resume de la tournee
            fenetre.setPanneauTournee(heureDeb, heureFin, dureeTotPrevue);

        }

    }
}
