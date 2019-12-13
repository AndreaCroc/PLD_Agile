package Vue;

import controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EcouteurBoutons permet de recuperer et gerer les evenements sur les boutons
 * de la fenetre
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EcouteurBoutons implements ActionListener {

    private Controleur controleur; //Controleur entre le modele et la vue
    private Fenetre fenetre; //Fenetre de l application

    /**
     * Constructeur de la classe EcouteurBoutons
     *
     * @param controleur controleur
     * @param fenetre fenetre
     */
    public EcouteurBoutons(Controleur controleur, Fenetre fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;
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
            //Si on veut zoomer sur la carte
            case Fenetre.ZOOMER:
                controleur.zoomer();
                break;
            //Si on veut dezoomer sur la carte
            case Fenetre.DEZOOMER:
                controleur.deZoomer();
                break;
            //Si on veut decaler la carte vers la droite
            case Fenetre.DROITE:
                controleur.decalage(1);
                break;
            //Si on veut decaler la carte vers la gauche
            case Fenetre.GAUCHE:
                controleur.decalage(-1);
                break;
            //Si on veut decaler la carte vers le haut
            case Fenetre.HAUT:
                controleur.decalage(-2);
                break;
            //Si on veut decaler la carte vers le bas
            case Fenetre.BAS:
                controleur.decalage(2);
                break;
            //Si on veut arreter le calcul de la tournee
            case Fenetre.ARRET:
                controleur.arreterCalculTournee();
                break;
        }

        //Recuperer un clic sur le bouton redo
        if (e.getSource() == fenetre.getBoutonRedo()) {
            controleur.redo();
        }

        //Recuperer un clic sur le bouton undo
        if (e.getSource() == fenetre.getBoutonUndo()) {
            controleur.undo();
        }

    }

}
