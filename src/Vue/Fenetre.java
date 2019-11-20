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

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

    private static final long serialVersionUID = 1L;
    protected final static String CHARGER_CARTE = "Charger carte";
    protected final static String CHARGER_LIVRAISONS = "Charger livraisons";
    protected final static String CALCULER_TOURNEE = "Calculer tournee";

    private JButton boutonChargerCarte;
    private JButton boutonChargerLivraisons;
    private JButton boutonCalculerTournee;

    private JLabel livraisons;
    private JLabel legende;
    private JLabel repChargCarte;
    private JLabel repChargLiv;
    
    private JTextField inputChargeCarte;

    private JPanel panneauGlobal1, panneauGlobal2, panneauGauche, panneauDroite, panneauTournee, panneauEtape, panneauLegende, panneauCarte;
    
    public Fenetre() {
        this.setLayout(null);
        this.setTitle("OptIFmodLyon");
        Toolkit outil = getToolkit();
        this.setSize(outil.getScreenSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panneauGauche = new JPanel();
        panneauGauche.setLayout(null);
        panneauGauche.setBackground(Color.yellow);
        
        panneauTournee = new JPanel();
        panneauTournee.setLayout(null);
        panneauTournee.setBackground(Color.red);
        
        livraisons = new JLabel("Livraisons");
        livraisons.setFont(new Font("Arial", Font.BOLD, 18));
        livraisons.setForeground(Color.white);
        panneauTournee.add(livraisons);
        panneauGauche.add(panneauTournee);
        
        panneauEtape = new JPanel();
        panneauEtape.setLayout(null);
        panneauEtape.setBackground(Color.green);
        panneauGauche.add(panneauEtape);
        
        panneauDroite = new JPanel();
        panneauDroite.setLayout(null);
        panneauDroite.setBackground(Color.blue);
        
        panneauLegende = new JPanel();
        panneauLegende.setLayout(null);
        panneauLegende.setBackground(Color.ORANGE);
        
        legende = new JLabel("Legende");
        legende.setFont(new Font("Arial", Font.BOLD, 18));
        legende.setForeground(Color.white);
        panneauLegende.add(legende);
        panneauDroite.add(panneauLegende);
        
        panneauCarte = new JPanel();
        panneauCarte.setLayout(null);
        panneauCarte.setBackground(Color.pink);
        panneauDroite.add(panneauCarte);
        
        panneauGlobal2 = new JPanel();
        panneauGlobal2.setLayout(null);
        panneauGlobal2.setBackground(Color.BLACK);
        panneauGlobal2.add(panneauGauche);
        panneauGlobal2.add(panneauDroite);
        this.setContentPane(panneauGlobal2);
        panneauGlobal2.setVisible(false);
        
        // Conteneur 1
        inputChargeCarte = new JTextField();
   
        repChargCarte = new JLabel("Coucou");
        repChargCarte.setFont(new Font("Arial", Font.BOLD, 18));
        repChargCarte.setForeground(Color.white);
        
        boutonChargerCarte = new JButton(CHARGER_CARTE);
        boutonChargerCarte.setFont(new Font("Arial", Font.BOLD, 16));
        boutonChargerCarte.setForeground(Color.white);
        boutonChargerCarte.setBackground(new Color(50,70,120));
        
        panneauGlobal1 = new JPanel();
        panneauGlobal1.setLayout(null);
        panneauGlobal1.setBackground(Color.BLACK);
        panneauGlobal1.add(inputChargeCarte);
        panneauGlobal1.add(boutonChargerCarte);
        panneauGlobal1.add(repChargCarte);
        this.setContentPane(panneauGlobal1);
        panneauGlobal1.setVisible(true);
        
        placeObjet1();
        placeObjet2();

    }
    public void placeObjet1() {
        panneauGlobal1.setBounds(0, 0, ((int) getSize().width), ((int) getSize().height));
        
        inputChargeCarte.setBounds(1 * (int) panneauGlobal1.getWidth()/4, 1 * (int) panneauGlobal1.getHeight()/3, 1 * (int) panneauGlobal1.getWidth()/4, 1 * (int) panneauGlobal1.getHeight()/20);
        boutonChargerCarte.setBounds(2 * ((int) panneauGlobal1.getWidth()/3), 1 * (int) panneauGlobal1.getHeight()/3, 1 * (int) panneauGlobal1.getWidth()/8, 1 * (int) panneauGlobal1.getHeight()/20);
        repChargCarte.setBounds(61 * (int) panneauGlobal1.getWidth()/100, 1 * (int) panneauGlobal1.getWidth()/4, 1 * (int) panneauGlobal1.getWidth()/4, 1 * (int) panneauGlobal1.getHeight()/10);
     }
    
     public void placeObjet2() {
        panneauGlobal2.setBounds(0, 0, ((int) getSize().width), ((int) getSize().height));
        panneauGauche.setBounds(0, 0, (int)panneauGlobal2.getWidth()/3, (int)panneauGlobal2.getHeight());
        panneauDroite.setBounds(1 * (int) panneauGlobal2.getWidth()/3, 0, 2 *(int) panneauGlobal2.getWidth()/3, 1 * (int) panneauGlobal2.getHeight());
        panneauTournee.setBounds(0, 0, (int) panneauGauche.getWidth(), 1 * (int) panneauGauche.getHeight() / 4);
        panneauEtape.setBounds(0, 1 * (int) panneauGauche.getHeight() / 4, 1 * ((int) panneauGauche.getWidth()), 3 * (int) panneauGauche.getHeight()/ 4);
        panneauLegende.setBounds(0, 0, (int) panneauDroite.getWidth(), 1 * (int) panneauDroite.getHeight()/ 4);
        panneauCarte.setBounds(0, 1 * (int)  panneauDroite.getHeight() / 4, 1 * (int) panneauDroite.getWidth(), 3 * (int) panneauDroite.getHeight() / 4);
        
        legende.setBounds(1 * (int) panneauLegende.getWidth()/10, 0, 1 * (int) panneauLegende.getWidth(), 1 * (int) panneauLegende.getHeight()/10);
        livraisons.setBounds(4 * ((int) panneauTournee.getWidth()/10), 0, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight()/10);
     }
}
