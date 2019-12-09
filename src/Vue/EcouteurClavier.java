package Vue;

import controleur.Controleur;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * EcouteurClavier permet de recuperer et gerer les evenements sur les touches
 * du clavier pour decaler la carte
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EcouteurClavier implements KeyListener {

    private Controleur controleur; //Controleur entre le modele et la vue
    private Fenetre fenetre; //Fenetre de l application

    public EcouteurClavier(Controleur controleur, Fenetre fenetre) {
        this.controleur = controleur;
        this.fenetre = fenetre;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("key typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        int code = e.getKeyCode();
        switch (code) {
            case 37:
                //Clic sur fleche gauche
                controleur.decalage(-1);
                break;
            case 38:
                //Clic sur fleche haut
                controleur.decalage(-2);
                break;
            case 39:
                //Clic sur fleche droite
                controleur.decalage(1);
                break;
            case 40:
                //Clic sur fleche bas
                controleur.decalage(2);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
