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

/**
 * 
 * Classe AffichageTournee permettant d'afficher les details d'une tournee
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
     * Affichage des etapes de la tournee
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
        String heureDepart = "";
        String type = "";
        String nomRueEntrepot = "";
        int duree = 0;
        String dureeTotPrevue;
        String heureDeb = "";
        String heureFin = "";
        int index = 0;

        //S'assurer que la liste contient des points d'interet
        if (successionPointsInteret != null && !successionPointsInteret.isEmpty()) {
            fenetre.viderPanneauEtapes();
            dureeTotPrevue = tournee.getDuree();
            for (PointInteret pt : successionPointsInteret) {
                //Recuperer le numero de l etape
                index = successionPointsInteret.indexOf(pt);
                Chemin c = pt.getCheminDepart();
                Troncon t = c.getSuccessionTroncons().get(0);
                //Recuperer l adresse
                nomRue = t.getNomRue();

                //Recuperer le point d'interet correspondant a l'entrepot
                if (index == 0) {
                    nomRueEntrepot = nomRue;
                    heureDeb = pt.getHeureDepart();
                    heureFin = pt.getHeureArrivee();
                    //Afficher le depart de l'entrepot
                    fenetre.setPanneauEtapesEntrepot(index,nomRueEntrepot,heureDeb,duree);
                } else {
                    if (pt.isEnlevement()) {
                        type = "Enlèvement";
                    } else {
                        type = "Livraison";
                    }
                    //Recuperer la duree de l etape
                    duree = pt.getDuree();

                    //Recuperer l heure d arrivee au point d interet
                    heureArrivee = pt.getHeureArrivee();
                    //Recuperer l heure de depart du point d interet
                    heureDepart = pt.getHeureDepart();
                    System.out.println("nomRue : " + nomRue + "type : " + type + "heure Arrivee : " + heureArrivee + "duree : " + duree);
                    //Afficher les etapes dans la fenetre
                    fenetre.setPanneauEtapes(index, type, nomRue, heureDepart, heureArrivee, duree);
                }
            }
            //Afficher le retour a l'entrepot
            fenetre.setPanneauEtapesEntrepot(index+1,nomRueEntrepot,heureFin,duree);
            //Afficher le resume de la tournee
            fenetre.setPanneauTournee(heureDeb, heureFin, dureeTotPrevue);

        }

    }
}
