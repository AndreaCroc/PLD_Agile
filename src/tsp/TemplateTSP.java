package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;


/**
 *Template TSP
 *
 * code Source Template TSP
 * (Ajout de parametres)
 * 
 * @version Version 1
 *
 *
 */

public abstract class TemplateTSP implements TSP {

    private Integer[] meilleureSolution;
    private Double coutMeilleureSolution = 0.0;
    private Boolean tempsLimiteAtteint;
    
    public Boolean getTempsLimiteAtteint() {
        return tempsLimiteAtteint;
    }
    
    
    public void chercheSolution(Integer tpsLimite, int nbSommets, 
            Double[][] cout, Integer[] duree)
    {};
    
    
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
                    System.currentTimeMillis(), tpsLimite,mapPredecesseur);
        System.out.print("Meilleure Solution : [");
        for (int i = 0; i < meilleureSolution.length; i++) {
            System.out.print(meilleureSolution[i]+", ");
        }
        System.out.print("]");

    }

    public Integer getMeilleureSolution(int i) {
        if ((meilleureSolution == null) || (i < 0) || 
                (i >= meilleureSolution.length)) {
            return null;
        }
        return meilleureSolution[i];
    }
    
    protected void setMeilleureSolution(Integer [] solution){
        meilleureSolution=solution;
    }
    
    public Integer[] getSolution(){
        return meilleureSolution;
    }
    
    public Double getCoutMeilleureSolution() {
        return coutMeilleureSolution;
    }

    /**
     * Methode devant etre redefinie par les sous-classes de TemplateTSP
     *
     * @param sommetCourant
     * @param nonVus : tableau des sommets restant a visiter
     * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i <
     * nbSommets et 0 <= j < nbSommets @param
     * duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i <
     *  mapPredecesseur : contraintes de precedences entre les sommets
	 * @retur
     * n une borne inferieure du cout des permutations commencant par
     * sommetCourant, contenant chaque sommet de nonVus exactement une fois et
     * terminant par le sommet 0
     */
    protected abstract float bound(Integer sommetCourant, 
            ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree,
            TreeMap<Integer,Integer> mapPredecesseur);

    /**
     * Methode devant etre redefinie par les sous-classes de TemplateTSP
     *
     * @param sommetCrt
     * @param nonVus : tableau des sommets restant a visiter
     * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i <
     * nbSommets et 0 <= j < nbSommets @param
     * duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i <
     * nbSommets
     *  mapPredecesseur : contraintes de precedences entre les sommets
	 * @return
     * un iterateur permettant d'iterer sur tous les sommets de nonVus
     */
    protected abstract Iterator<Integer> iterator(Integer sommetCrt, 
            ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree,
            TreeMap<Integer,Integer> mapPredecesseur);

    /**
     * Methode definissant le patron (template) d'une resolution par separation
     * et evaluation (branch and bound) du TSP
     *
     * @param sommetCrt le dernier sommet visite
     * @param nonVus la liste des sommets qui n'ont pas encore ete visites
     * @param vus la liste des sommets visites (y compris sommetCrt)
     * @param coutVus la somme des couts des arcs du chemin passant par tous les
     * sommets de vus + la somme des duree des sommets de vus
     * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i <
     * nbSommets et 0 <= j < nbSommets @param
     * duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i <
     * nbSommets @param
     * tpsDebut : moment ou la resolution a commence
     * @param tpsLimite : limite de temps pour la resolution
     * mapPredecesseur : contraintes de precedences entre les sommets
     */
    public void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus,
            ArrayList<Integer> vus, Double coutVus, Double[][] cout, 
            Integer[] duree, long tpsDebut, Integer tpsLimite, 
            TreeMap<Integer,Integer> mapPredecesseur) {
        
            if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
                tempsLimiteAtteint = true;
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
                Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, 
                        duree, mapPredecesseur);
                while (it.hasNext()) {
                    Integer prochainSommet = it.next();
                    vus.add(prochainSommet);
                    nonVus.remove(prochainSommet);
                    branchAndBound(prochainSommet, nonVus, vus, coutVus + 
                            cout[sommetCrt][prochainSommet] + 
                            duree[prochainSommet], cout, duree, 
                            tpsDebut, tpsLimite, mapPredecesseur);
                    vus.remove(prochainSommet);
                    nonVus.add(prochainSommet);
                }  
        }
    }
}
