package controleur;

import Vue.Fenetre;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Troncon;

/**
 * EtatAjouterPtEnlevement permettant d ajouter un point d enlevement a la
 * tournee Code inspire de l application PlaCo
 *
 * @version Version 1
 *
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatAjouterPtEnlevement implements Etat {


    /**
     * Ajouter un point d'enlevement a la tournee
     * @param controleur controleur
     * @param fenetre fenetre
     * @param carte carte
     * @param interE intersection sur laquelle ajouter le point d'enlevement
     */
    @Override
    public void ajouterPointEnlevement(Controleur controleur, Fenetre fenetre, 
                                       Carte carte, Intersection interE) {

        System.out.println("Etat Ajouter Pt Enlevement");
        int duree = 0;
        ArrayList<Troncon> listeT = interE.getTronconsDepart();
        String nomRue = "";
         //Numéro de la demande de livraison
        int numeroDemande;
        int nbDemandes = carte.getDemandesLivraisons().getListePointsInteret()
                                                      .size();
        
        // Si le point choisi n'existe pas dans la liste d'intersection, normalement cela n'arrive jamais
        boolean dansLaListe = false;
        for (Intersection i : carte.getListeIntersections()) {
            if (i == interE) {
                dansLaListe = true;
            }
        }
        if (!dansLaListe) {

            JOptionPane.showMessageDialog(fenetre, "L'endroit choisi "
                    + "n'est pas une intersection");
            return;
        }

        // Saisir la duree
        while (duree <= 0) {
            try {

                duree = Integer.parseInt(JOptionPane.showInputDialog("Merci "
                        + "de saisir la durée d'enlèvement en minute"));
                if (duree <= 0) {
                    JOptionPane.showMessageDialog(fenetre, "La durée doit être "
                            + "supérieure à 0.\nMerci de saisir "
                            + "une durée valide");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(fenetre, "Le format de la durée "
                        + "n'est pas correct.\nMerci de saisir "
                        + "une durée valide");
                duree = 0;
            }
        }

        // Verification des infos du point
        //Recuperer les rues qui intersectent le point d interet
        for (Troncon t : listeT) {
            if (!nomRue.contains(t.getNomRue())) {
                nomRue += t.getNomRue() + ", ";
            }
        }
        nomRue = nomRue.substring(0, nomRue.lastIndexOf(", "));
        int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer "
                + "les informations du point d'enlèvement:"
                + "\nRue(s): " + nomRue
                + "\nduree: " + duree);

        if (value == JOptionPane.NO_OPTION || value == JOptionPane.CANCEL_OPTION) {
            return;
        }

        // Enregistrer le point d'enlevement dans le Fenetre
        duree=duree*60;
        fenetre.setPE(new PointInteret(interE, duree));
        
        //S'il ne reste que l'entrepot :
        if (nbDemandes == 1) {
            numeroDemande = 1;
        } else {
            numeroDemande = (nbDemandes - 1) / 2 + 1;
        }
        fenetre.getPE().setNumeroDemande(numeroDemande);
        controleur.setEtat(controleur.etatAjouterPointAvantEnlvt);
        JOptionPane.showMessageDialog(fenetre, "Merci de choisir un point "
                + "précédent l'enlèvement dans la tournée");
    }

    /**
     * Annuler tous les ajouts deja effectues et retourenr a l'etat tournee
     * @param controleur controleur
     * @param fenetre fenetre
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
