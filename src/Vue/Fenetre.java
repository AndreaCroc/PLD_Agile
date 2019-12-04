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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
    private AffichageEtapes vueEtapes;
    private AffichagePIs vuePIs;

    //Tableau contenant le details des etapes de la tournee
    private JTable tableauEtapes;

    //Tabeau contenant une vue generale des points d'interets de la tournee
    private JTable tableauPIs;

    //Savoir si un element est deja mis en surbrillance
    private boolean surbrillance;

    //Constantes utilisee pour l affichage
    private static final long serialVersionUID = 1L;
    protected final static String CHARGER_CARTE = "Charger carte";
    protected final static String CHARGER_LIVRAISONS = "Charger livraisons";
    protected final static String CALCULER_TOURNEE = "Calculer tournée";
    protected final static String CHANGER_CARTE = "Changer carte";
    protected final static String MODIFIER = "Modifier";
    protected final static String SUPPRIMER = "Supprimer";
    
    protected final static String HEURE_DEBUT = "Heure de début prévue : ";
    protected final static String HEURE_FIN = "Heure de fin prévue : ";
    protected final static String DUREE = "Durée prévue : ";

    //Boutons sur lesquels l utilisateur peut cliquer
    private JButton boutonChargerCarte;
    private JButton boutonChargerLivraisons;
    private JButton boutonCalculerTournee;
    private JButton boutonChangerCarte;
    private JButton boutonModifier;
    private JButton boutonSupprimer;

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

        this.vueTournee = new AffichageTournee(tournee, this);

        this.ecouteurBoutons = new EcouteurBoutons(this.controleur);
        
        this.ecouteurListSelect = new EcouteurListSelection(this.controleur);

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
        repChargeLiv.setFont(new Font("Arial", Font.BOLD, 13));
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
        
        /* PanneauPIs (haut gauche) */
        //Vue sur les details des points d interets d une demande de livraison
        vuePIs = new AffichagePIs(new FormatCellRenderer(-1), this.carte, this);
        //Tableau contenant les details des points d interets
        tableauPIs = new JTable(vuePIs);
        //Ajuster la taille des lignes
        tableauPIs.setRowHeight(40);
        //Ajuster la taille des colonnes
        //tableauPIs.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableauPIs.getColumnModel().getColumn(3).setPreferredWidth(250);

        for (int i = 0; i < tableauPIs.getColumnModel().getColumnCount(); i++) {
            if(i<3){
                //Appliquer un formatage a certaines colonnes du tableau
                tableauPIs.getColumnModel().getColumn(i).setCellRenderer(this.vuePIs.getFormatcell());
            }else{
                //Appliquer un formatage a certaines colonnes du tableau
                 this.tableauPIs.setDefaultRenderer(JComponent.class, new TypeCellRenderer());
            }
        }
        
        tableauPIs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPIs = new JScrollPane(tableauPIs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // PanneauPIs (milieu haut gauche)
        panneauPIs = new JPanel();
        panneauPIs.setLayout(null);
        panneauPIs.setBackground(new Color(186, 228, 255));
        panneauPIs.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 70, 120)));
        panneauPIs.setVisible(false);
        panneauPIs.add(scrollPIs);
        panneauGauche.add(panneauPIs);

        /* PanneauTournee (milieu bas gauche) */
        //Titre de panneauTournee
        labelTitreTournee = new JLabel("Tournée");
        labelTitreTournee.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitreTournee.setForeground(Color.white);

        //Heure de debut de la tournee
        labelTournee = new JLabel(HEURE_DEBUT);
        labelTournee.setFont(new Font("Arial", Font.BOLD, 14));
        labelTournee.setForeground(Color.white);

        //Ajout des elements a panneauTournee et ajout de ce dernier a panneauGauche
        panneauTournee = new JPanel();
        panneauTournee.setLayout(null);
        panneauTournee.setBackground(new Color(186, 228, 255));
        panneauTournee.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(50, 70, 120)));
        panneauTournee.add(labelTitreTournee);
        panneauTournee.add(labelTournee);
        panneauTournee.setVisible(false);
        panneauGauche.add(panneauTournee);

        /* Fin PanneauTournee*/
        
        /* PanneauEtape (bas gauche)*/
        //Titre de panneauEtape
        etapesTitre = new JLabel("Etapes");
        etapesTitre.setFont(new Font("Arial", Font.BOLD, 18));
        etapesTitre.setForeground(Color.white);

        //Vue sur les etapes d une tournee
        vueEtapes = new AffichageEtapes(new FormatCellRenderer(-1),this,this.tournee);
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
        boutonSupprimer.setForeground(Color.white);
        boutonSupprimer.setBackground(new Color(50, 70, 120));
        boutonSupprimer.addActionListener(ecouteurBoutons);
        
        //Bouton pour modifier  la tournee
        boutonModifier = new JButton(MODIFIER);
        boutonModifier.setFont(new Font("Arial", Font.BOLD, 14));
        boutonModifier.setForeground(Color.white);
        boutonModifier.setBackground(new Color(50, 70, 120));
        boutonModifier.addActionListener(ecouteurBoutons);
        
        //Ajout des elements a panneauEtapes et ajout de ce dernier a panneauGauche
        panneauEtapes = new JPanel();
        panneauEtapes.setLayout(null);
        panneauEtapes.setBackground(new Color(186, 228, 255));
        panneauEtapes.add(etapesTitre);
        panneauEtapes.add(scrollEtapes);
        panneauEtapes.add(boutonSupprimer);
        panneauEtapes.add(boutonModifier);
        panneauEtapes.setVisible(false);
        panneauGauche.add(panneauEtapes);

        /* PanneauDroite */
        //Panneau droit contenant la legende et la carte
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

        //Bouton pour changer une carte, fichier XML
        boutonChangerCarte = new JButton(CHANGER_CARTE);
        boutonChangerCarte.setFont(new Font("Arial", Font.BOLD, 13));
        boutonChangerCarte.setForeground(Color.white);
        boutonChangerCarte.setBackground(new Color(50, 70, 120));
        boutonChangerCarte.addActionListener(ecouteurBoutons);

        //Pour afficher les messages d erreur lies au chargement du fichier
        repChangeCarte = new JLabel("Erreur dans le chargement du fichier");
        repChangeCarte.setFont(new Font("Arial", Font.BOLD, 14));
        repChangeCarte.setForeground(new Color(254, 79, 65));
        repChangeCarte.setVisible(false);

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
        panneauLegende.add(boutonChangerCarte);
        panneauLegende.add(repChangeCarte);
        panneauLegende.setBackground(new Color(186, 228, 255));

        legende = new JLabel("Légende");
        legende.setFont(new Font("Arial", Font.BOLD, 18));
        legende.setForeground(Color.white);
        panneauLegende.add(legende);
        panneauDroite.add(panneauLegende);

        /* Fin PanneauLegende */
        
        /* PanneauCarte (bas droit) */
        panneauCarte = new JCarte(this.carte, this.tournee, this);
        panneauCarte.setLayout(null);
        panneauCarte.setBackground(Color.white);
        panneauCarte.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(50, 70, 120)));
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
        panneauLivraisons.setBounds(0, 0, (int) panneauGauche.getWidth(), 1 * (int) panneauGauche.getHeight() / 6);
        panneauPIs.setBounds(0, 1 * (int) panneauGauche.getHeight() / 6, (int) panneauGauche.getWidth(), 30 * (int) panneauGauche.getHeight() / 100);
        panneauTournee.setBounds(0, 47 * (int) panneauGauche.getHeight() / 100, 1 * ((int) panneauGauche.getWidth()), 1 * (int) panneauGauche.getHeight() / 20);
        panneauEtapes.setBounds(0, 52 * (int) panneauGauche.getHeight() / 100, 1 * ((int) panneauGauche.getWidth()), 40 * (int) panneauGauche.getHeight() / 100);
        panneauLegende.setBounds(0, 0, (int) panneauDroite.getWidth(), 1 * (int) panneauDroite.getHeight() / 10);

        boutonChangerCarte.setBounds((int) 6 * panneauLegende.getWidth() / 10, (int) panneauLegende.getHeight() / 4, (int) panneauLegende.getWidth() / 4, (int) panneauLegende.getHeight() / 3);
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
        inputChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 20, 1 * (int) panneauLivraisons.getHeight() / 5, 1 * (int) panneauLivraisons.getWidth() / 2, 1 * (int) panneauLivraisons.getHeight() / 3);
        boutonChargerLivraisons.setBounds(60 * ((int) panneauLivraisons.getWidth() / 100), 1 * (int) panneauLivraisons.getHeight() / 5, 3 * (int) panneauLivraisons.getWidth() / 10, 1 * (int) panneauLivraisons.getHeight() / 3);
        boutonCalculerTournee.setBounds(45 * ((int) panneauLivraisons.getWidth() / 100), 6 * (int) panneauLivraisons.getHeight() / 10, 1 * (int) panneauLivraisons.getWidth() / 4, 1 * (int) panneauLivraisons.getHeight() / 3);
        repChargeLiv.setBounds(1 * (int) panneauLivraisons.getWidth() / 20, 1 * (int) panneauLivraisons.getHeight() / 2, 1 * (int) panneauLivraisons.getWidth(), 1 * (int) panneauLivraisons.getHeight() / 4);

        labelTitreTournee.setBounds(4 * (int) panneauTournee.getWidth() / 10, 0, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 2);
        labelTournee.setBounds(0, 1 * (int) panneauTournee.getHeight() / 2, 1 * (int) panneauTournee.getWidth(), 1 * (int) panneauTournee.getHeight() / 2);

        etapesTitre.setBounds(4 * (int) panneauEtapes.getWidth() / 10, 0, 1 * (int) panneauEtapes.getWidth(), 1 * (int) panneauEtapes.getHeight() / 20);
        tableauEtapes.setBounds(0, 1 * (int) panneauEtapes.getHeight() / 20, 1 * (int) panneauEtapes.getWidth(), 75 * (int) panneauEtapes.getHeight() / 100);
        scrollEtapes.setBounds(0, 1 * (int) panneauEtapes.getHeight() / 20, 1 * (int) panneauEtapes.getWidth(), 75 * (int) panneauEtapes.getHeight() / 100);
        boutonSupprimer.setBounds(1 * (int) panneauEtapes.getWidth()/5,82 * (int) panneauEtapes.getHeight() / 100,1 * (int) panneauEtapes.getWidth()/4,15 * (int) panneauEtapes.getHeight() / 100);
        boutonModifier.setBounds(1 * (int) panneauEtapes.getWidth()/2,82 * (int) panneauEtapes.getHeight() / 100,1 * (int) panneauEtapes.getWidth()/4,15 * (int) panneauEtapes.getHeight() / 100);

        scrollPIs.setBounds(0, 0, (int) panneauPIs.getWidth(), (int) panneauPIs.getHeight());
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
    public void cacherPanneauPI(){
        this.panneauPIs.setVisible(false);
    }

    /**
     * Pour rendre visible le panneauPI
     */
    public void afficherPanneauPI() {
        panneauPIs.setVisible(true);
        vuePIs.afficherPIs();
    }

    /**
     * Mettre en surbrillance la ligne du tableau correspondant a l index
     * 
     * @param index : ligne du tableau a encadrer
     */
    public void surbrillanceLigneTab(int index) {
        //Si on clique sur un point d interet alors qu il y en avait deja un  
        //encadre en rouge, enlever le cadre autour de ce point
        if (this.surbrillance) {
            this.vueEtapes.setLigneSelect(-1);
            this.panneauCarte.setFenetre(this);
            this.panneauCarte.updateUI();
            this.surbrillance = false;
        }
        if (tableauEtapes.getRowCount() != 0) {
            for (int j = 0; j < tableauEtapes.getColumnModel().getColumnCount(); j++) {
                //Encadrer en rouge la ligne correspond a l index
                this.vueEtapes.getFormatcell().setIndex(index);
                tableauEtapes.getColumnModel().getColumn(j).setCellRenderer(this.vueEtapes.getFormatcell());
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

    public JCarte getPanneauCarte() {
        return this.panneauCarte;
    }

    public void setPanneauCarte(JCarte nouvelleCarte) {
        this.panneauCarte = nouvelleCarte;
        this.panneauCarte.updateUI();

    }
    
    public EcouteurBoutons getEcouteurBoutons(){
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
     * @param numDemande numero de la demande de livraison associe a un point d interet
     * @param type le type de l etape
     * @param adresse l adresse de l etape
     * @param heureDep l heure de depart de l etape
     * @param heureArr l heure d arrivee de l etape
     * @param duree duree de l etape
     */
    public void setPanneauEtapes(int ordre, int numDemande,String type, String adresse, String heureDep, String heureArr, String duree) {
        LigneEtapes step = new LigneEtapes(ordre, numDemande,type, adresse, heureDep, heureArr, duree + " min");
        this.vueEtapes.addStep(step);
    }

    /**
     * Afficher le detail de chaque point d interet
     *  faisant partie de la demande de livraions
     * 
     * @param numEtape numero de la demande de livraison
     * @param type type de l etape
     * @param adresse  adresse de l etape
     * @param duree duree de l etape
     */
    public void setPanneauPIs(int numEtape, String type, String adresse,String duree) {
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
    public void setPanneauEtapesEntrepot(int ordre,int numDemande, String adresse, String heure) {
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
     * @param ligne ligne du tableau selectionnee
     */
    public void entourerPI(int ligne) {
        //Si une ligne du tableau etait deja en surbrillance au moment du clic sur une ligne
        //Enlever le contour rouge de cette ligne du tableau
        if (this.surbrillance) {
            this.vueEtapes.getFormatcell().setIndex(-1);
            this.surbrillance = false;
        }
        this.vueEtapes.setLigneSelect(ligne);
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
     * Cacher le message d erreur si tout s est bien passe lors du changement
     * de la carte
     */
    public void retireMessageErreur3() {
        repChangeCarte.setVisible(false);
    }
}
