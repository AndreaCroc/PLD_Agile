/*
 * Controleur
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import modele.Carte;
import modele.Tournee;

/**
 * Classe Controleur qui permet de faire le lien entre la vue et le modele
 */
public class Controleur {

    private Fenetre fenetre;
    private Carte carte;
    private Tournee tournee;
    private Etat etatCourant = new EtatInit();
    
    // Instances associees a chaque etat possible du controleur
    protected final EtatInit etatInit = new EtatInit();
    protected final EtatDeBase etatDeBase = new EtatDeBase();
    protected final EtatLivraison etatLivraison = new EtatLivraison();
    protected final EtatTournee etatTournee = new EtatTournee();
    protected final EtatSupprimer etatSupprimer = new EtatSupprimer();

    public Controleur() {
        carte = new Carte();
        tournee = new Tournee();
        fenetre = new Fenetre(this, carte, tournee); //lui passer this
    }

    /**
     * Charge une nouvelle carte
     *
     */
    public void chargerCarte() {
        etatCourant.chargerPageDeBase(this,fenetre,carte,tournee);
    }
    
    /**
     * 
     */
    public void changerCarte() {
        etatCourant.changerCarte(this,fenetre,carte);
    }
    
    /**
     * 
     */
    public void chargerLivraison () {
        etatCourant.chargerLivraison(this,fenetre, carte);
    }
    
    public void calculerTournee () {
        etatCourant.calculerTournee(this, fenetre, carte, tournee);
    }
    
    public void supprimer (int index) {
        etatCourant.supprimer(this, fenetre, carte, tournee, index);
    }
    
    public void annuler () {
        etatCourant.annuler(this, fenetre);
    }
    
    /**
     * 
     * @param etat 
     */
    public void setEtat(Etat etat) {
        etatCourant = etat;
    }

    /**
     * Changer la carte
     */
    //public void changerCarte() {

        
    //}

    /**
     * Charge une livraison
     *
     */
    //public void chargerLivraison() {

        
    //}

    /**
     * Calculer une tournee
     *
     */
    //public void calculerTournee() {

       

    //}


    /**
     * Mettre en surbrillance une ligne du tableau d etapes de la tournee
     *
     * @param index indice du point d interet clique
     */

    public void surbrillanceTableau(int index) {
        fenetre.surbrillanceLigneTab(index);
        fenetre.repaint();
    }

    
    /**
     * Encadrer un point d interet de la tournee
     *
     * @param ligne ligne du tableau selectionnee
     */
    public void surbrillancePI(int ligne) {
        fenetre.entourerPI(ligne);
        fenetre.repaint();
    }

    public Tournee getTournee() {
        return this.tournee;
    }
    
    public void setTournee(Tournee tournee){
        this.tournee = tournee;
    }

    public void setFenetreSurbrillance(boolean surb) {
        this.fenetre.setSurbrillance(surb);
    }

}
