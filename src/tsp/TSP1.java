package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;


/**
 *TSP 1 , implémentation du TSP avec contraintes de precedences.
 *
 * Inspire du code TSP
 * 
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */



public class TSP1 extends TemplateTSP implements TSP {
    
    private Integer[] meilleureSolution;
    private Double coutMeilleureSolution = 0.0;
    private Boolean tempsLimiteAtteint;
    private boolean nouvelleSolution2=false;

    
    /**
     * 
     *
     * @param sommetCourant
     * @param nonVus : tableau des sommets restant a visiter
     * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i <
     * nbSommets et 0 <= j < nbSommets @param
     * duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i <
     * nbSommets
	 * @retur
     * n une borne inferieure du cout des permutations commencant par
     * sommetCourant, contenant chaque sommet de nonVus exactement une fois et
     * terminant par le sommet 0
     */
    @Override
    protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, 
            Double[][] cout, Integer[] duree,
            TreeMap<Integer,Integer> mapPredecesseur ) {
        return 0;
    }

    protected Iterator<Integer> iterator(Integer sommetCrt,
            ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree, 
            TreeMap<Integer,Integer> mapPredecesseur) {
        
        return new IteratorSeqPredecesseur(nonVus, sommetCrt, mapPredecesseur);
    }
    
    /**
	 * Cherche un circuit de duree minimale passant par chaque sommet 
         * (compris entre 0 et nbSommets-1) en utilisant les contraintes de
         * precedences
	 * @param tpsLimite : limite (en millisecondes) sur le temps 
         * d'execution de chercheSolution
	 * @param nbSommets : nombre de sommets du graphe
	 * @param cout : cout[i][j] = duree pour aller de i a j, 
         * avec 0 <= i < nbSommets et 0 <= j < nbSommets
	 * @param duree : duree[i] = duree pour visiter le sommet i, 
         * avec 0 <= i < nbSommets
	 */
     @Override
     public void chercheSolutionPredecesseur(Integer tpsLimite, int nbSommets, 
             Double[][] cout, Integer[] duree,
             TreeMap<Integer,Integer> mapPredecesseur) {
        System.out.println("cherche solution");
        tempsLimiteAtteint = false;
        coutMeilleureSolution = Double.MAX_VALUE;
        meilleureSolution = new Integer[nbSommets];
        ArrayList<Integer> nonVus = new ArrayList<Integer>();
        for (int i = 1; i < nbSommets; i++) {
            nonVus.add(i);
        }
        ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
        vus.add(0); // le premier sommet visite est 0
 
        branchAndBound(0, nonVus, vus, 0.0, cout, duree, 
                System.currentTimeMillis(), tpsLimite, mapPredecesseur);
        
        System.out.print("Meilleure Solution : [");
        for (int i = 0; i < meilleureSolution.length; i++) {
            System.out.print(meilleureSolution[i]+", ");
        }
        System.out.print("]");
    }
    
    @Override
    public void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, 
            ArrayList<Integer> vus, Double coutVus, Double[][] cout, 
            Integer[] duree, long tpsDebut, Integer tpsLimite,
            TreeMap<Integer,Integer> mapPredecesseur) {

        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
            tempsLimiteAtteint = true;
            //return;
        }
        if (nonVus.size() == 0) { // tous les sommets ont ete visites
            coutVus += cout[sommetCrt][0];
            if (coutVus < coutMeilleureSolution) { 
            // on a trouve une solution meilleure que meilleureSolution
                vus.toArray(meilleureSolution);
                coutMeilleureSolution = coutVus;
                setMeilleureSolution(meilleureSolution);
            }
        } else if (coutVus + bound(sommetCrt, nonVus, cout, duree, 
                mapPredecesseur) < coutMeilleureSolution) {
            Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, duree,
                    mapPredecesseur);
            while (it.hasNext()) {
                Integer prochainSommet = it.next();
                vus.add(prochainSommet);
                nonVus.remove(prochainSommet);
                branchAndBound(prochainSommet, nonVus, vus, coutVus + 
                        cout[sommetCrt][prochainSommet] + duree[prochainSommet],
                        cout, duree, tpsDebut, tpsLimite, mapPredecesseur);
                vus.remove(prochainSommet);
                nonVus.add(prochainSommet);
            }
        }
    }
}
