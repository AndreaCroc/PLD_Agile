/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modele.Tournee;

/**
 *
 * @author acer
 */
public class AffichageEtapes extends AbstractTableModel {

    private Tournee tournee;
    private final ArrayList<LigneEtapes> steps;
    private final String header[];

    public AffichageEtapes(Tournee tournee) {
        this.header = new String[]{"Numéro","Type","Rue","Arrivée prévue","Départ prévu","Durée prévue"};
        this.steps = new ArrayList<>();
        this.tournee = tournee;
    }

    @Override
    public int getRowCount() {
        return this.steps.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
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
            case 5 :
                 return this.steps.get(rowIndex).getDuree();
            default:
                return null; 
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public void addStep(LigneEtapes step) {
        this.steps.add(step);

        this.fireTableRowsInserted(this.steps.size() - 1, this.steps.size() - 1);
    }

    public void removeStep(int rowIndex) {
        this.steps.remove(rowIndex);

        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void clearSteps() {
        this.steps.clear();
    }

}
