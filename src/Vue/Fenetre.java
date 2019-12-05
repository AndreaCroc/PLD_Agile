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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import modele.Carte;
import modele.PointInteret;
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
    private AffichageEtapes vueEtapes;
    private AffichagePIs vuePIs;

    //Tableau contenant le details des etapes de la tournee
    private JTable tableauEtapes;

    //Tabeau contenant une vue generale des points d'interets de la tournee
    private JTable tableauPIs;

    //Savoir si un element est deja mis en surbrillance
    private boolean surbrillance;

    //Savoir si le bouton supprime a ete clique
    private boolean clicSupp;

    //Constantes utilisee pour l affichage
    private static final long serialVersionUID = 1L;
    protected final static String CHARGER_CARTE = "Charger carte";
    protected final static String CHARGER_LIVRAISONS = "Charger livraisons";
    protected final static String CALCULER_TOURNEE = "Calculer tournée";
    protected final static String CHANGER_CARTE = "Changer carte";
    protected final static String MODIFIER = "Modifier";
    protected final static String SUPPRIMER = "Supprimer";
    protected final static String ANNULER = "Annuler";
    protected final static String AJOUTER = "Ajouter";

    protected final static String HEURE_DEBUT = "Heure de début prévue : ";
    protected final static String HEURE_FIN = "Heure de fin prévue : ";
    protected final static String DUREE = "Durée prévue : ";

    protected final static Color COULEUR_FOND = new Color(186, 228, 255);
    protected final static Color COULEUR_BOUTON = new Color(50, 70, 120);
    protected final static Color COULEUR_ERREUR = new Color(254, 79, 65);
    protected final static Color COULEUR_ECRITURE = Color.white;

    //Boutons sur lesquels l utilisateur peut cliquer
    private JButton boutonChargerCarte;
    private JButton boutonChargerLivraisons;
    private JButton boutonCalculerTournee;
    private JButton boutonChangerCarte;
    private JButton boutonModifier;
    private JButton boutonSupprimer;
    private JButton boutonAnnuler;
    private JButton boutonAjouterPoints;

    //Labels pour afficher les donnees
    private JLabel livraisons;
    private JLabel labelTitreTournee;
    private JLabel legende;
    private JLabel repChargeCarte;
    private JLabel repChargeLiv;
    private JLabel labelTournee;
    private JLabel etapesTitre;
    private JLabel labelRond;
    private JLabel legendeRond;
    private JLabel labelCarre;
    private JLabel legendeCarre;
    private JLabel labelTriangle;
    private JLabel legendeTriangle;
    private JLabel titreAppli;
    private JLabel repChangeCarte;
    private JLabel labelTabPI;
    private JLabel labelTabEtapes;

    //Pour afficher les details d une tournee
    private JScrollPane scrollEtapes;
    //Pour afficher les points d interets d une tournee
    private JScrollPane scrollPIs;

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
    private JPanel panneauPIs;

    //Pour reagir aux actions de l utilisateur
    private EcouteurBoutons ecouteurBoutons;
    private EcouteurSouris ecouteurSouris;
    private EcouteurListSelection ecouteurListSelect;

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

        this.surbrillance = false;
        this.clicSupp = false;

        this.vueTournee = new AffichageTournee(tournee, this);

        this.ecouteurBoutons = new EcouteurBoutons(this.controleur);

        this.ecouteurListSelect = new EcouteurListSelection(this.controleur, this);

        //Panneau gauche : contient panneauLivraison, panneauTournee
        panneauGauche = new JPanel();
        panneauGauche.setLayout(null);
        panneauGauche.setBackground(COULEUR_FOND);

        /* PanneauLivraison (haut gauche)*/
        panneauLivraisons = new JPanel();
        panneauLivraisons.setLayout(null);
        panneauLivraisons.setBackground(COULEUR_FOND);
        panneauLivraisons.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COULEUR_BOUTON));

        //Input pour ecrire le nom du fichier XML souhaite
        inputChargeLiv = new JTextField();

        //Bouton pour demander a charger un fichier XML contenant des livraisons
        boutonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
        boutonChargerLivraisons.setFont(new Font("Arial", Font.BOLD, 14));
        boutonChargerLivraisons.setForeground(COULEUR_ECRITURE);
        boutonChargerLivraisons.setBackground(COULEUR_BOUTON);
        boutonChargerLivraisons.addActionListener(ecouteurBoutons);

        //Pour afficher les messages d erreur lies au chargement du fichier
        repChargeLiv = new JLabel("Erreur dans le chargement du fichier");
        repChargeLiv.setFont(new Font("Arial", Font.BOLD, 13));
        repChargeLiv.setForeground(COULEUR_ERREUR);
        repChargeLiv.setVisible(false);

        //Bouton pour calculer une tournee
        boutonCalculerTournee = new JButton(CALCULER_TOURNEE);
        boutonCalculerTournee.setFont(new Font("Arial", Font.BOLD, 14));
        boutonCalculerTournee.setForeground(COULEUR_ECRITURE);
        boutonCalculerTournee.setBackground(COULEUR_BOUTON);
        boutonCalculerTournee.setEnabled(false);
        boutonCalculerTournee.addActionListener(ecouteurBoutons);

        livraisons = new JLabel("Livraisons");
        livraisons.setFont(new Font("Arial", Font.BOLD, 18));
        livraisons.setForeground(COULEUR_ECRITURE);

        //Remplissage du panneauLivraison et ajout de ce dernier au panneauGauche
        panneauLivraisons.add(livraisons);
        panneauLivraisons.add(inputChargeLiv);
        panneauLivraisons.add(boutonChargerLivraisons);
        panneauLivraisons.add(boutonCalculerTournee);
        panneauLivraisons.add(repChargeLiv);
        panneauGauche.add(panneauLivraisons);

        /* Fin PanneauLivraison */
 /* PanneauPIs (haut gauche) */
        //Vue sur les details des points d interets d une demande de livraison
        vuePIs = new AffichagePIs(new FormatCellRenderer(-1, 1), this.carte, this);
        //Tableau contenant les details des points d interets
        tableauPIs = new JTable(vuePIs);
        //Ajuster la taille des lignes
        tableauPIs.setRowHeight(40);
        //Ajuster la taille des colonnes
        //tableauPIs.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableauPIs.getColumnModel().getColumn(3).setPreferredWidth(250);

        for (int i = 0; i < tableauPIs.getColumnModel().getColumnCount(); i++) {
            if (i < 3) {
                //Appliquer un formatage a certaines colonnes du tableau
                tableauPIs.getColumnModel().getColumn(i).setCellRenderer(this.vuePIs.getFormatcell());
            } else {
                //Appliquer un formatage a certaines colonnes du tableau
                this.tableauPIs.setDefaultRenderer(JComponent.class, new TypeCellRenderer());
            }
        }
        tableauPIs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel listSelectModel = tableauPIs.getSelectionModel();
        //Ajouter un evenement sur les lignes du tableau
        listSelectModel.addListSelectionListener(this.ecouteurListSelect);

        scrollPIs = new JScrollPane(tableauPIs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Pour afficher si aucun point d interet dans le fichier de livraisons
        labelTabPI = new JLabel("Aucune donnée dans le fichier de livraisons chargé");
        labelTabPI.setFont(new Font("Arial", Font.BOLD, 14));
        labelTabPI.setForeground(COULEUR_ERREUR);
        labelTabPI.setVisible(false);

        // PanneauPIs (milieu haut gauche)
        panneauPIs = new JPanel();
        panneauPIs.setLayout(null);
        panneauPIs.setBackground(COULEUR_FOND);
        panneauPIs.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COULEUR_BOUTON));
        panneauPIs.setVisible(false);
        panneauPIs.add(scrollPIs);
        panneauPIs.add(labelTabPI);
        panneauGauche.add(panneauPIs);

        /* PanneauTournee (milieu bas gauche) */
        //Titre de panneauTournee
        labelTitreTournee = new JLabel("Tournée");
        labelTitreTournee.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitreTournee.setForeground(COULEUR_ECRITURE);

        //Heure de debut de la tournee
        labelTournee = new JLabel(HEURE_DEBUT);
        labelTournee.setFont(new Font("Arial", Font.BOLD, 14));
        labelTournee.setForeground(COULEUR_ECRITURE);

        //Ajout des elements a panneauTournee et ajout de ce dernier a panneauGauche
        panneauTournee = new JPanel();
        panneauTournee.setLayout(null);
        panneauTournee.setBackground(COULEUR_FOND);
        panneauTournee.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, COULEUR_BOUTON));
        panneauTournee.add(labelTitreTournee);
        panneauTournee.add(labelTournee);
        panneauTournee.setVisible(false);
        panneauGauche.add(panneauTournee);

        /* Fin PanneauTournee*/
 /* PanneauEtape (bas gauche)*/
        //Titre de panneauEtape
        etapesTitre = new JLabel("Etapes");
        etapesTitre.setFont(new Font("Arial", Font.BOLD, 18));
        etapesTitre.setForeground(COULEUR_ECRITURE);

        //Vue sur les etapes d une tournee
        vueEtapes = new AffichageEtapes(new FormatCellRenderer(-1, 2), this, this.tournee);
        //Tableau contenant les informatiosn sur les etapes
        tableauEtapes = new JTable(vueEtapes);
        tableauEtapes.setRowHeight(30);
        //tableauEtapes.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableauEtapes.getColumnModel().getColumn(3).setPreferredWidth(150);
        for (int i = 0; i < tableauEtapes.getColumnModel().getColumnCount(); i++) {
            //Appliquer un format aux colonnes du tableau
            tableauEtapes.getColumnModel().getColumn(i).setCellRenderer(this.vueEtapes.getFormatcell());
        }
        tableauEtapes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel listSelectionModel = tableauEtapes.getSelectionModel();
        //Ajouter un evenement sur les lignes du tableau
        listSelectionModel.addListSelectionListener(this.ecouteurListSelect);

        scrollEtapes = new JScrollPane(tableauEtapes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Bouton pour supprimer un point d interet de la tournee
        boutonSupprimer = new JButton(SUPPRIMER);
        boutonSupprimer.setFont(new Font("Arial", Font.BOLD, 14));
        boutonSupprimer.setForeground(COULEUR_ECRITURE);
        boutonSupprimer.setBackground(COULEUR_BOUTON);
        boutonSupprimer.addActionListener(ecouteurBoutons);

        //Bouton pour modifier  la tournee
        boutonModifier = new JButton(MODIFIER);
        boutonModifier.setFont(new Font("Arial", Font.BOLD, 14));
        boutonModifier.setForeground(COULEUR_ECRITURE);
        boutonModifier.setBackground(COULEUR_BOUTON);
        boutonModifier.addActionListener(ecouteurBoutons);

        //Bouton pour annuler  la suppression d un point d interet
        boutonAnnuler = new JButton(ANNULER);
        boutonAnnuler.setFont(new Font("Arial", Font.BOLD, 14));
        boutonAnnuler.setForeground(COULEUR_ECRITURE);
        boutonAnnuler.setBackground(COULEUR_BOUTON);
        boutonAnnuler.addActionListener(ecouteurBoutons);
        
        //Bouton pour ajouter des points
        boutonAjouterPoints = new JButton(AJOUTER);
        boutonAjouterPoints.setFont(new Font("Arial", Font.BOLD, 14));
        boutonAjouterPoints.setForeground(COULEUR_ECRITURE);
        boutonAjouterPoints.setBackground(COULEUR_BOUTON);
        //boutonAjouterPoints.setEnabled(false);
        boutonAjouterPoints.addActionListener(ecouteurBoutons);

        //Afficher un message si aucun point d interet
        labelTabEtapes = new JLabel("Aucun points d'intérêts");
        labelTabEtapes.setFont(new Font("Arial", Font.BOLD, 14));
        labelTabEtapes.setForeground(COULEUR_ERREUR);
        labelTabEtapes.setVisible(false);

        //Ajout des elements a panneauEtapes et ajout de ce dernier a panneauGauche
        panneauEtapes = new JPanel();
        panneauEtapes.setLayout(null);
        panneauEtapes.setBackground(COULEUR_FOND);
        panneauEtapes.add(etapesTitre);
        panneauEtapes.add(scrollEtapes);
        panneauEtapes.add(boutonSupprimer);
        panneauEtapes.add(boutonModifier);
        panneauEtapes.add(boutonAnnuler);
        panneauEtapes.add(boutonAjouterPoints);
        panneauEtapes.setVisible(false);
        panneauGauche.add(panneauEtapes);

        /* PanneauDroite */
        //Panneau droit contenant la legende et la carte
        panneauDroite = new JPanel();
        panneauDroite.setLayout(null);
        panneauDroite.setBackground(COULEUR_FOND);
        panneauDroite.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, COULEUR_BOUTON));

        /* PanneauLegende(haut droit)*/
        //Logo rond de la legende
        rond = new ImageIcon("rond-noir.jpg");
        Image imgR = rond.getImage();
        Image newimgR = imgR.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        rond = new ImageIcon(newimgR);

        labelRond = new JLabel(rond);

        legendeRond = new JLabel(": Point de livraison");
        legendeRond.setFont(new Font("Arial", Font.BOLD, 14));
        legendeRond.setForeground(COULEUR_ECRITURE);

        //Logo carre de la legende
        carre = new ImageIcon("carre-noir.png");
        Image imgC = carre.getImage();
        Image newimgC = imgC.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        carre = new ImageIcon(newimgC);

        labelCarre = new JLabel(carre);

        legendeCarre = new JLabel(": Point d'enlèvement");
        legendeCarre.setFont(new Font("Arial", Font.BOLD, 14));
        legendeCarre.setForeground(COULEUR_ECRITURE);

        //Logo triangle de la legende
        triangle = new ImageIcon("triangle-noir.png");
        Image imgT = triangle.getImage();
        Image newimgT = imgT.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        triangle = new ImageIcon(newimgT);

        labelTriangle = new JLabel(triangle);

        legendeTriangle = new JLabel(": Dépôt des vélos, point de départ de tous les livreurs");
        legendeTriangle.setFont(new Font("Arial", Font.BOLD, 14));
        legendeTriangle.setForeground(COULEUR_ECRITURE);

        //Bouton pour changer une carte, fichier XML
        boutonChangerCarte = new JButton(CHANGER_CARTE);
        boutonChangerCarte.setFont(new Font("Arial", Font.BOLD, 14));
        boutonChangerCarte.setForeground(COULEUR_ECRITURE);
        boutonChangerCarte.setBackground(COULEUR_BOUTON);
        boutonChangerCarte.addActionListener(ecouteurBoutons);

        //Pour afficher les messages d erreur lies au chargement du fichier
        repChangeCarte = new JLabel("Erreur dans le chargement du fichier");
        repChangeCarte.setFont(new Font("Arial", Font.BOLD, 14));
        repChangeCarte.setForeground(COULEUR_ERREUR);
        repChangeCarte.setVisible(false);

        //Ajout des elements a panneauLegende et ajout de ce dernier a panneauDroit
        panneauLegende = new JPanel();
        panneauLegende.setLayout(null);
        panneauLegende.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, COULEUR_BOUTON));
        panneauLegende.add(labelRond);
        panneauLegende.add(legendeRond);
        panneauLegende.add(labelCarre);
        panneauLegende.add(legendeCarre);
        panneauLegende.add(labelTriangle);
        panneauLegende.add(legendeTriangle);
        panneauLegende.add(boutonChangerCarte);
        panneauLegende.add(repChangeCarte);
        panneauLegende.setBackground(COULEUR_FOND);

        legende = new JLabel("Légende");
        legende.setFont(new Font("Arial", Font.BOLD, 18));
        legende.setForeground(COULEUR_ECRITURE);
        panneauLegende.add(legende);
        panneauDroite.add(panneauLegende);

        /* Fin PanneauLegende */
 /* PanneauCarte (bas droit) */
        panneauCarte = new JCarte(this.carte, this.tournee, this);
        panneauCarte.setLayout(null);
        panneauCarte.setBackground(COULEUR_ECRITURE);
        panneauCarte.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, COULEUR_BOUTON));
        panneauCarte.setSize((int) (this.getWidth() * 0.95), (int) (this.getHeight() * 0.2));
        //panneauCarte.setBounds((int)(this.getWidth()*0.3), (int)(this.getHeight()*0.3), (int)(this.getWidth()*0.8), (int)(this.getHeight()*0.8));
        panneauDroite.add(panneauCarte);

        ecouteurSouris = new EcouteurSouris(controleur, panneauCarte, this);
        addMouseListener(ecouteurSouris);

        /* Fin panneauCarte */
 /* Fin panneauDroite */

 /* PanneauGlobal2 : pour la deuxieme fenetre */
        panneauGlobal2 = new JPanel();
        panneauGlobal2.setLayout(null);
        panneauGlobal2.setBackground(COULEUR_FOND);
        panneauGlobal2.add(panneauGauche);
        panneauGlobal2.add(panneauDroite);

        /* Fin PanneauGlobal2 */

 /* PanneauGlobal1 : pour la premiere fenetre*/
        //Pour afficher le titre de l application
        titreAppli = new JLabel("Bienvenue sur Opt'IFmodLyon");
        titreAppli.setFont(new Font("Arial", Font.BOLD, 50));
        titreAppli.setForeground(COULEUR_ECRITURE);

        //Pour entrer le chemin vers un fichier XML
        inputChargeCarte = new JTextField();

        //Pour afficher une erreur en cas de probleme lors du chargement du fichier
        repChargeCarte = new JLabel("Erreur dans le chargement du fichier");
        repChargeCarte.setFont(new Font("Arial", Font.BOLD, 16));
        repChargeCarte.setForeground(COULEUR_ERREUR);
        repChargeCarte.setVisible(false);

        //Bouton pour lancer le chargement d une carte, fichier XML
        boutonChargerCarte = new JButton(CHARGER_CARTE);
        boutonChargerCarte.setFont(new Font("Arial", Font.BOLD, 16));
        boutonChargerCarte.setForeground(COULEUR_ECRITURE);
        boutonChargerCarte.setBackground(COULEUR_BOUTON);
        boutonChargerCarte.addActionListener(ecouteurBoutons);

        //On ajoute tout au panneauGlobal1
        panneauGlobal1 = new JPanel();
        panneauGlobal1.setLayout(null);
        panneauGlobal1.setBackground(COULEUR_FOND);
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

        titreAppli.setBounds(26 * (int) panneauGlobal1.getWidth() / 100, 1 * (int) panneauGlobal1.getHeight() / 6, 1 * (int) panneauGlobal1.getWidth(), 1 * (int) panneauGlobal1.getHeight() / 10);
        inputChargeCarte.setBounds(3 * (int) panneauGlobal1.getWidth() / 10, 1 * (int) panneauGlobal1.getHeight() / 3, 1 * (int) panneauGlobal1.getWidth() / 4, 1 * (int) panneauGlobal1.getHeight() / 20);
        boutonChargerCarte.setBounds(60 * ((int) panneauGlobal1.getWidth() / 100), 1 * (int) panneauGlobal1.getHeight() / 3, 1 * (int) panneauGlobal1.getWidth() / 8, 1 * (int) panneauGlobal1.getHeight() / 20);
        repChargeCarte.setBounds(3 * (int) panneauGlobal1.getWidth() / 10, 35 * (int) panneauGlobal1.getHeight() / 100, 1 * (int) panneauGlobal1.getWidth() / 4, 1 * (int) panneauGlobal1.getHeight() / 10);
    }

    /**
     * Placement des elements de la deuxieme fenetre
     */
    public void placeObjet2() {
        panneauGlobal2.setBounds(0, 0, ((int) getSize().width), ((int) getSize().height));
        panneauGauche.setBounds(0, 0, 47 * (int) panneauGlobal2.getWidth() / 100, (int) panneauGlobal2.getHeight());
        panneauDroite.setBounds(47 * (int) panneauGlobal2.getWidth() / 100, 0, 53 * (int) panneauGlobal2.getWidth() / 100, 1 * (int) panneauGlobal2.getHeight());
        panneauLivraisons.setBounds(0, 0, (int) panneauGauche.getWidth(), 1 * (int) panneauGauche.getHeight() / 6);
        panneauPIs.setBounds(0, 1 * (int) panneauGauche.getHeight() / 6, (int) panneauGauche.getWidth(), 30 * (int) panneauGauche.getHeight() / 100);
        panneauTournee.setBounds(0, 47 * (int) panneauGauche.getHeight() / 100, 1 * ((int) panneauGauche.getWidth()), 1 * (int) panneauGauche.getHeight() / 20);
        panneauEtapes.setBounds(0, 52 * (int) panneauGauche.getHeight() / 100, 1 * ((int) panneauGauche.getWidth()), 40 * (int) panneauGauche.getHeight() / 100);
        panneauLegende.setBounds(0, 0, (int) panneauDroite.getWidth(), 1 * (int) panneauDroite.getHeight() / 10);

        boutonChangerCarte.setBounds((int) 6 * panneauLegende.getWidth() / 10, (int) panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 4, 4 * (int) panneauLegende.getHeight() / 10);
        repChangeCarte.setBounds((int) 6 * panneauLegende.getWidth() / 10, (int) 2 * panneauLegende.getHeight() / 3, (int) panneauLegende.getWidth() / 2, (int) panneauLegende.getHeight() / 4);

        int largeurCarte = (int) panneauDroite.getHeight() - (int) panneauLegende.getHeight();
        panneauCarte.setBounds(0, 1 * (int) panneauDroite.getHeight() / 10, largeurCarte, 81 * (int) panneauDroite.getHeight() / 100);

        legende.setBounds(1 * (int) panneauLegende.getWidth() / 10, 0, 1 * (int) panneauLegende.getWidth(), 1 * (int) panneauLegende.getHeight() / 4);
        labelRond.setBounds(0, (int) panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 25, (int) panneauLegende.getHeight() / 4);
        legendeRond.setBounds((int) panneauLegende.getWidth() / 25, (int) panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 5, (int) panneauLegende.getHeight() / 4);
        labelCarre.setBounds(0, (int) 2 * panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 25, (int) panneauLegende.getHeight() / 4);
        legendeCarre.setBounds((int) panneauLegende.getWidth() / 25, (int) 2 * panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 5, (int) panneauLegende.getHeight() / 4);
        labelTriangle.setBounds(0, (int) 3 * panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 25, (int) panneauLegende.getHeight() / 4);
        legendeTriangle.setBounds((int) panneauLegende.getWidth() / 25, (int) 3 * panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth(), (int) panneauLegende.getHeight() / 4);

        livraisons.setBounds(4 * ((int) panneauLivraisons.getWidth() / 10), 0, 1 * (int) panneauLivraisons.getWidth(), 1 * (int) panneauLivraisons.getHeight() / 5);
        inputChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 20, 1 * (int) panneauLivraisons.getHeight() / 5, 55 * (int) panneauLivraisons.getWidth() / 100, 1 * (int) panneauLivraisons.getHeight() / 3);
        boutonChargerLivraisons.setBounds(70 * ((int) panneauLivraisons.getWidth() / 100), 1 * (int) panneauLivraisons.getHeight() / 5, 3 * (int) panneauLivraisons.getWidth() / 10, 1 * (int) panneauLivraisons.getHeight() / 3);
        boutonCalculerTournee.setBounds(45 * ((int) panneauLivraisons.getWidth() / 100), 6 * (int) panneauLivraisons.getHeight() / 10, 1 * (int) panneauLivraisons.getWidth() / 4, 1 * (int) panneauLivraisons.getHeight() / 3);
        repChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 20, 1 * (int) panneauLivraisons.getHeight() / 2, 1 * (int) panneauLivraisons.getWidth(), 1 * (int) panneauLivraisons.getHeight() / 4);

        labelTitreTournee.setBounds(4 * (int) panneauTournee.getWidth() / 10, 0, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 2);
        labelTournee.setBounds(0, 1 * (int) panneauTournee.getHeight() / 2, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 2);

        etapesTitre.setBounds(4 * (int) panneauEtapes.getWidth() / 10, 0, 1 * (int) panneauEtapes.getWidth(), 1 * (int) panneauEtapes.getHeight() / 20);
        tableauEtapes.setBounds(0, 1 * (int) panneauEtapes.getHeight() / 20, 1 * (int) panneauEtapes.getWidth(), 75 * (int) panneauEtapes.getHeight() / 100);
        scrollEtapes.setBounds(0, 1 * (int) panneauEtapes.getHeight() / 20, 1 * (int) panneauEtapes.getWidth(), 75 * (int) panneauEtapes.getHeight() / 100);
        boutonAjouterPoints.setBounds(1 * (int) panneauLivraisons.getWidth() / 25, 81 * (int) panneauLivraisons.getHeight() / 100, 1 * (int) panneauLivraisons.getWidth() / 5, 15 * (int) panneauLivraisons.getHeight() / 100);
        boutonSupprimer.setBounds(7 * (int) panneauEtapes.getWidth() / 25, 81 * (int) panneauEtapes.getHeight() / 100, 1 * (int) panneauEtapes.getWidth() / 5, 15 * (int) panneauEtapes.getHeight() / 100);
        boutonModifier.setBounds(13 * (int) panneauEtapes.getWidth() / 25, 81 * (int) panneauEtapes.getHeight() / 100, 1 * (int) panneauEtapes.getWidth() / 5, 15 * (int) panneauEtapes.getHeight() / 100);
        boutonAnnuler.setBounds(19 * (int) panneauEtapes.getWidth() / 25, 81 * (int) panneauEtapes.getHeight() / 100, 1 * (int) panneauEtapes.getWidth() / 5, 15 * (int) panneauEtapes.getHeight() / 100);
        
        scrollPIs.setBounds(0, 0, (int) panneauPIs.getWidth(), (int) panneauPIs.getHeight());
        labelTabPI.setBounds((int) panneauPIs.getWidth() / 4, (int) panneauPIs.getHeight() / 20, (int) panneauPIs.getWidth(), (int) panneauPIs.getHeight() / 20);

        

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
        this.boutonCalculerTournee.setEnabled(true);
        this.boutonAjouterPoints.setEnabled(true);
    }

    /**
     * Pour rendre non cliquable le bouton pour calculer une tournee
     */
    public void griserBoutonCalcul() {
        System.out.println("griser");
        this.boutonCalculerTournee.setEnabled(false);
    }

    /**
     * Pour rendre non cliquable le bouton pour annuler la suppression ou
     * modification Pour rendre cliquable les autres boutons
     */
    public void griserBoutonsSupprimer() {
        this.boutonAnnuler.setEnabled(true);

        this.boutonChangerCarte.setEnabled(false);
        this.boutonChargerLivraisons.setEnabled(false);
        this.boutonCalculerTournee.setEnabled(false);
        this.boutonSupprimer.setEnabled(false);
        this.boutonModifier.setEnabled(false);
    }

    /**
     * Pour afficher le bouton pour l annulation de la suppression ou la
     * modification Et poour griser les autres
     */
    public void afficherBoutonSupprimer() {
        this.boutonAnnuler.setEnabled(false);

        this.boutonChangerCarte.setEnabled(true);
        this.boutonChargerLivraisons.setEnabled(true);
        this.boutonCalculerTournee.setEnabled(true);
        this.boutonSupprimer.setEnabled(true);
        this.boutonModifier.setEnabled(true);
    }

    /**
     * Pour afficher les details d une tournee
     */
    public void afficherEtapesTour() {
        panneauEtapes.setVisible(true);
        panneauTournee.setVisible(true);
        vueTournee.setTournee(tournee);
        vueTournee.afficherTournee();
        vueEtapes.setTournee(tournee);
        vueEtapes.afficherEtapes();

    }

    /**
     * Pour cacher les panneaux etapes et tournee
     */
    public void cacherPanneauEtapesEtTour() {
        this.panneauEtapes.setVisible(false);
        this.panneauTournee.setVisible(false);
    }

    /**
     * Pour cacher le panneauPI
     */
    public void cacherPanneauPI() {
        this.panneauPIs.setVisible(false);
    }

    /**
     * Pour rendre visible le panneauPI
     */
    public void afficherPanneauPI() {
        panneauPIs.setVisible(true);
        vuePIs.setCarte(carte);
        vuePIs.afficherPIs();
    }

    /**
     * Mettre en surbrillance la ligne du tableau correspondant a l index du
     * point d interet clique
     *
     * @param index : ligne du tableau a encadrer
     */
    public void surbrillanceLigneTab(int index) {
        //Si on clique sur un point d interet alors qu il y en avait deja un  
        //encadre en rouge, enlever le cadre autour de ce point
        /*if (this.surbrillance) {
            this.vueEtapes.setLigneSelect(-1);
            this.vuePIs.setLigneSelect(-1);
            this.panneauCarte.setFenetre(this);
            this.panneauCarte.updateUI();
            this.surbrillance = false;
        }*/

        if (tableauEtapes.getRowCount() != 0) {
            for (int j = 0; j < tableauEtapes.getColumnModel().getColumnCount(); j++) {
                //Encadrer en rouge la ligne correspond a l index
                this.vueEtapes.getFormatcell().setIndex(index);
                tableauEtapes.getColumnModel().getColumn(j).setCellRenderer(this.vueEtapes.getFormatcell());
            }
        }
        if (tableauPIs.getRowCount() != 0) {
            for (int j = 0; j < tableauPIs.getColumnModel().getColumnCount(); j++) {
                //Encadrer en rouge la ligne correspond a l index
                this.vuePIs.getFormatcell().setIndex(index);
                tableauPIs.getColumnModel().getColumn(j).setCellRenderer(this.vuePIs.getFormatcell());
            }
        }
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

    /**
     * Afficher un message si aucune donnees dans le fichier des livraisons
     * charge
     *
     * @param afficher savoir si on veut afficher ou cacher le message
     */
    public void afficherOuCacherMessageLivraison(boolean afficher) {
        labelTabPI.setVisible(afficher);
    }

    /**
     * Afficher un message si aucune donnees dans le fichier des livraisons
     * charge
     *
     * @param afficher savoir si on veut afficher ou cacher le message
     */
    public void afficherOuCacherMessageTournee(boolean afficher) {
        labelTabEtapes.setVisible(afficher);
    }

    public JCarte getPanneauCarte() {
        return this.panneauCarte;
    }

    public void setPanneauCarte(JCarte nouvelleCarte) {
        this.panneauCarte = nouvelleCarte;
        this.panneauCarte.updateUI();

    }

    public EcouteurBoutons getEcouteurBoutons() {
        return this.ecouteurBoutons;
    }

    public AffichageEtapes getVueEtapes() {
        return this.vueEtapes;
    }

    /**
     * Pour afficher des donnees globales liees a une tournee
     *
     * @param heureDeb heure de debut de la tournee
     * @param heureFin heure de fin de la tournee
     * @param duree duree de la tournee
     */
    public void setPanneauTournee(String heureDeb, String heureFin, String duree) {
        this.labelTournee.setText("   " + HEURE_DEBUT + heureDeb + "      " + HEURE_FIN + heureFin + "      " + DUREE + duree);
    }

    /**
     * Afficher le detail de chaque etape de la tournee
     *
     * @param ordre ordre de l etape dans la tournee
     * @param numDemande numero de la demande de livraison associe a un point d
     * interet
     * @param type le type de l etape
     * @param adresse l adresse de l etape
     * @param heureDep l heure de depart de l etape
     * @param heureArr l heure d arrivee de l etape
     * @param duree duree de l etape
     */
    public void setPanneauEtapes(int ordre, int numDemande, String type, String adresse, String heureDep, String heureArr, String duree) {
        LigneEtapes step = new LigneEtapes(ordre, numDemande, type, adresse, heureDep, heureArr, duree + " min");
        this.vueEtapes.addStep(step);
    }

    /**
     * Afficher le detail de chaque point d interet faisant partie de la demande
     * de livraions
     *
     * @param numEtape numero de la demande de livraison
     * @param type type de l etape
     * @param adresse adresse de l etape
     * @param duree duree de l etape
     */
    public void setPanneauPIs(int numEtape, String type, String adresse, String duree) {
        LignePI pi = new LignePI(numEtape, type, adresse, duree);
        this.vuePIs.addPI(pi);
    }

    /**
     * Afficher le detail de chaque etape de la tournee
     *
     * @param ordre ordre de l etape dans la tournee
     * @param numDemande numero de la demande
     * @param adresse l adresse de l etape
     * @param heure l heure de depart ou d arrivee de l entrepot
     */
    public void setPanneauEtapesEntrepot(int ordre, int numDemande, String adresse, String heure) {
        LigneEtapes step;
        if (ordre == 0) {
            step = new LigneEtapes(ordre, numDemande, "Entrepot", adresse, heure, "", "");

        } else {
            step = new LigneEtapes(ordre, numDemande, "Entrepot", adresse, "", heure, "");
        }

        this.vueEtapes.addStep(step);
    }

    /**
     * Vider le panneauEtapes
     */
    public void viderPanneauEtapes() {
        this.vueEtapes.clearSteps();
        this.vueEtapes.getFormatcell().setIndex(-1);
        this.vueEtapes.setLigneSelect(-1);
        this.panneauCarte.setFenetre(this);
        this.panneauCarte.updateUI();
    }

    /**
     * Vider le panneauPIs
     */
    public void viderPanneauPIs() {
        this.vuePIs.clearPIs();
        this.vuePIs.getFormatcell().setIndex(-1);
        this.vuePIs.setLigneSelect(-1);
        this.panneauCarte.setFenetre(this);
        this.panneauCarte.updateUI();
    }

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
        this.panneauCarte.updateUI();

    }

    public void setSurbrillance(boolean surb) {
        this.surbrillance = surb;
    }

    /**
     * Entourer le point d interet correspond a la ligne du tableau selectionnee
     *
     * @param ligne ligne du tableau selectionnee
     */
    public void entourerPI(int ligne) {
        //Si une ligne du tableau etait deja en surbrillance au moment du clic sur une ligne
        //Enlever le contour rouge de cette ligne du tableau
        /*if (this.surbrillance) {
            System.out.println("surbrillance");
            this.vueEtapes.getFormatcell().setIndex(-1);
            this.vuePIs.getFormatcell().setIndex(-1);
            this.surbrillance = false;
        }*/
        this.vueEtapes.setLigneSelect(ligne);
        this.vuePIs.setLigneSelect(ligne);
        this.panneauCarte.setFenetre(this);
        this.panneauCarte.updateUI();
    }

    public int getWidthPanneauGauche() {
        return this.panneauGauche.getWidth();
    }

    public int getHeightPanneauLegende() {
        return this.panneauLegende.getHeight();
    }

    /**
     * Afficher un message d erreur lors d un changement de la carte
     *
     * @param message qui est le contenu du message d erreur
     */
    public void afficherMessageErreur3(String message) {
        repChangeCarte.setText(message);
        repChangeCarte.setVisible(true);
    }

    /**
     * Cacher le message d erreur si tout s est bien passe lors du changement de
     * la carte
     */
    public void retireMessageErreur3() {
        repChangeCarte.setVisible(false);
    }

    public void setClicSupp(boolean clic) {
        this.clicSupp = clic;
    }

    public boolean getClicSupp() {
        return this.clicSupp;
    }

    public String getInputChargeCarte() {
        return inputChargeCarte.getText();
    }

    public String getInputChargeLiv() {
        return inputChargeLiv.getText();
    }

    /**
     * Afficher une popup pour valider la suppression d un point d interet
     *
     * @return option choisie par l utilisateur
     */
    public int afficherPopSuppression(PointInteret pti) {
        JOptionPane jop = new JOptionPane();
        String type = "";
        String typeAssocie = "";
        String message = "Voulez-vous supprimer le point d'intérêt de type ";

        if (pti != null) {
            int num = pti.getNumeroDemande();
            if (pti.isEnlevement()) {
                type = "enlèvement";
                typeAssocie = "livraison";
            } else {
                type = "livraison";
                typeAssocie = "enlèvement";
            }
            message += type + "\n" + "correspondant à la demande de livraison " + num + "\n" + "en sachant que le point d'intérêt de type " + typeAssocie + "\n" + "associé sera également supprimé ?";
        }
        int option = jop.showConfirmDialog(null, message, "Suppression d'un point d 'intérêt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        return option;
    }

    /**
     * Afficher une popup d erreur dans le cas ou l utilisateur veut supprimer l
     * entrepot
     *
     */
    public void afficherPopSuppressionErreur() {
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "Vous ne pouvez pas supprimer l'entrepôt", "Erreur", JOptionPane.ERROR_MESSAGE);

    }
}
