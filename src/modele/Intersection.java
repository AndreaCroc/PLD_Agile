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
    private String id;
    private Double latitude;
    private Double longitude;
    private ArrayList<Troncon> tronconsDepart;
    
    
    //Pour dijkstra
    private Intersection predecesseur;
    private Double distance;

    public Intersection() {
        tronconsDepart = new ArrayList<Troncon>();
    }


    
    public Intersection(String id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = Carte.INFINI;
        this.tronconsDepart = new ArrayList<Troncon>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double dist) {
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
