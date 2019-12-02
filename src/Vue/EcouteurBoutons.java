/*
 * EcouteurBoutons
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package Vue;

import controleur.Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurBoutons implements ActionListener {

    private Controleur controleur;

    public EcouteurBoutons(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Quand on clique sur un bouton, on fait le bon traitement
        switch (e.getActionCommand()) {
            //Si on veut charger une carte
            case Fenetre.CHARGER_CARTE:
                controleur.chargerCarte();
                System.out.println("CHARGER CARTE ECOUTEUR BOUTONS");
                break;
            //Si on veut charger des livraisons
            case Fenetre.CHARGER_LIVRAISONS:
                controleur.chargerLivraison();
                System.out.println("CHARGER LIVRAISON ECOUTEUR BOUTONS");
                break;
            //Si on veut calculer une tournee
            case Fenetre.CALCULER_TOURNEE:
                controleur.calculerTournee();
                System.out.println("CHARGER CARTE CALCULER TROUNEE");
                break;
            //Si on veut changer une carte
            case Fenetre.CHANGER_CARTE:
                controleur.changerCarte();
                System.out.println("CHANGER CARTE");
                break;
        }

    }

}
