/*
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
 *
 * Classe AffichagePIs permet d afficher les informations d un point d interet
 * lors du chargement des livraisons
 *
 */
public class AffichagePIs extends AbstractTableModel {

    private final ArrayList<LignePI> lignePIs;
    private final String header[];
    private int ligneSelect;
    private FormatCellRenderer formatcell;
    private Carte carte;
    private Fenetre fenetre;

    public AffichagePIs(FormatCellRenderer formatcell, Carte carte, Fenetre fenetre) {
        this.lignePIs = new ArrayList<>();
        this.header = new String[]{"Numéro demande", "Type", "Durée", "Rue(s)"};
        this.ligneSelect = -1;
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

    public int getLigneSelect() {
        return this.ligneSelect;
    }

    public void setLigneSelect(int ligne) {
        this.ligneSelect = ligne;
    }

    public FormatCellRenderer getFormatcell() {
        return formatcell;
    }

    public void setFormatcell(FormatCellRenderer formatcell) {
        this.formatcell = formatcell;
    }

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
        System.out.println("AFFICHER PIS");
        if (afficher) {
            //Recuperer les points d interets
            this.setCarte(fenetre.getCarte());
            
            ArrayList<PointInteret> listePIs = this.carte.getListePointsInteretActuelle();
            System.out.println("carte pi : "+listePIs);
            String nomRue = "";
            String type = "";
            int duree = 0;
            String dureePt = "";
            int num = 0;
            Intersection intersection;
            ArrayList<Troncon> listeT;
            if (listePIs.size() > 1) {
                for (PointInteret pt : listePIs) {
                    nomRue = "";
                    System.out.println("INFO 2 : " + pt.getDuree());
                    
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
                    System.out.println("NOM : "+ nomRue + " DUREE : "+ dureePt +" TYPE : " + type);
                    
                    if (listePIs.indexOf(pt) == 0) {
                        type = "Entrepot";
                        dureePt = "";
                        System.out.println("IF");
                        
                    } else {
                        //Recuperer le numero de la demande du point d interet
                        System.out.println("ELSE");
                        num = pt.getNumeroDemande();
                    }
                    //Afficher les details des points d interets
                    this.fenetre.setPanneauPIs(num, type, nomRue, dureePt);
                        
                }
            } else {
                this.fenetre.cacherPanneauPI();
                //this.fenetre.cacherTablePI();
                this.fenetre.afficherOuCacherMessageLivraison(true);
            }

        } else {
            this.fenetre.cacherPanneauPI();
            //this.fenetre.cacherTablePI();
            this.fenetre.afficherOuCacherMessageLivraison(true);
        }

    }

}
