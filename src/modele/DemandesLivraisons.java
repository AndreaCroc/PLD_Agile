package modele;

import java.util.ArrayList;

/*
 * DemandesLivraisons
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class DemandesLivraisons {
    private int heureDepart;
    private PointInteret adresseDepart;
    private ArrayList<PointInteret> listePointsInteret;

    public DemandesLivraisons(PointInteret aD) {
        this.adresseDepart = aD;
        this.listePointsInteret = new ArrayList<PointInteret>();
    }
    
    

    public int getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(int heureDepart) {
        this.heureDepart = heureDepart;
    }

    public PointInteret getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(PointInteret aD) {
        this.adresseDepart = adresseDepart;
    }

    public ArrayList<PointInteret> getListePointsInteret() {
        return listePointsInteret;
    }
    
    public void ajouterPointInteret(PointInteret pI) {
        this.listePointsInteret.add(pI);
    }

 
    
}
