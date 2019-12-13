package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

// inspiré de https://github.com/maaouiah/Java_Algo-Colonies-fourmis

public class TSP2 extends TSP1 implements TSP {
    
    private Integer[] meilleureSolution;
    private Double coutMeilleureSolution = 0.0;
    private Boolean tempsLimiteAtteint;
    private boolean nouvelleSolution2=false;
    private double nbFourmi=10.0;
    private double nbPheromone=2000;
    private boolean arretDemande=false;
    private int i =0;
    private Double[][] matricePheromone;

    
    public Double[][] getMatricePheromone()
    {
        return (matricePheromone);
    }
    
    @Override
    protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree, TreeMap<Integer,Integer> mapPredecesseur) {

        double sommePheromone = 0;
        for (int i = 0;i<nonVus.size();i++)
        {
            if (sommetCourant != nonVus.get(i) )   
            {
                
                sommePheromone += matricePheromone [sommetCourant][nonVus.get(i)];
            }
        }
        float foundValueRandom = (float) (Math.random()* (sommePheromone * 1000))/(1000);
        return foundValueRandom;
    }
    
    public void AjoutPheromone(ArrayList<Integer> Vus, Double[][] matricePheromone, Double coutVus)
    {
        Double delta=0.0;
        
        if (coutVus==coutMeilleureSolution)
        {
            delta=10.0;
        }else {
            if (coutVus!=0) {
                delta = (coutMeilleureSolution/coutVus)%4;
               
            }
        }
        
        
        for ( int i = 0 ;
        i < Vus.size() - 1 ;
        i++)
        {
            matricePheromone[ Vus.get(i)][Vus.get(i+1)] += delta ; // + rajouter rest
        }
    }

    public void createPheromone(int nbSommets)
    {
        
        matricePheromone = new Double[nbSommets][nbSommets];
        //Creation de la tournée
        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                matricePheromone[i][j] = nbPheromone;
            }
        }
        

    }
        
    public void vaporisationPheromone(int nbSommets)
    {
        
        for ( int i =0 ;i<nbSommets; i++)
        {
            for (int j = 0 ;j<nbSommets;j++)
            {
                matricePheromone[i][j] = (double) (matricePheromone[i][j] - 5.5);//nbPheromone/nbFourmi)) ;
            }
        }    
    }
    
    @Override
    protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree, TreeMap<Integer,Integer> mapPredecesseur) {
        
        return new IteratorProbaTransition(nonVus, sommetCrt, mapPredecesseur, matricePheromone, cout);
    }
    
    
    public void arretDemande()
    {
        arretDemande=true;
    }
    
    @Override
     public void chercheSolutionPredecesseur(Integer tpsLimite, int nbSommets, Double[][] cout, Integer[] duree,TreeMap<Integer,Integer> mapPredecesseur) {
        System.out.println("cherche solution");
        tempsLimiteAtteint = false;
        coutMeilleureSolution = Double.MAX_VALUE;
        meilleureSolution = new Integer[nbSommets];
        createPheromone(nbSommets);
        ArrayList<Integer> nonVus = new ArrayList<Integer>();
        for (int i = 1; i < nbSommets; i++) {
            //System.out.println("ajout elt dans nonVus"+i);
            nonVus.add(i);
        }
        
        ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
        vus.add(0); // le premier sommet visite est 0
        //while(meilleureSolution)
       
        branchAndBound(0, nonVus, vus, 0.0, cout, duree, System.currentTimeMillis(), tpsLimite, mapPredecesseur);
        
        System.out.print("Meilleure Solution : [");
        for (int i = 0; i < meilleureSolution.length; i++) {
            System.out.print(meilleureSolution[i]+", ");
        }
        System.out.print("]");
        System.out.print("coutmeilleure sol [");
        System.out.print(coutMeilleureSolution);
        System.out.println("");
        
        if(nbSommets >9)
        {
            System.out.println(matricePheromone[0][9]);
        }
            //System.out.println("SOLUTION "+meilleureSolution.toString());
            //System.out.println("STOP1 "+stop);
    }
    
    
     
     
     
     
    @Override
    public void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, Double coutVus, Double[][] cout, Integer[] duree, long tpsDebut, Integer tpsLimite,TreeMap<Integer,Integer> mapPredecesseur) {
 
        //System.out.println(nonVus);
        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
            tempsLimiteAtteint = true;
            System.out.print("timeoutiii");
            //return;
        }
        else 
        {
            if (nonVus.size() == 0) { // tous les sommets ont ete visites
            coutVus += cout[sommetCrt][0];
            AjoutPheromone(vus,matricePheromone, coutVus);
            if (i++%3==0){
                vaporisationPheromone(vus.size());
            }
            if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
                AjoutPheromone(vus,matricePheromone, coutVus);
                System.out.println("meilleure sol" + coutMeilleureSolution.toString());
                //System.out.println("TemplateTSP ligne 101, nouvelle meilleure solution  : "+vus.toString());
                vus.toArray(meilleureSolution);
                coutMeilleureSolution = coutVus;
                setMeilleureSolution(meilleureSolution);
                
                
            }
        } else if (coutVus + bound(sommetCrt, nonVus, cout, duree,mapPredecesseur) < coutMeilleureSolution) {
            //System.out.println("Dans le else : ");
            Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, duree, mapPredecesseur);
            while (it.hasNext()) {
                Integer prochainSommet = it.next();
                vus.add(prochainSommet);
                nonVus.remove(prochainSommet);
                branchAndBound(prochainSommet, nonVus, vus, coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet], cout, duree, tpsDebut, tpsLimite, mapPredecesseur);
                vus.remove(prochainSommet);
                nonVus.add(prochainSommet);
            }
        }
        }
        
    }
}
