
package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;
import modele.Carte;
import modele.Chemin;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;

/**
 * JCarte permet d afficher la carte, les points d interets, la tournee
 *
 * @version Version 1
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class JCarte extends JPanel {

    private Carte carte; //Carte possedant les points dinteret
    private Tournee tournee; //les points dinteret faisant partie de la tournee
    private ArrayList<CoordPointInteret> listeCoordPtI; //liste des point dinerets de la carte
    private Map<Intersection, Point> intersectionsMap;
    private Fenetre fenetre; //fenetre de l application
    private ArrayList<Color> palette;
    /**
     * Constructeur de la classe JCarte
     * @param carte
     * @param tournee
     * @param fenetre
     */
    public JCarte(Carte carte, Tournee tournee, Fenetre fenetre) {
        this.carte = carte;
        this.tournee = tournee;
        this.listeCoordPtI = new ArrayList<>();
        this.intersectionsMap = new HashMap<>();
        this.fenetre = fenetre;
        this.palette=this.fenetre.getPalette();
        this.repaint();
    }


    /**
     * Modifier la tournee de la carte
     * @param nouvelleTournee 
     */
    public void setTournee(Tournee nouvelleTournee) {
        this.tournee = nouvelleTournee;
        this.repaint();
    }

    /**
     * Modifier la fenetre
     * @param fenetre nouvelle fenetre
     */
    public void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
        this.repaint();
    }

    /**
     * Recuperer la tournee
     * @return tournee
     */
    public Tournee getTournee() {
        return this.tournee;
    }


    /**
     * Ajouter un point au tableau stockant les coordonnees des points d
     * interets de la tournee
     *
     * @param p point a ajouter
     */
    public void ajouterCoordPtI(CoordPointInteret p) {
        this.listeCoordPtI.add(p);
    }
    
    public void ajouterPointToIntersectionsMap(Intersection i, Point p) {
        this.intersectionsMap.put(i, p);
    }
    
    public Map<Intersection, Point> getIntersectionsMap() {
        return this.intersectionsMap;
    }

    /**
     * Recuperer la liste des points dinteret et leurs coordonnees sur la carte
     * @return liste 
     */
    public ArrayList<CoordPointInteret> getCoordPtInterets() {
        return this.listeCoordPtI;
    }

    /*Recupère la latitude maximale présente sur la carte*/
    public Double maxLatitude(ArrayList<Intersection> intersections) {

        Double res = intersections.get(0).getLatitude();
        for (Intersection i : intersections) {
            if (i.getLatitude() > res) {
                res = i.getLatitude();
            }
        }
        return res;
    }

    public Double maxLatitudePIs(ArrayList<PointInteret> PIs) {

        Double res = PIs.get(0).getIntersection().getLatitude();
        for (PointInteret i : PIs) {
            if (i.getIntersection().getLatitude() > res) {
                res = i.getIntersection().getLatitude();
            }
        }
        return res;
    }

    public Double maxLongitude(ArrayList<Intersection> intersections) {

        Double res = intersections.get(0).getLongitude();
        for (Intersection i : intersections) {
            if (i.getLongitude() > res) {
                res = i.getLongitude();
            }
        }
        return res;
    }

    public Double maxLongitudePIs(ArrayList<PointInteret> PIs) {

        Double res = PIs.get(0).getIntersection().getLongitude();
        for (PointInteret i : PIs) {
            if (i.getIntersection().getLongitude() > res) {
                res = i.getIntersection().getLongitude();
            }
        }
        return res;
    }

    /*Recupère la latitude minimale présente sur la carte*/
    public Double minLatitude(ArrayList<Intersection> intersections) {
        Double res = intersections.get(0).getLatitude();
        for (Intersection i : intersections) {
            if (i.getLatitude() < res) {
                res = i.getLatitude();
            }
        }
        return res;
    }

    public Double minLatitudePIs(ArrayList<PointInteret> PIs) {
        Double res = PIs.get(0).getIntersection().getLatitude();
        for (PointInteret i : PIs) {
            if (i.getIntersection().getLatitude() < res) {
                res = i.getIntersection().getLatitude();
            }
        }
        return res;
    }

    /*Recupère la longitude minimale présente sur la carte*/
    public Double minLongitude(ArrayList<Intersection> intersections) {
        Double res = intersections.get(0).getLongitude();
        for (Intersection i : intersections) {
            if (i.getLongitude() < res) {
                res = i.getLongitude();
            }
        }
        return res;
    }

    public Double minLongitudePIs(ArrayList<PointInteret> PIs) {
        Double res = PIs.get(0).getIntersection().getLongitude();
        for (PointInteret i : PIs) {
            if (i.getIntersection().getLongitude() < res) {
                res = i.getIntersection().getLongitude();
            }
        }
        return res;
    }

    /*Recupère la position en Y de l'intersection sur le panel */
    public int getProportionalY(Intersection i, ArrayList<Intersection> intersections) {

        Double maxLatitude = this.maxLatitude(intersections);
        Double minLatitude = this.minLatitude(intersections);

        Double hauteurCarte = (maxLatitude - minLatitude);

        Double distMinLatitude = (i.getLatitude() - minLatitude);

        /*A quel pourcentage de latitude se trouve l'intersection par rapport à la carte*/
        Double pourcentageLatitude = ((distMinLatitude * 100 / hauteurCarte));

        int hauteurPanel = this.getHeight();

        /*Reporter ce pourcentage sur le panel, Attention, le sens est inversé en Java pour les y*/
        int proportionalY = hauteurPanel - (int) (pourcentageLatitude * hauteurPanel / 100);

        if (proportionalY == 0) {
            proportionalY = 2;
        } else if (proportionalY >= hauteurPanel) {
            proportionalY = hauteurPanel - 12;
        }
        
        //System.out.println("deplacement y " +fenetre.getDeplY());
        proportionalY *= fenetre.getZoom();
        proportionalY-=(fenetre.getDeplY());
        //System.out.println("nouveau Y " +proportionalY);

        return proportionalY;
    }

    public int getProportionalYPIs(Intersection i, ArrayList<PointInteret> PIs, ArrayList<Intersection> intersections) {

        Double maxLatitude = this.maxLatitude(intersections);
        Double minLatitude = this.minLatitude(intersections);

        Double hauteurCarte = (maxLatitude - minLatitude);

        Double distMinLatitude = (i.getLatitude() - minLatitude);

        /*A quel pourcentage de latitude se trouve l'intersection par rapport à la carte*/
        Double pourcentageLatitude = ((distMinLatitude * 100 / hauteurCarte));

        int hauteurPanel = this.getHeight();

        /*Reporter ce pourcentage sur le panel, Attention, le sens est inversé en Java pour les y*/
        int proportionalY = hauteurPanel - (int) (pourcentageLatitude * hauteurPanel / 100);

        if (proportionalY == 0) {
            proportionalY = 2;
        } else if (proportionalY >= hauteurPanel) {
            proportionalY = hauteurPanel - 12;
        }

        proportionalY *= fenetre.getZoom();
        proportionalY-=(fenetre.getDeplY());

        return proportionalY;
    }

    /*Recupère la position en X de l'intersection sur le panel */
    public int getProportionalX(Intersection i, ArrayList<Intersection> intersections) {

        Double maxLongitude = this.maxLongitude(intersections);
        Double minLongitude = this.minLongitude(intersections);

        Double largeurCarte = (maxLongitude - minLongitude);

        Double distMinLongitude = (i.getLongitude() - minLongitude);

        /*A quel pourcentage de longitude se trouve l'intersection par rapport à la carte*/
        int pourcentageLongitude = (int) (distMinLongitude * 100 / largeurCarte);

        int largeurPanel = this.getWidth();

        /*Reporter ce pourcentage sur le panel.*/
        int proportionalX = (int) (pourcentageLongitude * largeurPanel / 100);
        /*if (proportionalX == 0) {
            proportionalX = 2;
        } else if (proportionalX >= largeurPanel) {
            proportionalX = largeurPanel - 12;
        }*/

        proportionalX *= fenetre.getZoom();
        proportionalX-=(fenetre.getDeplX());

        return proportionalX;
    }

    public int getProportionalXPIs(Intersection i, ArrayList<PointInteret> PIs, ArrayList<Intersection> intersections) {

        Double maxLongitude = this.maxLongitude(intersections);
        Double minLongitude = this.minLongitude(intersections);

        Double largeurCarte = (maxLongitude - minLongitude);

        Double distMinLongitude = (i.getLongitude() - minLongitude);

        /*A quel pourcentage de longitude se trouve l'intersection par rapport à la carte*/
        int pourcentageLongitude = (int) (distMinLongitude * 100 / largeurCarte);

        int largeurPanel = this.getWidth();

        /*Reporter ce pourcentage sur le panel.*/
        int proportionalX = (int) (pourcentageLongitude * largeurPanel / 100);
        if (proportionalX == 0) {
            proportionalX = 2;
        } else if (proportionalX >= largeurPanel) {
            proportionalX = largeurPanel - 12;
        }

        proportionalX *= fenetre.getZoom();
        proportionalX-=fenetre.getDeplX();

        return proportionalX;
    }
    
    
    /*public void makePalette(){
        ArrayList<Color> palette=new ArrayList<Color>();
        
        if(carte.getListePointsInteretActuelle() != null)
        {
            System.out.println("iciiiiiiii");
            ArrayList<PointInteret> PIs = carte.getListePointsInteretActuelle();
            palette=new ArrayList<Color>();
            
            for (int c=0;c<(PIs.size()-1)/2;c++) {
                Random rand = new Random();
                    float r = rand.nextFloat();
                    float gg = rand.nextFloat();
                    float b = rand.nextFloat();
                    Color randomColor = new Color(r, gg, b);
                    palette.add(randomColor);
            }
        }
        System.out.println(palette.size());
        this.palette=palette;
        System.out.println(this.palette.size());
    }*/
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        ArrayList<Intersection> intersections = carte.getListeIntersections();

        for (Intersection i : intersections) {
            //ajout de l'intersection et de ses coordonnees correspondantes dans la map
            this.ajouterPointToIntersectionsMap(i, new Point(this.getProportionalX(i, intersections), 
                                                this.getProportionalY(i, intersections)));
            
            g.setColor(Color.BLACK);

            g.fillOval(this.getProportionalX(i, intersections), this.getProportionalY(i, intersections), 2, 2);

            ArrayList<Troncon> iTroncons = i.getTronconsDepart();
            for (Troncon t : iTroncons) {
                g.setColor(Color.gray);
                g.drawLine(this.getProportionalX(i, intersections) + 1, this.getProportionalY(i, intersections) + 1, this.getProportionalX(t.getDestination(), intersections) + 1, this.getProportionalY(t.getDestination(), intersections) + 1);
    
//ça affiche 1 fois par tronçon, il faudrait le faire  seul fois par avenue
                /*
                if(t.getNomRue().contains("Avenue")||t.getNomRue().contains("Cours")){
                    Graphics2D g2 = (Graphics2D)g; //cast for java2
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    g2.setColor(new Color(0,0,0));
                    g2.drawString(t.getNomRue(), this.getProportionalX(t.getDestination(), intersections),this.getProportionalY(t.getDestination(), intersections));// S, left, BOTTOM
                }*/
            }
            g.setColor(Color.red);
            g.fillOval((int)((1*fenetre.getZoom())-fenetre.getDeplX()), (int)((1*fenetre.getZoom())-fenetre.getDeplY()),20, 20);
            g.fillOval((int)((100*fenetre.getZoom())-fenetre.getDeplX()), (int)((100*fenetre.getZoom())-fenetre.getDeplY()),20, 20);
        }

        if (carte.getDemandesLivraisons() != null) {
            this.listeCoordPtI.clear();
            ArrayList<PointInteret> PIs = carte.getListePointsInteretActuelle();
            //ArrayList<PointInteret> PIs = carte.getDemandesLivraisons().getListePointsInteret();

            PointInteret depot = carte.getDemandesLivraisons().getAdresseDepart();
            int xDepot = this.getProportionalXPIs(depot.getIntersection(), PIs, intersections) - 2;
            int yDepot = this.getProportionalYPIs(depot.getIntersection(), PIs, intersections) - 2;
            int x[] = {xDepot, xDepot + 6, xDepot + 12};
            int y[] = {yDepot, yDepot + 12, yDepot};
            Polygon p = new Polygon(x, y, 3);
            g.setColor(Color.BLACK);
            g.fillPolygon(p);

            //Ajouter le point du depot dans la liste
            Point ptDepot = new Point(xDepot, yDepot);
            CoordPointInteret cptI = new CoordPointInteret(ptDepot, depot);
            this.ajouterCoordPtI(cptI);
            
            int indiceC=0;
            if (PIs != null) {
                
                for (PointInteret i : PIs) {
                    g.setColor(Color.BLACK);
                   
                    
                    if (i.isEnlevement()) {
                        
                        
                        g.setColor(this.palette.get(indiceC));
                        if(indiceC<this.palette.size())indiceC++;
                        
                        int xRect = this.getProportionalXPIs(i.getIntersection(), PIs, intersections) - 2;
                        int yRect = this.getProportionalYPIs(i.getIntersection(), PIs, intersections) - 2;
                        int xOval = this.getProportionalXPIs(i.getPointDependance().getIntersection(), PIs, intersections) - 2;
                        int yOval = this.getProportionalYPIs(i.getPointDependance().getIntersection(), PIs, intersections) - 2;
                        Point ptRect = new Point(xRect, yRect);
                        Point ptOval = new Point(xOval, yOval);
                        g.fillRect(xRect, yRect, 9, 9);
                        g.fillOval(xOval, yOval, 9, 9);
                        
                        //Ajout des points a la liste stockant les coordonnees des points d interets
                        CoordPointInteret cdPtIRect = new CoordPointInteret(ptRect, i);
                        CoordPointInteret cdPtIOval = new CoordPointInteret(ptOval, i.getPointDependance());
                        this.ajouterCoordPtI(cdPtIRect);
                        this.ajouterCoordPtI(cdPtIOval);

                    }

                }
            }
        }

        tournee = carte.getTournee();
        if (this.tournee != null) {

            ArrayList<PointInteret> PIs = this.tournee.getSuccessionPointsInteret();

            for (PointInteret i : PIs) {
                Chemin chemin = i.getCheminDepart();
                if (chemin != null) {
                    ArrayList<Troncon> iTroncons = chemin.getSuccessionTroncons();
                    for (Troncon t : iTroncons) {
                        g.setColor(Color.RED);
                        g.drawLine(this.getProportionalX(t.getOrigine(), intersections) + 1, this.getProportionalY(t.getOrigine(), intersections) + 1, this.getProportionalX(t.getDestination(), intersections) + 1, this.getProportionalY(t.getDestination(), intersections) + 1);

                        if (t.getLongueur() > 120) {
                            int x = (int) (Math.abs((this.getProportionalX(t.getDestination(), intersections) + this.getProportionalX(t.getOrigine(), intersections))) / 2);
                            int y = (int) (Math.abs((this.getProportionalY(t.getDestination(), intersections) + this.getProportionalY(t.getOrigine(), intersections))) / 2);
                            double k = ((double) (this.getProportionalY(t.getDestination(), intersections) - this.getProportionalY(t.getOrigine(), intersections))) / ((double) (this.getProportionalX(t.getDestination(), intersections) - this.getProportionalX(t.getOrigine(), intersections)));
                            int r = 6;      // taille de fleche
                            int sens = (t.getDestination().getLongitude() > t.getOrigine().getLongitude()) ? 1 : -1;  // sens de fleche
                            double v = (Math.sqrt(1 + k * k));
                            double w = Math.sqrt(1 + (-1 / k) * (-1 / k));
                            double ajoutX = (r / 2 * sens / v);
                            double ajoutY = (r / 2 * sens * k / v);
                            int xT[] = {(int) (2 * r * sens / v + ajoutX) + x, x - (int) (r / w - ajoutX), x + (int) (r / w + ajoutX)};
                            int yT[] = {(int) (2 * r * k * sens / v + ajoutY) + y, y - (int) (r * (-1 / k) / w - ajoutY), y + (int) (r * (-1 / k) / w + ajoutY)};
                            Polygon p = new Polygon(xT, yT, 3);
                            g.setColor(Color.RED);
                            g.fillPolygon(p);
                        }

                    }
                }

            }
        }
        if (this.fenetre != null) {
            int ligneTab = this.fenetre.getVuePIs().getLignePISelect();
            int ligneTabDep = this.fenetre.getVuePIs().getLignePIDepSelect();
            //Si une ligne du tableau des etapes de la tournee a ete selectionnee
            if (ligneTab != -1 && ligneTabDep != -1) {
                int xPI = 0;
                int yPI = 0;
                int xPIDep = 0;
                int yPIDep = 0;
                boolean select = false;
                boolean piDep = false;
                if (ligneTab < this.listeCoordPtI.size()) {
                    //Recuperer les coordonnes du point d interet associe a la ligne du tableau
                    xPI = this.listeCoordPtI.get(ligneTab).getPoint().getX();
                    yPI = this.listeCoordPtI.get(ligneTab).getPoint().getY();
                    select = true;
                    //Si le point selectionne est l entrepot
                } else if (ligneTab == this.listeCoordPtI.size()) {
                    xPI = this.listeCoordPtI.get(0).getPoint().getX();
                    yPI = this.listeCoordPtI.get(0).getPoint().getY();
                    select = true;
                }
                if (select) {
                    BasicStroke line = new BasicStroke(3.5f);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(line);
                    g2.setColor(Color.RED);
                    if (ligneTab == 0) {
                        //Faire un carre rouge autour du point d interet
                        g2.drawLine(xPI - 3, yPI - 3, xPI - 3, yPI + 14);
                        g2.drawLine(xPI - 3, yPI + 14, xPI + 14, yPI + 14);
                        g2.drawLine(xPI + 14, yPI + 14, xPI + 14, yPI - 3);
                        g2.drawLine(xPI + 14, yPI - 3, xPI - 3, yPI - 3);

                    } else {
                        //Faire un carre rouge autour du point d interet
                        g2.drawLine(xPI - 4, yPI - 4, xPI - 4, yPI + 13);
                        g2.drawLine(xPI - 4, yPI + 13, xPI + 13, yPI + 13);
                        g2.drawLine(xPI + 13, yPI + 13, xPI + 13, yPI - 4);
                        g2.drawLine(xPI + 13, yPI - 4, xPI - 4, yPI - 4);
                    }

                }

                if (ligneTabDep < this.listeCoordPtI.size() && ligneTabDep != 0) {
                    xPIDep = this.listeCoordPtI.get(ligneTabDep).getPoint().getX();
                    yPIDep = this.listeCoordPtI.get(ligneTabDep).getPoint().getY();
                    piDep = true;
                }

                if (piDep) {
                    BasicStroke line = new BasicStroke(3.5f);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(line);
                    g2.setColor(Color.ORANGE);

                    //Faire un carre orange autour du point d interet dependant
                    g2.drawLine(xPIDep - 4, yPIDep - 4, xPIDep - 4, yPIDep + 13);
                    g2.drawLine(xPIDep - 4, yPIDep + 13, xPIDep + 13, yPIDep + 13);
                    g2.drawLine(xPIDep + 13, yPIDep + 13, xPIDep + 13, yPIDep - 4);
                    g2.drawLine(xPIDep + 13, yPIDep - 4, xPIDep - 4, yPIDep - 4);

                }

            }
        }

    }
}
