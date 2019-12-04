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
    protected final EtatAccueil etatAccueil = new EtatAccueil();
    //etatCourant = etatInit;

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
        boolean chargerCarte = false;

        try {
            //Choix du fichier XML
            chargerCarte = carte.chargerCarte(false);

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre et un affiche la carte
            if (chargerCarte) {
                fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
                fenetre.repaint();
                fenetre.afficherConteneur2();
            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur1("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            fenetre.afficherMessageErreur1("Erreur lors de la sélection du fichier");
        }
        etatCourant.test1(this,fenetre);
        etatCourant = etatAccueil;
        etatCourant.test2(this,fenetre);
    }

    /**
     * Changer la carte
     */
    public void changerCarte() {

        boolean changerCarte = false;

        try {
            //Choix du fichier XML
            changerCarte = carte.chargerCarte(true);

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre et on affiche la carte
            if (changerCarte) {
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                this.fenetre.cacherPanneauEtapesEtTour();
                this.fenetre.cacherPanneauPI();
                this.fenetre.griserBoutonCalcul();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.repaint();
                fenetre.afficherConteneur2();
                fenetre.retireMessageErreur3();

            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur3("1 Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            fenetre.afficherMessageErreur3("2 Erreur lors de la sélection du fichier");
        }
    }

    /**
     * Charge une livraison
     *
     */
    public void chargerLivraison() {

        boolean chargerLivraison = false;

        try {
            //Choix du fichier XML
            chargerLivraison = carte.chargerLivraison();

            //Si le chargement des livraisons s est bien passe,
            // on affiche les livraisons
            if (chargerLivraison) {
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                this.fenetre.cacherPanneauEtapesEtTour();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.repaint();
                fenetre.afficherConteneur2();
                fenetre.afficherBoutonCalcul();
                fenetre.afficherPanneauPI();
            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur2("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            fenetre.afficherMessageErreur2("Erreur lors de la sélection du fichier");
        }

    }

    /**
     * Calculer une tournee
     *
     */
    public void calculerTournee() {

        fenetre.viderPanneauEtapes();
        this.tournee = carte.calculerTournee();
        fenetre.setPanneauCarte(new JCarte(this.carte, this.tournee, this.fenetre));
        fenetre.setTournee(this.tournee);
        fenetre.repaint();
        fenetre.afficherEtapesTour();

        System.out.println("Je lance le calcul d'une tournee");

    }


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

    public void setFenetreSurbrillance(boolean surb) {
        this.fenetre.setSurbrillance(surb);
    }

}
