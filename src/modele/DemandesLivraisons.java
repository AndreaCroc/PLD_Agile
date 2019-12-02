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
    private String heureDepart;
    private PointInteret adresseDepart;
    private ArrayList<PointInteret> listePointsInteret;

    public DemandesLivraisons(PointInteret aD) {
        this.adresseDepart = aD;
        this.listePointsInteret = new ArrayList<PointInteret>();
    }
    
    

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public PointInteret getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(PointInteret aD) {
        this.adresseDepart = adresseDepart;
        this.heureDepart = aD.getHeureDepart();
    }

    public ArrayList<PointInteret> getListePointsInteret() {
        return this.listePointsInteret;
    }
    
    public void ajouterPointInteret(PointInteret pI) {
        this.listePointsInteret.add(pI);
    }

    public void supprimerLivraison() {
        this.listePointsInteret.clear();
    }
 
    
}
