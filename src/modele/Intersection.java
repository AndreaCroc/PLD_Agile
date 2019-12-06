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

    /**
     * Retourne l'ID de l intersection
     * @return ID de lintersection
     */
    public String getId() {
        return id;
    }

    /**
     * Met un nouvel ID a l intersection
     * @param id id de lintersection
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retourne la lalitude de l intersection
     * @return la lalitude de l intersection
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Met une nouvelle latitude a l intersection
     * @param latitude nouvelle latitude a l intersection
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Retourne la longitude de l intersection
     * @return longitude de l intersection
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Met une nouvelle longitude a l intersection
     * @param longitude longitude de l intersection
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Retourne tous les troncons
     * @return tous les troncons
     */
    public ArrayList<Troncon> getTronconsDepart() {
        return tronconsDepart;
    }
  
    /**
     * Ajoute un troncon
     * @param t le troncon a ajouter
     */
    public void ajouterTronconDepart(Troncon t) {
        this.tronconsDepart.add(t);
    }
    
    /**
     * Retourne le predecesseur
     * @return le predecesseur
     */
    public Intersection getPredecesseur() {
        return predecesseur;
    }

    /**
     * Met un nouveau predecesseur
     * @param predecesseur le predecesseur
     */
    public void setPredecesseur(Intersection predecesseur) {
        this.predecesseur = predecesseur;
    }

    /**
     * Retourne la distance
     * @return la distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * Met a jour la distance
     * @param dist la distance
     */
    public void setDistance(Double dist) {
        this.distance = dist;
    }

    /** Méthode permettant de trouver tous les successeurs de l'intersection 
     * intersection donc toutes les intersections qu'on peut visiter à partir 
     * de celle-ci
    */
    public ArrayList<Intersection> retrouverSuccesseurs() {
        ArrayList<Intersection> listeSuccesseurs = new ArrayList<Intersection>();
        for (int i=0; i<this.tronconsDepart.size(); i++) {
            listeSuccesseurs.add(this.tronconsDepart.get(i).getDestination());
        }
        return listeSuccesseurs;
    }
}
