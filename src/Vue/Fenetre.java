/*
 * Fenetre
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package Vue;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fenetre extends JFrame {

    private static final long serialVersionUID = 1L;
    protected final static String CHARGER_CARTE = "Charger carte";
    protected final static String CHARGER_LIVRAISONS = "Charger livraisons";
    protected final static String CALCULER_TOURNEE = "Calculer tournee";

    private JButton boutonChargerCarte;
    private JButton boutonChargerLivraisons;
    private JButton boutonCalculerTournee;

    private JLabel titre;
    private JLabel livraisons;
    private JLabel legende;
    private JLabel repChargCarte;
    private JLabel repChargLiv;

    public Fenetre() {
        this.setLayout(null);
        this.setTitle("OptIFmodLyon");
        Toolkit outil = getToolkit();
        this.setSize(outil.getScreenSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
