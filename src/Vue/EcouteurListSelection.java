/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import controleur.Controleur;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author acer
 */
public class EcouteurListSelection implements ListSelectionListener {

    private Controleur controleur;

    public EcouteurListSelection(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        
        if (!lsm.isSelectionEmpty()&&!e.getValueIsAdjusting()) {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    controleur.surbrillancePI(i);
                }
            }
        }

    }

}
