/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;

/**
 *
 * @author GRAZIA GIULIA
 */
public class JCarte extends JPanel {

    private Carte carte;
    private Tournee tournee;
    private ArrayList<Point> coorPtInterets;
    private Map<Intersection, Point> intersectionsMap;
    private Fenetre fenetre;

    public JCarte(Carte carte, Tournee tournee, Fenetre fenetre) {
        System.out.println("constructeur 1");
        this.carte = carte;
        this.tournee = tournee;
        this.coorPtInterets = new ArrayList<>();
        this.intersectionsMap = new HashMap<>();
        this.fenetre = fenetre;
        this.repaint();
    }

    public void setTournee(Tournee nouvelleTournee) {
        this.tournee = nouvelleTournee;
        this.repaint();
    }

    public void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
        this.repaint();
    }

    public Tournee getTournee() {
        return this.tournee;
    }

    public void ajouterPoint(Point p) {
        this.coorPtInterets.add(p);
    }
    
    public void ajouterPointToIntersection(Intersection i, Point p) {
        this.intersectionsMap.put(i, p);
    }

    public ArrayList<Point> getCoorPtInterets() {
        return this.coorPtInterets;
    }
    
    public Map<Intersection, Point> getIntersectionsMap() {
        return this.intersectionsMap;
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
        if (proportionalX == 0) {
            proportionalX = 2;
        } else if (proportionalX >= largeurPanel) {
            proportionalX = largeurPanel - 12;
        }

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

        return proportionalX;
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("paint");
        super.paintComponent(g);
        ArrayList<Intersection> intersections = carte.getListeIntersections();

        for (Intersection i : intersections) {
            
            //ajout de l'intersection et de ses coordonnees correspondantes dans la map (sert au clic sur une intersection)
            this.ajouterPointToIntersection(i, new Point(this.getProportionalX(i, intersections), this.getProportionalY(i, intersections)));
            //System.out.println("ALEXANNE\r\nAjout à la map : \r\nIntersection : " + i.getId() + " x : " + getProportionalX(i, intersections) + " / y : " + getProportionalY(i, intersections));
            
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
        }
        if (carte.getDemandesLivraisons() != null) {
            this.coorPtInterets.clear();
            ArrayList<PointInteret> PIs = carte.getDemandesLivraisons().getListePointsInteret();

            PointInteret depot = carte.getDemandesLivraisons().getAdresseDepart();
            int xDepot = this.getProportionalXPIs(depot.getIntersection(), PIs, intersections) - 2;
            int yDepot = this.getProportionalYPIs(depot.getIntersection(), PIs, intersections) - 2;
            int x[] = {xDepot, xDepot + 6, xDepot + 12};
            int y[] = {yDepot, yDepot + 12, yDepot};
            Polygon p = new Polygon(x, y, 3);
            g.setColor(Color.BLACK);
            g.fillPolygon(p);

            Point ptDepot = new Point(xDepot, yDepot);
            this.ajouterPoint(ptDepot);

            if (PIs != null) {
                for (PointInteret i : PIs) {
                    g.setColor(Color.BLACK);
                    System.out.println(i.isEnlevement());
                    Random rand = new Random();
                    float r = rand.nextFloat();
                    float gg = rand.nextFloat();
                    float b = rand.nextFloat();
                    Color randomColor = new Color(r, gg, b);
                    g.setColor(randomColor);

                    if (i.isEnlevement()) {
                        int xRect = this.getProportionalXPIs(i.getIntersection(), PIs, intersections) - 2;
                        int yRect = this.getProportionalYPIs(i.getIntersection(), PIs, intersections) - 2;
                        int xOval = this.getProportionalXPIs(i.getPointDependance().getIntersection(), PIs, intersections) - 2;
                        int yOval = this.getProportionalYPIs(i.getPointDependance().getIntersection(), PIs, intersections) - 2;
                        Point ptRect = new Point(xRect, yRect);
                        Point ptOval = new Point(xOval, yOval);
                        g.fillRect(xRect, yRect, 9, 9);
                        g.fillOval(xOval, yOval, 9, 9);
                        this.ajouterPoint(ptRect);
                        this.ajouterPoint(ptOval);

                    }

                }
            }
        }

        tournee = carte.getTournee();
        if (this.tournee != null) {

            ArrayList<PointInteret> PIs = this.tournee.getSuccessionPointsInteret();

            for (PointInteret i : PIs) {

                ArrayList<Troncon> iTroncons = i.getCheminDepart().getSuccessionTroncons();
                for (Troncon t : iTroncons) {
                    g.setColor(Color.RED);
                    g.drawLine(this.getProportionalX(t.getOrigine(), intersections) + 1, this.getProportionalY(t.getOrigine(), intersections) + 1, this.getProportionalX(t.getDestination(), intersections) + 1, this.getProportionalY(t.getDestination(), intersections) + 1);

                    if (t.getLongueur() > 120) {
                        int x = (int) (Math.abs((this.getProportionalX(t.getDestination(), intersections) + this.getProportionalX(t.getOrigine(), intersections))) / 2);
                        System.out.println("x = " + x);
                        int y = (int) (Math.abs((this.getProportionalY(t.getDestination(), intersections) + this.getProportionalY(t.getOrigine(), intersections))) / 2);
                        int xT[] = {x - 2, x + 4, x + 7};
                        int yT[] = {y - 2, y + 7, y};
                        Polygon p = new Polygon(xT, yT, 3);
                        g.setColor(Color.RED);
                        g.fillPolygon(p);
                    }

                }
            }
        }
        if (this.fenetre != null) {
            int ligneTab = this.fenetre.getVueEtapes().getLigneSelect();
            System.out.println("ligneTab2 : " + ligneTab);
            if (ligneTab != -1) {
                int xPI = 5;
                int yPI = 5;
                boolean select = false;
                if (ligneTab < this.coorPtInterets.size()) {
                    xPI = this.coorPtInterets.get(ligneTab).getX();
                    yPI = this.coorPtInterets.get(ligneTab).getY();
                    select = true;
                    System.out.println("coloriage1");
                } else if (ligneTab == this.coorPtInterets.size()) {
                    xPI = this.coorPtInterets.get(0).getX();
                    yPI = this.coorPtInterets.get(0).getY();
                    select = true;
                    System.out.println("coloriage2");
                }
                if (select) {
                    g.setColor(Color.RED);
                    g.drawLine(xPI - 5, yPI - 5, xPI - 5, yPI + 15);
                    g.drawLine(xPI - 5, yPI + 15, xPI + 15, yPI + 15);
                    g.drawLine(xPI + 15, yPI + 15, xPI + 15, yPI - 5);
                    g.drawLine(xPI + 15, yPI - 5, xPI - 5, yPI - 5);
                }

            }
        }

    }
}
