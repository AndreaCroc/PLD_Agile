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
    private float longueur;
    private Intersection origine;
    private Intersection destination;

    public Troncon() {
    }

    public Troncon(String nomRue, float longueur, Intersection origine, Intersection destination) {
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

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
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
