package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class TSP2 extends TSP1 implements TSP {
    
    private Integer[] meilleureSolution;
    private Double coutMeilleureSolution = 0.0;
    private Boolean tempsLimiteAtteint;
    private boolean nouvelleSolution2=false;

    @Override
    protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree,TreeMap<Integer,Integer> mapPredecesseur ) {
        return 0;
    }

    protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree, TreeMap<Integer,Integer> mapPredecesseur) {
        
        return new IteratorSeq2(nonVus, sommetCrt, mapPredecesseur);
    }
    
    @Override
    public boolean getNouvelleSolution(){
        return nouvelleSolution2;
    }
    
    @Override
    public void setNouvelleSolution(boolean b){
        nouvelleSolution2=b;
    }
    
     @Override
     public void chercheSolutionPredecesseur(Integer tpsLimite, int nbSommets, Double[][] cout, Integer[] duree,TreeMap<Integer,Integer> mapPredecesseur) {
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
 
        branchAndBound(0, nonVus, vus, 0.0, cout, duree, System.currentTimeMillis(), tpsLimite, mapPredecesseur);
        
//        System.out.print("Meilleure Solution : [");
//        for (int i = 0; i < meilleureSolution.length; i++) {
//            System.out.print(meilleureSolution[i]+", ");
//        }
//        System.out.print("]");
            //System.out.println("SOLUTION "+meilleureSolution.toString());
            //System.out.println("STOP1 "+stop);
    }
    
    @Override
    public void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, Double coutVus, Double[][] cout, Integer[] duree, long tpsDebut, Integer tpsLimite,TreeMap<Integer,Integer> mapPredecesseur) {

        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
            tempsLimiteAtteint = true;
            //return;
        }
        if (nonVus.size() == 0) { // tous les sommets ont ete visites
            coutVus += cout[sommetCrt][0];
            if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
                
                //System.out.println("TemplateTSP ligne 101, nouvelle meilleure solution  : "+vus.toString());
                vus.toArray(meilleureSolution);
                coutMeilleureSolution = coutVus;
                setMeilleureSolution(meilleureSolution);
            }
        } else if (coutVus + bound(sommetCrt, nonVus, cout, duree, mapPredecesseur) < coutMeilleureSolution) {
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
