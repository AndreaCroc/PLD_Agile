package Vue;

import controleur.Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import javax.swing.JOptionPane;
import controleur.EtatAjouter;
import modele.Troncon;

/**
 * EcouteurSouris permet de recuperer et de gerer les evenements lies a la
 * souris
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class EcouteurSouris extends MouseAdapter {

    private Controleur controleur; //Controleur pour traiter les evenemtns
    private JCarte vueCarte; //Panneau de la carte
    private Fenetre fenetre; //Fenetre ouverte

    /**
     * Constructeur de la classe EcouteurSouris
     *
     * @param controleur
     * @param vueCarte
     * @param fenetre
     */
    public EcouteurSouris(Controleur controleur, JCarte vueCarte, 
                          Fenetre fenetre) {
        this.controleur = controleur;
        this.vueCarte = vueCarte;
        this.fenetre = fenetre;
    }

    /**
     * Traiter les evenements sur un clic de la souris
     *
     * @param evt evenement
     */
    @Override
    public void mouseClicked(MouseEvent evt) {
        if (this.fenetre != null) {
            //Recuperer la largeur du panneau gauche
            int xPanneauGauche = this.fenetre.getWidthPanneauGauche();
            //Recuperer la hauteur du panneau legende situee au dessus carte
            int yPanneauLegende = this.fenetre.getHeightPanneauLegende();

            if (this.vueCarte != null) {
                //Recuperer les coordonnees points interets qui sont sur carte
                ArrayList<CoordPointInteret> listeCoordPtI 
                                            = vueCarte.getCoordPtInterets();
                //Recuperer les coordonnees de toutes les intersections qui sont sur la carte
                ArrayList<Point> coordI = vueCarte.getCoorIntersections();
                Map<Intersection, Point> mesCoordIntersections 
                                            = vueCarte.getIntersectionsMap();
                Carte carte = fenetre.getCarte();
                if (carte != null) {
                    ArrayList<PointInteret> listePtI 
                                        = carte.getListePointsInteretActuelle();
                    //Recuperer les coordonnees de la souris lors du clic
                    int x = evt.getX() - 8;
                    int y = evt.getY() - 8;
                    //Si il y a bien des points d interets sur la carte
                    if (listeCoordPtI != null && !listeCoordPtI.isEmpty()) {
                        int index = 0;
                        for (CoordPointInteret p : listeCoordPtI) {
                            //Point(nxXpt,nvYpt) correspond au centre des 
                            //figures des points d interets
                            int nvXpt = p.getPoint().getX() + xPanneauGauche 
                                        + 5;
                            int nvYpt = p.getPoint().getY() + yPanneauLegende 
                                        + 25;
                            PointInteret pi = p.getPtI();
                            //Si le clic se trouve sur une figure d un point d interet
                            if ((x >= nvXpt - 5) && (x <= nvXpt + 5) 
                                    && (y >= nvYpt - 5) && (y <= nvYpt + 5)) {
                                if (listePtI != null && !listePtI.isEmpty()) {
                                    index = listePtI.indexOf(pi);
                                    if (index < listePtI.size() && index != -1) {
                                        //Mettre en surbrillance la ligne du 
                                        //tableau correspondante
                                        this.controleur.surbrillerTables(pi);
                                        //Surbriller le point sur lequel 
                                        //on vient de cliquer
                                        this.controleur.surbrillerPI(pi);
                                        if (this.fenetre.isClicSupp()) {
                                            this.controleur.supprimer(index);
                                        } else if (this.fenetre.isClicModif()) {
                                            this.controleur.modifier(index);
                                        }

                                        if (controleur.getEtatCourant() 
                                                instanceof EtatAjouter) {
                                            if (fenetre.getAvantPE() == null && fenetre.getPE() != null) {
                                                int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer si c'est le point que vous voulez");
                                                if (value == JOptionPane.YES_OPTION) {
                                                    fenetre.setAvantPEParIndex(index);
                                                    JOptionPane.showMessageDialog(fenetre, "Merci de choisir un point de livraison");
                                                    break;
                                                }
                                            } else if (fenetre.getAvantPE() != null && fenetre.getPE() != null) {
                                                if (fenetre.getPL() != null) {
                                                    int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer si c'est le point que vous voulez");
                                                    if (value == JOptionPane.YES_OPTION) {
                                                        fenetre.setAvantPLParIndex(index);
                                                        controleur.ajouter();
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    }

                                }

                            }
                        }

                    }

                    // si tableau des intersections non vide et non null
                    if (!mesCoordIntersections.isEmpty()) {
                        //test x y sont sur pt intersect coord 
                        for (Map.Entry<Intersection, Point> iEntry : mesCoordIntersections.entrySet()) {
                            //parcourir la map pour trouver me point qui correspond aux var x et y cliquées par l'utilisateur
                            Intersection key = iEntry.getKey();
                            Point value = iEntry.getValue();

                            //coordonnées adaptees des points d'intersections
                            int nvXpt2 = value.getX() + xPanneauGauche + 5;
                            int nvYpt2 = value.getY() + yPanneauLegende + 45;
                            if (x >= nvXpt2 && x <= nvXpt2 + 5 && y >= nvYpt2 - 5 && y <= nvYpt2 + 5) {
                                //les coordonnées correspondent

                                //On recupere la liste des troncons dans le but d'afficher leur noms
                                ArrayList<Troncon> listeTroncons = key.getTronconsDepart();
                                System.out.println(key.getTronconsDepart().size());
                                vueCarte.setTronconsNomsRues(listeTroncons);
                                vueCarte.repaint();
                            }

                        }

                    }

                    if (coordI != null && !coordI.isEmpty()) {
                        int index = 0;
                        for (Point p : coordI) {
                            index = coordI.indexOf(p);
                            //Point(nxXpt,nvYpt) correspond au centre des figures des points d interets
                            int nvXpt = p.getX() + xPanneauGauche + 5;
                            int nvYpt = p.getY() + yPanneauLegende + 25;

                            //Si le clic se trouve sur une intersection
                            if (x >= nvXpt - 5 && x <= nvXpt + 5 && y >= nvYpt - 5 && y <= nvYpt + 5) {
                                if (controleur.getEtatCourant() instanceof EtatAjouter) {
                                    if (fenetre.getPE() == null) {
                                        this.controleur.ajouterPointEnlevement(controleur.getIntersectionByIndex(index));
                                        break;
                                    } else {
                                        if (fenetre.getAvantPE() != null && fenetre.getPL() == null) {
                                            this.controleur.ajouterPointLivraison(controleur.getIntersectionByIndex(index));
                                            break;
                                        }
                                    }
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
