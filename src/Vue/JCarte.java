/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import controleur.Controleur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;
//import modele.Troncon;

/**
 *
 * @author GRAZIA GIULIA
 */
public class JCarte extends JPanel{

    private Carte carte;
    private Tournee tournee;
    
    public JCarte(Carte carte,Tournee tournee) {
        this.carte = carte;
        this.tournee=tournee;
    }
    public void setTournee(Tournee nouvelleTournee){
        this.tournee=nouvelleTournee;
        //this.repaint();
    }
    /*Recupère la latitude maximale présente sur la carte*/
    public Double maxLatitude(ArrayList<Intersection> intersections){
        
        Double res=intersections.get(0).getLatitude();
        for (Intersection i : intersections) {
            if(i.getLatitude()>res){
                res=i.getLatitude();
            }
        }
        return res;
    }
    
    public Double maxLatitudePIs(ArrayList<PointInteret> PIs){
        
        Double res=PIs.get(0).getIntersection().getLatitude();
        for (PointInteret i : PIs) {
            if(i.getIntersection().getLatitude()>res){
                res=i.getIntersection().getLatitude();
            }
        }
        return res;
    }
    
    public Double maxLongitude(ArrayList<Intersection> intersections){
        Double res=intersections.get(0).getLongitude();
        for (Intersection i : intersections) {
            if(i.getLongitude()>res){
                res=i.getLongitude();
            }
        }
        return res;
    }
    
    /*Recupère la Longitude maximale présente sur la carte*/
    public Double maxLongitudePIs(ArrayList<PointInteret> PIs){
        Double res=PIs.get(0).getIntersection().getLongitude();
        for (PointInteret i : PIs) {
            if(i.getIntersection().getLongitude()>res){
                res=i.getIntersection().getLongitude();
            }
        }
        return res;
    }
    
    /*Recupère la latitude minimale présente sur la carte*/
    public Double minLatitude(ArrayList<Intersection> intersections){
        Double res=intersections.get(0).getLatitude();
        for (Intersection i : intersections) {
            if(i.getLatitude()<res){
                res=i.getLatitude();
            }
        }
        return res;
    }
    
    public Double minLatitudePIs(ArrayList<PointInteret> PIs){
        Double res=PIs.get(0).getIntersection().getLatitude();
        for (PointInteret i : PIs) {
            if(i.getIntersection().getLatitude()<res){
                res=i.getIntersection().getLatitude();
            }
        }
        return res;
    }
    
    /*Recupère la longitude minimale présente sur la carte*/
    public Double minLongitude(ArrayList<Intersection> intersections){
        Double res=intersections.get(0).getLongitude();
        for (Intersection i : intersections) {
            if(i.getLongitude()<res){
                res=i.getLongitude();
            }
        }
        return res;
    }
    
     public Double minLongitudePIs(ArrayList<PointInteret> PIs){
        Double res=PIs.get(0).getIntersection().getLongitude();
        for (PointInteret i : PIs) {
            if(i.getIntersection().getLongitude()<res){
                res=i.getIntersection().getLongitude();
            }
        }
        return res;
    }
    
