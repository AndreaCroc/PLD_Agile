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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modele.Carte;
import modele.Tournee;

/**
 *
 * Classe Fenetre permettant d'afficher notre application
 */
public class Fenetre extends JFrame {

    private Controleur controleur;
    private Carte carte;
    private Tournee tournee;
    private AffichageTournee vueTournee;
    private JCarte panneauCarte;

    //Constantes utilisee pour l affichage
    private static final long serialVersionUID = 1L;
    protected final static String CHARGER_CARTE = "Charger carte";
    protected final static String CHARGER_LIVRAISONS = "Charger livraisons";
    protected final static String CALCULER_TOURNEE = "Calculer tournée";

    protected final static String HEURE_DEBUT = "Heure de début prévue : ";
    protected final static String HEURE_FIN = "Heure de fin prévue : ";
    protected final static String DUREE = "Durée prévue : ";

    protected final static String ETAPE = "Etape ";
    protected final static String TYPE = "Type : ";
    protected final static String ADRESSE = "Adresse : ";
    protected final static String HEURE_DEPART = "Heure de départ prévue : ";
    protected final static String HEURE_ARRIVEE = "Heure d'arrivée prévue : ";

    //Boutons sur lesquels l utilisateur peut cliquer
    private JButton boutonChargerCarte;
    private JButton boutonChargerLivraisons;
    private JButton boutonCalculerTournee;

    //Labels pour afficher les donnees
    private JLabel livraisons;
    private JLabel labelTournee;
    private JLabel legende;
    private JLabel repChargeCarte;
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
    private JLabel titreAppli;

    //Pour afficher les details d une tournee
    private JTextArea etape;
    private JScrollPane scrollEtapes;

    //Pour ecrire en dur le fichier XML souhaite
    private JTextField inputChargeCarte;
    private JTextField inputChargeLiv;

    //Les 3 types d icones qui sont affichees sur la carte
    private ImageIcon rond;
    private ImageIcon carre;
    private ImageIcon triangle;

    //Les differents panneaux d affichage
    private JPanel panneauGlobal1;
    private JPanel panneauGlobal2;
    private JPanel panneauDroite;
    private JPanel panneauLivraisons;
    private JPanel panneauEtapes;
    private JPanel panneauLegende;
    private JPanel panneauTournee;
    private JPanel panneauGauche;

    //Pour reagir aux actions de l utilisateur
    private EcouteurBoutons ecouteurBoutons;

