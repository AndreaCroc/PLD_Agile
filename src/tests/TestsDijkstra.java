package tests;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;
import modele.Carte;
import modele.Chemin;
import modele.DemandesLivraisons;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;

/*
 * Carte
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class TestsDijkstra {
     public static void testDijkstraPetitGraphe (){
        
          System.out.println("Test Dijkstra");
          Carte carte = new Carte();
          //Chargement de la carte
//        Intersection a = new Intersection("a",0.0,0.0);
//        Intersection b = new Intersection("b",0.0,0.0);
//        Intersection c = new Intersection("c",0.0,0.0);
//        Intersection d = new Intersection("d",0.0,0.0);
//        Intersection e = new Intersection("e",0.0,0.0);
//        Intersection f = new Intersection("f",0.0,0.0);
//        Intersection g = new Intersection("g",0.0,0.0);
//        Intersection h = new Intersection("h",0.0,0.0);
//        
//        
//        //Ajout des troncons de départ aux differentes intersections
//        a.ajouterTronconDepart(new Troncon("AB", 7.0, a, b));
//        a.ajouterTronconDepart(new Troncon("AE", 14.0, a, e));
//        b.ajouterTronconDepart(new Troncon("BC", 8.0, b, c));
//        c.ajouterTronconDepart(new Troncon("CD", 6.0, c, d));
//        d.ajouterTronconDepart(new Troncon("DA", 18.0, d, a));
//        d.ajouterTronconDepart(new Troncon("DF", 11.0, d, f));
//        e.ajouterTronconDepart(new Troncon("ED", 19.0, e, f));
//        f.ajouterTronconDepart(new Troncon("FG", 4.0, f, g));
//        f.ajouterTronconDepart(new Troncon("FH", 14.0, f, h));
//        g.ajouterTronconDepart(new Troncon("GC", 5.0, g, c));
//        g.ajouterTronconDepart(new Troncon("GH", 8.0, g, h));
//        h.ajouterTronconDepart(new Troncon("HC", 9.0, h, c));
//        
//        //Ajout au plan
//        carte.ajouterIntersection(a);
//        carte.ajouterIntersection(b);
//        carte.ajouterIntersection(c);
//        carte.ajouterIntersection(d);
//        carte.ajouterIntersection(e);
//        carte.ajouterIntersection(f);
//        carte.ajouterIntersection(g);
//        carte.ajouterIntersection(h); 
//        
//        //Point de départ : A
//        PointInteret A = new PointInteret(a,0);
//        PointInteret B = new PointInteret(b,0);
//        PointInteret D = new PointInteret(d,0);
//        PointInteret C = new PointInteret(c,0);
//        PointInteret E = new PointInteret(e,0);
//        PointInteret F = new PointInteret(f,0);
//        PointInteret G = new PointInteret(g,0);
//        PointInteret H = new PointInteret(h,0);
//        
//        
//        //Creation d'une demande de livraison
//        DemandesLivraisons dL = new DemandesLivraisons(A);
//        dL.setAdresseDepart(A);
//        dL.ajouterPointInteret(A);
//        dL.ajouterPointInteret(B);
//        dL.ajouterPointInteret(C);
//        dL.ajouterPointInteret(D);
//        dL.ajouterPointInteret(E);
//        dL.ajouterPointInteret(F);
//        dL.ajouterPointInteret(G);
//        dL.ajouterPointInteret(H);
//        //Affectation de la demande à la carte
//        carte.setDemandesLivraisons(dL);
//        
//        ArrayList<Intersection> listeIntersections = carte.getListeIntersections();
//        Intersection entrepot = carte.getDemandesLivraisons().getAdresseDepart().getIntersection();
//        
        //Calcul des plus courts chemins partant de l'entrepot
//        carte.dijkstra(a);
//        
//        //Affichage resultats
//        for (int i = 0; i < listeIntersections.size(); i++) {
//            System.out.println("point : " + listeIntersections.get(i).getId());
//            System.out.println("distance : " + listeIntersections.get(i).getDistance());
//            if (listeIntersections.get(i).getPredecesseur() != null) {
//                System.out.println("pred : " + listeIntersections.get(i).getPredecesseur().getId());
//            }
//            
//        }
//        
//        //Affichage plus court chemin de l'entrepot à 
//        Chemin cheminAH = carte.plusCourtChemin(a, h);
//        System.out.println(cheminAH);
        
//        //Affichage du graphe de plus court chemins
//        int nbSommets = carte.getDemandesLivraisons().getListePointsInteret().size();
//        Pair coutEtChemins = carte.creerGraphePCC();
//        Double[][] cout = (Double[][]) coutEtChemins.getKey();
//        Chemin[][] chemins = (Chemin[][]) coutEtChemins.getValue();
//        
//        for (int i=0; i<nbSommets; i++) {
//            for (int j=0;j<nbSommets;j++) {
//                    System.out.println("i = "+i+" j = "+j);
//                    System.out.println("cout (i,j) "+ cout[i][j]);
//                    System.out.println("chemin (i,j) " + chemins[i][j]);
//                   
//            }
//        }

          //Affichage tournée
//          Tournee tournee = carte.calculerTournee();
//          System.out.println(tournee);
//          ArrayList<PointInteret> listePoints = tournee.getSuccessionPointsInteret();
//          for (PointInteret pI : listePoints) {
//              System.out.println(pI.getCheminDepart());
//          }
//        
        
        

    }

    public static void main(String[] args) {
        testDijkstraPetitGraphe();
    }
}
