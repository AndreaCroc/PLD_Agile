package controleur;

import Vue.Fenetre;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;

import modele.Troncon;


/**
 * EtatAjouterPtLivraison permettant d ajouter un point de livraison a la 
 * tournee
 * Code inspire de l application PlaCo
 *
 * @version Version 1
 *
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatAjouterPtLivraison implements Etat {


    /**
     * Ajouter un point de livraison a la tournee
     * @param controleur controleur courant
     * @param fenetre fenetre courante
     * @param carte carte courante
     * @param interL intersection sur laquelle ajouter le point de livraison
     */
    @Override
    public void ajouterPointLivraison(Controleur controleur, Fenetre fenetre, 
                                      Carte carte,Intersection interL){
        int duree = 0;
        ArrayList<Troncon> listeT = interL.getTronconsDepart();
        String nomRue = "";     //Numéro de la demande de livraison
        int numeroDemande;
        int nbDemandes = carte.getDemandesLivraisons().getListePointsInteret()
                                                      .size();
        // Si le point choisi n'existe pas dans la liste d'intersection, 
        //normalement cela n'arrive jamais
        boolean dansLaListe = false;
        for (Intersection i : carte.getListeIntersections()) {
            if (i == interL) {
                dansLaListe = true;
            }
        }
        if (!dansLaListe) {
            JOptionPane.showMessageDialog(fenetre, "L'endroit choisi "
                    + "n'est pas une intersection");
            return;
        }
        // Si c'est le meme point que le point d'enlevement
        if(interL == fenetre.getPE().getIntersection()){
            JOptionPane.showMessageDialog(fenetre, "Le point de livraison ne "
                    + "peut pas etre le même que celui d'enlèvement.\n"
                    + "Merci de choisir un autre point");
            return;
        }
        
        
        
        // Saisir la duree
        while(duree <= 0){
            try{
                duree = Integer.parseInt(JOptionPane.showInputDialog("Merci de "
                        + "saisir la duree en minute du point de livraison"));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(fenetre, "le format du chiffre "
                        + "n'est pas bon, merci de resaisir une duree valide");
                duree = 0;
            }
        }
               
       //Recuperer les rues qui intersectent le point d interet
        for (Troncon t : listeT) {
            if (!nomRue.contains(t.getNomRue())) {
                nomRue += t.getNomRue() + ", ";
            }
        }
        nomRue = nomRue.substring(0, nomRue.lastIndexOf(", "));
        int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer "
                + "les informations du point de livraison :"
                + "\nRue(s): " + nomRue
                + "\nduree: " + duree);

        if (value == JOptionPane.NO_OPTION || value == JOptionPane.CANCEL_OPTION) {
            return;
        }
        
        duree*=60; 
        fenetre.setPL(new PointInteret(interL,duree));
        //S'il ne reste que l'entrepot :
        if (carte.getDemandesLivraisons().getListePointsInteret().size() == 1) {
            numeroDemande = 1;
        } else {
            numeroDemande = (nbDemandes - 1) / 2 + 1;
        }
        fenetre.getPL().setNumeroDemande(numeroDemande);
        controleur.setEtat(controleur.etatAjouterPointAvantLivr);
        JOptionPane.showMessageDialog(fenetre, "Merci de choisir un point "
                + "précédent la livraison dans la tournee");
    }
    
    /**
     * Annuler tous les ajouts deja effectues et retourner a l'etat tournee
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

