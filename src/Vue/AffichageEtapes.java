/*
 * AffichageEtapes
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

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * Classe AffichageEtapes permet d afficher les details des etapes d une tournee
 * dans un tableau
 */
public class AffichageEtapes extends AbstractTableModel {

    private final ArrayList<LigneEtapes> steps;
    private final String header[];
    private int ligneSelect;
    private FormatCellRenderer formatcell;

    public AffichageEtapes(FormatCellRenderer format) {
        this.header = new String[]{"Numéro", "Type", "Rue", "Arrivée prévue", "Départ prévu", "Durée prévue"};
        this.steps = new ArrayList<>();
        this.ligneSelect = -1;
        this.formatcell = format;
    }

    /**
     * Recuperer le nombre de lignes que contient le tableau
     *
     * @return : le nombre de lignes du tableau
     */
    @Override
    public int getRowCount() {
        return this.steps.size();
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
                return this.steps.get(rowIndex).getNumero();
            case 1:
                return this.steps.get(rowIndex).getType();
            case 2:
                return this.steps.get(rowIndex).getRue();
            case 3:
                return this.steps.get(rowIndex).getArrivee();
            case 4:
                return this.steps.get(rowIndex).getDepart();
            case 5:
                return this.steps.get(rowIndex).getDuree();
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

    /**
     * Ajouter une ligne au tableau
     * 
     * @param step : ligne a ajouter
     */
    public void addStep(LigneEtapes step) {
        this.steps.add(step);

        //Prevenir le tableau qu une ligne a ete ajoutee a la liste
        this.fireTableRowsInserted(this.steps.size() - 1, this.steps.size() - 1);
    }

    /**
     * Supprimer une ligne du tableau
     * 
     * @param rowIndex : numero de la ligne a supprimer
     */
    public void removeStep(int rowIndex) {
        this.steps.remove(rowIndex);

        //Prevenir le tableau qu une ligne a ete supprimee de la liste
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * Supprimer tous les elements de la liste associee a un tableau
     */
    public void clearSteps() {
        this.steps.clear();
    }

}
