/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import controleur.Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author acer
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
        int xPanneauGauche = this.fenetre.getWidthPanneauGauche();
        int yPanneauLegende = this.fenetre.getHeightPanneauLegende();
        System.out.println("width : "+xPanneauGauche);
        System.out.println("height : "+yPanneauLegende);
        
        ArrayList<Point> coordPis =  vueCarte.getCoorPtInterets();

        System.out.println("Fenetre cliquee");
        int x = evt.getX()-8;
        int y = evt.getY()-8;
        System.out.println("x : "+x+" y : "+y);
        
        if (coordPis != null && !coordPis.isEmpty()) {
            int index = 0;
            for (Point p : coordPis) {
                index = coordPis.indexOf(p);
                //Point(nxXpt,nvYpt) correspond au centre des figures
                int nvXpt = p.getX()+ xPanneauGauche+5;
                int nvYpt = p.getY() + yPanneauLegende+25;
                

                if (x >= nvXpt-5 && x <= nvXpt + 5 && y >= nvYpt-5 && y <= nvYpt + 5) {
                    System.out.println("nvxpt : "+nvXpt + " nvYpt : "+nvYpt);
                    this.controleur.setFenetreSurbrillance(true);
                    System.out.println("Point Interet clique");
                    this.controleur.surbrillanceTableau(index);
                    break;
                }
                
            }
        }

    }
}
