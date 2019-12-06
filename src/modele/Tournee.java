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

package modele;

import java.util.ArrayList;

/**
 * 
 * Classe Tournee 
 */
public class Tournee {
    private String duree;
    private ArrayList<PointInteret> successionPointsInteret;

    public Tournee() {
        successionPointsInteret=new ArrayList<PointInteret>();
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public ArrayList<PointInteret> getSuccessionPointsInteret() {
        return successionPointsInteret;
    }
    /** Méthode permettant d'ajouter un point d'intérêt à une tournée
     * 
     * @param pointInteret : le point d'intérêt à ajouter 
     */
    public void ajouterPointInteret(PointInteret pointInteret) {
        this.successionPointsInteret.add(pointInteret);
    }

    public void setSuccessionPointsInteret(ArrayList<PointInteret> successionPointsInteret) {
        this.successionPointsInteret = successionPointsInteret;
    }

    
    @Override
    public String toString() {
        String out = "Tournée : \n";
        for (PointInteret pI : successionPointsInteret) {
            out+="Point "+pI.getIntersection().getId()+"\n "+pI.getCheminDepart()+"\n";
        }
        return out;
    }

    

    
    
    
    
}
