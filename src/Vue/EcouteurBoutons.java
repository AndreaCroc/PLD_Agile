package Vue;

import controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EcouteurBoutons
 *
 * Version 1
 *
 *
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne MAGNIEN,
 * Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EcouteurBoutons implements ActionListener {

    /**
     * Classe EcouteurBoutons pour recuperer et gerer les evenements sur les
     * boutons de la fenetre
     *
     */

    private Controleur controleur; //Controleur entre le modele et la vue

    /**
     * Constructeur de la classe EcouteurBoutons
     *
     * @param controleur
     */
    public EcouteurBoutons(Controleur controleur) {
        this.controleur = controleur;
    }

    /**
     * Traiter les evenements lies a un clic sur un bouton
     *
     * @param e evenement
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Quand on clique sur un bouton, on fait le bon traitement
        switch (e.getActionCommand()) {
            //Si on veut charger une carte
            case Fenetre.CHARGER_CARTE:
                controleur.chargerCarte();
                break;
            //Si on veut charger des livraisons
            case Fenetre.CHARGER_LIVRAISONS:
                controleur.chargerLivraison();
                break;
            //Si on veut calculer une tournee
            case Fenetre.CALCULER_TOURNEE:
                controleur.calculerTournee();
                break;
            //Si on veut changer une carte
            case Fenetre.CHANGER_CARTE:
                controleur.changerCarte();
                break;
            //Si on veut supprimer un point dinteret
            case Fenetre.SUPPRIMER:
                controleur.supprimer(-1);
                break;
            //Si on veut annuler la suppression
            case Fenetre.ANNULER:
                controleur.annuler();
                break;
            //Si on veut ajouter un point dinteret
            case Fenetre.AJOUTER:
                controleur.ajouter();
                break;
            //Si on veut modifier l ordre d un point d interet
            case Fenetre.MODIFIER:
                controleur.modifier(-1);
                break;
                //Si on veut modifier l ordre d un point d interet
            case Fenetre.ZOOMER:
                System.out.println("Je veux zoomer !");
                controleur.zoomer();
                break;
                //Si on veut modifier l ordre d un point d interet
            case Fenetre.DEZOOMER:
                System.out.println("Je veux dezoomer !");
                controleur.deZoomer();
                break;
        }

    }

}
