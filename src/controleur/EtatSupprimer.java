package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * EtatSupprimer quand on veut supprimer un point d interet
 * Code inspire de l application PlaCo
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatSupprimer implements Etat {

    /**
     * Supprimer un point d interet de la tournee et son point correspondant On
     * repasse soit a l etat tournee soit a l etat de base
     *
     * @param controleur controleur
     * @param fenetre fenetre courante
     * @param carte carte courante
     * @param tournee tournee courante
     * @param index index du point d'interet a supprimer
     * @param listeCommandes liste des commandes deja effectuees
     */
    @Override
    public void supprimer(Controleur controleur, Fenetre fenetre, Carte carte, 
                          Tournee tournee, int index, 
                          ListeCdesTournee listeCommandes) {

        //Si ce n est pas l entrepot
        if (index != 0) {
            ArrayList<PointInteret> listePIs = carte
                                               .getListePointsInteretActuelle();

            //Recuperer le point d interet que l utilisateur veut supprimer
            int option = 1; //choix de valider ou non la suppression
            boolean suppOk = false; //si la suppression s'est bien passee
            //point d interet qu on veut supprimer
            PointInteret ptI = new PointInteret();
            //point de dependance du point a supprimer
            PointInteret ptD = new PointInteret(); 
            //point interet apres lequel se trouve l'enlevement
            PointInteret ptAvantEnlevement = new PointInteret(); 
            //point interet apres lequel se trouve la livraison 
            PointInteret ptAvantLivraison = new PointInteret(); 
            //Commande de suppression
            CdeSupprime commande;
           
            
            //Si l index est inferieur a la taille de la liste
            if (index < listePIs.size()) {
                ptI = listePIs.get(index);
                
                //Enregistrement des informations de la commande
                ptD = ptI.getPointDependance();
                //indice du point interet dans la tournee
                int indPtITournee = tournee.getSuccessionPointsInteret()
                                            .indexOf(ptI);
                //indice du point de dépendance dans la tournee
                int indPtDTournee = tournee.getSuccessionPointsInteret()
                                            .indexOf(ptD);
                if (ptI.isEnlevement()) {
                    ptAvantEnlevement = tournee.getSuccessionPointsInteret()
                                                .get(indPtITournee-1);
                    ptAvantLivraison = tournee.getSuccessionPointsInteret()
                                               .get(indPtDTournee-1);
                } else {
                    ptAvantEnlevement = tournee.getSuccessionPointsInteret()
                                                .get(indPtDTournee-1);
                    ptAvantLivraison = tournee.getSuccessionPointsInteret()
                                               .get(indPtITournee-1);
                }
                
                //Afficher un popup de confirmation de suppression
                option = fenetre.afficherPopSuppression(ptI);

                //Si on confirme la suppression
                if (option == JOptionPane.OK_OPTION) {

                    suppOk = carte.supprimerPointInteret(ptI);
                    tournee = carte.getTournee();
                    listePIs = carte.getListePointsInteretActuelle();

                    if (listePIs.size() == 1 && suppOk) {
                        tournee = null;
                        carte.setUneTournee(null);
                    }
                    fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
                    fenetre.setTournee(tournee);
                    controleur.setTournee(tournee);
                    fenetre.viderPanneauEtapes();
                    fenetre.viderPanneauPIs();
                    fenetre.afficherBoutonSupprimer();
                    fenetre.repaint();

                    //Plus que l entrepot dans la liste des points 
                    //d interets de la carte
                    if (listePIs.size() == 1 && suppOk) {
                        fenetre.cacherPanneauEtapesEtTour();
                        fenetre.cacherPanneauPI();
                        fenetre.griserBoutonArretCalcul();
                        fenetre.griserBoutonCalcul();
                        //Afficher popup plus d elements liste
                        fenetre.afficherPopSuppressionVide();
                        controleur.setEtat(controleur.etatDeBase);
                    } else {
                        fenetre.afficherEtapesTour(true);
                        fenetre.afficherPanneauPI(true);
                        controleur.setEtat(controleur.etatTournee);
                        //Si suppression pas effectuee a cause d une erreur
                        if (!suppOk) {
                            //Afficher popup suppression annulee
                            fenetre.afficherPopSuppressionAnnulee();
                        }
                      
                    }
                    
                    commande = new CdeSupprime(ptI, ptD, ptAvantEnlevement, 
                                               ptAvantLivraison);
                    listeCommandes.ajouterCommande(commande);
                    //Si on annule la suppression
                } else {
                    fenetre.afficherBoutonSupprimer();
                    controleur.setEtat(controleur.etatTournee);
                }
            } else {
                fenetre.afficherBoutonSupprimer();
                controleur.setEtat(controleur.etatTournee);
            }

        } else {
            //Afficher popup d erreur car pas possible de supprimer l entrepot
            fenetre.afficherPopSuppressionErreur();
            fenetre.afficherBoutonSupprimer();
            controleur.setEtat(controleur.etatTournee);
        }
        fenetre.repaint();
        fenetre.setClicSupp(false);

    }

    /**
     * Annuler le mode suppression d un point d interet On repasse dans l etat
     * EtatTournee
     *
     * @param controleur controleur
     * @param fenetre fenetre courante
     */
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre
    ) {
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }

    /**
     * Mettre en surbrillance une ligne du tableau d etapes de la tournee et du
     * tableau d informations generales sur un point d interet
     *
     * @param fenetre fenetre courante
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
     * @param fenetre fenetre courante
     * @param p poitn d interet selectionne
     */
    @Override
    public void surbrillerPI(Fenetre fenetre, PointInteret p) {
        fenetre.surbrillerPI(p);
        fenetre.repaint();
    }

}
