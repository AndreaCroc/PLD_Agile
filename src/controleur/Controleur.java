package controleur;

import Vue.Fenetre;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;

/**
 * Controleur permet de faire le lien entre la vue et le modele
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Controleur {

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
    protected final EtatAjouterPtEnlevement etatAjouterPtEnlevement = new 
                                                     EtatAjouterPtEnlevement();
    protected final EtatAjouterPtLivraison etatAjouterPtLivraison = new 
                                                     EtatAjouterPtLivraison();
    protected final EtatAjouterPointAvantEnlvt etatAjouterPointAvantEnlvt = new 
                                                   EtatAjouterPointAvantEnlvt();
    protected final EtatAjouterPointAvantLivr etatAjouterPointAvantLivr = new 
                                                   EtatAjouterPointAvantLivr();
    /**
     * Constructeur de la classe du Controleur
     */
    public Controleur() {
        carte = new Carte();
        tournee = new Tournee();
        fenetre = new Fenetre(this, carte, tournee); //lui passer this
        listeCommandes = new ListeCdesTournee(carte, tournee, fenetre, this);
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
        etatCourant.modifier(this, fenetre, tournee, carte, index, listeCommandes);
    }

    /**
     * Supprimer un point d interet de la tournee
     *
     * @param index : numero du point d interet a supprimer
     */
    public void supprimer(int index) {
        etatCourant.supprimer(this, fenetre, carte, tournee, index, listeCommandes);
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
        etatCourant.ajouter(this, fenetre, carte, tournee, listeCommandes);
    }

    /**
     * Modifier l etat courant du controleur
     *
     * @param etat
     */
    public void setEtat(Etat etat) {
        etatCourant = etat;
    }

    /**
     * Methode appelee par la fenetre quand l'utilisateur clique sur le bouton
     * "Undo" Code source : PlaCo
     */
    public void undo() {
        etatCourant.undo(listeCommandes, fenetre);
    }

    /**
     * Methode appelee par fenetre apres un clic sur le bouton "Redo" Code
     * source : PlaCo
     */
    public void redo() {
        etatCourant.redo(listeCommandes, fenetre);
    }

    /**
     * Permet d annuler les commandes stockees precedemment
     */
    public void annulerAnciennesCommandes() {
        listeCommandes.annulerAnciennesCommandes();
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

    /**
     * Effectue un zoom sur une zone de la carte
     */
    public void zoomer() {
        this.fenetre.setZoom(this.fenetre.getZoom() + 0.5);
        fenetre.repaint();

    }

    /**
     * Effectue un dezoome sur la zone de la carte qui etait zoomee
     */
    public void deZoomer() {
        if (this.fenetre.getZoom() - 0.5 >= 1) {
            this.fenetre.setZoom(this.fenetre.getZoom() - 0.5);
            //if((this.fenetre.getDeplY()+20)>=0 &&
            //(this.fenetre.getDeplY()+20)<=(this.fenetre.getHeight()*0.85)){
            //this.fenetre.setDeplY(this.fenetre.getDeplY()+20*1/fenetre.getZoom());//test
            //this.fenetre.setDeplX(this.fenetre.getDeplX()+20*1/fenetre.getZoom());//test
            // }
            fenetre.repaint();
        }
    }

    public void decalage(int decalage) {
        System.out.println(this.fenetre.getWidth());
        if (decalage == 1 || decalage == -1) {

            if ((this.fenetre.getDeplX() + decalage * 50) >= 0
                    && (this.fenetre.getDeplX() + decalage * 50) <= this.fenetre.getWidth() * 0.30) {
                this.fenetre.setDeplX(this.fenetre.getDeplX() + decalage * 50);//test
                this.fenetre.repaint();
            }
        } else {
            if ((this.fenetre.getDeplY() + ((int) (decalage / 2)) * 50) >= 0
                    && (this.fenetre.getDeplY() + ((int) (decalage / 2)) * 50) <= (this.fenetre.getHeight() * 0.78)) {
                this.fenetre.setDeplY(this.fenetre.getDeplY() + ((int) (decalage / 2)) * 50);//test
                this.fenetre.repaint();
            }

        }
    }

    public void arreterCalculTournee(){
        System.out.println("Arret Calcul tournee");
    }
    
    public void ajouterPointEnlevement(Intersection interE) {
        etatCourant.ajouterPointEnlevement(this, fenetre, carte, interE);
    }

    public void ajouterPointLivraison(Intersection interL) {
        etatCourant.ajouterPointLivraison(this, fenetre, carte, interL);
    }

    public void ajouterPointAvantEnlevement(int index) {
        etatCourant.ajouterPointAvantEnlevement(this, fenetre, carte, index);
    }

    public void ajouterPointAvantLivraison(int index) {
        etatCourant.ajouterPointAvantLivraison(this, fenetre, carte, index);
    }

    public Intersection getIntersectionByIndex(int index) {
        return carte.getListeIntersections().get(index);
    }

    public Etat getEtatCourant() {
        return this.etatCourant;
    }
}
