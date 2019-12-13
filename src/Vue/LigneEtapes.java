
package Vue;

/*
 * LigneEtapes correspond a une ligne du tableauEtapes
 *
 * @version Version 1
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class LigneEtapes {

    private int ordre; //numero de l etape dans la tournee
    private int numDemande; //numero de demande associe au point dinteret
    private String type; //type du point dinteret
    private String rue; //rue du point dinteret
    private String depart; //heure de depart du point dinteret
    private String arrivee; //heure d arrivee au point dinteret
    private String duree; //duree au point dinteret

    /**
     * Constructeur de la classe LigneEtapes
     * @param ordre numero de l'etape dans la tournee
     * @param numero numero de la demande
     * @param type type du point d'interet
     * @param rue rue ou se trouve le point d'interet
     * @param depart heure de depart du point d'interet
     * @param arrivee heure d'arrivee au point d'interet
     * @param duree duree disponible une fois arrivée au point d'interet
     */
    public LigneEtapes(int ordre,int numero, String type, String rue, 
                       String depart, String arrivee, String duree) {
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
     * Recuperer l'heure de depart du point dinteret
     * @return heure de depart
     */
    public String getDepart() {
        return depart;
    }

    /**
     * Recuperer l'heure darrivee au point d interet
     * @return l'heure d'arrivee
     */
    public String getArrivee() {
        return arrivee;
    }

    /**
     * Recuperer la duree de l'etape
     * @return duree de l'etape
     */
    public String getDuree() {
        return duree;
    }
  
}