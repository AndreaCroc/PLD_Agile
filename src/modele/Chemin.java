package modele;

import java.util.ArrayList;

/*
 * Chemin
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class Chemin {
    private int dureeTrajet;
    private ArrayList<Troncon> successionTroncons;

    public Chemin() {
        this.successionTroncons = new ArrayList<Troncon>();
    }

    public Chemin(ArrayList<Troncon> successionTroncons) {
        this.successionTroncons = successionTroncons;
    }
    
    
    public int getDureeTrajet() {
        return dureeTrajet;
    }

    public void setDureeTrajet(int dureeTrajet) {
        this.dureeTrajet = dureeTrajet;
    }

    public ArrayList<Troncon> getSuccessionTroncons() {
        return successionTroncons;
    }

    public void ajouterTroncon(Troncon troncon) {
        this.successionTroncons.add(troncon);
    }

    
    
}
