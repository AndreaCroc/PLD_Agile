package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * EtatLivraison qui correspond a laffichage des points d interets dune
 * livraison sur la carte Code inspire de l application PlaCo
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatLivraison implements Etat {

    /**
     * Afficher le bouton arreter la tournee avant de faire le calcul
     *
     * @param fenetre fenetre
     */
    @Override
    public void attendreCalcul(Fenetre fenetre) {
        fenetre.afficherBoutonArretCalcul();
    }

    /**
     * Calculer une tournee Dans tous les cas, on passe a l etat EtatTournee
     *
     * @param controleur
     * @param fenetre
     * @param carte
     * @param tournee
     */
    @Override
    public void calculerTournee(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee) {

        fenetre.viderPanneauEtapes();
        fenetre.viderPanneauPIs();
        fenetre.afficherMessageErreur3("");
        tournee = carte.calculerTournee();
        fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
        fenetre.setTournee(tournee);
        controleur.setTournee(tournee);

        fenetre.afficherEtapesTour(true);
        fenetre.afficherPanneauPI(true);
        fenetre.afficherBoutonSupprimer();
        //fenetre.griserBoutonCalcul();
        //fenetre.griserBoutonArretCalcul();
        fenetre.repaint();
        controleur.setEtat(controleur.etatTournee);
    }

    /**
     * Arreter le calcul de la tournee en cours
     *
     * @param controleur controleur
     * @param fenetre fenetre
     * @param carte carte
     * @param tournee tournee
     */
    @Override
    public void arreterCalculTournee(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee) {
        System.out.println("arreter calcul tournee etat livraison");
        controleur.setEtat(controleur.etatTournee);
    }

    /**
     * Charger un fichier de livraisons Dans tous les cas, on reste dans cet
     * etat
     *
     * @param controleur
     * @param fenetre
     * @param carte
     */
    @Override
    public void chargerLivraison(Controleur controleur, Fenetre fenetre, Carte carte) {
        
        boolean chargerLivraison = false;
        String cheminFichier = fenetre.getInputChargeLiv();

        fenetre.afficherMessageErreur3("");
        try {
            //Choix du fichier XML
            chargerLivraison = carte.chargerLivraison(cheminFichier);

            //Si le chargement des livraisons s est bien passe,
            // on affiche les livraisons et on reste dans le meme etat
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
     * Changer la carte deja chargee Dans tous les cas, on retourne dans l etat
     * EtatDeBase
     *
     * @param controleur
     * @param fenetre
     * @param carte
     */
    @Override
    public void changerCarte(Controleur controleur, Fenetre fenetre, Carte carte) {
        boolean changerCarte = false;
        
        try {
            //Choix du fichier XML
            changerCarte = carte.chargerCarte(true, "");

            //Si le chargement de la carte s est bien passe,
            // on change de fenetre et on affiche la carte et on change detat
            if (changerCarte) {
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                fenetre.cacherPanneauEtapesEtTour();
                fenetre.cacherPanneauPI();
                fenetre.griserBoutonCalcul();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.setZoom(1);
                fenetre.setDeplX(0);
                fenetre.setDeplY(0);
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.afficherConteneur2();
                fenetre.retireMessageErreur3();
                fenetre.repaint();
                controleur.setEtat(controleur.etatDeBase);
                

            } else {
                //Sinon, on affiche un message d erreur
                fenetre.afficherMessageErreur3("Erreur lors du chargement du fichier");
                carte.setDemandesLivraisons(null);
                fenetre.viderPanneauEtapes();
                fenetre.viderPanneauPIs();
                fenetre.cacherPanneauEtapesEtTour();
                fenetre.cacherPanneauPI();
                fenetre.griserBoutonCalcul();
                fenetre.setTournee(null);
                carte.setUneTournee(null);
                fenetre.setPanneauCarte(new JCarte(carte, null, fenetre));
                fenetre.repaint();
                controleur.setEtat(controleur.etatDeBase);

            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            fenetre.afficherMessageErreur3("Erreur lors de la sélection du fichier");
        }

    }

    /**
     * Mettre en surbrillance une ligne du tableau d etapes de la tournee et du
     * tableau d informations generales sur un point d interet
     *
     * @param fenetre
     * @param ptI point d interet selectionne
     */
    @Override
    public void surbrillerTables(Fenetre fenetre, PointInteret ptI) {
        fenetre.surbrillerLigneTabPI(ptI);
        fenetre.repaint();
    }

    /**
     * Encadrer un point d interet present sur la carte
     *
     * @param fenetre
     * @param p point d interet selectionne
     */
    @Override
    public void surbrillerPI(Fenetre fenetre, PointInteret p) {
        fenetre.surbrillerPI(p);
        fenetre.repaint();
    }

}
