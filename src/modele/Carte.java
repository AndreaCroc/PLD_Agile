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
public class Carte {
     
    private ArrayList<Intersection> listeIntersections;
    private DemandesLivraisons demandesLivraisons;

    public static final int INFINI = 1000;//Valeur max INT
    public static final int NON_DEFINI = -1000;
    
    public Carte() {
        this.listeIntersections = new ArrayList<Intersection>();
    }

    public ArrayList<Intersection> getListeIntersections() {
        return listeIntersections;
    }

    public void ajouterIntersection(Intersection i) {
       this.listeIntersections.add(i);
    }

    public DemandesLivraisons getDemandesLivraisons() {
        return demandesLivraisons;
    }

    public void setDemandesLivraisons(DemandesLivraisons dL) {
        this.demandesLivraisons = dL;
    }

    public void relacherArc(Intersection depart, Intersection arrivee) {
        System.out.println("Relachement Arc ("+depart.getId()+","+arrivee.getId()+")" );
        float coutArc = INFINI;
        ArrayList<Troncon> listeTronconsDepart = depart.getTronconsDepart();
        Troncon arc = new Troncon();
        //Recherche du coût de l'arc
        for (int i=0; i<listeTronconsDepart.size(); i++) {
            if (listeTronconsDepart.get(i).getDestination()==arrivee) {
                arc = listeTronconsDepart.get(i);
                coutArc = arc.getLongueur();
            }
        }
        if (arrivee.getDistance() > depart.getDistance() + coutArc) {
            //Relachement de l'arc
            arrivee.setDistance(depart.getDistance()+coutArc);
            arrivee.setPredecesseur(depart);
        }
    }
    
   
    
    public void dijkstra(Intersection depart) {
        float dMin;
        Intersection sMin; //sommet de la liste de sommets gris ayant la distance minimale
        ArrayList<Intersection> sommetsBlancs = new ArrayList<Intersection>();
        ArrayList<Intersection> sommetsGris = new ArrayList<Intersection>();
        ArrayList<PointInteret> pointsInteret = this.demandesLivraisons.getListePointsInteret(); 
        ArrayList<Intersection> successeursMin; //liste des successeurs du sommet
        //gris ayant la distance minimale
        
       
        
        for (int i=0; i<this.listeIntersections.size(); i++) {
            this.listeIntersections.get(i).setDistance(INFINI);
            listeIntersections.get(i).setPredecesseur(null);
            sommetsBlancs.add(this.listeIntersections.get(i));                  
        }
        //Initialisation de la distance du sommet de départ
        depart.setDistance(0);
        sommetsGris.add(depart);
        
        
        while (sommetsGris.size() != 0) {
            //Recherche du sommet Si ayant la distance minimale par rapport au
            //sommet de départ
            //Initialisation de indMin et dMin
            dMin = sommetsGris.get(0).getDistance();
            sMin = sommetsGris.get(0);
            for (int i=0; i<sommetsGris.size(); i++) {
                if (sommetsGris.get(i).getDistance()<dMin) {
                    dMin = sommetsGris.get(i).getDistance();
                    sMin = sommetsGris.get(i);
                }
            }
            

            successeursMin = sMin.retrouverSuccesseurs();
            for (int j=0; j<successeursMin.size(); j++) {
                //Si Sj est gris ou blanc alors
                if (sommetsBlancs.contains(successeursMin.get(j)) ||
                    sommetsGris.contains(successeursMin.get(j))) {
                    //Relacher l'arc (Si, Sj)
                    relacherArc(sMin, successeursMin.get(j));
                    
        
                    //Si Sj est blanc, alors
                    if (sommetsBlancs.contains(successeursMin.get(j))) {
                        //Colorier en gris
                        sommetsGris.add(successeursMin.get(j));
                        sommetsBlancs.remove(successeursMin.get(j));
                        
                    }
                }  
            }
            sommetsGris.remove(sMin);        
        }
        

    }
    
    public PointInteret trouverPointInteret(Intersection intersection) {
        /*Méthode permettant de trouver le point d'interet correspondant à 
        l'intersection intersection. Celui-ci est null si intersection n'est pas
        un point d'interet.
        */
        PointInteret pI = null;
        ArrayList<PointInteret> pointsInteret = this.demandesLivraisons.getListePointsInteret(); 
        for (int i=0; i<pointsInteret.size(); i++) {
            if (pointsInteret.get(i).getIntersection()== intersection) {
                pI = pointsInteret.get(i);
            }               
        }
        return pI;
        
    }
    



   
    
    
}
