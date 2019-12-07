/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import modele.PointInteret;

/**
 *
 * @author acer
 */
public class CoordPointInteret {
    private Point point;
    private PointInteret ptI;

    public CoordPointInteret(Point point, PointInteret ptI) {
        this.point = point;
        this.ptI = ptI;
    }

    public Point getPoint() {
        return point;
    }

    public PointInteret getPtI() {
        return ptI;
    }
    
    
}
