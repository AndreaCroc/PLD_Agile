
package Vue;

/**
 * Point permet de representer un point sur la fenetre
 *
 * @version Version 1
 * 
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Point {

    private int x; //Coordoonees sur l axe des abscisses
    private int y; //Coordonnees sur l axe des ordonnees
    
    /**
     * Constructeur de la classe Point
     * @param x
     * @param y 
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Recuperer x
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Recuperer y
     * @return y
     */
    public int getY() {
        return y;
    }
    
}