    /*Recupère la position en Y de l'intersection sur le panel */
    public int getProportionalY(Intersection i,ArrayList<Intersection> intersections){
        
        Double maxLatitude=this.maxLatitude(intersections);
        Double minLatitude=this.minLatitude(intersections);
        
         /*on multiplie pour réduire la différence d'échelle*/
        Double hauteurCarte=(maxLatitude-minLatitude);
        //System.out.println("HauteurCarte : "+hauteurCarte);
        
        Double distMinLatitude=(i.getLatitude()-minLatitude);
        
        /*A quel pourcentage de latitude se trouve l'intersection par rapport à la carte*/
        Double pourcentageLatitude=((distMinLatitude*100/hauteurCarte));
        //System.out.println("PourcentageLatitude : "+pourcentageLatitude);
        
        int hauteurPanel=this.getHeight();
        //System.out.println("hauteurPanel : "+hauteurPanel);
        
        /*Reporter ce pourcentage sur le panel, Attention, le sens est inversé en Java*/
        int proportionalY=(int)(pourcentageLatitude*hauteurPanel/100);
        //System.out.println("proportionalY : "+proportionalY);
        if(proportionalY==0){
            proportionalY=15;
        }else if(proportionalY>=hauteurPanel){
            proportionalY=hauteurPanel-35;
        }
        //System.out.println("proportionalY : "+proportionalY);
        return proportionalY;
    }
    
    
    public int getProportionalYPIs(Intersection i,ArrayList<PointInteret> PIs,ArrayList<Intersection> intersections){
        
        /*Double maxLatitude=this.maxLatitudePIs(PIs);
        Double minLatitude=this.minLatitudePIs(PIs);*/
        Double maxLatitude=this.maxLatitude(intersections);
        Double minLatitude=this.minLatitude(intersections);
        //System.out.println("ML : "+maxLatitude+" mL : "+minLatitude);
        
        Double hauteurCarte=(maxLatitude-minLatitude);
        //System.out.println("HauteurCarte : "+hauteurCarte);
        
        Double distMinLatitude=(i.getLatitude()-minLatitude);
        
        /*A quel pourcentage de latitude se trouve l'intersection par rapport à la carte*/
        Double pourcentageLatitude=((distMinLatitude*100/hauteurCarte));
        //System.out.println("PourcentageLatitude : "+pourcentageLatitude);
        
        int hauteurPanel=this.getHeight();
        //System.out.println("hauteurPanel : "+hauteurPanel);
        
        /*Reporter ce pourcentage sur le panel, Attention, le sens est inversé en Java*/
        int proportionalY=(int)(pourcentageLatitude*hauteurPanel/100);
        //System.out.println("proportionalY : "+proportionalY);
        if(proportionalY==0){
            proportionalY=15;
        }else if(proportionalY>=hauteurPanel){
            proportionalY=hauteurPanel-35;
        }
        //System.out.println("proportionalY : "+proportionalY);
        return proportionalY;
    }
    
