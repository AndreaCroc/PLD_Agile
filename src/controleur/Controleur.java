package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * Controleur
 *
 * Version 1
 *
 *
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne MAGNIEN,
 * Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Controleur {

    /**
     * Classe Controleur qui permet de faire le lien entre la vue et le modele
     */

    private Fenetre fenetre; //Fenetre qui traite actions recuperees par controleur
    private Carte carte; //Carte
    private Tournee tournee; //Tournee realisee
    private Etat etatCourant = new EtatInit(); //Etat du controleur
    private ListeCdesTournee listeCommandes;

    // Instances associees a chaque etat possible du controleur
    protected final EtatInit etatInit = new EtatInit();
    protected final EtatDeBase etatDeBase = new EtatDeBase();
    protected final EtatLivraison etatLivraison = new EtatLivraison();
    protected final EtatTournee etatTournee = new EtatTournee();
    protected final EtatSupprimer etatSupprimer = new EtatSupprimer();
    protected final EtatAjouter etatAjouter = new EtatAjouter();
    protected final EtatModifier etatModifier = new EtatModifier();

    /**
     * Constructeur de la classe du Controleur
     */
    public Controleur() {
        carte = new Carte();
        tournee = new Tournee();
        fenetre = new Fenetre(this, carte, tournee); //lui passer this
        listeCommandes = new ListeCdesTournee(carte, tournee,fenetre, this);
    }

    /**
     * Charge une nouvelle carte
     *
     */
    public void chargerCarte() {
        etatCourant.chargerPageDeBase(this, fenetre, carte, tournee);
    }

    /**
     * Change la carte donc en charge une nouvelle
     */
    public void changerCarte() {
        etatCourant.changerCarte(this, fenetre, carte);
    }

    /**
     * Charger une livraison
     */
    public void chargerLivraison() {
        etatCourant.chargerLivraison(this, fenetre, carte);
    }

    /**
     * Calculer une tournee
     */
    public void calculerTournee() {
        etatCourant.calculerTournee(this, fenetre, carte, tournee);
    }

    public void modifier(int index) {
        etatCourant.modifier(this, fenetre, tournee, carte, index);
    }

    /**
     * Supprimer un point d interet de la tournee
     *
     * @param index : numero du point d interet a supprimer
     */
    public void supprimer(int index) {
        etatCourant.supprimer(this, fenetre, carte, tournee, index);
    }

    /**
     * Annuler la suppression d un point d interet
     */
    public void annuler() {
        etatCourant.annuler(this, fenetre);
    }

    /**
     * Ajouter un point dinteret a la tournee
     */
    public void ajouter() {
        etatCourant.ajouter(this, fenetre, carte, tournee);
    }

    /**
     *
     * @param etat
     */
    public void setEtat(Etat etat) {
        etatCourant = etat;
    }

    /**
     * Methode appelee par la fenetre quand l'utilisateur clique sur le bouton
     * "Undo"
     * Code source : PlaCo
     */
    public void undo() {
        etatCourant.undo(listeCommandes);
    }

    /**
     * Methode appelee par fenetre apres un clic sur le bouton "Redo"
     * Code source : PlaCo
     */
    public void redo() {
        etatCourant.redo(listeCommandes);
    }

    /**
     * Mettre en surbrillance une ligne du tableau d etapes de la tournee et du
     * tableau d informations generales sur un point d interet
     *
     * @param ptI point d interet clique
     */
    public void surbrillerTables(PointInteret ptI) {
        etatCourant.surbrillerTables(fenetre, ptI);
    }

    /**
     * Encadrer un point d interet present sur la carte
     *
     * @param p point d interet clique
     */
    public void surbrillerPI(PointInteret p) {
        etatCourant.surbrillerPI(fenetre, p);
    }

    /**
     * Recuperer la tournee
     *
     * @return tournee
     */
    public Tournee getTournee() {
        return this.tournee;
    }

    /**
     * Modifier la tournee
     *
     * @param tournee nouvelle tournee
     */
    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

}
