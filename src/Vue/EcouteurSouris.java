
package Vue;

import controleur.Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import modele.Carte;
import modele.PointInteret;

/**
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

public class EcouteurSouris extends MouseAdapter {
/**
 *
 * Classe EcouteurSouris permettant de recuperer et de gerer les evenements lies
 * a la souris et ici sur un clic
 *
 */

    private Controleur controleur; //Controleur pour traiter les evenemtns
    private JCarte vueCarte; //Panneau de la carte
    private Fenetre fenetre; //Fenetre ouverte

    /**
     * Constructeur de la classe EcouteurSouris
     * @param controleur
     * @param vueCarte
     * @param fenetre 
     */
    public EcouteurSouris(Controleur controleur, JCarte vueCarte, Fenetre fenetre) {
        this.controleur = controleur;
        this.vueCarte = vueCarte;
        this.fenetre = fenetre;
    }

    /**
     * Traiter les evenements sur un clic de la souris
     * @param evt evenement
     */
    @Override
    public void mouseClicked(MouseEvent evt) {
        if (this.fenetre != null) {
            //Recuperer la largeur du panneau gauche
            int xPanneauGauche = this.fenetre.getWidthPanneauGauche();
            //Recuperer la hauteur du panneau legende situee au dessus de la carte
            int yPanneauLegende = this.fenetre.getHeightPanneauLegende();

            if (this.vueCarte != null) {
                //Recuperer les coordonnees de tous les points d interets qui sont sur la carte
                ArrayList<CoordPointInteret> listeCoordPtI = vueCarte.getCoordPtInterets();
                Carte carte = fenetre.getCarte();
                if (carte != null) {
                    ArrayList<PointInteret> listePtI = carte.getListePointsInteretActuelle();
                    //Recuperer les coordonnees de la souris lors du clic
                    int x = evt.getX() - 8;
                    int y = evt.getY() - 8;

                    //Si il y a bien des points d interets sur la carte
                    if (listeCoordPtI != null && !listeCoordPtI.isEmpty()) {
                        int index = 0;
                        for (CoordPointInteret p : listeCoordPtI) {
                            //Point(nxXpt,nvYpt) correspond au centre des figures des points d interets
                            int nvXpt = p.getPoint().getX() + xPanneauGauche + 5;
                            int nvYpt = p.getPoint().getY() + yPanneauLegende + 25;
                            PointInteret pi = p.getPtI();
                            //Si le clic se trouve sur une figure d un point d interet
                            if (x >= nvXpt - 5 && x <= nvXpt + 5 && y >= nvYpt - 5 && y <= nvYpt + 5) {
                                if (listePtI != null && !listePtI.isEmpty()) {
                                    index = listePtI.indexOf(pi);
                                    if (index < listePtI.size() && index != -1) {
                                        //Mettre en surbrillance la ligne du tableau correspondante
                                        this.controleur.surbrillerTables(pi);
                                        //Surbriller le point sur lequel on vient de cliquer
                                        this.controleur.surbrillerPI(pi);
                                        if (this.fenetre.getClicSupp()) {
                                            this.controleur.supprimer(index);
                                        }
                                        break;
                                    }

                                }

                            }
                        }

                    }
                }

            }

        }

    }

}