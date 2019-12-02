/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author acer
 */
public class AffichagePIs extends AbstractTableModel{

    private final ArrayList<LignePI> listePIs;
    private final String header[];
    private int ligneSelect;
    private FormatCellRenderer formatcell;

    public AffichagePIs(FormatCellRenderer formatcell) {
        this.listePIs = new ArrayList<>();
        this.header = new String[]{"Numéro","Type","Rue","Modifier","Supprimer"};
        this.ligneSelect = -1;
        this.formatcell = formatcell;
    }
    
     @Override
    public int getRowCount() {
        return this.listePIs.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return this.listePIs.get(rowIndex).getNumero();
            case 1:
                return this.listePIs.get(rowIndex).getType();
            case 2:
                return this.listePIs.get(rowIndex).getRue();
            
            default:
                return null; 
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }
    
    public int getLigneSelect(){
        return this.ligneSelect;
    }
    
    public void setLigneSelect(int ligne){
        this.ligneSelect = ligne;
    }

    public FormatCellRenderer getFormatcell() {
        return formatcell;
    }

    public void setFormatcell(FormatCellRenderer formatcell) {
        this.formatcell = formatcell;
    }

    public void addStep(LignePI pi) {
        this.listePIs.add(pi);

        this.fireTableRowsInserted(this.listePIs.size() - 1, this.listePIs.size() - 1);
    }

    public void removeStep(int rowIndex) {
        this.listePIs.remove(rowIndex);

        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void clearSteps() {
        this.listePIs.clear();
    }

    
}
