package modele;

/*
 * Troncon
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Troncon {
    private String nomRue;
    private Double longueur;
    private Intersection origine;
    private Intersection destination;

    public Troncon() {
    }

    public Troncon(String nomRue, Double longueur, Intersection origine, Intersection destination) {
        this.nomRue = nomRue;
        this.longueur = longueur;
        this.origine = origine;
        this.destination = destination;
    }

    
    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public Intersection getOrigine() {
        return origine;
    }

    public void setOrigine(Intersection origine) {
        this.origine = origine;
    }

    public Intersection getDestination() {
        return destination;
    }

    public void setDestination(Intersection destination) {
        this.destination = destination;
    }
    
    
}
