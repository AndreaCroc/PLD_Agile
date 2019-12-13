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
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatModifier implements Etat {

    /**
     * Modifier l'ordre de passage des points d'interet de la tournee
     *
     * @param controleur controleur
     * @param fenetre fenetre courante
     * @param tournee tournee courante
     * @param carte carte courante
     * @param index index du point d'interet a modifier
     * @param listeCommandes liste des commandes effectuees 
     */
    @Override
    public void modifier(Controleur controleur, Fenetre fenetre, 
                         Tournee tournee, Carte carte, int index, 
                         ListeCdesTournee listeCommandes) {
        if (index != 0) {
            //Recuperer le point d interet que l utilisateur veut deplacer
            ArrayList<PointInteret> listePIs = carte
                                            .getListePointsInteretActuelle();
            
            int option = 1;             //choix de valider ou non le deplacement 
            int decalage = 0;           //de combien a ete deplace le point d interet
            boolean modifOk = false;    //si le deplacement s'est bien passee
            
            //point d interet qu on veut deplacer
            PointInteret ptI = new PointInteret(); 
            int pos = 0; //Position du point d interet dan sla tournee
            int min = 0; //deplacement max au plus tot
            int max = 0; //deplacement max au plus tard
            
             //Retour methode classe Fenetre
            ArrayList<Integer>choix = new ArrayList<Integer>();
            
            //liste de la classe Tournee
            ArrayList<PointInteret> listeTournee = new ArrayList<PointInteret>(); 
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
                    fenetre.repaint();

                    fenetre.afficherEtapesTour(true);
                    fenetre.afficherPanneauPI(true);

                    //Si modification effectuee mais non respect contrainte
                    if (!modifOk) {
                        
                        //Afficher popup pour prevenir modification ne 
                        //respecte pas contrainte
                        fenetre.afficherPopPrevenirModification();
                    }
                    commande = new CdeModif(ptI, decalage);
                    listeCommandes.ajouterCommande(commande);
                    
                    //Si on annule la modification via la popup
                } else {
                    fenetre.afficherBoutonSupprimer();
                }
            } else {
                fenetre.afficherBoutonSupprimer();
            }

        } else {
            //Afficher popup d erreur car pas possible de deplacer l entrepot
            fenetre.afficherPopDeplacerErreur();
            fenetre.afficherBoutonSupprimer();
        }

        controleur.setEtat(controleur.etatTournee);
        fenetre.repaint();
        fenetre.setClicModif(false);
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
     * @param controleur controleur courant
     * @param fenetre fenetre courante
     */
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }
}