    public Fenetre(Controleur controleur, Carte carte, Tournee tournee) {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();

        //Mise en place des caracteristiques de la fenetre
        this.setLayout(null);
        this.setTitle("Opt'IFmodLyon");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.controleur = controleur;
        this.carte = carte;
        this.tournee = tournee;

        this.vueTournee = new AffichageTournee(tournee, this);

        this.ecouteurBoutons = new EcouteurBoutons(this.controleur);

        //Panneau gauche : contient panneauLivraison, panneauTournee
        panneauGauche = new JPanel();
        panneauGauche.setLayout(null);
        panneauGauche.setBackground(new Color(186, 228, 255));

        /* PanneauLivraison (haut gauche)*/
        panneauLivraisons = new JPanel();
        panneauLivraisons.setLayout(null);
        panneauLivraisons.setBackground(new Color(186, 228, 255));
        panneauLivraisons.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 70, 120)));

        //Input pour ecrire le nom du fichier XML souhaite
        inputChargeLiv = new JTextField();

        //Bouton pour demander a charger un fichier XML contenant des livraisons
        boutonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
        boutonChargerLivraisons.setFont(new Font("Arial", Font.BOLD, 14));
        boutonChargerLivraisons.setForeground(Color.white);
        boutonChargerLivraisons.setBackground(new Color(50, 70, 120));
        boutonChargerLivraisons.addActionListener(ecouteurBoutons);

        //Pour afficher les messages d erreur lies au chargement du fichier
        repChargeLiv = new JLabel("Erreur dans le chargement du fichier");
        repChargeLiv.setFont(new Font("Arial", Font.BOLD, 14));
        repChargeLiv.setForeground(new Color(254, 79, 65));
        repChargeLiv.setVisible(false);

        //Bouton pour calculer une tournee
        boutonCalculerTournee = new JButton(CALCULER_TOURNEE);
        boutonCalculerTournee.setFont(new Font("Arial", Font.BOLD, 14));
        boutonCalculerTournee.setForeground(Color.white);
        boutonCalculerTournee.setBackground(new Color(50, 70, 120));
        boutonCalculerTournee.setEnabled(false);
        boutonCalculerTournee.addActionListener(ecouteurBoutons);

        livraisons = new JLabel("Livraisons");
        livraisons.setFont(new Font("Arial", Font.BOLD, 18));
        livraisons.setForeground(Color.white);

        //Remplissage du panneauLivraison et ajout de ce dernier au panneauGauche
        panneauLivraisons.add(livraisons);
        panneauLivraisons.add(inputChargeLiv);
        panneauLivraisons.add(boutonChargerLivraisons);
        panneauLivraisons.add(boutonCalculerTournee);
        panneauLivraisons.add(repChargeLiv);
        panneauGauche.add(panneauLivraisons);

        /* Fin PanneauLivraison */
 /* PanneauTournee (milieu gauche) */
        //Titre de panneauTournee
        labelTournee = new JLabel("Tournée");
        labelTournee.setFont(new Font("Arial", Font.BOLD, 18));
        labelTournee.setForeground(Color.white);

        //Heure de debut de la tournee
        heureDeb = new JLabel(HEURE_DEBUT);
        heureDeb.setFont(new Font("Arial", Font.BOLD, 16));
        heureDeb.setForeground(Color.white);

        //Heure de fin de la tournee
        heureFin = new JLabel(HEURE_FIN);
        heureFin.setFont(new Font("Arial", Font.BOLD, 16));
        heureFin.setForeground(Color.white);

        //Duree de la tournee
        dureeTournee = new JLabel(DUREE);
        dureeTournee.setFont(new Font("Arial", Font.BOLD, 16));
        dureeTournee.setForeground(Color.white);

        //Ajout des elements a panneauTournee et ajout de ce dernier a panneauGauche
        panneauTournee = new JPanel();
        panneauTournee.setLayout(null);
        panneauTournee.setBackground(new Color(186, 228, 255));
        panneauTournee.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 70, 120)));
        panneauTournee.add(labelTournee);
        panneauTournee.add(heureDeb);
        panneauTournee.add(heureFin);
        panneauTournee.add(dureeTournee);
        panneauTournee.setVisible(false);
        panneauGauche.add(panneauTournee);

        /* Fin PanneauTournee*/
 /* PanneauEtape (bas gauche)*/
        //Titre de panneauEtape
        etapesTitre = new JLabel("Etapes");
        etapesTitre.setFont(new Font("Arial", Font.BOLD, 18));
        etapesTitre.setForeground(Color.white);

        //Espace contenant les etapes intermediaires d une tournee
        etape = new JTextArea();
        etape.setFont(new Font("Arial", Font.BOLD, 14));
        etape.setForeground(Color.gray);
        etape.setEditable(false);
        etape.setOpaque(false);
        etape.setLineWrap(true);

        scrollEtapes = new JScrollPane(etape, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //panneauGauche.add(scrollEtapes);

        //Ajout des elements a panneauEtapes et ajout de ce dernier a panneauGauche
        panneauEtapes = new JPanel();
        panneauEtapes.setLayout(null);
        panneauEtapes.setBackground(new Color(186, 228, 255));
        panneauEtapes.add(etapesTitre);
        panneauEtapes.add(scrollEtapes);
        panneauEtapes.setVisible(false);
        panneauGauche.add(panneauEtapes);

        panneauDroite = new JPanel();
        panneauDroite.setLayout(null);
        panneauDroite.setBackground(new Color(186, 228, 255));
        panneauDroite.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(50, 70, 120)));

        /* PanneauLegende(haut droit)*/
        //Logo rond de la legende
        rond = new ImageIcon("rond-noir.jpg");
        Image imgR = rond.getImage();
        Image newimgR = imgR.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        rond = new ImageIcon(newimgR);

        labelRond = new JLabel(rond);

        legendeRond = new JLabel(": Point de livraison");
        legendeRond.setFont(new Font("Arial", Font.BOLD, 14));
        legendeRond.setForeground(Color.white);

        //Logo carre de la legende
        carre = new ImageIcon("carre-noir.png");
        Image imgC = carre.getImage();
        Image newimgC = imgC.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        carre = new ImageIcon(newimgC);

        labelCarre = new JLabel(carre);

        legendeCarre = new JLabel(": Point d'enlèvement");
        legendeCarre.setFont(new Font("Arial", Font.BOLD, 14));
        legendeCarre.setForeground(Color.white);

        //Logo triangle de la legende
        triangle = new ImageIcon("triangle-noir.png");
        Image imgT = triangle.getImage();
        Image newimgT = imgT.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        triangle = new ImageIcon(newimgT);

        labelTriangle = new JLabel(triangle);

        legendeTriangle = new JLabel(": Dépôt des vélos, point de départ de tous les livreurs");
        legendeTriangle.setFont(new Font("Arial", Font.BOLD, 14));
        legendeTriangle.setForeground(Color.white);

        //Ajout des elements a panneauLegende et ajout de ce dernier a panneauDroit
        panneauLegende = new JPanel();
        panneauLegende.setLayout(null);
        panneauLegende.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, new Color(50, 70, 120)));
        panneauLegende.add(labelRond);
        panneauLegende.add(legendeRond);
        panneauLegende.add(labelCarre);
        panneauLegende.add(legendeCarre);
        panneauLegende.add(labelTriangle);
        panneauLegende.add(legendeTriangle);
        panneauLegende.setBackground(new Color(186, 228, 255));

        legende = new JLabel("Légende");
        legende.setFont(new Font("Arial", Font.BOLD, 18));
        legende.setForeground(Color.white);
        panneauLegende.add(legende);
        panneauDroite.add(panneauLegende);

        /* Fin PanneauLegende */
 /* PanneauCarte (bas droit) */
        panneauCarte = new JCarte(this.carte, this.tournee);
        panneauCarte.setLayout(null);
        panneauCarte.setBackground(Color.white);
        panneauCarte.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(50, 70, 120)));
        panneauCarte.setSize((int) (this.getWidth() * 0.95), (int) (this.getHeight() * 0.2));
        //panneauCarte.setBounds((int)(this.getWidth()*0.3), (int)(this.getHeight()*0.3), (int)(this.getWidth()*0.8), (int)(this.getHeight()*0.8));
        panneauDroite.add(panneauCarte);

        /* Fin panneauCarte */

 /* PanneauGlobal2 : pour la deuxieme fenetre*/
        panneauGlobal2 = new JPanel();
        panneauGlobal2.setLayout(null);
        panneauGlobal2.setBackground(new Color(186, 228, 255));
        panneauGlobal2.add(panneauGauche);
        panneauGlobal2.add(panneauDroite);

        /* Fin PanneauGlobal2 */

 /* PanneauGlobal1 : pour la premiere fenetre*/
        //Pour afficher le titre de l application
        titreAppli = new JLabel("Bienvenue sur Opt'IFmodLyon");
        titreAppli.setFont(new Font("Arial", Font.BOLD, 40));
        titreAppli.setForeground(Color.white);

        //Pour entrer le chemin vers un fichier XML
        inputChargeCarte = new JTextField();

        //Pour afficher une erreur en cas de probleme lors du chargement du fichier
        repChargeCarte = new JLabel("Erreur dans le chargement du fichier");
        repChargeCarte.setFont(new Font("Arial", Font.BOLD, 16));
        repChargeCarte.setForeground(new Color(254, 79, 65));
        repChargeCarte.setVisible(false);

        //Bouton pour lancer le chargement d une carte, fichier XML
        boutonChargerCarte = new JButton(CHARGER_CARTE);
        boutonChargerCarte.setFont(new Font("Arial", Font.BOLD, 16));
        boutonChargerCarte.setForeground(Color.white);
        boutonChargerCarte.setBackground(new Color(50, 70, 120));
        boutonChargerCarte.addActionListener(ecouteurBoutons);

        //On ajoute tout au panneauGlobal1
        panneauGlobal1 = new JPanel();
        panneauGlobal1.setLayout(null);
        panneauGlobal1.setBackground(new Color(186, 228, 255));
        panneauGlobal1.add(titreAppli);
        panneauGlobal1.add(inputChargeCarte);
        panneauGlobal1.add(boutonChargerCarte);
        panneauGlobal1.add(repChargeCarte);
        this.setContentPane(panneauGlobal1);
        panneauGlobal1.setVisible(true);

        /* Fin PanneauGlobal1 */
        //On place les elements
        placeObjet1();
        placeObjet2();

    }

    /**
     * Placement des elements de la premiere fenetre
     */
    public void placeObjet1() {
        panneauGlobal1.setBounds(0, 0, ((int) getSize().width), ((int) getSize().height));

        titreAppli.setBounds(1 * (int) panneauGlobal1.getWidth() / 3, 1 * (int) panneauGlobal1.getHeight() / 6, 1 * (int) panneauGlobal1.getWidth() / 2, 1 * (int) panneauGlobal1.getHeight() / 10);
        inputChargeCarte.setBounds(1 * (int) panneauGlobal1.getWidth() / 4, 1 * (int) panneauGlobal1.getHeight() / 3, 1 * (int) panneauGlobal1.getWidth() / 4, 1 * (int) panneauGlobal1.getHeight() / 20);
        boutonChargerCarte.setBounds(55 * ((int) panneauGlobal1.getWidth() / 100), 1 * (int) panneauGlobal1.getHeight() / 3, 1 * (int) panneauGlobal1.getWidth() / 8, 1 * (int) panneauGlobal1.getHeight() / 20);
        repChargeCarte.setBounds(1 * (int) panneauGlobal1.getWidth() / 4, 35 * (int) panneauGlobal1.getHeight() / 100, 1 * (int) panneauGlobal1.getWidth() / 4, 1 * (int) panneauGlobal1.getHeight() / 10);
    }

    /**
     * Placement des elements de la deuxieme fenetre
     */
    public void placeObjet2() {
        panneauGlobal2.setBounds(0, 0, ((int) getSize().width), ((int) getSize().height));
        panneauGauche.setBounds(0, 0, 47 * (int) panneauGlobal2.getWidth() / 100, (int) panneauGlobal2.getHeight());
        panneauDroite.setBounds(47 * (int) panneauGlobal2.getWidth() / 100, 0, 53 * (int) panneauGlobal2.getWidth() / 100, 1 * (int) panneauGlobal2.getHeight());
        panneauLivraisons.setBounds(0, 0, (int) panneauGauche.getWidth(), 1 * (int) panneauGauche.getHeight() / 4);
        panneauTournee.setBounds(0, 1 * (int) panneauGauche.getHeight() / 4, 1 * ((int) panneauGauche.getWidth()), 1 * (int) panneauGauche.getHeight() / 6);
        panneauEtapes.setBounds(0, 10 * (int) panneauGauche.getHeight() / 24, 1 * ((int) panneauGauche.getWidth()), 13 * (int) panneauGauche.getHeight() / 24);
        panneauLegende.setBounds(0, 0, (int) panneauDroite.getWidth(), 1 * (int) panneauDroite.getHeight() / 10);

        int largeurCarte = (int) panneauDroite.getHeight() - (int) panneauLegende.getHeight();
        panneauCarte.setBounds(0, 1 * (int) panneauDroite.getHeight() / 10, largeurCarte, 81 * (int) panneauDroite.getHeight() / 100);

        legende.setBounds(1 * (int) panneauLegende.getWidth() / 10, 0, 1 * (int) panneauLegende.getWidth(), 1 * (int) panneauLegende.getHeight() / 4);
        labelRond.setBounds(0, (int) panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 25, (int) panneauLegende.getHeight() / 4);
        legendeRond.setBounds((int) panneauLegende.getWidth() / 25, (int) panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 5, (int) panneauLegende.getHeight() / 4);
        labelCarre.setBounds(0, (int) 2 * panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 25, (int) panneauLegende.getHeight() / 4);
        legendeCarre.setBounds((int) panneauLegende.getWidth() / 25, (int) 2 * panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 5, (int) panneauLegende.getHeight() / 4);
        labelTriangle.setBounds(0, (int) 3 * panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 25, (int) panneauLegende.getHeight() / 4);
        legendeTriangle.setBounds((int) panneauLegende.getWidth() / 25, (int) 3 * panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 2, (int) panneauLegende.getHeight() / 4);


        livraisons.setBounds(4 * ((int) panneauLivraisons.getWidth() / 10), 0, 1 * (int) panneauLivraisons.getWidth(), 1 * (int) panneauLivraisons.getHeight() / 10);
        inputChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 20, 1 * (int) panneauLivraisons.getHeight() / 5, 1 * (int) panneauLivraisons.getWidth() / 2, 1 * (int) panneauLivraisons.getHeight() / 6);
        boutonChargerLivraisons.setBounds(60 * ((int) panneauLivraisons.getWidth() / 100), 1 * (int) panneauLivraisons.getHeight() / 5, 1 * (int) panneauLivraisons.getWidth() / 4, 1 * (int) panneauLivraisons.getHeight() / 6);
        boutonCalculerTournee.setBounds(1 * ((int) panneauLivraisons.getWidth() / 3), 1 * (int) panneauLivraisons.getHeight() / 2, 1 * (int) panneauLivraisons.getWidth() / 4, 1 * (int) panneauLivraisons.getHeight() / 6);
        repChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 20, 35 * (int) panneauLivraisons.getHeight() / 100, 1 * (int) panneauLivraisons.getWidth(), 1 * (int) panneauLivraisons.getHeight() / 6);

        labelTournee.setBounds(4 * (int) panneauTournee.getWidth() / 10, 0, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 5);
        heureDeb.setBounds(0, 1 * (int) panneauTournee.getHeight() / 5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 5);
        heureFin.setBounds(0, 2 * (int) panneauTournee.getHeight() / 5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 5);
        dureeTournee.setBounds(0, 3 * (int) panneauTournee.getHeight() / 5, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 5);

        etapesTitre.setBounds(4 * (int) panneauEtapes.getWidth() / 10, 0, 1 * (int) panneauEtapes.getWidth(), 1 * (int) panneauEtapes.getHeight() / 20);
        etape.setBounds(0, 1 * (int) panneauEtapes.getHeight() / 20, 1 * (int) panneauEtapes.getWidth(), 9 * (int) panneauEtapes.getHeight() / 10);
        //scrollEtapes.setBounds(0, 10 * (int) panneauGauche.getHeight() / 24, 1 * ((int) panneauGauche.getWidth()), 14 * (int) panneauGauche.getHeight() / 24);
        scrollEtapes.setBounds(0, 1 * (int) panneauEtapes.getHeight() / 20, 1 * (int) panneauEtapes.getWidth(), 9 * (int) panneauEtapes.getHeight() / 10);

    }

    /**
     * Pour passer de la premiere fenetre a la deuxieme fenetre
     */
    public void afficherConteneur2() {
        repChargeCarte.setVisible(false);
        repChargeLiv.setVisible(false);
        this.setContentPane(panneauGlobal2);
        panneauGlobal1.setVisible(false);
        panneauGlobal2.setVisible(true);
        Toolkit outil = getToolkit();
        this.setSize(outil.getScreenSize());
    }

    /**
     * Pour afficher le bouton pour calculer une tournee
     */
    public void afficherBoutonCalcul() {
        boutonCalculerTournee.setEnabled(true);
    }

    /**
     * Pour afficher les details d une tournee
     */
    public void afficherEtapesTour() {

        //scrollEtapes.setVisible(true);
        panneauEtapes.setVisible(true);
        panneauTournee.setVisible(true);
        vueTournee.setTournee(tournee);
        vueTournee.afficherTournee();

    }

    /**
     * Afficher un message d erreur lors d un chargement d une carte
     *
     * @param message qui est le contenu du message d erreur
     */
    public void afficherMessageErreur1(String message) {
        repChargeCarte.setText(message);
        repChargeCarte.setVisible(true);
    }

    /**
     * Afficher un message d erreur lors d un chargement des livraisons
     *
     * @param message qui est le contenu du message d erreur
     */
    public void afficherMessageErreur2(String message) {
        repChargeLiv.setText(message);
        repChargeLiv.setVisible(true);
    }

    public JCarte getPanneauCarte() {
        return this.panneauCarte;
    }

    public void setPanneauCarte(JCarte nouvelleCarte) {
        this.panneauCarte = nouvelleCarte;
        this.panneauCarte.updateUI();

    }

    /**
     * Pour afficher des donnees globales liees a une tournee
     *
     * @param heureDeb heure de debut de la tournee
     * @param heureFin heure de fin de la tournee
     * @param duree duree de la tournee
     */
    public void setPanneauTournee(String heureDeb, String heureFin, String duree) {
        this.heureDeb.setText(HEURE_DEBUT + heureDeb);
        this.heureFin.setText(HEURE_FIN + heureFin);
        this.dureeTournee.setText(DUREE + duree);
    }

    /**
     * Afficher le detail de chaque etape de la tournee
     *
     * @param numEtape numero de l etape
     * @param type le type de l etape
     * @param adresse l adresse de l etape
     * @param heureDep l heure de depart de l etape
     * @param heureArr l heure d arrivee de l etape
     * @param duree duree de l etape
     */
    public void setPanneauEtapes(int numEtape, String type, String adresse, String heureDep, String heureArr, String duree) {
        this.etape.append(ETAPE + numEtape + "\r\n");
        this.etape.append(TYPE + type + "\r\n");
        this.etape.append(ADRESSE + adresse + "\r\n");
        this.etape.append(HEURE_ARRIVEE + heureArr + "\r\n");
        this.etape.append(HEURE_DEPART + heureDep + "\r\n");
        this.etape.append(DUREE + duree + " minutes \r\n\r\n");
    }

    /**
     * Afficher le detail de chaque etape de la tournee
     *
     * @param numEtape numero de l etape
     * @param adresse l adresse de l etape
     * @param heure l heure de depart ou d arrivee de l entrepot
     */
    public void setPanneauEtapesEntrepot(int numEtape, String adresse, String heure) {
        this.etape.append(ETAPE + numEtape + "\r\n");
        this.etape.append(TYPE + "Entrepôt" + "\r\n");
        this.etape.append(ADRESSE + adresse + "\r\n");
        if (numEtape == 0) {
            this.etape.append(HEURE_DEPART + heure + "\r\n\r\n");
        } else {
            this.etape.append(HEURE_ARRIVEE + heure + "\r\n\r\n");
        }
    }

    /**
     * Vider le panneauEtapes
     */
    public void viderPanneauEtapes() {
        this.etape.setText("");
    }

    public void setTournee(Tournee tournee) {
        //System.out.println("AVANT   "+this.tournee.getSuccessionPointsInteret());
        this.tournee = tournee;
        //System.out.println("Dans setTournee FENETRE"+this.tournee.getSuccessionPointsInteret());
        //this.panneauCarte.setTournee(tournee);
        this.panneauCarte.updateUI();

    }

}
