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

    public void ajouterPointInteret(PointInteret pI) {
        this.successionPointsInteret.add(pI);
        //calculerDuree();
    }
//    
//    public Integer calculerDuree() {
//        PointInteret entrepot = this.successionPointsInteret.get(0);
//        Integer heureArr = entrepot.heureToInt(entrepot.getHeureArrivee());
//        Integer heureDep = entrepot.heureToInt(entrepot.getHeureDepart());
//        Integer duree = heureArr-heureDep;
//        this.duree = duree;
//        return duree;
//    }

    @Override
    public String toString() {
        String tournee = "Tournee : duree = "+duree+" itineraire : ";
        for (PointInteret pointInteret : successionPointsInteret) {
            tournee+=pointInteret.getIntersection().getId()+" , ";
        }
        return tournee;
    }
    
    
    
    
}
