package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import modele.Carte;
import modele.Intersection;

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

    private Etat etatCourant;
    private Fenetre fenetre;
    private Carte carte;

    public Controleur() {
        //etatCourant = etatInit;
        fenetre = new Fenetre(this); //lui passer this
        carte=new Carte();
    }

    /**
     * Change l'etat courant du controleur
     *
     * @param etat le nouvel etat courant
     */
    protected void setEtatCourant(Etat etat) {
        etatCourant = etat;
    }

    public Carte getCarte(){
        return this.carte;
    }
    
    public void setCarte(Carte nCarte){
        this.carte=nCarte;
    }
    
    public void chargerCarte() {
        //Appeler methode affichage carte + ...
        boolean chargerCarte = true;

        try{
            
            carte.chargerCarte();
            fenetre.setPanneauCarte(new JCarte(this));
            fenetre.repaint();
            fenetre.afficherConteneur2(chargerCarte);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("Je lance le chargement d'une carte");
        
    }

    public void chargerLivraison() {

        boolean chargerLivraison = true;
        
        try{
            
            carte.chargerLivraison();

            fenetre.setPanneauCarte(new JCarte(this));
            fenetre.repaint();

            fenetre.afficherConteneur2(true);

            
        }catch(Exception e){
            e.printStackTrace();
        }
        

        fenetre.afficherBoutonCalcul(chargerLivraison);
        
        System.out.println("Je lance le chargement d'une livraison");

    }

    public void calculerTournee() {

        boolean calculTournee = true;
        fenetre.afficherEtapesTour(calculTournee);
        System.out.println("Je lance le calcul d'une tournee");

    }

}