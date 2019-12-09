
package Vue;

import javax.swing.JTextArea;

/**
 * LignePIs correspond a une ligne du tableauPIs
 *
 * @version Version 1
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class LignePI {

    private int numeroDemande; //numero de la demande de livraison
    private String type; //type du point dinteret
    private JTextArea rue; //Rue
    private String duree; //duree de letape

    /**
     * Constructeur de la classe LignePI
     * @param numero
     * @param type
     * @param rue
     * @param duree 
     */
    public LignePI(int numero, String type, String rue, String duree) {
        this.numeroDemande = numero;
        this.type = type;
        this.rue = new JTextArea(rue);
        this.rue.setLineWrap(true);
        this.rue.setWrapStyleWord(true);
        this.duree = duree;
    }

    /**
     * Constructeur vide de la classe LignePI
     */
    public LignePI() {
    }

    /**
     * Recuperer le numero de la demande
     * @return numero
     */
    public int getNumero() {
        return numeroDemande;
    }

    /**
     * Recuperer le type du point dinteret
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Recuperer les rues ou se trouvent le point dinteret
     * @return rue
     */
    public JTextArea getRue() {
        return rue;
    }
    
    /**
     * Recuperer la duree de letape
     * @return duree
     */
    public String getDuree() {
        return duree;
    }

    /**
     * Modifier le numero de la demande
     * @param numero nouveau numero
     */
    public void setNumero(int numero) {
        this.numeroDemande = numero;
    }

    /**
     * Modifier le type du point
     * @param type nouveau type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Modifier la rue du point
     * @param rue nouvelle rue
     */
    public void setRue(JTextArea rue) {
        this.rue = rue;
    }

    /**
     * Modifier la duree de letape
     * @param duree nouvelle duree
     */
    public void setDuree(String duree) {
        this.duree = duree;
    }
    
    


    

}
