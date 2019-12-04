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

    private int numero;
    private String type;
    private String rue;
    private String depart;
    private String arrivee;
    private String duree;

    public LigneEtapes(int numero, String type, String rue, String depart, String arrivee, String duree) {
        this.numero = numero;
        this.type = type;
        this.rue = rue;
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree = duree;
    }

    public LigneEtapes() {
    }

    public int getNumero() {
        return numero;
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