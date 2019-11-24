package tests;

import java.util.ArrayList;
import java.util.HashMap;
import modele.Carte;
import modele.Chemin;
import modele.DemandesLivraisons;
import modele.Intersection;
import modele.PointInteret;
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
        //Création des intersections
        Intersection a = new Intersection(1,0.0,0.0);
        Intersection b = new Intersection(2,0.0,0.0);
        Intersection c = new Intersection(3,0.0,0.0);
        Intersection d = new Intersection(4,0.0,0.0);
        Intersection e = new Intersection(5,0.0,0.0);
        Intersection f = new Intersection(6,0.0,0.0);
        Intersection g = new Intersection(7,0.0,0.0);
        Intersection h = new Intersection(8,0.0,0.0);
        
        
        //Ajout des troncons de départ aux differentes intersections
        a.ajouterTronconDepart(new Troncon("AB", 7.0, a, b));
        a.ajouterTronconDepart(new Troncon("AE", 14.0, a, e));
        b.ajouterTronconDepart(new Troncon("BC", 8.0, b, c));
        c.ajouterTronconDepart(new Troncon("CD", 6.0, c, d));
        d.ajouterTronconDepart(new Troncon("DA", 18.0, d, a));
        d.ajouterTronconDepart(new Troncon("DF", 11.0, d, f));
        e.ajouterTronconDepart(new Troncon("ED", 19.0, e, f));
        f.ajouterTronconDepart(new Troncon("FG", 4.0, f, g));
        f.ajouterTronconDepart(new Troncon("FH", 14.0, f, h));
        g.ajouterTronconDepart(new Troncon("GC", 5.0, g, c));
        g.ajouterTronconDepart(new Troncon("GH", 8.0, g, h));
        h.ajouterTronconDepart(new Troncon("HC", 9.0, h, c));
        
        //Ajout au plan
        carte.ajouterIntersection(a);
        carte.ajouterIntersection(b);
        carte.ajouterIntersection(c);
        carte.ajouterIntersection(d);
        carte.ajouterIntersection(e);
        carte.ajouterIntersection(f);
        carte.ajouterIntersection(g);
        carte.ajouterIntersection(h); 
        
        //Point de départ : C
        PointInteret C = new PointInteret(c,0);
        //Creation d'une demande de livraison
        DemandesLivraisons dL = new DemandesLivraisons(C);
        
        //Affectation de la demande à la carte
        carte.setDemandesLivraisons(dL);
        
        ArrayList<Intersection> listeIntersections = carte.getListeIntersections();
        Intersection entrepot = carte.getDemandesLivraisons().getAdresseDepart().getIntersection();
        
        //Calcul des plus courts chemins partant de l'entrepot
        carte.dijkstra(entrepot);
        
        //Affichage resultats
        for (int i = 0; i < listeIntersections.size(); i++) {
            System.out.println("point : " + listeIntersections.get(i).getId());
            System.out.println("distance : " + listeIntersections.get(i).getDistance());
            if (listeIntersections.get(i).getPredecesseur() != null) {
                System.out.println("pred : " + listeIntersections.get(i).getPredecesseur().getId());
            }
            
        }
        
        //Affichage plus court chemin de l'entrepot à h
        Chemin cheminCH = carte.plusCourtChemin(entrepot, h);
        for (Troncon t : cheminCH.getSuccessionTroncons()) {
            System.out.println(t.getOrigine().getId()+ " à "+t.getDestination().getId());
        }
        System.out.println("Longueur : "+ cheminCH.getLongueur());
        
        
        
        
        

    }

    public static void main(String[] args) {
        testDijkstraPetitGraphe();
    }
}
