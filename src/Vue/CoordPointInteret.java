
package Vue;

import modele.PointInteret;

/**
 * CoordPointInteret
 *
 * Version 1
 * 
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class CoordPointInteret {
    private Point point; //coordonnees du point d interet a l echelle de la fenetre
    private PointInteret ptI; //point d interet

    /**
     * Constructeur de la classe CoordPointIntert
     * @param point point ou se trouve le point d interet
     * @param ptI point d interet
     */
    public CoordPointInteret(Point point, PointInteret ptI) {
        this.point = point;
        this.ptI = ptI;
    }

    /**
     * Recuperer les coordonnees du point
     * @return point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Recuperer le point d interet
     * @return point d interet
     */
    public PointInteret getPtI() {
        return ptI;
    }
    
    
}
