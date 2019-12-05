/*
 * LigneEtapes
 *
 * Version 1
 * 
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package Vue;

/**
 *
 * Classe LigneEtapes correspond a une ligne du tableauEtapes
 */
public class LigneEtapes {

    private int ordre;
    private int numDemande;
    private String type;
    private String rue;
    private String depart;
    private String arrivee;
    private String duree;

    public LigneEtapes(int ordre,int numero, String type, String rue, String depart, String arrivee, String duree) {
        this.ordre = ordre;
        this.numDemande = numero;
        this.type = type;
        this.rue = rue;
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree = duree;
    }

    public LigneEtapes() {
    }

    public int getOrdre() {
        return ordre;
    }

    public int getNumDemande() {
        return numDemande;
    }

    public String getType() {
        return type;
    }

    public String getRue() {
        return rue;
    }

    public String getDepart() {
        return depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public String getDuree() {
        return duree;
    }
  
}