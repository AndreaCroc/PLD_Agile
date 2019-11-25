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
        this.dureeTrajet=this.calculerDureeTrajet();
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

    public Integer getDureeTrajet() {
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
        //mise a jour du depart et de l'arrivee
        this.depart = this.successionTroncons.get(0).getOrigine();
        int nbTroncons = this.successionTroncons.size();
        this.arrivee=this.successionTroncons.get(nbTroncons-1).getDestination();
        //mise a jour de la longueur
        calculerLongueur();
        //mise a jour de la duree
        calculerDureeTrajet();
    }
    
    public Double calculerLongueur() {
        Double longueur = 0.0;
        for (Troncon troncon : this.successionTroncons) {
            longueur=longueur+troncon.getLongueur();
        }
        this.longueur = longueur;
        return longueur;
    }

    public Integer calculerDureeTrajet() {
        Integer duree = 0;
        for (Troncon troncon : this.successionTroncons) {
            duree=duree+(int)((troncon.getLongueur()*15)/3.6);
        }
        this.dureeTrajet = duree;
        return duree;
    }
    @Override
    public String toString() {
        String chemin = "longueur : "+this.longueur+ " \n";
        for (Troncon troncon : this.successionTroncons) {
            chemin = chemin + troncon.getOrigine().getId()+" à "+troncon.getDestination().getId()+" \n";
        }
        return chemin; 
    }
    
    
    
}
