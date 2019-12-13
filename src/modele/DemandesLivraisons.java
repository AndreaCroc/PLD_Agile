package modele;

import java.util.ArrayList;

/*
 * DemandesLivraisons est l'ensemble des point d'interet d'une demande de 
 * livraison et son adresse de départ
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class DemandesLivraisons {
    private String heureDepart;
    private PointInteret adresseDepart;
    private ArrayList<PointInteret> listePointsInteret;

    /**
     * Constructeur d une demande de livraison a partir d une adresse de depart
     * @param pI l adresse de depart (un point d interet)
     */
    public DemandesLivraisons(PointInteret aD) {
        this.adresseDepart = aD;
        this.listePointsInteret = new ArrayList<PointInteret>();
    }
    
    /**
     * Retourne l heure de depart
     * @return heure de depart
     */
    public String getHeureDepart() {
        return heureDepart;
    }

    /**
     * Met a jour l heure de depart
     * @param heureDepart nouvelle heure de depart
     */
    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Retourner ladresse de depart
     * @return l adresse de depart
     */
    public PointInteret getAdresseDepart() {
        return adresseDepart;
    }

    /**
     * Mettre a jour l adresse de depart
     * @param aD adresse de depart
     */
    public void setAdresseDepart(PointInteret aD) {
        this.adresseDepart = adresseDepart;
        this.heureDepart = aD.getHeureDepart();
    }

    /**
     * Retourner la liste des points d interet
     * @return la liste des points d interet
     */
    public ArrayList<PointInteret> getListePointsInteret() {
        return this.listePointsInteret;
    }
    
    /**
     * Ajouter un point d interet a la liste
     * @param pI le point d interet a ajouter
     */
    public void ajouterPointInteret(PointInteret pI) {
        this.listePointsInteret.add(pI);
    }

    /**
     * Supprimer les points d interet
     */
    public void supprimerLivraison() {
        this.listePointsInteret.clear();
    }
 
    /**
     * Retourner la liste des points d interet
     * @return la liste des points d interet
     */
    public ArrayList<PointInteret> getPis() {
        return this.listePointsInteret;
    }
    
}
