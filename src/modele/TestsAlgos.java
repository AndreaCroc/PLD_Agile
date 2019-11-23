package modele;

import java.util.ArrayList;
import java.util.HashMap;

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

public class TestsAlgos {
     public static void testDijkstraPetitGraphe (){
        
        System.out.println("Test Dijkstra");
        Carte carte = new Carte();
        //Création des intersections
        Intersection a = new Intersection(1,0,0);
        Intersection b = new Intersection(2,0,0);
        Intersection c = new Intersection(3,0,0);
        Intersection d = new Intersection(4,0,0);
        Intersection e = new Intersection(5,0,0);
        Intersection f = new Intersection(6,0,0);
        Intersection g = new Intersection(7,0,0);
        Intersection h = new Intersection(8,0,0);
        
        
        //Ajout des troncons de départ aux differentes intersections
        a.ajouterTronconDepart(new Troncon("AB", 7, a, b));
        a.ajouterTronconDepart(new Troncon("AE", 14, a, e));
        b.ajouterTronconDepart(new Troncon("BC", 8, b, c));
        c.ajouterTronconDepart(new Troncon("CD", 6, c, d));
        d.ajouterTronconDepart(new Troncon("DA", 18, d, a));
        d.ajouterTronconDepart(new Troncon("DF", 11, d, f));
        e.ajouterTronconDepart(new Troncon("ED", 19, e, f));
        f.ajouterTronconDepart(new Troncon("FG", 4, f, g));
        f.ajouterTronconDepart(new Troncon("FH", 14, f, h));
        g.ajouterTronconDepart(new Troncon("GC", 5, g, c));
        g.ajouterTronconDepart(new Troncon("GH", 8, g, h));
        h.ajouterTronconDepart(new Troncon("HC", 9, h, c));
        
        //Ajout au plan
        carte.ajouterIntersection(a);
        carte.ajouterIntersection(b);
        carte.ajouterIntersection(c);
        carte.ajouterIntersection(d);
        carte.ajouterIntersection(e);
        carte.ajouterIntersection(f);
        carte.ajouterIntersection(g);
        carte.ajouterIntersection(h); 
        
        //Point de départ : A
        PointInteret A = new PointInteret(a,0);
        //Creation d'une demande de livraison
        DemandesLivraisons dL = new DemandesLivraisons(A);
        
        //Affectation de la demande à la carte
        carte.setDemandesLivraisons(dL);
        
        ArrayList<Intersection> listeIntersections = carte.getListeIntersections();
        Intersection entrepot = carte.getDemandesLivraisons().getAdresseDepart().getIntersection();
        
        carte.dijkstra(entrepot);
        
        //Affichage resultats
        for (int i = 0; i < listeIntersections.size(); i++) {
            System.out.println("point : " + listeIntersections.get(i).getId());
            System.out.println("distance : " + listeIntersections.get(i).getDistance());
            if (i != 0) {
                System.out.println("pred : " + listeIntersections.get(i).getPredecesseur().getId());
            }
            
        }

    }

    public static void main(String[] args) {
        testDijkstraPetitGraphe();
    }
}
