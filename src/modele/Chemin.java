package modele;

import java.util.ArrayList;

/*
 * Chemin
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class Chemin {
    private Integer dureeTrajet;
    private Intersection depart;
    private Intersection arrivee;
    private ArrayList<Troncon> successionTroncons;
    private Double longueur;

    public Chemin() {
        this.successionTroncons = new ArrayList<Troncon>();
    }

    public Chemin(Intersection depart, Intersection arrivee, ArrayList<Troncon> successionTroncons) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.successionTroncons = successionTroncons;
        this.longueur = this.calculerLongueur();
        this.dureeTrajet=this.calculerDureeTrajet();
    }

    /**
     * Retourne l intersection de depart
     * @return intersection de depart
     */
    public Intersection getDepart() {
        return depart;
    }
    
    /**
     * Modifie l intersection de depart du chemin
     * @param depart intersection de depart
     */
    public void setDepart(Intersection depart) {
        this.depart = depart;
    }

    /**
     * Renvoie l intersection d arrivee du chemin
     * @return intersection d arrivee du chemin
     */
    public Intersection getArrivee() {
        return arrivee;
    }

    /**
     * Modifie l arrivee du chemin
     * @param arrivee intersection d arrivee du chemin
     */
    public void setArrivee(Intersection arrivee) {
        this.arrivee = arrivee;
    }

    /**
     * Renvoie la longueur du chemin
     * @return longueur de type Double
     */
    public Double getLongueur() {
        return longueur;
    }

    /**
     * Modifie la longueur du chemin
     * @param longueur nouvelle longueur du chemin
     */
    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    /**
     * Renvoie la duree de parcours du chemin
     * @return duree du trajet
     */
    public Integer getDureeTrajet() {
        return dureeTrajet;
    }

    /**
     * Modifie la duree de parcours du chemin
     * @param dureeTrajet nouvelle duree du trajet
     */
    public void setDureeTrajet(Integer dureeTrajet) {
        this.dureeTrajet = dureeTrajet;
    }

    /**
     * Renvoie la liste des troncons qui composent le chemin
     * @return liste des troncons qui composent le chemin
     */
    public ArrayList<Troncon> getSuccessionTroncons() {
        return successionTroncons;
    }

    /**
     * Ajout d un troncon au chemin
     * @param troncon troncon a ajouter
     */
    public void ajouterTroncon(Troncon troncon) {
        this.successionTroncons.add(troncon);
        //mise a jour du depart et de l'arrivee
        this.depart = this.successionTroncons.get(0).getOrigine();
        int nbTroncons = this.successionTroncons.size();
        this.arrivee=this.successionTroncons.get(nbTroncons-1).getDestination();
        //mise a jour de la longueur
        calculerLongueur();
        //mise a jour de la duree
        calculerDureeTrajet();
    }
    
    /**
     * Methode permettant de calculer mettre a jour la longueur du chemin
     * a partir des troncons qui le composent
     * @return longueur calculee
     */
    public Double calculerLongueur() {
        Double longueur = 0.0;
        for (Troncon troncon : this.successionTroncons) {
            longueur=longueur+troncon.getLongueur();
        }
        this.longueur = longueur;
        return longueur;
    }
    
    /**
     * Methode permettant de calculer et mettre a jour la durée du trajet pour 
     * un chemin a partir des troncons qui le composent
     * @return duree calculee
     */
    public Integer calculerDureeTrajet() {
        Integer dureeChemin = 0;
        Double dureeDouble = longueur;
        
        this.dureeTrajet = dureeDouble.intValue();
        return dureeTrajet;
    }

    /**
     * permet de retourner les attributs d'un chemin en chaine de caracteres
     * (utilisee pour les tests)
     * @return chaine de carcteres definissant le chemin
     */
    @Override
    public String toString() {
        String out = "Chemin de " + depart.getId() + " à " + arrivee.getId();
        return out;
    }
    
}
