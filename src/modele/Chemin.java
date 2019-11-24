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
    private Integer dureeTrajet;
    private Intersection depart;
    private Intersection arrivee;
    private ArrayList<Troncon> successionTroncons;
    private Double longueur;

    public Chemin() {
        this.successionTroncons = new ArrayList<Troncon>();
    }

    public Chemin(Intersection depart, Intersection arrivee, ArrayList<Troncon> successionTroncons) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.successionTroncons = successionTroncons;
        this.longueur = this.calculerLongueur();
    }

    public Intersection getDepart() {
        return depart;
    }

    public void setDepart(Intersection depart) {
        this.depart = depart;
    }

    public Intersection getArrivee() {
        return arrivee;
    }

    public void setArrivee(Intersection arrivee) {
        this.arrivee = arrivee;
    }

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public int getDureeTrajet() {
        return dureeTrajet;
    }

    public void setDureeTrajet(Integer dureeTrajet) {
        this.dureeTrajet = dureeTrajet;
    }

    public ArrayList<Troncon> getSuccessionTroncons() {
        return successionTroncons;
    }

    public void ajouterTroncon(Troncon troncon) {
        this.successionTroncons.add(troncon);
    }
    
    public Double calculerLongueur() {
        Double longueur = 0.0;
        for (Troncon troncon : this.successionTroncons) {
            System.out.println(troncon.getLongueur());
            longueur=longueur+troncon.getLongueur();
        }
        this.longueur = longueur;
        return longueur;
    }
    
    
}
