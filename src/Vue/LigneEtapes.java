/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

/**
 *
 * @author acer
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