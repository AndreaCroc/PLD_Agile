/*
 * LignePIs
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

import javax.swing.JTextArea;

/**
 *
 * Classe LignePI correspond a un eligne du tableauPIs
 */
public class LignePI {

    private int numeroDemande;
    private String type;
    private JTextArea rue;
    private String duree;

    public LignePI(int numero, String type, String rue, String duree) {
        this.numeroDemande = numero;
        this.type = type;
        this.rue = new JTextArea(rue);
        this.rue.setLineWrap(true);
        this.rue.setWrapStyleWord(true);
        this.duree = duree;
    }

    public LignePI() {
    }

    public int getNumero() {
        return numeroDemande;
    }

    public String getType() {
        return type;
    }

    public JTextArea getRue() {
        return rue;
    }
    
    public String getDuree() {
        return duree;
    }

    public void setNumero(int numero) {
        this.numeroDemande = numero;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRue(JTextArea rue) {
        this.rue = rue;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
    
    


    

}
