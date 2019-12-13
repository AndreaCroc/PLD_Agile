package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, Alexanne
 * MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class JCarte extends JPanel {

    private Carte carte; //Carte possedant les points dinteret
    private Tournee tournee; //les points dinteret faisant partie de la tournee
    //liste des point dinerets de la carte
    private ArrayList<CoordPointInteret> listeCoordPtI;
    //Liste des intersections de la carte
    private Map<Intersection, Point> intersectionsMap;
    //Liste des troncons de la carte
    private ArrayList<Troncon> tronconsNomsRues;
    private Fenetre fenetre; //fenetre de l application
    //Liste des couleurs possibles des points d'interet
    private ArrayList<Color> palette;
    //listes des coordonnées des intersections dans la carte
    private ArrayList<Point> coorIntersections = new ArrayList<Point>();

    /**
     * Constructeur de la classe JCarte
     *
     * @param carte carte
     * @param tournee tournee
     * @param fenetre fenetre
     */
    public JCarte(Carte carte, Tournee tournee, Fenetre fenetre) {
        this.carte = carte;
        this.tournee = tournee;

        this.listeCoordPtI = new ArrayList<CoordPointInteret>();
        this.intersectionsMap = new HashMap<Intersection, Point>();
        this.tronconsNomsRues = new ArrayList<Troncon>();
        
        this.fenetre = fenetre;
        this.palette = this.fenetre.getPalette();
        this.coorIntersections = new ArrayList<Point>();
        this.repaint();
    }

    /**
     * Modifier la tournee de la carte
     *
     * @param nouvelleTournee
     */
    public void setTournee(Tournee nouvelleTournee) {
        this.tournee = nouvelleTournee;
        this.repaint();
    }

    /**
     * Modifier la fenetre
     *
     * @param fenetre nouvelle fenetre
     */
    public void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
        this.repaint();
    }

    /**
     * Recuperer la tournee
     *
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

    /**
     * Ajouter une intersection a la liste des intersections de la carte
     * @param i intersection a ajouter
     * @param p coordonnees de l'intersection
     */
    public void ajouterPointToIntersectionsMap(Intersection i, Point p) {
        this.intersectionsMap.put(i, p);
    }

    /**
     * Recuperer la liste des intersections et de leurs coordonnees 
     * sur la carte
     * @return liste des intersections de la carte
     */
    public Map<Intersection, Point> getIntersectionsMap() {
        return this.intersectionsMap;
    }

    /**
     * Recuperer la liste des coordonnées des intersections
     *
     * @return liste de coordonnées des intersections
     */
    public ArrayList<Point> getCoorIntersections() {
        return coorIntersections;
    }

    /**
     * Recuperer la liste des points dinteret et leurs coordonnees sur la carte
     *
     * @return liste
     */
    public ArrayList<CoordPointInteret> getCoordPtInterets() {
        return this.listeCoordPtI;
    }
    
    /**
     * Modifier la liste des troncons dont on veut afficher le nom de la rue
     * @param lTroncons liste de troncons
     */
    public void setTronconsNomsRues(ArrayList<Troncon> lTroncons) {
        this.tronconsNomsRues = lTroncons;
    }

    /**
     * Recupère la latitude maximale présente sur la carte
     * @param intersections liste des intersections de la carte
     * @return latitude max
     */
    public Double maxLatitude(ArrayList<Intersection> intersections) {

        Double res = intersections.get(0).getLatitude();
        for (Intersection i : intersections) {
            if (i.getLatitude() > res) {
                res = i.getLatitude();
            }
        }
        return res;
    }

    /**
     * Recupere la latitude max presente sur la carte pour les points d'interet
     * @param PIs liste des points d'interet de la carte
     * @return latitude max
     */
    public Double maxLatitudePIs(ArrayList<PointInteret> PIs) {

        Double res = PIs.get(0).getIntersection().getLatitude();
        for (PointInteret i : PIs) {
            if (i.getIntersection().getLatitude() > res) {
                res = i.getIntersection().getLatitude();
            }
        }
        return res;
    }

    /**
     * Recupere la longitude max presente sur la carte
     * @param intersections liste des intersections de la carte
     * @return longitude max
     */
    public Double maxLongitude(ArrayList<Intersection> intersections) {

        Double res = intersections.get(0).getLongitude();
        for (Intersection i : intersections) {
            if (i.getLongitude() > res) {
                res = i.getLongitude();
            }
        }
        return res;
    }

    /**
     * Recupere la longitude max presente sur la carte pour les points d'interet
     * @param PIs liste des points d'interet de la carte
     * @return longitude max
     */
    public Double maxLongitudePIs(ArrayList<PointInteret> PIs) {

        Double res = PIs.get(0).getIntersection().getLongitude();
        for (PointInteret i : PIs) {
            if (i.getIntersection().getLongitude() > res) {
                res = i.getIntersection().getLongitude();
            }
        }
        return res;
    }

    /**
     * Recupère la latitude minimale présente sur la carte
     * @param intersections liste des intersections de la carte
     * @return latitude min
     */
    public Double minLatitude(ArrayList<Intersection> intersections) {
        Double res = intersections.get(0).getLatitude();
        for (Intersection i : intersections) {
            if (i.getLatitude() < res) {
                res = i.getLatitude();
            }
        }
        return res;
    }

    /**
     * Recupere la latitude minimale sur la carte pour les points d'interet
     * @param PIs liste des points d'interet de la carte
     * @return latitude min
     */
    public Double minLatitudePIs(ArrayList<PointInteret> PIs) {
        Double res = PIs.get(0).getIntersection().getLatitude();
        for (PointInteret i : PIs) {
            if (i.getIntersection().getLatitude() < res) {
                res = i.getIntersection().getLatitude();
            }
        }
        return res;
    }

    /**
     * Recupère la longitude minimale présente sur la carte
     * @param intersections liste des intersections de la carte
     * @return longitude min
     */
    public Double minLongitude(ArrayList<Intersection> intersections) {
        Double res = intersections.get(0).getLongitude();
        for (Intersection i : intersections) {
            if (i.getLongitude() < res) {
                res = i.getLongitude();
            }
        }
        return res;
    }

    /**
     * Recupère la longitude minimale présente sur la carte pour
     * les points d'interets
     * @param PIs liste de spoints d'interet de la carte
     * @return longitude min
     */
    public Double minLongitudePIs(ArrayList<PointInteret> PIs) {
        Double res = PIs.get(0).getIntersection().getLongitude();
        for (PointInteret i : PIs) {
            if (i.getIntersection().getLongitude() < res) {
                res = i.getIntersection().getLongitude();
            }
        }
        return res;
    }

    /**
     * Recupère la position en Y de l'intersection sur le panel
     * @param i intersection
     * @param intersections liste des intersections
     * @return position Y
     */
    public int getProportionalY(Intersection i, 
            ArrayList<Intersection> intersections) {

        Double maxLatitude = this.maxLatitude(intersections);
        Double minLatitude = this.minLatitude(intersections);

        Double hauteurCarte = (maxLatitude - minLatitude);

        Double distMinLatitude = (i.getLatitude() - minLatitude);

        /*A quel pourcentage de latitude se trouve l'intersection
        par rapport à la carte*/
        Double pourcentageLatitude = ((distMinLatitude * 100 / hauteurCarte));

        int hauteurPanel = this.getHeight();

        /*Reporter ce pourcentage sur le panel, Attention, le sens est inversé 
        en Java pour les y*/
        int proportionalY = hauteurPanel - (int) (pourcentageLatitude
                * hauteurPanel / 100);

        if (proportionalY == 0) {
            proportionalY = 2;
        } else if (proportionalY >= hauteurPanel) {
            proportionalY = hauteurPanel - 12;
        }

        proportionalY *= fenetre.getZoom();
        proportionalY -= (fenetre.getDeplY());

        return proportionalY;
    }

    /**
     * Recupère la position en Y de l'intersection sur le panel
     * @param i intersection
     * @param PIs liste des points d'interet
     * @param intersections liste des intersections
     * @return position en Y
     */
    public int getProportionalYPIs(Intersection i, ArrayList<PointInteret> PIs,
            ArrayList<Intersection> intersections) {

        Double maxLatitude = this.maxLatitude(intersections);
        Double minLatitude = this.minLatitude(intersections);

        Double hauteurCarte = (maxLatitude - minLatitude);

        Double distMinLatitude = (i.getLatitude() - minLatitude);

        /*A quel pourcentage de latitude se trouve l'intersection 
        par rapport à la carte*/
        Double pourcentageLatitude = ((distMinLatitude * 100 / hauteurCarte));

        int hauteurPanel = this.getHeight();

        /*Reporter ce pourcentage sur le panel, Attention, le sens est 
        inversé en Java pour les y*/
        int proportionalY = hauteurPanel - (int) (pourcentageLatitude * 
                hauteurPanel / 100);

        if (proportionalY == 0) {
            proportionalY = 2;
        } else if (proportionalY >= hauteurPanel) {
            proportionalY = hauteurPanel - 12;
        }

        proportionalY *= fenetre.getZoom();
        proportionalY -= (fenetre.getDeplY());

        return proportionalY;
    }

    /**
     * Recupère la position en X de l'intersection sur le panel
     * @param i intersection
     * @param intersections liste des intersections
     * @return position en X
     */
    public int getProportionalX(Intersection i,
            ArrayList<Intersection> intersections) {

        Double maxLongitude = this.maxLongitude(intersections);
        Double minLongitude = this.minLongitude(intersections);

        Double largeurCarte = (maxLongitude - minLongitude);

        Double distMinLongitude = (i.getLongitude() - minLongitude);

        /*A quel pourcentage de longitude se trouve l'intersection 
        par rapport à la carte*/
        int pourcentageLongitude = (int) (distMinLongitude * 100
                / largeurCarte);

        int largeurPanel = this.getWidth();

        /*Reporter ce pourcentage sur le panel.*/
        int proportionalX = (int) (pourcentageLongitude * largeurPanel / 100);
        if (proportionalX == 0) {
            proportionalX = 2;
        } else if (proportionalX >= largeurPanel) {
            proportionalX = largeurPanel - 12;
        }

        proportionalX *= fenetre.getZoom();
        proportionalX -= (fenetre.getDeplX());

        return proportionalX;
    }

    /**
     * Recupère la position en X de l'intersection sur le panel
     * @param i intersection 
     * @param PIs liste des points d'interet
     * @param intersections liste des intersections
     * @return position en X
     */
    public int getProportionalXPIs(Intersection i, ArrayList<PointInteret> PIs,
            ArrayList<Intersection> intersections) {

        Double maxLongitude = this.maxLongitude(intersections);
        Double minLongitude = this.minLongitude(intersections);

        Double largeurCarte = (maxLongitude - minLongitude);

        Double distMinLongitude = (i.getLongitude() - minLongitude);

        /*A quel pourcentage de longitude se trouve l'intersection 
        par rapport à la carte*/
        int pourcentageLongitude = (int) (distMinLongitude * 100
                / largeurCarte);

        int largeurPanel = this.getWidth();

        /*Reporter ce pourcentage sur le panel.*/
        int proportionalX = (int) (pourcentageLongitude * largeurPanel / 100);
        if (proportionalX == 0) {
            proportionalX = 2;
        } else if (proportionalX >= largeurPanel) {
            proportionalX = largeurPanel - 12;
        }

        proportionalX *= fenetre.getZoom();
        proportionalX -= fenetre.getDeplX();

        return proportionalX;
    }

    /**
     * Dessiner les elements de la carte sur la fenetre
     * @param g graphic 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d=(Graphics2D)g;
        int wi=this.getWidth();
        int h=this.getHeight();
        
        g2d.translate(wi/2,h/2);
        g2d.scale(fenetre.getZoom(),fenetre.getZoom());
        g2d.translate(-wi/2,-h/2);

        ArrayList<Intersection> intersections = carte.getListeIntersections();
        //Affichage de la carte
        for (Intersection i : intersections) {
            //ajout de l'intersection et de ses coordonnees 
            //correspondantes dans la map
            this.ajouterPointToIntersectionsMap(i, new Point(this
                    .getProportionalX(i,
                            intersections),
                    this.getProportionalY(i,
                            intersections)));

            g.setColor(Color.BLACK);
            
            g.fillOval(this.getProportionalX(i, intersections), this
                    .getProportionalY(i, intersections), 2, 2);

            this.coorIntersections.add(new Point(getProportionalX(i, 
                                                                 intersections),
                    getProportionalY(i, intersections)));

            ArrayList<Troncon> iTroncons = i.getTronconsDepart();
            for (Troncon t : iTroncons) {
                g.setColor(Color.gray);
                
                g.drawLine(this.getProportionalX(i, intersections) + 1,
                        this.getProportionalY(i, intersections) + 1,
                        this.getProportionalX(t.getDestination(),
                                intersections) + 1,
                        this.getProportionalY(t.getDestination(),
                                intersections) + 1);

            }

        }
        //Affichage des noms des rues cliquées
        for (Troncon tRues : tronconsNomsRues) {
            
            double xOrig, yOrig;        //coordonnees depart du troncon
            double xDest, yDest;        //coordonnees arrivees du troncon
            double k;                   //pente du vecteur troncon
            double numerateur, denom;   //calcul de k
            double nvX, nvY;            //coordonnees du nom de rue
            double longueurString;      //longueur de la rue affichee
            double longueurTroncon;     //longueur du troncon
            double xVect, yVect;        //coordonnees du vecteur troncon
            double facteur;             //facteur de multiplication servant a 
                                        //connaitre le nombre de fois par 
                                        //lequel on multiplie le vecteur pour
                                        //avoir la longueur du nom de rue 
                                        //(cas ou le nom de rue est plus long 
                                        //que le vecteur troncon
            double radian;              //angle d inclinaison du nom de rue
            
            
            Graphics2D g2 = (Graphics2D)g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g2.setColor(new Color(0,0,0));

            //traitement pour les coordonnes d affichage
            xDest = this.getProportionalX(tRues.getDestination(), intersections);
            yDest = this.getProportionalY(tRues.getDestination(), intersections);
            xOrig = this.getProportionalX(tRues.getOrigine(), intersections);
            yOrig = this.getProportionalY(tRues.getOrigine(), intersections);

            //calcul de la pente k du vecteur
            numerateur = (yDest - yOrig);
            denom = (xDest - xOrig);
            if(denom != 0) {
                k = numerateur / denom;
            } else {
                k = numerateur / 0.001;
            }
            
            //calcul des longueurs du troncon et du label de la rue
            longueurString = g2.getFontMetrics().stringWidth(tRues.getNomRue());
            longueurTroncon = Math.sqrt(Math.pow((xDest-xOrig), 2) 
                              + Math.pow((yDest-yOrig), 2));
            
            //calcul des coordonnees du vecteur troncon
            xVect = xDest - xOrig;
            yVect = yDest - yOrig;
            
            //traitement affichage des noms de rues a gauche par rapport
            //a l intersection cliquee 
            if((xVect < 0) && (longueurString >= 3/5 * longueurTroncon)) {
                facteur = longueurString / longueurTroncon;
                nvX = xOrig + (facteur + 0.2) * xVect;
                nvY = yOrig + (facteur + 0.2) * yVect;
            } else {
                nvX = (3 * this.getProportionalX(tRues.getDestination(), intersections) 
                       + 2 * this.getProportionalX(tRues.getOrigine(), intersections)) / 5;
                nvY = (3 * this.getProportionalY(tRues.getDestination(), intersections) 
                       + 2 * this.getProportionalY(tRues.getOrigine(), intersections)) / 5;
            }
            
            radian = Math.atan(k);
            g2.rotate(radian,nvX, nvY); //mieux avec x et y précisees
            g2.drawString(tRues.getNomRue(), (int) nvX,(int) nvY);
            g2.dispose();
        }
        
        //Affichage de la tournee
        tournee = carte.getTournee();
        if (this.tournee != null) {

            ArrayList<PointInteret> PIs = this.tournee
                    .getSuccessionPointsInteret();

            for (PointInteret i : PIs) {
                Chemin chemin = i.getCheminDepart();
                if (chemin != null) {
                    ArrayList<Troncon> iTroncons = chemin
                            .getSuccessionTroncons();
                    for (Troncon t : iTroncons) {
                        
                        Graphics2D g1 = (Graphics2D) g;
                        g1.setStroke( new BasicStroke(2.5f));
                        g1.setColor(Color.RED);
                        g1.drawLine(this.getProportionalX(t.getOrigine(),
                                intersections) , this.getProportionalY(
                                        t.getOrigine(), intersections) ,
                                this.getProportionalX(t.getDestination(),
                                        intersections) , this
                                        .getProportionalY(
                                                t.getDestination(),
                                                intersections) );

                        if (t.getLongueur() > 120) {
                            int x = (int) (((this.getProportionalX(
                                    t.getDestination(), intersections)
                                    + this.getProportionalX(t.getOrigine(),
                                            intersections))) / 2);
                            int y = (int) (((this.getProportionalY(
                                    t.getDestination(), intersections)
                                    + this.getProportionalY(t.getOrigine(),
                                            intersections))) / 2);
                            double k = ((double) (this.getProportionalY(
                                    t.getDestination(), intersections)
                                    - this.getProportionalY(t.getOrigine(),
                                            intersections))) / ((double) (this.
                                                    getProportionalX(t.
                                                            getDestination(),
                                            intersections)
                                    - this.getProportionalX(t.getOrigine(),
                                            intersections)));
                            int r = 5;      // taille de fleche
                            // sens de fleche
                            int sens = (t.getDestination().getLongitude()
                                    > t.getOrigine().getLongitude()) ? 1 : -1;
                            double v = (Math.sqrt(1 + k * k));
                            double w = Math.sqrt(1 + (-1 / k) * (-1 / k));
                            double ajoutX = (r / 2 * sens / v);
                            double ajoutY = (r / 2 * sens * k / v);
                            int xT[] = {(int) (2 * r * sens / v + ajoutX) + x,
                                x - (int) (r / w - ajoutX), x + (int) (r / w 
                                                                    + ajoutX)};
                            int yT[] = {(int) (2 * r * k * sens / v + ajoutY)
                                + y, y - (int) (r * (-1 / k) / w - ajoutY),
                                y + (int) (r * (-1 / k) / w + ajoutY)};
                            Polygon p = new Polygon(xT, yT, 3);
                            g.setColor(Color.RED);
                            g.fillPolygon(p);
                        }

                    }
                }

            }
        }
        
        
        
        //Affichage des points d'interet
        if (carte.getDemandesLivraisons() != null) {
            this.listeCoordPtI.clear();
            ArrayList<PointInteret> PIs = carte.getListePointsInteretActuelle();

            PointInteret depot = carte.getDemandesLivraisons()
                    .getAdresseDepart();
            int xDepot = this.getProportionalXPIs(depot.getIntersection(), PIs,
                    intersections) - 2;
            int yDepot = this.getProportionalYPIs(depot.getIntersection(), PIs,
                    intersections) - 2;
            int x[] = {xDepot, xDepot + 8, xDepot + 18};
            int y[] = {yDepot, yDepot + 18, yDepot};
            Polygon p = new Polygon(x, y, 3);
            g.setColor(Color.BLACK);
            g.fillPolygon(p);

            //Ajouter le point du depot dans la liste
            Point ptDepot = new Point(xDepot, yDepot);
            CoordPointInteret cptI = new CoordPointInteret(ptDepot, depot);
            this.ajouterCoordPtI(cptI);

            int indiceC = 0;
            if (PIs != null) {

                for (PointInteret i : PIs) {
                    g.setColor(Color.BLACK);

                    if (i.isEnlevement()) {

                        g.setColor(this.palette.get(indiceC));
                        if (indiceC < this.palette.size()) {
                            indiceC++;
                        }

                        int xRect = this.getProportionalXPIs(i
                                .getIntersection(), PIs, intersections) -4;
                        int yRect = this.getProportionalYPIs(i
                                .getIntersection(), PIs, intersections) -4;
                        int xOval = this.getProportionalXPIs(i
                                .getPointDependance().getIntersection(),
                                PIs, intersections) -5;
                        int yOval = this.getProportionalYPIs(i
                                .getPointDependance().getIntersection(),
                                PIs, intersections) -5;
                        Point ptRect = new Point(xRect, yRect);
                        Point ptOval = new Point(xOval, yOval);
                        g.fillRect(xRect, yRect, 12, 12);
                        g.fillOval(xOval, yOval,12, 12);

                        //Ajout des points a la liste stockant 
                        //les coordonnees des points d interets
                        CoordPointInteret cdPtIRect = new CoordPointInteret(
                                                                    ptRect, i);
                        CoordPointInteret cdPtIOval = new CoordPointInteret(
                                                ptOval, i.getPointDependance());
                        this.ajouterCoordPtI(cdPtIRect);
                        this.ajouterCoordPtI(cdPtIOval);

                    }

                }
            }
        }
        
        
        //Surbrillance
        if (this.fenetre != null) {
            int ligneTab = this.fenetre.getVuePIs().getLignePISelect();
            int ligneTabDep = this.fenetre.getVuePIs().getLignePIDepSelect();
            //Si une ligne du tableau des etapes de tournee a ete selectionnee
            if (ligneTab != -1 && ligneTabDep != -1) {
                int xPI = 0;
                int yPI = 0;
                int xPIDep = 0;
                int yPIDep = 0;
                boolean select = false;
                boolean piDep = false;
                if (ligneTab < this.listeCoordPtI.size()) {
                    //Recuperer les coordonnes du point d interet associe 
                    //a la ligne du tableau
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
                    g2.setColor(Color.blue);
                    if (ligneTab == 0) {
                        //Faire un carre bleu autour entrepot
                        g2.drawLine(xPI - 3, yPI - 3, xPI - 3, yPI + 19);
                        g2.drawLine(xPI - 3, yPI + 19, xPI + 19, yPI + 19);
                        g2.drawLine(xPI + 19, yPI + 19, xPI + 19, yPI - 3);
                        g2.drawLine(xPI + 19, yPI - 3, xPI - 3, yPI - 3);

                    } else {
                        //Faire un carre bleu autour du point d interet
                        g2.drawLine(xPI - 4, yPI - 4, xPI - 4, yPI + 16);
                        g2.drawLine(xPI - 4, yPI + 16, xPI + 16, yPI + 16);
                        g2.drawLine(xPI + 16, yPI + 16, xPI + 16, yPI - 4);
                        g2.drawLine(xPI + 16, yPI - 4, xPI - 4, yPI - 4);
                    }

                }

                if (ligneTabDep < this.listeCoordPtI.size() && ligneTabDep != 0) {
                    xPIDep = this.listeCoordPtI.get(ligneTabDep).getPoint()
                            .getX();
                    yPIDep = this.listeCoordPtI.get(ligneTabDep).getPoint()
                            .getY();
                    piDep = true;
                }

                if (piDep) {
                    BasicStroke line = new BasicStroke(3.5f);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(line);
                    g2.setColor(Color.ORANGE);

                    //Faire un carre orange autour du point d interet dependant
                    g2.drawLine(xPIDep - 4, yPIDep - 4, xPIDep - 4, yPIDep
                            + 16);
                    g2.drawLine(xPIDep - 4, yPIDep + 16, xPIDep + 16, yPIDep
                            + 16);
                    g2.drawLine(xPIDep + 16, yPIDep + 16, xPIDep + 16, yPIDep
                            - 4);
                    g2.drawLine(xPIDep + 16, yPIDep - 4, xPIDep - 4, yPIDep
                            - 4);

                }

            }
        }

    }
}
