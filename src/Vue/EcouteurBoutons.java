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
            case Fenetre.CHARGER_CARTE:
                controleur.chargerCarte();
                System.out.println("CHARGER CARTE ECOUTEUR BOUTONS");
                break;
            case Fenetre.CHARGER_LIVRAISONS:
                controleur.chargerLivraison();
                System.out.println("CHARGER LIVRAISON ECOUTEUR BOUTONS");
                break;
            case Fenetre.CALCULER_TOURNEE:
                controleur.calculerTournee();
                System.out.println("CHARGER CARTE CALCULER TROUNEE");
                break;
        }

    }

}
