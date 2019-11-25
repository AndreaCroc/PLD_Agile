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
    private Intersection intersection;
    private Integer duree;
    private boolean estEnlevement;
    private String heureDepart;
    private String heureArrivee;
    private PointInteret pointDependance;
    private Chemin cheminDepart;

    public PointInteret() {
    }

    public PointInteret(Intersection intersection, int duree) {
        this.intersection = intersection;
        this.duree = duree;
        this.cheminDepart = new Chemin();
        this.heureDepart="0";
        this.heureArrivee="0";
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setIntersection(Intersection intersection) {
        this.intersection = intersection;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public boolean isEstEnlevement() {
        return estEnlevement;
    }

    public void setEstEnlevement(boolean estEnlevement) {
        this.estEnlevement = estEnlevement;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public PointInteret getPointDependance() {
        return pointDependance;
    }

    public void setPointDependance(PointInteret pointDependance) {
        this.pointDependance = pointDependance;
    }

    public Chemin getCheminDepart() {
        return cheminDepart;
    }

    public void setCheminDepart(Chemin cheminDepart) {
        this.cheminDepart = cheminDepart;
    }

    
    
    
}
