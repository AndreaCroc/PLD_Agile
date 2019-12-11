package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * EtatTournee quand on affiche toutes les donnees (sur la carte et
 * sur les tableaux de gauche) a une tournee calculee
 * Code inspire de PlaCo
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatTournee implements Etat {

    /**
     * Changer la carte deja chargee Dans tous les cas, on retourne dans l
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
                controleur.annulerAnciennesCommandes();
                fenetre.griserBoutonsUndoRedo();

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
                fenetre.griserBoutonsUndoRedo();

            }

        } catch (Exception e) {
            //En cas d erreur lie a la selection d un fichier, on affiche un message
            fenetre.afficherMessageErreur3("Erreur lors de la sélection du fichier");
        }
    }

    /**
     * Charger des livraisons depuis un fichier XML En cas de succes on retourne
     * a l etat EtatLivraison En cas d echec, on reste dans cet etat
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
                controleur.setEtat(controleur.etatLivraison);
                controleur.annulerAnciennesCommandes();
                
                fenetre.griserBoutonsUndoRedo();
                
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
     * Supprimer un point d interet de la tournee Dans tous les cas, on passe
     * dans l etat EtatSupprimer
     *
     * @param controleur
     * @param fenetre
     * @param carte
     * @param tournee
     * @param index
     * @param listeCommandes
     */
    @Override
    public void supprimer(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee, int index, ListeCdesTournee listeCommandes) {
        fenetre.setClicSupp(true);
        fenetre.griserBoutonsSupprimer();
        controleur.setEtat(controleur.etatSupprimer);
    }

    /**
     * Supprimer un point d interet de la tournee Dans tous les cas, on passe
     * dans l etat EtatSupprimer
     *
     * @param controleur
     * @param fenetre
     * @param carte
     * @param tournee
     */
    @Override
    public void ajouter(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee, ListeCdesTournee listeCommandes) {
        controleur.setEtat(controleur.etatAjouterPtEnlevement);
        fenetre.griserBoutonsSupprimer();
        fenetre.clearAllPointsAjoutes();
        JOptionPane.showMessageDialog(fenetre, "Merci de choisir un emplacement pour l'enlèvement");
    }

    /**
     * Modifier l ordre de passage d un point d interet dans la tournee On passe
     * dans l etat EtatModifier
     *
     * @param controleur
     * @param fenetre
     * @param tournee
     * @param carte
     * @param index
     * @param listeCommandes
     */
    @Override
    public void modifier(Controleur controleur, Fenetre fenetre, Tournee tournee, Carte carte, int index, ListeCdesTournee listeCommandes) {
        fenetre.setClicModif(true);
        fenetre.griserBoutonsSupprimer();
        controleur.setEtat(controleur.etatModifier);
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
        fenetre.surbrillerLigneTabEtapes(ptI);
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
    
    /**
     * Retourner a l action precedente
     * @param liste 
     */
    @Override
    public void undo(ListeCdesTournee liste, Fenetre fenetre){
        liste.undo();
        fenetre.afficherBoutonCalcul();
    }
    
    /**
     * Refaire l action
     * @param liste 
     */
    @Override
    public void redo(ListeCdesTournee liste, Fenetre fenetre){
        liste.redo();
        fenetre.afficherBoutonCalcul();
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
        fenetre.repaint();
        controleur.setEtat(controleur.etatTournee);
        controleur.annulerAnciennesCommandes();
    }

}
