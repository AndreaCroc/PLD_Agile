/*
 * EcouteurSouris
 *
 * Version 1
 * 
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package Vue;

import controleur.Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * Classe EcouteurSouris permettant de recuperer et de gerer les evenements
 * lies a la souris et ici sur un clic
 * 
 */
public class EcouteurSouris extends MouseAdapter {

    private Controleur controleur;
    private JCarte vueCarte;
    private Fenetre fenetre;

    public EcouteurSouris(Controleur controleur, JCarte vueCarte, Fenetre fenetre) {
        this.controleur = controleur;
        this.vueCarte = vueCarte;
        this.fenetre = fenetre;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        //Recuperer la largeur du panneau gauche
        int xPanneauGauche = this.fenetre.getWidthPanneauGauche();
        //Recuperer la hauteur du panneau legende situee au dessus de la carte
        int yPanneauLegende = this.fenetre.getHeightPanneauLegende();
        
        //Recuperer les coordonnees de tous les points d interets qui sont sur la carte
        ArrayList<Point> coordPis =  vueCarte.getCoorPtInterets();

        //Recuperer les coordonnees de la souris lors du clic
        int x = evt.getX()-8;
        int y = evt.getY()-8;
        
        //Si il y a bien des points d interets sur la carte
        if (coordPis != null && !coordPis.isEmpty()) {
            int index = 0;
            for (Point p : coordPis) {
                index = coordPis.indexOf(p);
                //Point(nxXpt,nvYpt) correspond au centre des figures des points d interets
                int nvXpt = p.getX()+ xPanneauGauche+5;
                int nvYpt = p.getY() + yPanneauLegende+25;
                
                //Si le clic se trouve sur un efigure d un point d interet
                if (x >= nvXpt-5 && x <= nvXpt + 5 && y >= nvYpt-5 && y <= nvYpt + 5) {
                    //Prevenir le controleur qu un point a ete clique
                    this.controleur.setFenetreSurbrillance(true);
                    //Mettre en surbrillance la ligne du tableau correspondante
                    this.controleur.surbrillanceTableau(index);
                    if(this.fenetre.getClicSupp()){
                        this.controleur.supprimer(index);
                    }
                    break;
                }
                
            }
        }

    }
}
