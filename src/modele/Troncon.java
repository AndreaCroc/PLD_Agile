package modele;

/*
 * Troncon
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Troncon {
    private String nomRue;
    private Double longueur;
    private Intersection origine;
    private Intersection destination;

    /**
     * Constructeur par defaut d un troncon
     */
    public Troncon() {
    }

    /**
     * Constructeur d un troncon a partir du nom de la rue, de la longueur du 
     * troncon, de son origine et de sa destination
     * @param nomRue nom de la rue correspondante
     * @param longueur longueur du troncon
     * @param origine intersection d origine du troncon
     * @param destination intersection de destination du troncon
     */
    public Troncon(String nomRue, Double longueur, Intersection origine, Intersection destination) {
        this.nomRue = nomRue;
        this.longueur = longueur;
        this.origine = origine;
        this.destination = destination;
    }
    
    /**
     * Obtention du nom de la rue correspondant au troncon
     * @return Nom de la rue
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Modifie le nom de la rue du troncon
     * @param nomRue nouveau nom de la rue
     */
    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    /**
     * Obtention de la longueur du troncon
     * @return longueur du troncon
     */
    public Double getLongueur() {
        return longueur;
    }

    /**
     * Modifie la longueur du troncon
     * @param longueur nouvelle longueur du troncon
     */
    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    /**
     * Obtention de l intersection d origine du troncon
     * @return Nouvelle intersection d origine
     */
    public Intersection getOrigine() {
        return origine;
    }

    /**
     * Modifie l intersection d origine
     * @param origine 
     */
    public void setOrigine(Intersection origine) {
        this.origine = origine;
    }

    /**
     * Obtention de l intersection de destination de troncon
     * @return Nouvelle intersection de destination de troncon
     */
    public Intersection getDestination() {
        return destination;
    }

    /**
     * Modifie la destination du troncon
     * @param destination nouvelle destination
     */
    public void setDestination(Intersection destination) {
        this.destination = destination;
    }
    
}
