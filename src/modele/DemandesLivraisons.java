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
    private Intersection adresseDepart;
    private ArrayList<PointInteret> listePointsInterets;

    public int getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(int heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Intersection getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(Intersection adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public ArrayList<PointInteret> getListePointsInterets() {
        return listePointsInterets;
    }
    
    public void ajouterPointInteret(PointInteret pI) {
        this.listePointsInterets.add(pI);
    }

}
