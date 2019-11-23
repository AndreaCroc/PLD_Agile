package modele;

import java.util.ArrayList;

/*
 * Intersection
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Intersection {
    private int id;
    private float latitude;
    private float longitude;
    private ArrayList<Troncon> tronconsDepart;
    
    
    //Pour dijkstra
    private Intersection predecesseur;
    private float distance;

    public Intersection() {
        tronconsDepart = new ArrayList<Troncon>();
    }


    
    public Intersection(int id, float latitude, float longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = Carte.INFINI;
        this.tronconsDepart = new ArrayList<Troncon>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Troncon> getTronconsDepart() {
        return tronconsDepart;
    }
  
    public void ajouterTronconDepart(Troncon t) {
        this.tronconsDepart.add(t);
    }
    
    public Intersection getPredecesseur() {
        return predecesseur;
    }

    public void setPredecesseur(Intersection predecesseur) {
        this.predecesseur = predecesseur;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float dist) {
        this.distance = dist;
    }

    
    
    
    public ArrayList<Intersection> retrouverSuccesseurs() {
        ArrayList<Intersection> listeSuccesseurs = new ArrayList<Intersection>();
        for (int i=0; i<this.tronconsDepart.size(); i++) {
            listeSuccesseurs.add(this.tronconsDepart.get(i).getDestination());
        }
        return listeSuccesseurs;
    }
}
