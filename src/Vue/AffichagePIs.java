
package Vue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Troncon;

/**
 * AffichagePIs
 *
 * Version 1
 * 
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class AffichagePIs extends AbstractTableModel {
/**
 *
 * Classe AffichagePIs permet d afficher les informations d un point d interet
 * lors du chargement des livraisons
 *
 */

    private final ArrayList<LignePI> lignePIs; //Liste des points d interet 
    private final String header[]; //En tete du tableau
    private int lignePISelect; //Numero de la ligne selectionnee
    private int lignePIDepSelect; //Numero de la ligne du point dependant de la lgine selectionnee
    private FormatCellRenderer formatcell; //Format a appliquer au tableau
    private Carte carte; //Carte
    private Fenetre fenetre; //Fenetre ou se trouve le tableau

    /**
     * Constructeur de la classe AffichagePIS
     * @param formatcell format a appliquer au tableau
     * @param carte carte qui possede la liste des points d interet
     * @param fenetre fenetre ou afficher le tableau
     */
    public AffichagePIs(FormatCellRenderer formatcell, Carte carte, Fenetre fenetre) {
        this.lignePIs = new ArrayList<>();
        this.header = new String[]{"Numéro demande", "Type", "Durée", "Rue(s)"};
        this.lignePISelect = -1;
        this.lignePIDepSelect = -1;
        this.formatcell = formatcell;
        this.carte = carte;
        this.fenetre = fenetre;
    }

    /**
     * Recuperer le nombre de lignes que contient le tableau
     *
     * @return : le nombre de lignes du tableau
     */
    @Override
    public int getRowCount() {
        return this.lignePIs.size();
    }

    /**
     * Recuperer le nombre de colonnes que contient le tableau
     *
     * @return : le nombre de colonnes du tableau
     */
    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    /**
     * Recuperer la valeur d une cellue du tableau
     *
     * @param rowIndex : numero de la ligne
     * @param columnIndex : numero de la colonne
     * @return : Objet qui est dans la cellule de coordonnees
     * [rowIndex,columnIndex]
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.lignePIs.get(rowIndex).getNumero();
            case 1:
                return this.lignePIs.get(rowIndex).getType();
            case 2:
                return this.lignePIs.get(rowIndex).getDuree();
            case 3:
                return this.lignePIs.get(rowIndex).getRue();
            default:
                return null;
        }
    }

    /**
     * Recuperer le nom de la colonne
     *
     * @param columnIndex : numero de la colonne
     * @return : nom de la colonne
     */
    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    /**
     * Recuperer la classe d un element du tableau
     *
     * @param columnIndex : indice de la colonne dont on veut recuperer la
     * classe
     * @return : classe de la colonne
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 3:
                return JTextArea.class;
            default:
                return Object.class;
        }
    }

    /**
     * Recuperer le numero de ligne du tableau selectionnee
     * @return numero de la ligne
     */
    public int getLignePISelect() {
        return lignePISelect;
    }

    /**
     * Modifier la ligne du tabelau selectionnee
     * @param lignePISelect nouveau numero de la ligne
     */
    public void setLignePISelect(int lignePISelect) {
        this.lignePISelect = lignePISelect;
    }

    /**
     * Recuperer la ligne possedant le point dependant de celui
     * de la ligne qui a ete selectionnee
     * @return numero ligne du point dependant
     */
    public int getLignePIDepSelect() {
        return lignePIDepSelect;
    }

    /**
     * Modifier la ligne du point dependant
     * @param lignePIDepSelect nouveau numero de la ligne 
     */
    public void setLignePIDepSelect(int lignePIDepSelect) {
        this.lignePIDepSelect = lignePIDepSelect;
    }

    /**
     * Recuperer le format des cellules
     * @return format cellule
     */
    public FormatCellRenderer getFormatcell() {
        return formatcell;
    }

    /**
     * Modifier le format du tableau
     * @param formatcell nouveau format 
     */
    public void setFormatcell(FormatCellRenderer formatcell) {
        this.formatcell = formatcell;
    }

    /**
     * Modifier la carte associee au tableau
     * @param carte nouvelle carte
     */
    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    /**
     * Ajouter une ligne a lia liste du tableau
     *
     * @param pi : ligne a ajouter
     */
    public void addPI(LignePI pi) {
        this.lignePIs.add(pi);

        //Prevenir le tableau qu une ligne a ete ajoutee a la liste associee
        this.fireTableRowsInserted(this.lignePIs.size() - 1, this.lignePIs.size() - 1);
    }

    /**
     * Supprimer une ligne de la liste du tableau
     *
     * @param rowIndex : indice de la ligne a supprimer
     */
    public void removePI(int rowIndex) {
        this.lignePIs.remove(rowIndex);

        //Prevenir le tableau qu une ligne a ete supprimee de la liste
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * Supprimer tous les elements de la liste
     */
    public void clearPIs() {
        this.lignePIs.clear();
    }

    /**
     * Afficher tous les points d interet au chargement du fichier XML
     * correspondant a une livraison
     *
     * @param afficher savoir si la liste a afficher est non vide
     */
    public void afficherPIs(boolean afficher) {
        if (afficher) {
            //Recuperer les points d interets
            this.setCarte(fenetre.getCarte());
            
            //Liste des points d interet de la carte
            ArrayList<PointInteret> listePIs = this.carte.getListePointsInteretActuelle();
            System.out.println("carte pi : "+listePIs);
            String nomRue = ""; //Nom de la rue ou se trouve le point d interet
            String type = ""; //Type du point d interet
            int duree = 0; //Duree au point d interet
            String dureePt = ""; //Duree du point d interet
            int num = 0; //Numero de la demande de livraison
            Intersection intersection; //Intersection du point d interet
            ArrayList<Troncon> listeT; //Liste des troncons de l intersection
            
            //Si la carte possede au moins deux points
            if (listePIs.size() > 1) {
                for (PointInteret pt : listePIs) {
                    nomRue = "";
                    
                    intersection = pt.getIntersection();
                    listeT = intersection.getTronconsDepart();

                    //Recuperer les noms des rues qui intersectent le point d interet
                    for (Troncon t : listeT) {
                        if (!nomRue.contains(t.getNomRue())) {
                            nomRue += t.getNomRue() + ", ";
                        }
                    }
                    nomRue = nomRue.substring(0, nomRue.lastIndexOf(", "));

                    //Recuperer la duree une fois arrivee au point d interet
                    DecimalFormat df = new DecimalFormat("0.00");
                    duree = pt.getDuree();
                    dureePt = df.format(duree / 60);
                    dureePt = dureePt.substring(0, dureePt.lastIndexOf(",")) + " min";
                    
                    

                    //Recuperer le type du point d interet
                    if (pt.isEnlevement()) {
                        type = "Enlèvement";
                    } else {
                        type = "Livraison";
                    }
                    
                    if (listePIs.indexOf(pt) == 0) {
                        type = "Entrepot";
                        dureePt = "";
                        System.out.println("IF");
                        
                    } else {
                        //Recuperer le numero de la demande du point d interet
                        num = pt.getNumeroDemande();
                    }
                    //Afficher les details des points d interets
                    this.fenetre.setPanneauPIs(num, type, nomRue, dureePt);
                        
                }
            } else {
                this.fenetre.cacherPanneauPI();
            }

        } else {
            this.fenetre.cacherPanneauPI();
        }

    }

}
