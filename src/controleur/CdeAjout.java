
package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * CdeAjout
 *
 * @version Version 1
 *
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class CdeAjout implements CommandeTournee{

    private Double latitudeEnlvt; // latitude du point d'enlèvement
    private Double longitudeEnlvt; // longitude du point d'enlèvement
    private Double latitudeLivr; // latitude du point de livraison
    private Double longitudeLivr; // latitude du point de livraison
    private PointInteret pointAvantLivr; // point d'intérêt après lequel on 
    // souhaite placer le point de livraison
    private PointInteret pointAvantEnlvt; // point d'intérêt après lequel 
    //on souhaite placer le point d'enlèvement
    private int dureeEnlevement; // durée d'enlèvement
    private int dureeLivraison; // durée de livraison

    public CdeAjout(Double latitudeEnlvt, Double longitudeEnlvt, Double latitudeLivr, Double longitudeLivr, PointInteret pointAvantLivr, PointInteret pointAvantEnlvt, int dureeEnlevement, int dureeLivraison) {
        this.latitudeEnlvt = latitudeEnlvt;
        this.longitudeEnlvt = longitudeEnlvt;
        this.latitudeLivr = latitudeLivr;
        this.longitudeLivr = longitudeLivr;
        this.pointAvantLivr = pointAvantLivr;
        this.pointAvantEnlvt = pointAvantEnlvt;
        this.dureeEnlevement = dureeEnlevement;
        this.dureeLivraison = dureeLivraison;
    }
    
    @Override
    public void doCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur) {
        
    }

    @Override
    public void undoCde(Carte carte, Tournee tournee, Fenetre fenetre, Controleur controleur) {
    }
    
}
