package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;

/**
 * EtatAjouter permettant d ajouter un point dinteret a la tournee
 * Code inspire de l application PlaCo
 *
 * @version Version 1
 *
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EtatAjouter implements Etat {

    @Override
    public void ajouter(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee) {
         System.out.println("pAE dans etatAj:"+fenetre.getAvantPE().getIntersection().getId()+" pAL dans etatAj:"+fenetre.getAvantPL().getIntersection().getId());
        if(carte.ajouterLivraison(fenetre.getPE(), fenetre.getPL(), fenetre.getAvantPE(), fenetre.getAvantPL(), 100, 100)){
            tournee = carte.getTournee();
            fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre));
            fenetre.setTournee(tournee);
            controleur.setTournee(tournee);

            fenetre.viderPanneauEtapes();
            fenetre.viderPanneauPIs();
            fenetre.afficherEtapesTour(true);
            fenetre.afficherPanneauPI(true);

            fenetre.repaint();
            fenetre.afficherBoutonSupprimer();
            controleur.setEtat(controleur.etatTournee);
            
        }else if(tournee.getSuccessionPointsInteret().indexOf(fenetre.getAvantPE())>tournee.getSuccessionPointsInteret().indexOf(fenetre.getAvantPL())){
            JOptionPane.showMessageDialog(fenetre, "Erreur contrainte de precedence");
        }
    }
    
    @Override
    public void ajouterPointEnlevement(Controleur controleur, Fenetre fenetre, Carte carte,Intersection interE){
        int duree = 0;
        
        // Si le point choisi est un point d'interet existant
        for(PointInteret p:carte.getDemandesLivraisons().getListePointsInteret()){
            if(p.getIntersection() == interE){
                JOptionPane.showMessageDialog(fenetre, "Vous ne pouvez pas choisir un point d'interet existant,"
                        + "\nmerci de cliquer sur un autre point.");
                return;
            }
        }
        
        // Si le point choisi n'existe pas dans la liste d'intersection, normalement cela n'arrive jamais
        boolean dansLaListe = false;
        for(Intersection i:carte.getListeIntersections()){
            if(i == interE){
                dansLaListe = true;
            }
        }
        if(!dansLaListe){
            JOptionPane.showMessageDialog(fenetre, "Le point choisi n'existe pas dans la liste");
            return;
        }
        
        // Saisir la duree
        while(duree <= 0){
            try{
                duree = Integer.parseInt(JOptionPane.showInputDialog("Merci de saisir la duree du point d'enlevement"));
                if(duree<=0){
                    JOptionPane.showMessageDialog(fenetre, "la duree doit etre superieur a 0, merci de resaisir une duree valide");
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(fenetre, "le format du chiffre n'est pas bon, merci de resaisir une duree valide");
                duree = 0;
            }
        }
               
        // Verification des infos du point
        int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer les informations du point d'enlevement:"
                + "\nid: "+interE.getId()
                + "\nlatitude: "+interE.getLatitude()
                + "\nlongitude: "+interE.getLongitude()
                + "\nduree: "+duree);

        if (value == JOptionPane.NO_OPTION || value == JOptionPane.CANCEL_OPTION) {
            return;
        }
       
        // Enregistrer le point d'enlevement dans le Fenetre
        fenetre.setPE(new PointInteret(interE,duree));
        JOptionPane.showMessageDialog(fenetre, "Merci de choisir un point d'avant");
    }
    
    @Override
    public void ajouterPointLivraison(Controleur controleur, Fenetre fenetre, Carte carte,Intersection interL){
        int duree = 0;
        
        // Si c'est le meme point que le point d'enlevement
        if(interL == fenetre.getPE().getIntersection()){
            JOptionPane.showMessageDialog(fenetre, "Le point de livraison ne peut pas etre le meme que celui d'enlevement,merci de re-choisir un autre point");
            return;
        }
        
        // Si le point choisi est un point d'interet existant
        for(PointInteret p:carte.getDemandesLivraisons().getListePointsInteret()){
            if(p.getIntersection() == interL){
                JOptionPane.showMessageDialog(fenetre, "Vous ne pouvez pas choisir un point d'interet existant,"
                        + "\nmerci de cliquer sur un autre point.");
                return;
            }
        }
        
        // Si le point choisi n'existe pas dans la liste d'intersection, normalement cela n'arrive jamais
        boolean dansLaListe = false;
        for(Intersection i:carte.getListeIntersections()){
            if(i == interL){
                dansLaListe = true;
            }
        }
        if(!dansLaListe){
            return;
        }
        
        // Saisir la duree
        while(duree <= 0){
            try{
                duree = Integer.parseInt(JOptionPane.showInputDialog("Merci de saisir la duree du point de livraison"));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(fenetre, "le format du chiffre n'est pas bon, merci de resaisir une duree valide");
                duree = 0;
            }
        }
               
        // Verification des infos du point
        int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer les informations du point de livraison:"
                + "\nid: "+interL.getId()
                + "\nlatitude: "+interL.getLatitude()
                + "\nlongitude: "+interL.getLongitude()
                + "\nduree: "+duree);

        if (value == JOptionPane.NO_OPTION || value == JOptionPane.CANCEL_OPTION) {
            return;
        }
        
        fenetre.setPL(new PointInteret(interL,duree));
        
        JOptionPane.showMessageDialog(fenetre, "Merci de choisir le point d'avant");
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
