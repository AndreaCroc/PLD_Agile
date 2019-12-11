<<<<<<< HEAD
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;

/**
 * EtatAjouterPtLivraison permettant d ajouter un point d enlevement a la 
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

    @Override
    public void ajouterPointLivraison(Controleur controleur, Fenetre fenetre, Carte carte,Intersection interL){
        System.out.println("etat ajouter pt livraison");
        int duree = 0;
        ArrayList<Troncon> listeT = interL.getTronconsDepart();;
        String nomRue = "";
        // Si le point choisi n'existe pas dans la liste d'intersection, normalement cela n'arrive jamais
        boolean dansLaListe = false;
        for (Intersection i : carte.getListeIntersections()) {
            if (i == interL) {
                dansLaListe = true;
            }
        }
        if (!dansLaListe) {
            JOptionPane.showMessageDialog(fenetre, "L'endroit choisi n'est pas une intersection");
            return;
        }
        // Si c'est le meme point que le point d'enlevement
        if(interL == fenetre.getPE().getIntersection()){
            JOptionPane.showMessageDialog(fenetre, "Le point de livraison ne peut pas être le même que celui d'enlèvement.\nMerci de choisir un autre point");
            return;
        }
        
        
        
        // Saisir la duree
        while(duree <= 0){
            try{
                duree = Integer.parseInt(JOptionPane.showInputDialog("Merci de saisir la durée en minute du point de livraison"));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(fenetre, "le format de la durée n'est pas correct.\n Merci de saisir une durée valide");
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
        int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer les informations du point de livraison :"
                + "\nRue(s): " + nomRue
                + "\nDurée: " + duree + " min");

        if (value == JOptionPane.NO_OPTION || value == JOptionPane.CANCEL_OPTION) {
            return;
        }
        
        duree*=60; 
        fenetre.setPL(new PointInteret(interL,duree));
        controleur.setEtat(controleur.etatAjouterPointAvantLivr);
        JOptionPane.showMessageDialog(fenetre, "Merci de choisir un point précédent la livraison dans la tournée");
    }
    
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
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
}

=======
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;

/**
 * EtatAjouterPtLivraison permettant d ajouter un point d enlevement a la 
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

    @Override
    public void ajouterPointLivraison(Controleur controleur, Fenetre fenetre, Carte carte,Intersection interL){
        System.out.println("etat ajouter pt livraison");
        int duree = 0;
        ArrayList<Troncon> listeT = interL.getTronconsDepart();;
        String nomRue = "";
        // Si le point choisi n'existe pas dans la liste d'intersection, normalement cela n'arrive jamais
        boolean dansLaListe = false;
        for (Intersection i : carte.getListeIntersections()) {
            if (i == interL) {
                dansLaListe = true;
            }
        }
        if (!dansLaListe) {
            JOptionPane.showMessageDialog(fenetre, "L'endroit choisi n'est pas une intersection");
            return;
        }
        // Si c'est le meme point que le point d'enlevement
        if(interL == fenetre.getPE().getIntersection()){
            JOptionPane.showMessageDialog(fenetre, "Le point de livraison ne peut pas etre le même que celui d'enlèvement.\nMerci de choisir un autre point");
            return;
        }
        
        
        
        // Saisir la duree
        while(duree <= 0){
            try{
                duree = Integer.parseInt(JOptionPane.showInputDialog("Merci de saisir la duree en minute du point de livraison"));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(fenetre, "le format du chiffre n'est pas bon, merci de resaisir une duree valide");
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
        int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer les informations du point de livraison :"
                + "\nRue(s): " + nomRue
                + "\nduree: " + duree);

        if (value == JOptionPane.NO_OPTION || value == JOptionPane.CANCEL_OPTION) {
            return;
        }
        
        duree*=60; 
        fenetre.setPL(new PointInteret(interL,duree));
        controleur.setEtat(controleur.etatAjouterPointAvantLivr);
        JOptionPane.showMessageDialog(fenetre, "Merci de choisir un point précédent la livraison dans la tournee");
    }
    
    @Override
    public void annuler(Controleur controleur, Fenetre fenetre) {
        fenetre.afficherBoutonSupprimer();
        controleur.setEtat(controleur.etatTournee);
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
}

>>>>>>> dac17706415fc7ebc42600bc2124c55f627ddbf7