    /*Recupère la position en Y de l'intersection sur le panel */
    public int getProportionalX(Intersection i,ArrayList<Intersection> intersections){
        
        Double maxLongitude=this.maxLongitude(intersections);
        Double minLongitude=this.minLongitude(intersections);
        //System.out.println("MLo : "+maxLongitude+" mLo : "+minLongitude);
        
        Double largeurCarte=(maxLongitude-minLongitude);
        //System.out.println("largeurCarte : "+largeurCarte);
        
        Double distMinLongitude=(i.getLongitude()-minLongitude);
        
        /*A quel pourcentage de longitude se trouve l'intersection par rapport à la carte*/
        int pourcentageLongitude=(int)(distMinLongitude*100/largeurCarte);
        //System.out.println("pourcentageLongitude : "+pourcentageLongitude);
        
        int largeurPanel=this.getWidth();
        //System.out.println("largeurPanel : "+largeurPanel);
        
        /*Reporter ce pourcentage sur le panel.*/
        int proportionalX=(int)(pourcentageLongitude*largeurPanel/100);
        if(proportionalX==0){
            proportionalX=15;
        }else if(proportionalX>=largeurPanel){
            proportionalX=largeurPanel-25;
        }
        //System.out.println("proportionalX : "+proportionalX);
        //System.out.println(proportionalX);
        return proportionalX;
    }
    
    
    public int getProportionalXPIs(Intersection i,ArrayList<PointInteret> PIs,ArrayList<Intersection> intersections){
        
        /*Double maxLongitude=this.maxLongitudePIs(PIs);
        Double minLongitude=this.minLongitudePIs(PIs);*/
        Double maxLongitude=this.maxLongitude(intersections);
        Double minLongitude=this.minLongitude(intersections);
        //System.out.println("MLo : "+maxLongitude+" mLo : "+minLongitude);
        
        Double largeurCarte=(maxLongitude-minLongitude);
        //System.out.println("largeurCarte : "+largeurCarte);
        
        Double distMinLongitude=(i.getLongitude()-minLongitude);
        
        /*A quel pourcentage de longitude se trouve l'intersection par rapport à la carte*/
        int pourcentageLongitude=(int)(distMinLongitude*100/largeurCarte);
        //System.out.println("pourcentageLongitude : "+pourcentageLongitude);
        
        int largeurPanel=this.getWidth();
        //System.out.println("largeurPanel : "+largeurPanel);
        
        /*Reporter ce pourcentage sur le panel.*/
        int proportionalX=(int)(pourcentageLongitude*largeurPanel/100);
        if(proportionalX==0){
            proportionalX=15;
        }else if(proportionalX>=largeurPanel){
            proportionalX=largeurPanel-25;
        }
        //System.out.println("proportionalX : "+proportionalX);
        //System.out.println(proportionalX);
        return proportionalX;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        ArrayList<Intersection> intersections=carte.getListeIntersections();
        
        
        //System.out.println("Taille : "+intersections.size());
        for (Intersection i : intersections) {
            
            //System.out.println("Le point " + i.getId()/*+" "+i.getLatitude()+ " "+i.getLongitude()*/);
            //System.out.println(this.getProportionalX(i)+" : "+this.getProportionalY(i));
            
            g.setColor(Color.BLACK);
            //-3 pour centrer le symbole sur le point
            g.fillOval(this.getProportionalX(i,intersections)-2,this.getProportionalY(i,intersections)-2,6,6);
            
            ArrayList<Troncon> iTroncons=i.getTronconsDepart();
            for (Troncon t : iTroncons) {
                g.setColor(Color.gray);
                g.drawLine(this.getProportionalX(i,intersections)+2, this.getProportionalY(i,intersections)+2,this.getProportionalX(t.getDestination(),intersections)+2,this.getProportionalY(t.getDestination(),intersections)+2);
                
            }
            
            
	}
        if(carte.getDemandesLivraisons()!=null)
        {
            ArrayList<PointInteret> PIs=carte.getDemandesLivraisons().getListePointsInteret();
            
            PointInteret depot=carte.getDemandesLivraisons().getAdresseDepart();
            int xDepot=this.getProportionalXPIs(depot.getIntersection(),PIs,intersections)-2;
            int yDepot=this.getProportionalYPIs(depot.getIntersection(),PIs,intersections)-2;
            int x[]={xDepot,xDepot+6,xDepot+12};
            int y[]={yDepot,yDepot+12,yDepot};
            Polygon p=new Polygon(x,y,3);
            g.setColor(Color.RED);
            g.fillPolygon(p);
            
            if(PIs!=null){
                for(PointInteret i : PIs) {
                    g.setColor(Color.BLACK);
                    System.out.println(i.isEstEnlevement());
                    Random rand = new Random();
                    float r = rand.nextFloat();
                    float gg = rand.nextFloat();
                    float b = rand.nextFloat();
                    Color randomColor = new Color(r, gg, b);
                    g.setColor(randomColor);

                    if(i.isEstEnlevement()){
                        g.fillRect(this.getProportionalXPIs(i.getIntersection(),PIs,intersections)-2,this.getProportionalYPIs(i.getIntersection(),PIs, intersections)-2,8,8);
                        g.fillOval(this.getProportionalXPIs(i.getPointDependance().getIntersection(),PIs,intersections)-2,this.getProportionalYPIs(i.getPointDependance().getIntersection(),PIs,intersections)-2,8,8);

                    }
                }       
            }
        }
        
        /*faire un autre liste avec la tournée et du coup l'avoir dans la classe carte*/
        //if(this.tournee.getDuree()!=null){
            System.out.println("ICIIII");
            ArrayList<PointInteret> PIs=this.tournee.getSuccessionPointsInteret();
            System.out.println("AAAAAAAA  "+PIs);
            //if(PIs!=null){
                //System.out.println("LAAA");
                for(PointInteret i : PIs) {
                    System.out.println("LAAA-BAAAAS");
                    ArrayList<Troncon> iTroncons=i.getCheminDepart().getSuccessionTroncons();
                    for (Troncon t : iTroncons) {
                        g.setColor(Color.RED);
                        g.drawLine(this.getProportionalX(t.getOrigine(),intersections)+2, this.getProportionalY(t.getOrigine(),intersections)+2,this.getProportionalX(t.getDestination(),intersections)+2,this.getProportionalY(t.getDestination(),intersections)+2);
                        
                    }
                }       
            //}
       // }
    }
}
