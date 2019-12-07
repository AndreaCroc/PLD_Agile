
package Vue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import modele.PointInteret;
import modele.Tournee;

/*
 * AffichageTournee
 *
 * Version 1
 * 
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class AffichageTournee {
/**
 *
 * Classe AffichageTournee permettant d'afficher les donnes globales d'une
 * tournee
 */

    private Tournee tournee; //Tournee avec tous les points d interets par lesquels on passe
    private Fenetre fenetre; //Fenetre ou se trouve la tournee

    /**
     * Constructeur de la classe AffichageTournee
     * @param tournee tournee effectuee
     * @param f fenetre
     */
    public AffichageTournee(Tournee tournee, Fenetre f) {
        this.tournee = tournee;
        this.fenetre = f;
    }

    /**
     * Modifier la tournee
     * @param tournee nouvelle tournee
     */
    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

    /**
     * Affichage des heures de debut, fin et duree de la tournee
     */
    public void afficherTournee() {
        //Liste des points d interet de la tournee
        ArrayList<PointInteret> successionPointsInteret = tournee.getSuccessionPointsInteret();

        String dureeTotPrevue = ""; //Duree totale de la tournee
        String heureDeb = ""; //Heure de debut de la tournee
        String heureFin = ""; //Heure de fin de la tournee

        //S assurer que la liste contient des points d'interet
        if (successionPointsInteret != null && !successionPointsInteret.isEmpty()) {
            DecimalFormat df = new DecimalFormat("0.00");
            //Recuperer la duree
            dureeTotPrevue = tournee.getDuree();
            dureeTotPrevue = dureeTotPrevue.substring(0, dureeTotPrevue.lastIndexOf(":"));
            dureeTotPrevue = dureeTotPrevue.replace(":", "h");

            //Recuperer le point d'interet correspondant a l'entrepot
            heureDeb = successionPointsInteret.get(0).getHeureDepart();
            heureDeb = heureDeb.substring(0, heureDeb.lastIndexOf(":"));
            heureDeb = heureDeb.replace(":", "h");
            heureFin = successionPointsInteret.get(0).getHeureArrivee();
            heureFin = heureFin.substring(0, heureFin.lastIndexOf(":"));
            heureFin = heureFin.replace(":", "h");

            //Afficher le resume de la tournee
            fenetre.setPanneauTournee(heureDeb, heureFin, dureeTotPrevue);

        }

    }
}
