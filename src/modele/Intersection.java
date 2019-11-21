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

    public Intersection() {
    }

    public Intersection(int id, float latitude, float longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
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
    
}
