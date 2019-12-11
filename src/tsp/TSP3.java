package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

// inspiré de https://github.com/maaouiah/Java_Algo-Colonies-fourmis

public class TSP3 extends TSP2 implements TSP {
    
    private Integer[] meilleureSolution;
    private Double coutMeilleureSolution = 0.0;
    private Boolean tempsLimiteAtteint;
    private boolean nouvelleSolution2=false;
    private int nbFourmi=10;


    protected float bound3(Integer sommetCourant, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree,Double[][] matricePheromone) {
//       
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

    protected Iterator<Integer> iterator2(Integer sommetCrt, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree, TreeMap<Integer,Integer> mapPredecesseur) {
        
        return new IteratorSeq2(nonVus, sommetCrt, mapPredecesseur);
    }
    
 
    
 
     public void chercheSolution3(Integer tpsLimite, int nbSommets, Double[][] cout, Integer[] duree,TreeMap<Integer,Integer> mapPredecesseur , Double[][] matricePheromone) {
        System.out.println("cherche solution");
        tempsLimiteAtteint = false;
        coutMeilleureSolution = Double.MAX_VALUE;
        meilleureSolution = new Integer[nbSommets];
        ArrayList<Integer> nonVus = new ArrayList<Integer>();
        for (int i = 1; i < nbSommets; i++) {
            //System.out.println("ajout elt dans nonVus"+i);
            nonVus.add(i);
        }
        ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
        vus.add(0); // le premier sommet visite est 0
        //while(meilleureSolution)
        branchAndBound3(0, nonVus, vus, 0.0, cout, duree, System.currentTimeMillis(), tpsLimite, mapPredecesseur, matricePheromone);
        System.out.print("Meilleure Solution : [");
        for (int i = 0; i < meilleureSolution.length; i++) {
            System.out.print(meilleureSolution[i]+", ");
        }
        System.out.print("]");
            //System.out.println("SOLUTION "+meilleureSolution.toString());
            //System.out.println("STOP1 "+stop);
    }
    
    
    void branchAndBound3(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, Double coutVus, Double[][] cout, Integer[] duree, long tpsDebut, Integer tpsLimite,TreeMap<Integer,Integer> mapPredecesseur, Double[][] matricePheromone) {
 
        //System.out.println(nonVus);
        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
            tempsLimiteAtteint = true;
            //return;
        }
        if (nonVus.size() == 0) { // tous les sommets ont ete visites
            coutVus += cout[sommetCrt][0];
            if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
                
                System.out.println("TemplateTSP ligne 101, nouvelle meilleure solution  : "+vus.toString());
                vus.toArray(meilleureSolution);
                coutMeilleureSolution = coutVus;
                setMeilleureSolution(meilleureSolution);
            }
        } else if (coutVus + bound3(sommetCrt, nonVus, cout, duree,matricePheromone) < coutMeilleureSolution) {
            Iterator<Integer> it = iterator2(sommetCrt, nonVus, cout, duree, mapPredecesseur);
            while (it.hasNext()) {
                Integer prochainSommet = it.next();
                vus.add(prochainSommet);
                nonVus.remove(prochainSommet);
                branchAndBound3(prochainSommet, nonVus, vus, coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet], cout, duree, tpsDebut, tpsLimite, mapPredecesseur, matricePheromone);
                vus.remove(prochainSommet);
                nonVus.add(prochainSommet);
            }
        }
    }
}
