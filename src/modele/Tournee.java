package modele;

import java.util.ArrayList;

/**
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
    private String duree; //duree de la tournee
    private ArrayList<PointInteret> successionPointsInteret; //liste des pointd d interets de la tournee

    /**
     * Constructeur par defaut d une tournee
     */
    public Tournee() {
        successionPointsInteret=new ArrayList<PointInteret>();
    }

    /**
     * Retourne la duree de la tournee
     * @return duree
     */
    public String getDuree() {
        return duree;
    }
    
    /**
     * Met a jour la duree
     * @param duree nouvelle duree de la tournee
     */
    public void setDuree(String duree) {
        this.duree = duree;
    }
    /**
     * Recuperer la succession des points d interets de la tournee
     * @return liste des points d interets de la tournee
     */
    public ArrayList<PointInteret> getSuccessionPointsInteret() {
        return successionPointsInteret;
    }
    
    /** Methode permettant d'ajouter un point d'interet à une tournée
     * 
     * @param pointInteret : le point d'interet a ajouter 
     */
    public void ajouterPointInteret(PointInteret pointInteret) {
        this.successionPointsInteret.add(pointInteret);
    }

    /**
     * Modifier la liste de succesion des points d interets de la tournee
     * @param successionPointsInteret nouvelle liste des points d interets
     */
    public void setSuccessionPointsInteret(ArrayList<PointInteret> successionPointsInteret) {
        this.successionPointsInteret = successionPointsInteret;
    }

    /**
     * Afficher les attributs d une tournee
     * @return la chaine de caracteres a afficher
     */
    @Override
    public String toString() {
        String out = "Tournée : \n";
        for (PointInteret pI : successionPointsInteret) {
            out+="Point "+pI.getIntersection().getId()+"\n "+pI.getCheminDepart()+"\n";
        }
        return out;
    }

    

    
    
    
    
}
