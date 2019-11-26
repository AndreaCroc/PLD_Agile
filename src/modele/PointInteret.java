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
    private boolean enlevement;
    private boolean entrepot;
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

    public boolean isEnlevement() {
        return enlevement;
    }

    public void setEstEnlevement(boolean estEnlevement) {
        this.enlevement = estEnlevement;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public boolean isEntrepot() {
        return entrepot;
    }

    public void setEntrepot(boolean entrepot) {
        this.entrepot = entrepot;
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

    public String intToHeure (Integer heureInt) {
        String heureStr;
        int nbHeures = heureInt/3600;
        int nbMinutes = (heureInt-(nbHeures*3600))/60;
        int nbSecondes = heureInt-(nbHeures*3600)-(nbMinutes*60);
        String nbH = Integer.toString(nbHeures);
        String nbM = Integer.toString(nbMinutes); 
        String nbS = Integer.toString(nbSecondes);
        heureStr=nbH+":"+nbM+":"+nbS;
        return heureStr;
    }
    
    public Integer heureToInt(String heureStr) {
        Integer heureInt;
        String[] elements = heureStr.split(":");
        int nbHeure = Integer.parseInt(elements[0]);
        int nbMinutes = Integer.parseInt(elements[1]);
        int nbSecondes = Integer.parseInt(elements[2]);
        heureInt = nbHeure*3600 + nbMinutes*60 + nbSecondes;
        return heureInt;
    }
    

}
