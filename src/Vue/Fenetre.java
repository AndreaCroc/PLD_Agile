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

import controleur.Controleur;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

    private Controleur controleur;
    
    private static final long serialVersionUID = 1L;
    protected final static String CHARGER_CARTE = "Charger carte";
    protected final static String CHARGER_LIVRAISONS = "Charger livraisons";
    protected final static String CALCULER_TOURNEE = "Calculer tournee";
    
    protected final static String HEURE_DEBUT = "Heure de début prévue : ";
    protected final static String HEURE_FIN = "Heure de fin prévue : ";
    protected final static String DUREE = "Durée prévue : ";
    
    protected final static String ETAPE = "Etape";
    protected final static String TYPE = "Type : ";
    protected final static String ADRESSE = "Adresse : ";
    protected final static String HEURE_ARRIVEE = "Heure d'arrivée prévue : ";

    private JButton boutonChargerCarte;
    private JButton boutonChargerLivraisons;
    private JButton boutonCalculerTournee;

    private JLabel livraisons;
    private JLabel tournee;
    private JLabel legende;
    private JLabel repChargCarte;
    private JLabel repChargeLiv;
    private JLabel heureDeb;
    private JLabel heureFin;
    private JLabel duree;
    
    private JTextField inputChargeCarte;
    private JTextField inputChargeLiv;
    
    private JPanel panneauGlobal1, panneauGlobal2, panneauGauche, panneauDroite, panneauLivraisons, panneauEtape, panneauLegende, panneauCarte, panneauTournee;
    
    private EcouteurBoutons ecouteurBoutons;
    
    public Fenetre(Controleur controleur) {
        
        this.setLayout(null);
        this.setTitle("OptIFmodLyon");
        Toolkit outil = getToolkit();
        this.setSize(outil.getScreenSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.controleur = controleur;
        this.ecouteurBoutons = new EcouteurBoutons(this.controleur);
        
        panneauGauche = new JPanel();
        panneauGauche.setLayout(null);
        panneauGauche.setBackground(Color.yellow);
        
        panneauLivraisons = new JPanel();
        panneauLivraisons.setLayout(null);
        panneauLivraisons.setBackground(Color.red);
        
        inputChargeLiv = new JTextField();
        
        boutonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
        boutonChargerLivraisons.setFont(new Font("Arial", Font.BOLD, 16));
        boutonChargerLivraisons.setForeground(Color.white);
        boutonChargerLivraisons.setBackground(new Color(50,70,120));
        boutonChargerLivraisons.addActionListener(ecouteurBoutons);
        
        repChargeLiv = new JLabel("Erreur dans le chargement du fichier");
        repChargeLiv.setFont(new Font("Arial", Font.BOLD, 16));
        repChargeLiv.setForeground(Color.white);
        
        boutonCalculerTournee = new JButton(CALCULER_TOURNEE);
        boutonCalculerTournee.setFont(new Font("Arial", Font.BOLD, 16));
        boutonCalculerTournee.setForeground(Color.white);
        boutonCalculerTournee.setBackground(new Color(50,70,120));
        boutonCalculerTournee.addActionListener(ecouteurBoutons);
        
        livraisons = new JLabel("Livraisons");
        livraisons.setFont(new Font("Arial", Font.BOLD, 18));
        livraisons.setForeground(Color.white);
        
        panneauLivraisons.add(livraisons);
        panneauLivraisons.add(inputChargeLiv);
        panneauLivraisons.add(boutonChargerLivraisons);
        panneauLivraisons.add(boutonCalculerTournee);
        panneauLivraisons.add(repChargeLiv);
        panneauGauche.add(panneauLivraisons);
        
        tournee = new JLabel("Tournee");
        tournee.setFont(new Font("Arial", Font.BOLD, 18));
        tournee.setForeground(Color.white);
        
        heureDeb = new JLabel(HEURE_DEBUT);
        heureDeb.setFont(new Font("Arial", Font.BOLD, 16));
        heureDeb.setForeground(Color.white);
        
        heureFin = new JLabel(HEURE_FIN);
        heureFin.setFont(new Font("Arial", Font.BOLD, 16));
        heureFin.setForeground(Color.white);
        
        duree = new JLabel(DUREE);
        duree.setFont(new Font("Arial", Font.BOLD, 16));
        duree.setForeground(Color.white);
        
        panneauTournee = new JPanel();
        panneauTournee.setLayout(null);
        panneauTournee.setBackground(Color.cyan);
        panneauTournee.add(tournee);
        panneauTournee.add(heureDeb);
        panneauTournee.add(heureFin);
        panneauTournee.add(duree);
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
        panneauGlobal2.setVisible(true);
        
        // Conteneur 1
        inputChargeCarte = new JTextField();
   
        repChargCarte = new JLabel("Erreur dans le chargement du fichier");
        repChargCarte.setFont(new Font("Arial", Font.BOLD, 16));
        repChargCarte.setForeground(Color.white);
        
        boutonChargerCarte = new JButton(CHARGER_CARTE);
        boutonChargerCarte.setFont(new Font("Arial", Font.BOLD, 16));
        boutonChargerCarte.setForeground(Color.white);
        boutonChargerCarte.setBackground(new Color(50,70,120));
        boutonChargerCarte.addActionListener(ecouteurBoutons);
        
        panneauGlobal1 = new JPanel();
        panneauGlobal1.setLayout(null);
        panneauGlobal1.setBackground(Color.BLACK);
        panneauGlobal1.add(inputChargeCarte);
        panneauGlobal1.add(boutonChargerCarte);
        panneauGlobal1.add(repChargCarte);
        //this.setContentPane(panneauGlobal1);
        panneauGlobal1.setVisible(false);
        
        placeObjet1();
        placeObjet2();

    }
    public void placeObjet1() {
        panneauGlobal1.setBounds(0, 0, ((int) getSize().width), ((int) getSize().height));
        
        inputChargeCarte.setBounds(1 * (int) panneauGlobal1.getWidth()/4, 1 * (int) panneauGlobal1.getHeight()/3, 1 * (int) panneauGlobal1.getWidth()/4, 1 * (int) panneauGlobal1.getHeight()/20);
        boutonChargerCarte.setBounds(55 * ((int) panneauGlobal1.getWidth()/100), 1 * (int) panneauGlobal1.getHeight()/3, 1 * (int) panneauGlobal1.getWidth()/8, 1 * (int) panneauGlobal1.getHeight()/20);
        repChargCarte.setBounds(35 * (int) panneauGlobal1.getWidth()/100, 38 * (int) panneauGlobal1.getHeight()/100, 1 * (int) panneauGlobal1.getWidth()/4, 1 * (int) panneauGlobal1.getHeight()/10);
     }
    
     public void placeObjet2() {
        panneauGlobal2.setBounds(0, 0, ((int) getSize().width), ((int) getSize().height));
        panneauGauche.setBounds(0, 0, (int)panneauGlobal2.getWidth()/3, (int)panneauGlobal2.getHeight());
        panneauDroite.setBounds(1 * (int) panneauGlobal2.getWidth()/3, 0, 2 *(int) panneauGlobal2.getWidth()/3, 1 * (int) panneauGlobal2.getHeight());
        panneauLivraisons.setBounds(0, 0, (int) panneauGauche.getWidth(), 1 * (int) panneauGauche.getHeight() / 4);
        panneauTournee.setBounds(0, 1 * (int) panneauGauche.getHeight() / 4, 1 * ((int) panneauGauche.getWidth()), 1 * (int) panneauGauche.getHeight()/ 6);
        panneauEtape.setBounds(0, 10 * (int) panneauGauche.getHeight() / 24, 1 * ((int) panneauGauche.getWidth()), 14 * (int) panneauGauche.getHeight()/ 24);
        panneauLegende.setBounds(0, 0, (int) panneauDroite.getWidth(), 1 * (int) panneauDroite.getHeight()/ 4);
        panneauCarte.setBounds(0, 1 * (int)  panneauDroite.getHeight() / 4, 1 * (int) panneauDroite.getWidth(), 3 * (int) panneauDroite.getHeight() / 4);
        
        legende.setBounds(1 * (int) panneauLegende.getWidth()/10, 0, 1 * (int) panneauLegende.getWidth(), 1 * (int) panneauLegende.getHeight()/10);
        
        livraisons.setBounds(4 * ((int) panneauLivraisons.getWidth()/10), 0, 1 * (int) panneauLivraisons.getWidth(), 1 * (int) panneauLivraisons.getHeight()/10);
        inputChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth()/4, 1 * (int) panneauLivraisons.getHeight()/4, 1 * (int) panneauLivraisons.getWidth()/4, 1 * (int) panneauLivraisons.getHeight()/6);
        boutonChargerLivraisons.setBounds(55 * ((int) panneauLivraisons.getWidth()/100), 1 * (int) panneauLivraisons.getHeight()/4, 1 * (int) panneauLivraisons.getWidth()/3, 1 * (int) panneauLivraisons.getHeight()/6);
        boutonCalculerTournee.setBounds(1 * ((int) panneauLivraisons.getWidth()/3), 2 * (int) panneauLivraisons.getHeight()/3, 1 * (int) panneauLivraisons.getWidth()/3, 1 * (int) panneauLivraisons.getHeight()/6);
        repChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth()/4, 4 * (int) panneauLivraisons.getHeight()/10, 1 * (int) panneauLivraisons.getWidth()/2, 1 * (int) panneauLivraisons.getHeight()/6);
        
        tournee.setBounds(4 * (int) panneauTournee.getWidth()/10, 0, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight()/10);
        heureDeb.setBounds(0, 1 * (int) panneauTournee.getHeight()/5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight()/10);
        heureFin.setBounds(0, 2 * (int) panneauTournee.getHeight()/5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight()/10);
        duree.setBounds(0, 3 * (int) panneauTournee.getHeight()/5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight()/10);
     }
}
