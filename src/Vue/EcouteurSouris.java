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
import java.util.Map;
import modele.Intersection;

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
                    System.out.println("Point Interet clique");
                    this.controleur.surbrillanceTableau(index);
                    
                    
                    
                    break;
                }
                
            }
        }
        
        
        Map<Intersection, Point> mesCoordIntersections = vueCarte.getIntersectionsMap();
        
        // si tableau des intersections non vide et non null
        if(!mesCoordIntersections.isEmpty()) {
            //test x y sont sur pt intersect coord 
            for (Map.Entry<Intersection, Point> iEntry : mesCoordIntersections.entrySet()) {
                //parcourir la map pour trouver me point qui correspond aux var x et y cliquées par l'utilisateur
                Intersection key = iEntry.getKey();
                Point value = iEntry.getValue();
                                
                //coordonnées adaptees des points d'intersections
                int nvXpt2 = value.getX()+ xPanneauGauche+5;
                int nvYpt2 = value.getY() + yPanneauLegende+45;
                
                //System.out.println("Intersection : "+ key.getId());
                //System.out.println("ix:"+nvXpt2);
                //System.out.println("iy:"+nvYpt2);
                
                if(x >= nvXpt2 && x <= nvXpt2+5 && y >= nvYpt2-5 && y <= nvYpt2+5) {
                    //les coordonnées correspondent
                    System.out.println("Clique sur intersection numero : " + key.getId());
                    System.out.println("Coordonnees cliquees par utilisateur x : " + x + " / y : " + y);
                    
                    //On recupere la liste des troncons dans le but d'afficher leur noms
                    //key.getTronconsDepart();
                    
                }
                
            }
                    
        }
    }           

    }
