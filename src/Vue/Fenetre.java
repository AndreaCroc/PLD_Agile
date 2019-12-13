package Vue;

import controleur.Controleur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * Fenetre permet d'afficher notre application
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Fenetre extends JFrame {

    private Controleur controleur; //Controleur entre la vue et le modele
    private Carte carte; //Carte a afficher
    private Tournee tournee; //Tournee realisee
    private AffichageTournee vueTournee; //Afficher la tournee
    private JCarte panneauCarte; //Afficher la carte
    private AffichageEtapes vueEtapes; //Afficher les etapes
    private AffichagePIs vuePIs; //Afficher les points dinterets generaux
    private double zoom;
    private double deplacementX;
    private double deplacementY;
    //Tableau contenant le details des etapes de la tournee
    private JTable tableauEtapes;

    //Tabeau contenant une vue generale des points d'interets de la tournee
    private JTable tableauPIs;

    //Savoir si le bouton supprimer a ete clique
    private boolean clicSupp;

    //Savoir si le bouton modifier a ete clique
    private boolean clicModif;
    
    private ArrayList<Color> palette=new ArrayList<Color>();

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
    protected final static String ZOOMER = "+";
    protected final static String DEZOOMER = "-";
    protected final static String UNDO = "Undo";
    protected final static String REDO = "Redo";
    protected final static String DROITE = "→";
    protected final static String GAUCHE = "←";
    protected final static String HAUT = "↑";
    protected final static String BAS = "↓";
    
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
    private JButton boutonZoomer;
    private JButton boutonDezoomer;
    private JButton boutonUndo;
    private JButton boutonRedo;
    private JButton boutonDirDroite;
    private JButton boutonDirGauche;
    private JButton boutonDirHaut;
    private JButton boutonDirBas;

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

    private ListSelectionModel listSelectModelPI;
    private ListSelectionModel listSelectModelEtapes;
    
    //Permettre de reprendre les points à ajouter
    private PointInteret pE;
    private PointInteret avantPE;
    private PointInteret pL;
    private PointInteret avantPL;
    
    //Savoir si on a bine clique sur une intersection
    private boolean clicAjoutAvantEnlvt;

    public Fenetre(Controleur controleur, Carte carte, Tournee tournee) {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit()
                              .getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();

        //Mise en place des caracteristiques de la fenetre
        this.setLayout(null);
        this.setTitle("Opt'IFmodLyon");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);

        this.controleur = controleur;
        this.carte = carte;
        this.tournee = tournee;

        this.clicSupp = false;
        this.clicModif = false;

        this.vueTournee = new AffichageTournee(tournee, this);

        this.ecouteurBoutons = new EcouteurBoutons(this.controleur,this);

        this.ecouteurListSelect = new EcouteurListSelection(this.controleur, 
                                                            this);

        //Panneau gauche : contient panneauLivraison, panneauTournee
        panneauGauche = new JPanel();
        panneauGauche.setLayout(null);
        panneauGauche.setBackground(COULEUR_FOND);

        /* PanneauLivraison (haut gauche)*/
        panneauLivraisons = new JPanel();
        panneauLivraisons.setLayout(null);
        panneauLivraisons.setBackground(COULEUR_FOND);
        panneauLivraisons.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, 
                                                               COULEUR_BOUTON));

        //Input pour ecrire le nom du fichier XML souhaite
        inputChargeLiv = new JTextField();
        
        //Bouton pour demander a charger un fichier XML contenant des livraisons
        boutonChargerLivraisons = new JButton(CHARGER_LIVRAISONS);
        boutonChargerLivraisons.setFont(new Font("Arial", Font.BOLD, 14));
        boutonChargerLivraisons.setForeground(COULEUR_ECRITURE);
        boutonChargerLivraisons.setBackground(COULEUR_BOUTON);
        boutonChargerLivraisons.addActionListener(ecouteurBoutons);
        
        //Bouton pour faire un undo
        ImageIcon undo = new ImageIcon("undo.png");
        Image imgUndo = undo.getImage();
        Image newimgUndo = imgUndo.getScaledInstance(40, 40, 
                                                     Image.SCALE_SMOOTH);
        undo = new ImageIcon(newimgUndo);
        boutonUndo = new JButton(undo);
        boutonUndo.addActionListener(ecouteurBoutons);
        boutonUndo.setVisible(false);
        
        //Bouton pour faire un redo
        ImageIcon redo = new ImageIcon("redo.png");
        Image imgRedo = redo.getImage();
        Image newimgRedo = imgRedo.getScaledInstance(40, 40, 
                                                     Image.SCALE_SMOOTH);
        redo = new ImageIcon(newimgRedo);
        boutonRedo = new JButton(redo);
        boutonRedo.addActionListener(ecouteurBoutons);
        boutonRedo.setVisible(false);

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

        //Remplissage du panneauLivraison + ajout de ce dernier au panneauGauche
        panneauLivraisons.add(livraisons);
        panneauLivraisons.add(inputChargeLiv);
        panneauLivraisons.add(boutonChargerLivraisons);
        panneauLivraisons.add(boutonCalculerTournee);
        panneauLivraisons.add(boutonUndo);
        panneauLivraisons.add(boutonRedo);
        panneauLivraisons.add(repChargeLiv);
        panneauGauche.add(panneauLivraisons);

        /* Fin PanneauLivraison */
        
        /* PanneauPIs (haut gauche) */
        //Vue sur les details des points d interets d une demande de livraison
        vuePIs = new AffichagePIs(new FormatCellRenderer(-1, -1, 1), this.carte, 
                                  this);
        //Tableau contenant les details des points d interets
        tableauPIs = new JTable(vuePIs);
        //Ajuster la taille des lignes
        tableauPIs.setRowHeight(40);
        //Ajuster la taille des colonnes
        tableauPIs.getColumnModel().getColumn(3).setPreferredWidth(250);

        for (int i = 0; i < tableauPIs.getColumnModel().getColumnCount(); i++) {
            if (i < 3) {
                //Appliquer un formatage a certaines colonnes du tableau
                tableauPIs.getColumnModel().getColumn(i).setCellRenderer(
                                                this.vuePIs.getFormatcell());
            } else {
                //Appliquer un formatage a certaines colonnes du tableau
                this.tableauPIs.setDefaultRenderer(JComponent.class, 
                                                   new TypeCellRenderer());
            }
        }
        tableauPIs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectModelPI = tableauPIs.getSelectionModel();
        //Ajouter un evenement sur les lignes du tableau
        listSelectModelPI.addListSelectionListener(this.ecouteurListSelect);

        scrollPIs = new JScrollPane(tableauPIs, JScrollPane
                .VERTICAL_SCROLLBAR_ALWAYS, JScrollPane
                        .HORIZONTAL_SCROLLBAR_NEVER);

        // PanneauPIs (milieu haut gauche)
        panneauPIs = new JPanel();
        panneauPIs.setLayout(null);
        panneauPIs.setBackground(COULEUR_FOND);
        panneauPIs.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, 
                                                             COULEUR_BOUTON));
        panneauPIs.setVisible(false);
        panneauPIs.add(scrollPIs);
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
        panneauTournee.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, 
                                                               COULEUR_BOUTON));
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
        vueEtapes = new AffichageEtapes(new FormatCellRenderer(-1, -1, 2), this, 
                                        this.tournee);
        //Tableau contenant les informatiosn sur les etapes
        tableauEtapes = new JTable(vueEtapes);
        tableauEtapes.setRowHeight(30);
        //tableauEtapes.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableauEtapes.getColumnModel().getColumn(3).setPreferredWidth(150);
        for (int i = 0; i < tableauEtapes.getColumnModel().getColumnCount(); 
                i++) {
            //Appliquer un format aux colonnes du tableau
            tableauEtapes.getColumnModel().getColumn(i).setCellRenderer(
                                                this.vueEtapes.getFormatcell());
        }
        tableauEtapes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectModelEtapes = tableauEtapes.getSelectionModel();
        //Ajouter un evenement sur les lignes du tableau
        listSelectModelEtapes.addListSelectionListener(this.ecouteurListSelect);

        scrollEtapes = new JScrollPane(tableauEtapes, JScrollPane
                        .VERTICAL_SCROLLBAR_ALWAYS, JScrollPane
                        .HORIZONTAL_SCROLLBAR_NEVER);

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
        boutonAjouterPoints.addActionListener(ecouteurBoutons);

        //Ajout des elements a panneauEtapes et ajout de ce dernier 
        //a panneauGauche
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
        panneauDroite.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, 
                                                              COULEUR_BOUTON));

        /* PanneauLegende(haut droit)*/
        //Logo rond de la legende
        rond = new ImageIcon("rond-noir.jpg");
        Image imgR = rond.getImage();
        Image newimgR = imgR.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        rond = new ImageIcon(newimgR);

        labelRond = new JLabel(rond);

        //Legende du rond
        legendeRond = new JLabel(": Point de livraison");
        legendeRond.setFont(new Font("Arial", Font.BOLD, 14));
        legendeRond.setForeground(COULEUR_ECRITURE);

        //Logo carre de la legende
        carre = new ImageIcon("carre-noir.png");
        Image imgC = carre.getImage();
        Image newimgC = imgC.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        carre = new ImageIcon(newimgC);

        labelCarre = new JLabel(carre);

        //Legende du carre
        legendeCarre = new JLabel(": Point d'enlèvement");
        legendeCarre.setFont(new Font("Arial", Font.BOLD, 14));
        legendeCarre.setForeground(COULEUR_ECRITURE);

        //Logo triangle de la legende
        triangle = new ImageIcon("triangle-noir.png");
        Image imgT = triangle.getImage();
        Image newimgT = imgT.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        triangle = new ImageIcon(newimgT);

        
        labelTriangle = new JLabel(triangle);
        //Legende du triangle
        legendeTriangle = new JLabel(": Dépôt des vélos, point de départ de "
                                     + "tous les livreurs");
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

        //Bouton pour zoomer sur la carte
        boutonZoomer = new JButton(ZOOMER);
        boutonZoomer.setFont(new Font("Arial", Font.BOLD, 14));
        boutonZoomer.setForeground(COULEUR_ECRITURE);
        boutonZoomer.setBackground(COULEUR_BOUTON);
        boutonZoomer.addActionListener(ecouteurBoutons);

        //Bouton pour dézoomer sur la carte
        boutonDezoomer = new JButton(DEZOOMER);
        boutonDezoomer.setFont(new Font("Arial", Font.BOLD, 14));
        boutonDezoomer.setForeground(COULEUR_ECRITURE);
        boutonDezoomer.setBackground(COULEUR_BOUTON);
        boutonDezoomer.addActionListener(ecouteurBoutons);

        //Bouton pour decaler la carte vers la droite
        boutonDirDroite = new JButton(DROITE);
        boutonDirDroite.setFont(new Font("Arial", Font.BOLD, 14));
        boutonDirDroite.setForeground(COULEUR_ECRITURE);
        boutonDirDroite.setBackground(COULEUR_BOUTON);
        boutonDirDroite.addActionListener(ecouteurBoutons);
        
        //Bouton pour decaler la carte vers la gauche
        boutonDirGauche = new JButton(GAUCHE);
        boutonDirGauche.setFont(new Font("Arial", Font.BOLD, 14));
        boutonDirGauche.setForeground(COULEUR_ECRITURE);
        boutonDirGauche.setBackground(COULEUR_BOUTON);
        boutonDirGauche.addActionListener(ecouteurBoutons);
        
        //Bouton pour decaler la carte vers le haut
        boutonDirHaut = new JButton(HAUT);
        boutonDirHaut.setFont(new Font("Arial", Font.BOLD, 14));
        boutonDirHaut.setForeground(COULEUR_ECRITURE);
        boutonDirHaut.setBackground(COULEUR_BOUTON);
        boutonDirHaut.addActionListener(ecouteurBoutons);
        
        //Bouton pour decaler la carte vers le bas
        boutonDirBas = new JButton(BAS);
        boutonDirBas.setFont(new Font("Arial", Font.BOLD, 14));
        boutonDirBas.setForeground(COULEUR_ECRITURE);
        boutonDirBas.setBackground(COULEUR_BOUTON);
        boutonDirBas.addActionListener(ecouteurBoutons);

        //Ajout des elements a panneauLegende et ajout de 
        //ce dernier a panneauDroit
        panneauLegende = new JPanel();
        panneauLegende.setLayout(null);
        panneauLegende.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, 
                                                              COULEUR_BOUTON));
        panneauLegende.add(labelRond);
        panneauLegende.add(legendeRond);
        panneauLegende.add(labelCarre);
        panneauLegende.add(legendeCarre);
        panneauLegende.add(labelTriangle);
        panneauLegende.add(legendeTriangle);
        panneauLegende.add(boutonChangerCarte);
        panneauLegende.add(boutonZoomer);
        panneauLegende.add(boutonDezoomer);
        panneauLegende.add(repChangeCarte);
        panneauLegende.add(boutonDirDroite);
        panneauLegende.add(boutonDirGauche);
        panneauLegende.add(boutonDirBas);
        panneauLegende.add(boutonDirHaut);
        panneauLegende.setBackground(COULEUR_FOND);

        legende = new JLabel("Légende");
        legende.setFont(new Font("Arial", Font.BOLD, 18));
        legende.setForeground(COULEUR_ECRITURE);
        panneauLegende.add(legende);
        panneauDroite.add(panneauLegende);

        /* Fin PanneauLegende */
        
        /* PanneauCarte (bas droit) */
        this.setZoom(1.0);
        this.setDeplX(0);
        this.setDeplY(0);
        System.out.println("Dans la creation de la fenetre : "+zoom);


        this.makePalette();

        panneauCarte = new JCarte(this.carte, this.tournee, this);
        panneauCarte.setLayout(null);
        panneauCarte.setBackground(COULEUR_ECRITURE);
        panneauCarte.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, 
                                                              COULEUR_BOUTON));
        panneauCarte.setSize((int) (this.getWidth() * 0.95),
                             (int) (this.getHeight() * 0.2));
        
        panneauDroite.add(panneauCarte);

        ecouteurSouris = new EcouteurSouris(controleur, panneauCarte, this);
        this.addMouseListener(ecouteurSouris);
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

        //afficher une erreur en cas de probleme lors du chargement du fichier
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
        panneauGlobal1.setBounds(0, 0, ((int) getSize().width), 
                                ((int) getSize().height));

        titreAppli.setBounds(26 * (int) panneauGlobal1.getWidth() / 100, 
                             1 * (int) panneauGlobal1.getHeight() / 6, 
                             1 * (int) panneauGlobal1.getWidth(), 
                             1 * (int) panneauGlobal1.getHeight() / 10);
        
        inputChargeCarte.setBounds(3 * (int) panneauGlobal1.getWidth() / 10, 
                                   1 * (int) panneauGlobal1.getHeight() / 3, 
                                   1 * (int) panneauGlobal1.getWidth() / 4, 
                                   1 * (int) panneauGlobal1.getHeight() / 20);
        
        boutonChargerCarte.setBounds(6 * ((int) panneauGlobal1.getWidth() / 10), 
                                     1 * (int) panneauGlobal1.getHeight() / 3, 
                                     1 * (int) panneauGlobal1.getWidth() / 8, 
                                     1 * (int) panneauGlobal1.getHeight() / 20);
        
        repChargeCarte.setBounds(3 * (int) panneauGlobal1.getWidth() / 10, 
                                 35 * (int) panneauGlobal1.getHeight() / 100, 
                                 1 * (int) panneauGlobal1.getWidth() / 4, 
                                 1 * (int) panneauGlobal1.getHeight() / 10);
    }

    /**
     * Placement des elements de la deuxieme fenetre
     */
    public void placeObjet2() {
        panneauGlobal2.setBounds(0, 0, ((int) getSize().width), 
                                ((int) getSize().height));
        
        panneauGauche.setBounds(0, 0, 47 * (int) panneauGlobal2.getWidth() 
                                / 100, (int) panneauGlobal2.getHeight());
        
        panneauDroite.setBounds(47 * (int) panneauGlobal2.getWidth() / 100, 0, 
                                53 * (int) panneauGlobal2.getWidth() / 100, 
                                1 * (int) panneauGlobal2.getHeight());
        
        panneauLivraisons.setBounds(0, 0, (int) panneauGauche.getWidth(), 
                                    1 * (int) panneauGauche.getHeight() / 6);
        
        panneauPIs.setBounds(0, 1 * (int) panneauGauche.getHeight() / 6, 
                            (int) panneauGauche.getWidth(), 
                             30 * (int) panneauGauche.getHeight() / 100);
       
        panneauTournee.setBounds(0, 47 * (int) panneauGauche.getHeight() / 100, 
                                 1 * ((int) panneauGauche.getWidth()), 
                                 1 * (int) panneauGauche.getHeight() / 20);
        
        panneauEtapes.setBounds(0, 52 * (int) panneauGauche.getHeight() / 100, 
                                1 * ((int) panneauGauche.getWidth()), 
                                40 * (int) panneauGauche.getHeight() / 100);
       
        panneauLegende.setBounds(0, 0, (int) panneauDroite.getWidth(), 
                                 1 * (int) panneauDroite.getHeight() / 10);

        boutonZoomer.setBounds((int) 76 * panneauLegende.getWidth() / 100, 
                               (int) panneauLegende.getHeight() / 4, 
                               (int) panneauLegende.getWidth() / 15, 
                               4 * (int) panneauLegende.getHeight() / 10);
        
        boutonDezoomer.setBounds((int) 83 * panneauLegende.getWidth() / 100, 
                                 (int) panneauLegende.getHeight() / 4, 
                                 (int) panneauLegende.getWidth() / 15, 
                                 4 * (int) panneauLegende.getHeight() / 10);
        
        boutonChangerCarte.setBounds((int) 4 * panneauLegende.getWidth() / 10, 
                                     (int) panneauLegende.getHeight() / 4, 
                                     (int) panneauLegende.getWidth() / 4, 4 * 
                                     (int) panneauLegende.getHeight() / 10);
        
        boutonDirGauche.setBounds((int) 69 * panneauLegende.getWidth() / 100, 
                                  7*(int) panneauLegende.getHeight() / 10, 
                                  (int) panneauLegende.getWidth() / 15, 
                                  25 * (int) panneauLegende.getHeight() / 100);
        
        boutonDirDroite.setBounds((int) 76 * panneauLegende.getWidth() / 100, 
                                   7*(int) panneauLegende.getHeight() / 10, 
                                   (int) panneauLegende.getWidth() / 15, 
                                   25 * (int) panneauLegende.getHeight() / 100);
        
        boutonDirHaut.setBounds((int) 83 * panneauLegende.getWidth() / 100,
                                7*(int) panneauLegende.getHeight() / 10, 
                                (int) panneauLegende.getWidth() / 15, 
                                25 * (int) panneauLegende.getHeight() / 100);
        
        boutonDirBas.setBounds((int) 90 * panneauLegende.getWidth() / 100, 
                               7*(int) panneauLegende.getHeight() / 10, 
                               (int) panneauLegende.getWidth() / 15, 
                               25 * (int) panneauLegende.getHeight() / 100);

        repChangeCarte.setBounds((int) 4 * panneauLegende.getWidth() / 10, 0, 
                                 (int) panneauLegende.getWidth() / 2, 
                                 (int) panneauLegende.getHeight() / 4);

        int largeurCarte = (int) panneauDroite.getHeight() 
                         - (int) panneauLegende.getHeight();
        panneauCarte.setBounds(0, 1 * (int) panneauDroite.getHeight() / 10, 
                               largeurCarte, 81 * (int) panneauDroite
                                       .getHeight() / 100);
        
        legende.setBounds(1 * (int) panneauLegende.getWidth() / 10, 0, 
                          1 * (int) panneauLegende.getWidth()/6, 
                          1 * (int) panneauLegende.getHeight() / 4);
        
        labelRond.setBounds(0, (int) panneauLegende.getHeight() / 4, 
                            (int) panneauLegende.getWidth() / 25, 
                            (int) panneauLegende.getHeight() / 4);
        
        legendeRond.setBounds((int) panneauLegende.getWidth() / 25, 
                              (int) panneauLegende.getHeight() / 4, 
                              (int) panneauLegende.getWidth() / 5, 
                              (int) panneauLegende.getHeight() / 4);
        
        labelCarre.setBounds(0, (int) 2 * panneauLegende.getHeight() / 4, 
                            (int) panneauLegende.getWidth() / 25, 
                            (int) panneauLegende.getHeight() / 4);
        
        legendeCarre.setBounds((int) panneauLegende.getWidth() / 25, 
                               (int) 2 * panneauLegende.getHeight() / 4, 
                               (int) panneauLegende.getWidth() / 5, 
                               (int) panneauLegende.getHeight() / 4);
        
        labelTriangle.setBounds(0, (int) 3 * panneauLegende.getHeight() / 4, 
                                (int) panneauLegende.getWidth() / 25, 
                                (int) panneauLegende.getHeight() / 4);
        
        legendeTriangle.setBounds((int) panneauLegende.getWidth() / 25, 
                                  (int) 3 * panneauLegende.getHeight() / 4, 
                                  (int) panneauLegende.getWidth(), 
                                  (int) panneauLegende.getHeight() / 4);

        livraisons.setBounds(4 * ((int) panneauLivraisons.getWidth() / 10), 0, 
                             1 * (int) panneauLivraisons.getWidth(), 
                             1 * (int) panneauLivraisons.getHeight() / 5);
        
        inputChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 20, 
                                 1 * (int) panneauLivraisons.getHeight() / 5, 
                                 55 * (int) panneauLivraisons.getWidth() / 100, 
                                 1 * (int) panneauLivraisons.getHeight() / 3);
        
        boutonChargerLivraisons.setBounds(70 
                                          * ((int) panneauLivraisons.getWidth() 
                                          / 100), 1 * (int) panneauLivraisons
                                                  .getHeight() / 5, 
                                          3 * (int) panneauLivraisons.getWidth() 
                                              / 10, 1 * (int) panneauLivraisons
                                                          .getHeight() / 3);
        
        boutonCalculerTournee.setBounds(45 * ((int) panneauLivraisons.getWidth() 
                                        / 100), 6 * (int) panneauLivraisons
                                        .getHeight() / 10, 
                                        1 * (int) panneauLivraisons.getWidth() 
                                        / 4, 1 * (int) panneauLivraisons
                                                        .getHeight() / 3);
        
         
        repChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 20, 
                               1 * (int) panneauLivraisons.getHeight() / 2, 
                               1 * (int) panneauLivraisons.getWidth(), 
                               1 * (int) panneauLivraisons.getHeight() / 3);
        
        boutonUndo.setBounds(90 * ((int) panneauLivraisons.getWidth() / 100),
                             6 * (int) panneauLivraisons.getHeight() / 10,
                             1 * (int) panneauLivraisons.getWidth() / 15,
                             1 * (int) panneauLivraisons.getHeight() / 3);
        
        boutonRedo.setBounds(98 * ((int) panneauLivraisons.getWidth() / 100),
                             6 * (int) panneauLivraisons.getHeight() / 10,
                             1 * (int) panneauLivraisons.getWidth() / 15,
                             1 * (int) panneauLivraisons.getHeight() / 3);
        
        labelTitreTournee.setBounds(4 * (int) panneauTournee.getWidth() / 10, 0, 
                                    1 * (int) panneauTournee.getWidth(), 
                                    1 * (int) panneauTournee.getHeight() / 2);
        
        labelTournee.setBounds(0, 1 * (int) panneauTournee.getHeight() / 2, 
                               1 * (int) panneauTournee.getWidth(), 
                               1 * (int) panneauTournee.getHeight() / 2);

        etapesTitre.setBounds(4 * (int) panneauEtapes.getWidth() / 10, 0, 
                              1 * (int) panneauEtapes.getWidth(), 
                              1 * (int) panneauEtapes.getHeight() / 20);
        tableauEtapes.setBounds(0, 1 * (int) panneauEtapes.getHeight() / 20, 
                                1 * (int) panneauEtapes.getWidth(), 
                                75 * (int) panneauEtapes.getHeight() / 100);
        
        scrollEtapes.setBounds(0, 1 * (int) panneauEtapes.getHeight() / 20, 
                               1 * (int) panneauEtapes.getWidth(), 
                               75 * (int) panneauEtapes.getHeight() / 100);
        
        boutonAjouterPoints.setBounds(1 * (int) panneauEtapes.getWidth() / 25, 
                                     81 * (int) panneauEtapes.getHeight() / 100, 
                                     1 * (int) panneauEtapes.getWidth() / 5, 
                                    15 * (int) panneauEtapes.getHeight() / 100);
        
        boutonSupprimer.setBounds(7 * (int) panneauEtapes.getWidth() / 25, 
                                  81 * (int) panneauEtapes.getHeight() / 100, 
                                  1 * (int) panneauEtapes.getWidth() / 5, 
                                  15 * (int) panneauEtapes.getHeight() / 100);
        
        boutonModifier.setBounds(13 * (int) panneauEtapes.getWidth() / 25, 
                                 81 * (int) panneauEtapes.getHeight() / 100, 
                                 1 * (int) panneauEtapes.getWidth() / 5, 
                                 15 * (int) panneauEtapes.getHeight() / 100);
        
        boutonAnnuler.setBounds(19 * (int) panneauEtapes.getWidth() / 25, 
                                81 * (int) panneauEtapes.getHeight() / 100, 
                                1 * (int) panneauEtapes.getWidth() / 5, 
                                15 * (int) panneauEtapes.getHeight() / 100);

        scrollPIs.setBounds(0, 0, (int) panneauPIs.getWidth(), 
                            (int) panneauPIs.getHeight());

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
    }
    

    /**
     * Pour rendre non cliquable le bouton pour calculer une tournee
     */
    public void griserBoutonCalcul() {
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
        this.boutonAjouterPoints.setEnabled(false);
        this.boutonUndo.setVisible(false);
        this.boutonRedo.setVisible(false);
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
        this.boutonAjouterPoints.setEnabled(true);
        this.boutonUndo.setVisible(true);
        this.boutonRedo.setVisible(true);
    }
    
    /**
     * Pour afficher les details d une tournee
     *
     * @param afficher savoir si la liste a afficher est non vide
     */
    public void afficherEtapesTour(boolean afficher) {
        panneauEtapes.setVisible(true);
        panneauTournee.setVisible(true);
        vueTournee.setTournee(tournee);
        vueTournee.afficherTournee();
        vueEtapes.setTournee(tournee);
        vueEtapes.afficherEtapes(afficher);

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
     *
     * @param afficher savoir si la liste a afficher est vide
     */
    public void afficherPanneauPI(boolean afficher) {
        panneauPIs.setVisible(true);
        vuePIs.setCarte(carte);
        vuePIs.afficherPIs(afficher);
    }

    /**
     * Mettre en surbrillance la ligne du tableau correspondant au point d
     * interet clique
     *
     * @param ptI point d'interet a mettre en surbrillance dans le tableau
     */
    public void surbrillerLigneTabPI(PointInteret ptI) {
        if (carte != null) {
            ArrayList<PointInteret> listePtI = carte
                                               .getListePointsInteretActuelle();

            if (listePtI != null && !listePtI.isEmpty()) {
                int indexPI = listePtI.indexOf(ptI);
                if (indexPI != -1 && tableauPIs.getRowCount() != 0) {
                    PointInteret ptIDep = new PointInteret();
                    int indexPIDep = 0;

                    //Ce n est pas l entrepot qui est selectionne
                    if (indexPI != 0) {
                        ptIDep = ptI.getPointDependance();
                        indexPIDep = listePtI.indexOf(ptIDep);
                    }
                    for (int j = 0; j < tableauPIs.getColumnModel()
                            .getColumnCount(); j++) {
                        //Encadrer en rouge la ligne correspond a l index
                        this.vuePIs.getFormatcell().setIndexPI(indexPI);
                        this.vuePIs.getFormatcell().setIndexPIDep(indexPIDep);
                        tableauPIs.getColumnModel().getColumn(j)
                                .setCellRenderer(this.vuePIs.getFormatcell());
                    }
                }
            }
        }

    }

    /**
     * Mettre en surbrillance la ligne du tableau correspondant au point d
     * interet clique
     *
     * @param ptI point d'interet a mettre en surbrillance
     */
    public void surbrillerLigneTabEtapes(PointInteret ptI) {
        if (tournee != null) {
            ArrayList<PointInteret> listePtEtapes = tournee
                                    .getSuccessionPointsInteret();
            if (listePtEtapes != null && !listePtEtapes.isEmpty()) {
                int indexPI = listePtEtapes.indexOf(ptI);
                if (indexPI != -1 && tableauEtapes.getRowCount() != 0) {
                    PointInteret ptIDep = new PointInteret();
                    int indexPIDep = 0;
                    if (indexPI != 0) {
                        ptIDep = ptI.getPointDependance();
                        indexPIDep = listePtEtapes.indexOf(ptIDep);
                    }
                    for (int j = 0; j < tableauEtapes.getColumnModel()
                         .getColumnCount(); j++) {
                        //Encadrer en rouge la ligne correspond a l index
                        this.vueEtapes.getFormatcell().setIndexPI(indexPI);
                        this.vueEtapes.getFormatcell().setIndexPIDep(indexPIDep);
                        tableauEtapes.getColumnModel().getColumn(j)
                               .setCellRenderer(this.vueEtapes.getFormatcell());
                    }

                }

            }
        }
    }

    /**
     * Entourer le point d interet correspondant a la ligne du tableau
     * selectionnee
     *
     * @param pi point d'interet a surbriller
     */
    public void surbrillerPI(PointInteret pi) {
        ArrayList<PointInteret> listePtI = carte
                                           .getListePointsInteretActuelle();

        //La liste n est pas nulle ni vide
        if (listePtI != null && !listePtI.isEmpty()) {
            int lignePi = listePtI.indexOf(pi);

            //Le point d interet fait partie de la liste
            if (lignePi != -1) {
                PointInteret piDep = new PointInteret();
                int ligneDep = 0;

                //Ce n est pas l entrepot qui est selectionne
                if (lignePi != 0) {
                    piDep = pi.getPointDependance();
                    ligneDep = listePtI.indexOf(piDep);
                }
                this.vueEtapes.setLignePISelect(lignePi);
                this.vuePIs.setLignePISelect(lignePi);
                this.vueEtapes.setLignePIDepSelect(ligneDep);
                this.vuePIs.setLignePIDepSelect(ligneDep);
                this.panneauCarte.setFenetre(this);
                this.panneauCarte.updateUI();
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
     * Recuperer le panneau de la carte
     *
     * @return pannneau carte
     */
    public JCarte getPanneauCarte() {
        return this.panneauCarte;
    }
    

    /**
     * Modifier le panneau de la carte
     *
     * @param nouvelleCarte nouveau panneau carte
     */
    public void setPanneauCarte(JCarte nouvelleCarte) {
        this.panneauCarte = nouvelleCarte;
        //this.panneauCarte.makePalette();
        this.panneauCarte.updateUI();

    }
    /**
     * Modifier la valeur du zoom de la carte
     * @param z  nouveau zoom
     */
    public void setZoom(double z){
        this.zoom=z;
    }
    
    /**
     * Recuperer la valeur du zoom appliquee a la carte
     * 
     * @return zoom actuel
     */
    public double getZoom(){
        return zoom;
        
    }
    
    /**
     * Modifier la valeur du deplacement sur l'axe des x 
     * appliquee a la vue de la carte
     * @param dx nouvelle valeur de x
     */
    public void setDeplX(double dx){
        this.deplacementX=dx;
    }
    
    /**
     * Recuperer la valeur du deplacement sur x appliquee a la vue de la carte
     * @return deplacement sur x
     */
    public double getDeplX(){
        return this.deplacementX;
    }
    
    /**
     * Modifier la valeur du decalagae sur l'axe des y appliquee a la vue 
     * de la carte
     * @param dy nouveau decalage sur y
     */
    public void setDeplY(double dy){
        this.deplacementY=dy;
    }
    
    /**
     * Recuperer la valeur du decalage sur y appliquee a la vue de la carte
     * @return decalage actuel sur y
     */
    public double getDeplY(){
        return this.deplacementY;
    }
    
    /**
     * Faire un tableau de 50 couleurs differentes pour les couleurs
     * des points d'interet sur l'affichage de la carte
     */
    public void makePalette(){
        this.palette.add(Color.MAGENTA);
        this.palette.add(Color.GREEN);
        this.palette.add(Color.pink);
        this.palette.add(new Color(108,2,119));
        this.palette.add(new Color(250, 158, 10));
        this.palette.add(new Color(210, 80, 20));
        this.palette.add(new Color(1, 121, 111));
        this.palette.add(new Color(252, 93, 93));
        this.palette.add(new Color(128, 128, 0));
        for (int c=8;c<50;c++) {
                Random rand = new Random();
                float r = rand.nextFloat();
                float gg = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, gg, b);
                if( !randomColor.equals(Color.white)
                    && !randomColor.equals(Color.black)
                    && !randomColor.equals(Color.lightGray)
                    && !randomColor.equals(palette.get(0))  
                    && !randomColor.equals(palette.get(1)) 
                    && !randomColor.equals(palette.get(2)) 
                    && !randomColor.equals(palette.get(3)) 
                    && !randomColor.equals(palette.get(4)) 
                    && !randomColor.equals(palette.get(5)) 
                    && !randomColor.equals(palette.get(6)) 
                    && !randomColor.equals(palette.get(7)) 
                    && !randomColor.equals(palette.get(8)))
                        this.palette.add(randomColor);
                }
    }
    
    /**
     * Recuperer la liste crée des couleurs de spoints d'intéret
     * @return liste de couleurs
     */
    public ArrayList<Color> getPalette(){
        return this.palette;
    }

    /**
     * Recuperer la vue sur les etapes
     *
     * @return vue etapes
     */
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
    public void setPanneauTournee(String heureDeb, String heureFin, 
                                  String duree) {
        this.labelTournee.setText("   " + HEURE_DEBUT + heureDeb + "      " 
                                  + HEURE_FIN + heureFin + "      " 
                                  + DUREE + duree);
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
    public void setPanneauEtapes(int ordre, int numDemande, String type, 
                                 String adresse, String heureDep, 
                                 String heureArr, String duree) {
        LigneEtapes step = new LigneEtapes(ordre, numDemande, type, adresse, 
                                           heureDep, heureArr, duree + " min");
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
    public void setPanneauPIs(int numEtape, String type, String adresse, 
                              String duree) {
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
    public void setPanneauEtapesEntrepot(int ordre, int numDemande, 
                                         String adresse, String heure) {
        LigneEtapes step;
        if (ordre == 0) {
            step = new LigneEtapes(ordre, numDemande, "Entrepot", adresse, 
                                   heure, "", "");

        } else {
            step = new LigneEtapes(ordre, numDemande, "Entrepot", adresse, "", 
                                   heure, "");
        }

        this.vueEtapes.addStep(step);
    }

    /**
     * Vider le panneauEtapes
     */
    public void viderPanneauEtapes() {
        this.vueEtapes.clearSteps();
        this.vueEtapes.getFormatcell().setIndexPI(-1);
        this.vueEtapes.getFormatcell().setIndexPIDep(-1);
        this.vueEtapes.setLignePISelect(-1);
        this.vueEtapes.setLignePIDepSelect(-1);
        this.panneauCarte.setFenetre(this);
        this.panneauCarte.updateUI();
    }

    /**
     * Vider le panneauPIs
     */
    public void viderPanneauPIs() {
        this.vuePIs.clearPIs();
        this.vuePIs.getFormatcell().setIndexPI(-1);
        this.vuePIs.getFormatcell().setIndexPIDep(-1);
        this.vuePIs.setLignePISelect(-1);
        this.vuePIs.setLignePIDepSelect(-1);
        this.panneauCarte.setFenetre(this);
        this.panneauCarte.updateUI();
    }

    /**
     * Modifier la tournee
     *
     * @param tournee nouvelle tournee
     */
    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
        this.panneauCarte.updateUI();

    }

    /**
     * Recuperer la largeur du panneau gauche
     *
     * @return largeur panneau de gauche
     */
    public int getWidthPanneauGauche() {
        return this.panneauGauche.getWidth();
    }

    /**
     * Recuperer la hauteur du panneau de legende
     *
     * @return hauteur panneau legende
     */
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
     * Cacher le message d erreur si tout s est bien passe lors 
     * du changement de la carte
     */
    public void retireMessageErreur3() {
        repChangeCarte.setVisible(false);
    }

    /**
     * Modifier la valeur du clic sur le bouton supprimer
     *
     * @param clic nouveau clic
     */
    public void setClicSupp(boolean clic) {
        this.clicSupp = clic;
    }

    /**
     * Savoir si on a clique sur le bouton supprimer
     *
     * @return clic
     */
    public boolean isClicSupp() {
        return this.clicSupp;
    }

    /**
     * Savoir si on a clique sur le bouton modifier
     *
     * @return clic modifier
     */
    public boolean isClicModif() {
        return clicModif;
    }

    /**
     * Modifier la valeur du clic sur le bouton modifier
     *
     * @param clicModif nouveau clic
     */
    public void setClicModif(boolean clicModif) {
        this.clicModif = clicModif;
    }

    /**
     * Recuperer le chemin vers le fichier XML de la carte
     *
     * @return valeur input
     */
    public String getInputChargeCarte() {
        return inputChargeCarte.getText();
    }

    /**
     * Recuperer le chemin vers le fichier XML des livraisons
     *
     * @return valeur input
     */
    public String getInputChargeLiv() {
        return inputChargeLiv.getText();
    }

    /**
     * Recuperer la carte
     *
     * @return carte
     */
    public Carte getCarte() {
        return carte;
    }

    /**
     * Recuperer la tournee
     *
     * @return tournee
     */
    public Tournee getTournee() {
        return tournee;
    }

    /**
     * Recuperer la vue sur les points dinteret
     *
     * @return vuePIs
     */
    public AffichagePIs getVuePIs() {
        return vuePIs;
    }

    /**
     * Recuperer le modele de la liste de selection des points dinteret
     *
     * @return modele liste selection point dinteret
     */
    public ListSelectionModel getListSelectModelPI() {
        return listSelectModelPI;
    }

    /**
     * Recuperer le modele de la liste de selection des etapes
     *
     * @return modele de la liste de selection des etapes
     */
    public ListSelectionModel getListSelectModelEtapes() {
        return listSelectModelEtapes;
    }
    
    /**
     * Recuperer le boutonUndo pour savoir quand est ce qu il est clique
     * 
     * @return boutonUndo
     */
    public JButton getBoutonUndo(){
        return boutonUndo;
    }
    
    /**
     * Recuperer le boutonRedo pour savoir quand est ce qu il est clique
     * 
     * @return boutonRedo
     */
    public JButton getBoutonRedo(){
        return boutonRedo;
    }
    

    /**
     * Afficher une popup pour valider la suppression d un point d interet
     *
     * @param pti point d interet a supprimer
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
            message += type + "\n" + "correspondant à la demande de livraison " 
                    + num + "\n" + "en sachant que le point d'intérêt de type " 
                    + typeAssocie + "\n" + "associé sera également supprimé ?";
        }
        int option = jop.showConfirmDialog(null, message, 
                "Suppression d'un point d 'intérêt", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

        return option;
    }

    /**
     * Afficher une popup d erreur dans le cas ou l utilisateur veut supprimer l
     * entrepot
     *
     */
    public void afficherPopSuppressionErreur() {
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "Vous ne pouvez pas supprimer l'entrepôt", 
                              "Erreur", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Afficher une popup pour prevenir que plus de points dans la liste
     *
     */
    public void afficherPopSuppressionVide() {
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "La tournée ne possède plus de points", 
                              "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Afficher une popup pour prevenir que la suppression n a pas ete effectuee
     *
     */
    public void afficherPopSuppressionAnnulee() {
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "La suppression n'a pas pu être effectuée ", 
                              "Information", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Afficher une popup d erreur dans le cas ou l utilisateur veut deplacer l
     * entrepot
     *
     */
    public void afficherPopDeplacerErreur() {
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "Vous ne pouvez pas déplacer l'entrepôt", 
                              "Erreur", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Afficher une popup pour savoir de combien deplacer le point d interet
     *
     * @param min indice minimal dont on peut deplacer le point
     * @param max indice maximal dont on peut deplacer le point
     * @return la liste d'indices possibles
     */
    public ArrayList<Integer> afficherPopModification(int min, int max) {
        SpinnerNumberModel sModel = new SpinnerNumberModel(0, min, max, 1);
        JSpinner spinner = new JSpinner(sModel);

        int option = JOptionPane.showOptionDialog(null, spinner, 
                "Déplacement d'un point d'intérêt", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, null, null);
        int value = ((Integer) spinner.getValue()).intValue();
        ArrayList<Integer> choix = new ArrayList<Integer>();
        choix.add(option);
        choix.add(value);
        return choix;
    }
    
    

    /**
     * Afficher une popup pour prevenir que la contrainte de precedence n est
     * pas respectee
     */
    public void afficherPopPrevenirModification() {
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, "La modification a été effectuée même si \n"
                + " point d'intérêt de livraison est placé\n avant le point "
                + "d'enlèvement dans la tournée", "Information", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Modifier le point d'enlevement que l'utilisateur veut ajouter
     * @param pE nouveau point d'interet
     */
    public void setPE(PointInteret pE){
        this.pE = pE;
    }
    
    /**
     * Modifier le point d'interet que l'utilisateur a choisi avant son point
     * d'enlevement
     * @param index indice du point d'intéret place avant le point d'enlevement
     */
    public void setAvantPEParIndex(int index){
        this.avantPE = carte.getListePointsInteretActuelle().get(index);
        System.out.println("pAE dans setter:"+avantPE.getIntersection()
                                                     .getId());
    }    
    
    /**
     * Modifier le point de livraison que l'utilisateur veut ajouter
     * a la tournee
     * @param pL nouveau point de livraison
     */
    public void setPL(PointInteret pL){
        this.pL = pL;
    }
    
    /**
     * Modifier le point d'intéret qui est avant le point de livraison a 
     * ajouter a la tournee
     * @param index indice du point d'interetplace avant le point de livraison
     * dans la tournee
     */
    public void setAvantPLParIndex(int index){
        this.avantPL = carte.getListePointsInteretActuelle().get(index);
    }
    
    /**
     * Remettre tous les points a ajoutes a la tournee a null
     */
    public void clearAllPointsAjoutes(){
        this.pE = null;
        this.pL = null;
        this.avantPE = null;
        this.avantPL = null;
    }
    
    /**
     * Récupérer le point d'enlèvement sélectionné
     *
     * @return point d'enlèvement
     */
    public PointInteret getPE(){
        return this.pE;
    }
    
    /**
     * Récupérer le point d'avant-enlèvement sélectionné
     *
     * @return point d'avant-enlèvement
     */
    public PointInteret getAvantPE(){
        return this.avantPE;
    }
    
    /**
     * Récupérer le point de livraison
     *
     * @return point de livraison
     */    
    public PointInteret getPL(){
        return this.pL;
    }
    
    /**
     * Récupérer le point d'avant-livraison sélectionné
     *
     * @return point d'avant-livraison
     */    
    public PointInteret getAvantPL(){
        return this.avantPL;
    }

    /**
     * Savoir si un point d'interet avant l'enlevement a ete selectionne
     * sur la carte
     * @return booleean si un point a ete clique
     */
    public boolean isClicAjoutAvantEnlvt() {
        return clicAjoutAvantEnlvt;
    }

    /**
     * Modifier la valeur du boolean pour savoir si un point a ete clique
     * avant de passer a l'etat d'ajout d'apres
     * @param clicAjoutAvantEnlvt valeur du booleen du clic actuel
     */
    public void setClicAjoutAvantEnlvt(boolean clicAjoutAvantEnlvt) {
        this.clicAjoutAvantEnlvt = clicAjoutAvantEnlvt;
    }
    
    /**
     * Pour rendre les boutons undo / redo non cliquables 
     */
    public void griserBoutonsUndoRedo() {
        this.boutonUndo.setVisible(false);
        this.boutonRedo.setVisible(false);
    }

}
