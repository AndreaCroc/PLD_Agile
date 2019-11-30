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

    @Override
    public String toString() {
        String out = "Tournée : \n";
        for (PointInteret pI : successionPointsInteret) {
            out+="Point "+pI.getIntersection().getId()+"\n chemin "+pI.getCheminDepart()+"\n";
        }
        return out;
    }

    

    
    
    
    
}
