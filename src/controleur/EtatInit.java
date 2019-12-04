/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import modele.Carte;
import modele.Tournee;

/**
 *
 * @author labou
 */
public class EtatInit implements Etat {

    /**
     *
     * @param controleur
     * @param fenetre
     * @param carte
     * @param tournee
     */
    @Override
    public void chargerPageDeBase(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee) {
       
        //On charge la carte
        boolean chargerCarte = false;

        try {
            //Choix du fichier XML
            chargerCarte = carte.chargerCarte(false);

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre, on affiche la carte
            //et on change d etat
            if (chargerCarte) {
                fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
                fenetre.repaint();
                fenetre.afficherConteneur2();
                controleur.setEtat(controleur.etatDeBase);
            } else {
                //Sinon, on affiche un message d erreur et on reste dans le meme etat
                fenetre.afficherMessageErreur1("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            //fenetre.afficherMessageErreur1("Erreur lors de la sélection du fichier");
        }
       
    }

}
