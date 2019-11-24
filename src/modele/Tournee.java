package modele;

import java.util.ArrayList;

/*
 * Tournee
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Tournee {
    private Integer duree;
    private ArrayList<PointInteret> successionPointsInteret;

    public Tournee() {
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public ArrayList<PointInteret> getSuccessionPointsInteret() {
        return successionPointsInteret;
    }

    public void ajouterPointInteret(PointInteret pI) {
        this.successionPointsInteret.add(pI);
    }
    
    
}
