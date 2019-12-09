package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * EtatModifier pour modifier l ordre de passage d un point 
 * d interet dans la tournee
 * Code inspire de l application PlaCo
 *
 * Version 1
 *
 *
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne MAGNIEN,
 * Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatModifier implements Etat {

    /**
     * 
     *
     * @param controleur
     * @param fenetre
     * @param tournee
     * @param carte
     * @param index
     */
    @Override
    public void modifier(Controleur controleur, Fenetre fenetre, Tournee tournee, Carte carte, int index, ListeCdesTournee listeCommandes) {
        System.out.println("LAA");
        if (index != 0) {
            ArrayList<PointInteret> listePIs = carte.getListePointsInteretActuelle();
            //Recuperer le point d interet que l utilisateur veut deplacer
            int option = 1; //choix de valider ou non le deplacement 
            int decalage = 0; //de combien a ete deplace le point d interet
            boolean modifOk = false; //si le deplacement s'est bien passee
            PointInteret ptI = new PointInteret(); //point d interet qu on veut deplacer
            int pos = 0; //Position du point d interet dan sla tournee
            int min = 0; //deplacement max au plus tot
            int max = 0; //deplacement max au plus tard
            ArrayList<Integer>choix = new ArrayList(); //Retour methode classe Fenetre
            ArrayList<PointInteret> listeTournee = new ArrayList(); //liste de la classe Tournee
            CdeModif commande;
            //Si l index est inferieur a la taille de la liste de la carte
            if (index < listePIs.size()) {
                ptI = listePIs.get(index);
                listeTournee = tournee.getSuccessionPointsInteret();
                pos = listeTournee.indexOf(ptI);
                min = (-1) * (pos - 1);
                max = listeTournee.size() - pos - 1;
                
                //Afficher une popup de modification
                choix = fenetre.afficherPopModification(min, max);
                option = choix.get(0);
                decalage = choix.get(1);
                //Si on confirme la modification
                if (option == JOptionPane.OK_OPTION) {
                    modifOk = carte.deplacerPointInteret(ptI,decalage);
                    tournee = carte.getTournee();

                    fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
                    fenetre.setTournee(tournee);
                    controleur.setTournee(tournee);
                    fenetre.viderPanneauEtapes();
                    fenetre.viderPanneauPIs();
                    fenetre.afficherBoutonSupprimer();
                    fenetre.griserBoutonCalcul();
                    fenetre.repaint();

                    fenetre.afficherEtapesTour(true);
                    fenetre.afficherPanneauPI(true);

                    //Si modification effectuee mais non respect contrainte
                    if (!modifOk) {
                        //Afficher popup pour prevenir modification ne respecte pas contrainte
                        fenetre.afficherPopPrevenirModification();
                    }
                    commande = new CdeModif(ptI, decalage);
                    listeCommandes.ajouterCommande(commande);
                    //Si on annule la modification via la popup
                } else {
                    fenetre.afficherBoutonSupprimer();
                    fenetre.griserBoutonCalcul();
                }
            } else {
                fenetre.afficherBoutonSupprimer();
                fenetre.griserBoutonCalcul();
            }

        } else {
            //Afficher popup d erreur car pas possible de deplacer l entrepot
            fenetre.afficherPopDeplacerErreur();
            fenetre.afficherBoutonSupprimer();
            fenetre.griserBoutonCalcul();
        }

        controleur.setEtat(controleur.etatTournee);
        fenetre.repaint();
        fenetre.setClicModif(false);
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
     * Annuler le mode modification d un point d interet On repasse dans l etat
     * EtatTournee
     *
     * @param controleur
     * @param fenetre
     */
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherBoutonSupprimer();
        fenetre.griserBoutonCalcul();
        controleur.setEtat(controleur.etatTournee);
    }
}
