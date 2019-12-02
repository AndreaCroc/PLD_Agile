package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import modele.Carte;
import modele.Tournee;

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
public class Controleur {

    private Fenetre fenetre;
    private Carte carte;
    private Tournee tournee;

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
        //Appeler methode affichage carte + ...
        boolean chargerCarte = false;

        try {
            //Choix du fichier XML
            chargerCarte = carte.chargerCarte(false);

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre et un affiche la carte
            if (chargerCarte) {
                fenetre.setPanneauCarte(new JCarte(carte,tournee,fenetre));
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

    }
    
    /**
     * Charge une nouvelle carte
     */
    public void changerCarte() {
        
        boolean changerCarte = false;

        try {
            //Choix du fichier XML
            changerCarte = carte.chargerCarte(true);

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre et un affiche la carte
            if (changerCarte) {
                fenetre.viderPanneauEtapes();
                this.fenetre.cacherPanneauEtapesEtTour();
                this.fenetre.griserBoutonCalcul();
                fenetre.setPanneauCarte(new JCarte(carte,tournee,fenetre));
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
                this.fenetre.cacherPanneauEtapesEtTour();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.setPanneauCarte(new JCarte(carte,null,fenetre));
                fenetre.repaint();
                fenetre.afficherConteneur2();
                fenetre.afficherBoutonCalcul();
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
        //Appeler methode calculerTournee de Tournee : tournee.calculerTourner();
        this.tournee = carte.calculerTournee();
        fenetre.setPanneauCarte(new JCarte(this.carte,this.tournee,this.fenetre));
        fenetre.setTournee(this.tournee);
        fenetre.repaint();
        fenetre.afficherEtapesTour();

        System.out.println("Je lance le calcul d'une tournee");
        
    }
    
    public void surbrillanceTableau(int index){
        fenetre.surbrillanceLigneTab(index);
        fenetre.repaint();
    }
    public void surbrillancePI(int ligne){
        fenetre.entourerPI(ligne);
        fenetre.repaint();
    }
    public Tournee getTournee(){
        return this.tournee;
    }
            
            
            
            

}
