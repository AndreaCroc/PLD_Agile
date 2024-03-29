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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

    private Controleur controleur;

    private static final long serialVersionUID = 1L;
    protected final static String CHARGER_CARTE = "Charger carte";
    protected final static String CHARGER_LIVRAISONS = "Charger livraisons";
    protected final static String CALCULER_TOURNEE = "Calculer tournée";

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
    private JLabel dureeTournee;
    private JLabel etapesTitre;
    private JLabel labelRond;
    private JLabel legendeRond;
    private JLabel labelCarre;
    private JLabel legendeCarre;
    private JLabel labelTriangle;
    private JLabel legendeTriangle;
    
    private JTextArea etape;
    private JScrollPane scrollEtapes;

    private JTextField inputChargeCarte;
    private JTextField inputChargeLiv;

    private ImageIcon rond;
    private ImageIcon carre;
    private ImageIcon triangle;

    private JPanel panneauGlobal1;
    private JPanel panneauGlobal2;
    private JPanel panneauDroite;
    private JPanel panneauLivraisons;
    private JPanel panneauEtape;
    private JPanel panneauLegende;
    private JPanel panneauCarte;
    private JPanel panneauTournee;
    private JPanel panneauGauche;

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
        boutonChargerLivraisons.setBackground(new Color(50, 70, 120));
        boutonChargerLivraisons.addActionListener(ecouteurBoutons);

        repChargeLiv = new JLabel("Erreur dans le chargement du fichier");
        repChargeLiv.setFont(new Font("Arial", Font.BOLD, 16));
        repChargeLiv.setForeground(new Color(254, 79, 65));
        repChargeLiv.setVisible(false);

        boutonCalculerTournee = new JButton(CALCULER_TOURNEE);
        boutonCalculerTournee.setFont(new Font("Arial", Font.BOLD, 16));
        boutonCalculerTournee.setForeground(Color.white);
        boutonCalculerTournee.setBackground(new Color(50, 70, 120));
        boutonCalculerTournee.setEnabled(false);
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

        tournee = new JLabel("Tournée");
        tournee.setFont(new Font("Arial", Font.BOLD, 18));
        tournee.setForeground(Color.white);

        heureDeb = new JLabel(HEURE_DEBUT);
        heureDeb.setFont(new Font("Arial", Font.BOLD, 16));
        heureDeb.setForeground(Color.white);

        heureFin = new JLabel(HEURE_FIN);
        heureFin.setFont(new Font("Arial", Font.BOLD, 16));
        heureFin.setForeground(Color.white);

        dureeTournee = new JLabel(DUREE);
        dureeTournee.setFont(new Font("Arial", Font.BOLD, 16));
        dureeTournee.setForeground(Color.white);

        panneauTournee = new JPanel();
        panneauTournee.setLayout(null);
        panneauTournee.setBackground(Color.cyan);
        panneauTournee.add(tournee);
        panneauTournee.add(heureDeb);
        panneauTournee.add(heureFin);
        panneauTournee.add(dureeTournee);
        panneauTournee.setVisible(false);
        panneauGauche.add(panneauTournee);

        etapesTitre = new JLabel("Etapes");
        etapesTitre.setFont(new Font("Arial", Font.BOLD, 18));
        etapesTitre.setForeground(Color.white);

        etape = new JTextArea();
        etape.setFont(new Font("Arial", Font.BOLD, 16));
        etape.setForeground(Color.gray);
        etape.setEditable(false);
        etape.setOpaque(false);
        etape.setLineWrap(true);

        panneauEtape = new JPanel();
        panneauEtape.setLayout(null);
        panneauEtape.setBackground(Color.green);
        panneauEtape.add(etapesTitre);
        panneauEtape.add(etape);

        scrollEtapes = new JScrollPane(panneauEtape, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollEtapes.setVisible(false);
        panneauGauche.add(scrollEtapes);

        panneauDroite = new JPanel();
        panneauDroite.setLayout(null);
        panneauDroite.setBackground(Color.blue);

        rond = new ImageIcon("rond-noir.jpg");
        Image imgR = rond.getImage();
        Image newimgR = imgR.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        rond = new ImageIcon(newimgR);
        
        labelRond = new JLabel(rond);
        
        legendeRond = new JLabel(": Point de livraison");
        legendeRond.setFont(new Font("Arial", Font.BOLD, 16));
        legendeRond.setForeground(Color.white);
        
        carre = new ImageIcon("carre-noir.png");
        Image imgC = carre.getImage();
        Image newimgC = imgC.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        carre = new ImageIcon(newimgC);
        
        labelCarre = new JLabel(carre);
        
        legendeCarre = new JLabel(": Point d'enlèvement");
        legendeCarre.setFont(new Font("Arial", Font.BOLD, 16));
        legendeCarre.setForeground(Color.white);
        
        triangle = new ImageIcon("triangle-noir.png");
        Image imgT = triangle.getImage();
        Image newimgT = imgT.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        triangle = new ImageIcon(newimgT);
        
        labelTriangle = new JLabel(triangle);
        
        legendeTriangle = new JLabel(": Dépôt des vélos, point de départ de tous les livreurs");
        legendeTriangle.setFont(new Font("Arial", Font.BOLD, 16));
        legendeTriangle.setForeground(Color.white);

        panneauLegende = new JPanel();
        panneauLegende.setLayout(null);
        panneauLegende.add(labelRond);
        panneauLegende.add(legendeRond);
        panneauLegende.add(labelCarre);
        panneauLegende.add(legendeCarre);
        panneauLegende.add(labelTriangle);
        panneauLegende.add(legendeTriangle);
        panneauLegende.setBackground(Color.ORANGE);

        legende = new JLabel("Légende");
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

        // Conteneur 1
        inputChargeCarte = new JTextField();

        repChargCarte = new JLabel("Erreur dans le chargement du fichier");
        repChargCarte.setFont(new Font("Arial", Font.BOLD, 16));
        repChargCarte.setForeground(new Color(254, 79, 65));
        repChargCarte.setVisible(false);

        boutonChargerCarte = new JButton(CHARGER_CARTE);
        boutonChargerCarte.setFont(new Font("Arial", Font.BOLD, 16));
        boutonChargerCarte.setForeground(Color.white);
        boutonChargerCarte.setBackground(new Color(50, 70, 120));
        boutonChargerCarte.addActionListener(ecouteurBoutons);

        panneauGlobal1 = new JPanel();
        panneauGlobal1.setLayout(null);
        panneauGlobal1.setBackground(new Color(186, 228, 255));
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

        inputChargeCarte.setBounds(1 * (int) panneauGlobal1.getWidth() / 4, 1 * (int) panneauGlobal1.getHeight() / 3, 1 * (int) panneauGlobal1.getWidth() / 5, 1 * (int) panneauGlobal1.getHeight() / 20);
        boutonChargerCarte.setBounds(52 * ((int) panneauGlobal1.getWidth() / 100), 1 * (int) panneauGlobal1.getHeight() / 3, 1 * (int) panneauGlobal1.getWidth() / 8, 1 * (int) panneauGlobal1.getHeight() / 20);
        repChargCarte.setBounds(1 * (int) panneauGlobal1.getWidth() / 4, 35 * (int) panneauGlobal1.getHeight() / 100, 1 * (int) panneauGlobal1.getWidth() / 4, 1 * (int) panneauGlobal1.getHeight() / 10);
    }

    public void placeObjet2() {
        panneauGlobal2.setBounds(0, 0, ((int) getSize().width), ((int) getSize().height));
        panneauGauche.setBounds(0, 0, (int) panneauGlobal2.getWidth() / 3, (int) panneauGlobal2.getHeight());
        panneauDroite.setBounds(1 * (int) panneauGlobal2.getWidth() / 3, 0, 2 * (int) panneauGlobal2.getWidth() / 3, 1 * (int) panneauGlobal2.getHeight());
        panneauLivraisons.setBounds(0, 0, (int) panneauGauche.getWidth(), 1 * (int) panneauGauche.getHeight() / 4);
        panneauTournee.setBounds(0, 1 * (int) panneauGauche.getHeight() / 4, 1 * ((int) panneauGauche.getWidth()), 1 * (int) panneauGauche.getHeight() / 6);
        panneauEtape.setBounds(0, 10 * (int) panneauGauche.getHeight() / 24, 1 * ((int) panneauGauche.getWidth()), 14 * (int) panneauGauche.getHeight() / 24);
        panneauLegende.setBounds(0, 0, (int) panneauDroite.getWidth(), 1 * (int) panneauDroite.getHeight() / 4);
        panneauCarte.setBounds(0, 1 * (int) panneauDroite.getHeight() / 4, 1 * (int) panneauDroite.getWidth(), 3 * (int) panneauDroite.getHeight() / 4);

        legende.setBounds(1 * (int) panneauLegende.getWidth() / 10, 0, 1 * (int) panneauLegende.getWidth(), 1 * (int) panneauLegende.getHeight() / 10);

        livraisons.setBounds(4 * ((int) panneauLivraisons.getWidth() / 10), 0, 1 * (int) panneauLivraisons.getWidth(), 1 * (int) panneauLivraisons.getHeight() / 10);
        inputChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 6, 1 * (int) panneauLivraisons.getHeight() / 4, 2 * (int) panneauLivraisons.getWidth() / 4, 1 * (int) panneauLivraisons.getHeight() / 6);
        boutonChargerLivraisons.setBounds(55 * ((int) panneauLivraisons.getWidth() / 100), 1 * (int) panneauLivraisons.getHeight() / 4, 4 * (int) panneauLivraisons.getWidth() / 10, 1 * (int) panneauLivraisons.getHeight() / 6);
        boutonCalculerTournee.setBounds(1 * ((int) panneauLivraisons.getWidth() / 3), 2 * (int) panneauLivraisons.getHeight() / 3, 4 * (int) panneauLivraisons.getWidth() / 10, 1 * (int) panneauLivraisons.getHeight() / 6);
        repChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 6, 4 * (int) panneauLivraisons.getHeight() / 10, 1 * (int) panneauLivraisons.getWidth() / 2, 1 * (int) panneauLivraisons.getHeight() / 6);

        tournee.setBounds(4 * (int) panneauTournee.getWidth() / 10, 0, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 5);
        heureDeb.setBounds(0, 1 * (int) panneauTournee.getHeight() / 5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 5);
        heureFin.setBounds(0, 2 * (int) panneauTournee.getHeight() / 5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 5);
        dureeTournee.setBounds(0, 3 * (int) panneauTournee.getHeight() / 5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 5);

        etapesTitre.setBounds(4 * (int) panneauEtape.getWidth() / 10, 0, 1 * (int) panneauEtape.getWidth(), 1 * (int) panneauEtape.getHeight() / 20);
        etape.setBounds(0, 0, 1 * (int) panneauEtape.getWidth(), 1 * (int) panneauEtape.getHeight() / 5);
        scrollEtapes.setBounds(0, 10 * (int) panneauGauche.getHeight() / 24, 1 * ((int) panneauGauche.getWidth()), 14 * (int) panneauGauche.getHeight() / 24);
        
        labelRond.setBounds(0,(int)panneauLegende.getHeight()/5,(int)panneauLegende.getWidth()/25,(int)panneauLegende.getHeight()/5);
        legendeRond.setBounds((int)panneauLegende.getWidth()/25,(int)panneauLegende.getHeight()/5,(int)panneauLegende.getWidth()/5,(int)panneauLegende.getHeight()/5);
        labelCarre.setBounds(0,(int)2*panneauLegende.getHeight()/5,(int)panneauLegende.getWidth()/25,(int)panneauLegende.getHeight()/5);
        legendeCarre.setBounds((int)panneauLegende.getWidth()/25,(int)2*panneauLegende.getHeight()/5,(int)panneauLegende.getWidth()/5,(int)panneauLegende.getHeight()/5);
        labelTriangle.setBounds(0,(int)3*panneauLegende.getHeight()/5,(int)panneauLegende.getWidth()/25,(int)panneauLegende.getHeight()/5);
        legendeTriangle.setBounds((int)panneauLegende.getWidth()/25,(int)3*panneauLegende.getHeight()/5,(int)panneauLegende.getWidth()/2,(int)panneauLegende.getHeight()/5);
    }

    public void afficherConteneur2(boolean chargerCarte) {
        if (chargerCarte) {
            this.setContentPane(panneauGlobal2);
            panneauGlobal1.setVisible(false);
            panneauGlobal2.setVisible(true);
        } else {
            repChargCarte.setVisible(true);
        }
    }

    public void afficherBoutonCalcul(boolean chargerLivraison) {
        if (chargerLivraison) {
            boutonCalculerTournee.setEnabled(true);
        } else {
            repChargeLiv.setVisible(true);
        }

    }

    public void afficherEtapesTour(boolean calculTournee) {
        scrollEtapes.setVisible(true);
        panneauTournee.setVisible(true);
    }
}
