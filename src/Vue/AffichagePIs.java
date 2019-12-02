/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import modele.Carte;
import modele.Chemin;
import modele.DemandesLivraisons;
import modele.PointInteret;
import modele.Troncon;

/**
 *
 * @author acer
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
        this.header = new String[]{"Numéro", "Type", "Rue", "Modifier", "Supprimer"};
        this.ligneSelect = -1;
        this.formatcell = formatcell;
        this.carte = carte;
        this.fenetre = fenetre;
    }

    @Override
    public int getRowCount() {
        return this.lignePIs.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.lignePIs.get(rowIndex).getNumero();
            case 1:
                return this.lignePIs.get(rowIndex).getType();
            case 2:
                return this.lignePIs.get(rowIndex).getRue();
            case 3:
                return this.lignePIs.get(rowIndex).getBoutonModifier();
            case 4:
                return this.lignePIs.get(rowIndex).getBoutonSupp();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 3:
                return JButton.class;
            case 4:
                return JButton.class;
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

    public void addPI(LignePI pi) {
        this.lignePIs.add(pi);

        this.fireTableRowsInserted(this.lignePIs.size() - 1, this.lignePIs.size() - 1);
    }

    public void removePI(int rowIndex) {
        this.lignePIs.remove(rowIndex);

        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void clearPIs() {
        this.lignePIs.clear();
    }

    public void setBouton(LignePI pi) {
        int i = this.lignePIs.indexOf(pi);
        this.lignePIs.get(i).getBoutonModifier().addActionListener(this.fenetre.getEcouteurBoutons());
        this.lignePIs.get(i).getBoutonSupp().addActionListener(this.fenetre.getEcouteurBoutons());

    }

    public void afficherPIs() {
        //ArrayList<PointInteret>listePIs = this.carte.getDemandesLivraisons().getListePointsInteret();
        DemandesLivraisons liste = this.carte.getDemandesLivraisons();
        ArrayList<PointInteret> listePIs = liste.getListePointsInteret();
        System.out.println("demandesLivraisons : " + listePIs.get(1).getDuree());
        String nomRue = "";
        int index = 0;

        for (PointInteret pt : listePIs) {

            //Recuperer le numero du point d interet
            index = listePIs.indexOf(pt);

            //Recuperer le point d'interet correspondant a l'entrepot
            String type = "";
            if (pt.isEnlevement()) {
                type = "Enlèvement";
            } else {
                type = "Livraison";
            }
            if (index != 0) {
                this.fenetre.setPanneauPIs(index, type, nomRue);
            }

        }
    }

}
