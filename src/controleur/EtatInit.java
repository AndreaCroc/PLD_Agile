
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import modele.Carte;
import modele.Tournee;

/**
 * EtatInit qui correspond a la page d accueil
 * Code inspire de l application PlaCo
 *
 * @version Version 1
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class EtatInit implements Etat {

    /**
     * Charger la premiere carte
     * En cas de succes, on passe a l etat EtatDeBase
     * En cas d echec on reste dans cet etat
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
        String cheminFichier = fenetre.getInputChargeCarte();
        try {
            //Choix du fichier XML
            chargerCarte = carte.chargerCarte(false,cheminFichier);

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre, on affiche la carte
            //et on change d etat
            if (chargerCarte) {
                fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
                fenetre.afficherConteneur2();
                fenetre.repaint();
                controleur.setEtat(controleur.etatDeBase);
            } else {
                //Sinon, on affiche un message d erreur et on reste dans le meme etat
                fenetre.afficherMessageErreur1("Erreur lors du chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            fenetre.afficherMessageErreur1("Erreur lors de la sélection du fichier");
        }
       
    }

}
