
package Vue;

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

public class LigneEtapes {
/**
 *
 * Classe LigneEtapes correspond a une ligne du tableauEtapes
 */

    private int ordre; //numero de l etape dans la tournee
    private int numDemande; //numero de demande associe au point dinteret
    private String type; //type du point dinteret
    private String rue; //rue du point dinteret
    private String depart; //heure de depart du point dinteret
    private String arrivee; //heure d arrivee au point dinteret
    private String duree; //duree au point dinteret

    /**
     * Constructeur de la classe LigneEtapes
     * @param ordre
     * @param numero
     * @param type
     * @param rue
     * @param depart
     * @param arrivee
     * @param duree 
     */
    public LigneEtapes(int ordre,int numero, String type, String rue, String depart, String arrivee, String duree) {
        this.ordre = ordre;
        this.numDemande = numero;
        this.type = type;
        this.rue = rue;
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree = duree;
    }

    /**
     * Constructeur vide de la classe LigneEtapes
     */
    public LigneEtapes() {
    }

    /**
     * Recuperer le numero de letape dans la tournee
     * @return numero ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Recuperer le numero de la demande de livraison
     * @return numero de la demande
     */
    public int getNumDemande() {
        return numDemande;
    }

    /**
     * Recuperer le type du point dinteret
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Recuperer le nom de la rue
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * Recuperer lheure de depart du point dinteret
     * @return 
     */
    public String getDepart() {
        return depart;
    }

    /**
     * Recuperer l heure darrivee au point d interet
     * @return 
     */
    public String getArrivee() {
        return arrivee;
    }

    /**
     * Recuperer la duree de letape
     * @return 
     */
    public String getDuree() {
        return duree;
    }
  
}