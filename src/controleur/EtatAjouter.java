package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.PointInteret;
import modele.Tournee;

/**
 * EtatAjouter permettant d ajouter un nouveau couple de points d'interets
 * a la tournee 
 * Code inspire de l application PlaCo
 *
 * @version Version 1
 *
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatAjouter implements Etat {

    /**
     * Ajouter les nouveaux points de livraison et d'enlevement a la tournee
     * 
     * @param controleur controleur courant
     * @param fenetre fenetre courante
     * @param carte carte courante
     * @param tournee tournee courante
     * @param listeCommandes listeCommandes pour undo/redo
     */
    @Override
    public void ajouter(Controleur controleur, Fenetre fenetre, Carte carte, 
            Tournee tournee, ListeCdesTournee listeCommandes) {
        CdeAjout commande;
        if (carte.ajouterLivraison(fenetre.getPE(), fenetre.getPL(), 
                fenetre.getAvantPE(), fenetre.getAvantPL())) {
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
                    commande = new CdeAjout(fenetre.getPE(), fenetre.getPL(), 
                            fenetre.getAvantPE(), fenetre.getAvantPL());
                    listeCommandes.ajouterCommande(commande);

        } else {
            JOptionPane.showMessageDialog(fenetre, " La contrainte de "
                    + "précédence n'est pas respectée.\n Les points n'ont "
                    + "pas été ajoutés à la tournée.");
        }
        
        fenetre.repaint();
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
    }

    /**
     * Annuler l'ajout dun nouveau couple de points d'interets
     * @param controleur controleur courant
     * @param fenetre fenetre courante
     */
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre) {
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
     * @param p point d interet selectionne
     */
    @Override
    public void surbrillerPI(Fenetre fenetre, PointInteret p) {
        fenetre.surbrillerPI(p);
        fenetre.repaint();
    }
}
