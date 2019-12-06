package modele;

/*
 * PointInteret
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class PointInteret {
    private Intersection intersection; //intersection ou se trouve le point d interet
    private Integer duree; //duree une fois arrivee au point d interet
    private boolean enlevement; //si le point d interet est un enlevement
    private boolean entrepot; //si le point d interet est l entrepot
    private String heureDepart; //heure de depart du point d interet
    private String heureArrivee; //heure d arrivee au point d interet
    private PointInteret pointDependance; //point d enelvement ou de livraison correspondantr au point d interet
    private Chemin cheminDepart; //decrit le chemin de depart du point d interet
    private Integer numeroDemande; //numero de demande du point d interet

    /**
     * Constructeur par defaut d un point d interet
     */
    public PointInteret() {
    }

    /**
     * Constructeur d un point d interet a partir d une intersection et d une duree
     * @param intersection intersection ou se situe le point d interet
     * @param duree duree au point d interet
     */
    public PointInteret(Intersection intersection, int duree) {
        this.intersection = intersection;
        this.duree = duree;
        this.cheminDepart = new Chemin();
        this.heureDepart="0";
        this.heureArrivee="0";
    }

    /**
     * Recuperer l intersection du point d interet
     * @return intersection
     */
    public Intersection getIntersection() {
        return intersection;
    }
    
    /**
     * Modifier l intersection du point d interet
     * @param intersection nouvelle intersection
     */
    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
    }

    /**
     * Recuperer la duree au point d interet
     * @return duree
     */
    public Integer getDuree() {
        return duree;
    }

    /**
     * Mettre a jour la duree au point d interet
     * @param duree nouvelle duree au point d interet
     */
    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    /**
     * Savoir si le point d interet est de type enlevement
     * @return enlevement
     */
    public boolean isEnlevement() {
        return enlevement;
    }

    /**
     * Modifier le type du point d interet
     * @param estEnlevement nouveau type du point d interet 
     */
    public void setEnlevement(boolean estEnlevement) {
        this.enlevement = estEnlevement;
    }

    /**
     * Recuperer l heure de depart du point d interet
     * @return heure de depart
     */
    public String getHeureDepart() {
        return heureDepart;
    }

    /**
     * Savoir si le point d interet est l entrepot
     * @return entrepot
     */
    public boolean isEntrepot() {
        return entrepot;
    }

    /**
     * Modifier le type du point d interet
     * @param entrepot nouveau type
     */
    public void setEntrepot(boolean entrepot) {
        this.entrepot = entrepot;
    }

    /**
     * Mettre a jour l heure de depart du point d interet
     * @param heureDepart nouvelle heure de depart
     */
    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Recuperer l heure d arrivee au point d interet
     * @return heure d arrivee
     */
    public String getHeureArrivee() {
        return heureArrivee;
    }

    /**
     * Modifier l heure d arrivee au point d interet
     * @param heureArrivee nouvelle heure d arrivee
     */
    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    /**
     * Recuperer le point d interet dependant du point d interet actuel
     * @return point de dependance
     */    
    public PointInteret getPointDependance() {
        return pointDependance;
    }

    /**
     * Modifier le point de dependance
     * @param pointDependance nouveau point de dependance
     */
    public void setPointDependance(PointInteret pointDependance) {
        this.pointDependance = pointDependance;
    }

    /**
     * Recuperer le chemin de depart du point d interet
     * @return chemin de depart
     */
    public Chemin getCheminDepart() {
        return cheminDepart;
    }

    /**
     * Modifier le chemin de depart
     * @param cheminDepart nouveau chemin de depart
     */
    public void setCheminDepart(Chemin cheminDepart) {
        this.cheminDepart = cheminDepart;
    }

    /**
     * Recuperer le numero de demande du point d interet
     * @return numero du demande
     */
    public Integer getNumeroDemande() {
        return numeroDemande;
    }

    /**
     * Modifier le numero de demande du point d interet
     * @param num nouveau numero de demande
     */
    public void setNumeroDemande(Integer num) {
        this.numeroDemande = num;
    }

}
