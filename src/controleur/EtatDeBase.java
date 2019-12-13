
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import modele.Carte;

/**
 * EtatDeBase qui correspond a l affichage de la carte sans rien dessus
 * Depuis cet etat on peut charger une nouvelle carte ou charger une livraison
 * permettant d ajouter un point dinteret a la tournee
 * Code inspire de l application PlaCo
 *
 * @version Version 1
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */

public class EtatDeBase implements Etat {

    /**
     * Changer la carte deja chargee Dans tous les cas (succes ou echec) on
     * reste dans cet EtatDeBase
     *
     * @param controleur controleur courant
     * @param fenetre fenetre courante
     * @param carte carte courante
     */
    @Override
    public void changerCarte(Controleur controleur, Fenetre fenetre, 
                             Carte carte) {

        boolean changerCarte = false;
        try {
            //Choix du fichier XML
            changerCarte = carte.chargerCarte(true, "");

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre et on affiche la carte
            // Dans tous les cas, on reste dans le meme etat
            if (changerCarte) {
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                fenetre.cacherPanneauEtapesEtTour();
                fenetre.cacherPanneauPI();
                fenetre.griserBoutonCalcul();
                fenetre.setTournee(null);
                fenetre.setZoom(1);
                fenetre.setDeplX(0);
                fenetre.setDeplY(0);
                carte.setUneTournee(null);
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.repaint();
                fenetre.afficherConteneur2();
                fenetre.retireMessageErreur3();

            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur3("Erreur lors du chargement "
                                                + "du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, 
            //on affiche un message
            fenetre.afficherMessageErreur3("Erreur lors de la "
                                            + "sélection du fichier");
        }

    }

    /**
     * Charger une livraison lorsqu une carte est chargee En cas de succes, on
     * passe a l etat EtatLivraison En cas d echec, on reste dans cet etat
     *
     * @param controleur controleur courant
     * @param fenetre fenetre courante
     * @param carte carte courante
     */
    @Override
    public void chargerLivraison(Controleur controleur, Fenetre fenetre, 
                                 Carte carte) {

        boolean chargerLivraison = false;
        String cheminFichier = fenetre.getInputChargeLiv();
        try {
            //Choix du fichier XML
            chargerLivraison = carte.chargerLivraison(cheminFichier);

            //Si le chargement des livraisons s est bien passe,
            // on affiche les livraisons et on change d etat
            if (chargerLivraison) {
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                fenetre.cacherPanneauEtapesEtTour();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.makePalette();
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.afficherConteneur2();
                fenetre.afficherBoutonCalcul();
                fenetre.afficherPanneauPI(true);
                fenetre.repaint();
                controleur.setEtat(controleur.etatLivraison);
            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur2("Erreur lors du "
                                                + "chargement du fichier");
            }

        } catch (Exception e) {
            //En cas d erreur liee a la selection d un fichier, 
            //on affiche un message
            fenetre.afficherMessageErreur2("Erreur lors de la "
                                            + "sélection du fichier");
        }
    }

}
