package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *TSP 2 , implémentation du TSP avec contraintes de  precedences
 * et algorithme avec une heuristique d'optimisation inspiré des algorithmes
 * de colonies de fourmi
 *
 * Inspire du code TSP
 * inspiré de https://github.com/maaouiah/Java_Algo-Colonies-fourmis
 * 
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU, 
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */


public class TSP2 extends TSP1 implements TSP {
    
    private Integer[] meilleureSolution;
    private Double coutMeilleureSolution = 0.0;
    private Boolean tempsLimiteAtteint;
    private boolean nouvelleSolution2=false;
    private double nbPheromone=2000;
    private int i =0;
    private Double[][] matricePheromone;

    /**
     * Retourne la matrice des Pheromone
     * @return matricePheromone
     */
    public Double[][] getMatricePheromone()
    {
        return (matricePheromone);
    }
    
    /**
     * Calcule l'heuristique de la solution courante pour déterminer
     * si on doit poursuivre la recherche sur cette branche
     *
     * @param sommetCourant : sommet courant
     * @param nonVus : tableau des sommets restant a visiter
     * @param cout : cout[i][j] = duree pour aller de i a j
     * @param duree : duree[i] = duree pour visiter le sommet i 
     *  @param mapPredecesseur : contraintes de precedences entre les sommets
	 * @return
     * n une borne inferieure du cout des permutations commencant par
     * sommetCourant, contenant chaque sommet de nonVus exactement une fois et
     * terminant par le sommet 0
     */
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
    
    
     /**
     * Ajoute les phéromone à la matrice des pheromones pour le chemin courant
     * trouvé,
     * i.e. 
     *
     * @param coutVus cout du chemin courant trouvé
     * @param Vus : tableau des sommets visité
     * @param matricePheromone : matrice des pheromones contenus entre
     * les sommets du graphe
     */
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
            matricePheromone[ Vus.get(i)][Vus.get(i+1)] += delta ; 
        }
    }

    
    /**
     * Créer le tableau des phéromone à partir du nombre de 
     * pheromone en attribut
     *
     * @param nbSommets nombre de Sommets du graphe
     */
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
       
    
    /**
     * Vaporise les phéromone de matricePheromone
     * i.e. évite que le nombre de pheromone sur une arête devienne
     * trop grand
     * @param nbSommets nombre de Sommets du graphe
     */
    public void vaporisationPheromone(int nbSommets)
    {
        
        for ( int i =0 ;i<nbSommets; i++)
        {
            for (int j = 0 ;j<nbSommets;j++)
            {
                matricePheromone[i][j] = (double) (matricePheromone[i][j]-5.5);
            }
        }    
    }
    
    /**
     * Methode permettant d'itérer sur les sommets nonVus
     *en prenant en compte les prédécesseurs
     * et une variante de la probabilité des algorithmes de 
     * de colonies de fourmi
     * 
     * @param sommetCrt : sommet courant
     * @param nonVus : tableau des sommets restant a visiter
     * @param cout : cout[i][j] = duree pour aller de i a j
     * @param duree : duree[i] = duree pour visiter le sommet i
     * @param  mapPredecesseur : contraintes de precedences entre les sommets
	 * @return
     * un iterateur permettant d'iterer sur tous les sommets de nonVus dont
     * les predecesseurs ont déja été vus
     */
    @Override
    protected Iterator<Integer> iterator(Integer sommetCrt, 
            ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree, 
            TreeMap<Integer,Integer> mapPredecesseur) {
        
        return new IteratorProbaTransition(nonVus, sommetCrt, mapPredecesseur, 
                matricePheromone, cout);
    }
    
    
     /**
	 * Cherche un circuit de duree minimale passant par chaque sommet 
         * (compris entre 0 et nbSommets-1) en utilisant les contraintes de
         * precedences
	 * @param tpsLimite : limite (en millisecondes) sur le temps 
         * d'execution de chercheSolution
	 * @param nbSommets : nombre de sommets du graphe
	 * @param cout : cout[i][j] = duree pour aller de i a j
	 * @param duree : duree[i] = duree pour visiter le sommet i
         * mapPredecesseur : contraintes de precedences entre les sommets
	 */
    @Override
     public void chercheSolutionPredecesseur(Integer tpsLimite, int nbSommets, 
             Double[][] cout, Integer[] duree,
             TreeMap<Integer,Integer> mapPredecesseur) {
        tempsLimiteAtteint = false;
        coutMeilleureSolution = Double.MAX_VALUE;
        meilleureSolution = new Integer[nbSommets];
        createPheromone(nbSommets);
        ArrayList<Integer> nonVus = new ArrayList<Integer>();
        for (int i = 1; i < nbSommets; i++) {
            nonVus.add(i);
        }
        
        ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
        vus.add(0); // le premier sommet visite est 0
       
        branchAndBound(0, nonVus, vus, 0.0, cout, duree, 
                System.currentTimeMillis(), tpsLimite, mapPredecesseur);
        
         
    }
    
    
     
     
     
          /**
     * Methode definissant la résolution par separation
     * et evaluation du TSP2
     *
     * @param sommetCrt le dernier sommet visite
     * @param nonVus la liste des sommets qui n'ont pas encore ete visites
     * @param vus la liste des sommets visites (y compris sommetCrt)
     * @param coutVus la somme des couts des arcs du chemin passant par tous les
     * sommets de vus + la somme des duree des sommets de vus
     * @param cout : cout[i][j] = duree pour aller de i a j
     * @param duree : duree[i] = duree pour visiter le sommet i
     * @param tpsDebut : moment ou la resolution a commence
     * @param tpsLimite : limite de temps pour la resolution
     * @param mapPredecesseur : contraintes de precedences entre les sommets
     */
    @Override
    public void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, 
            ArrayList<Integer> vus, Double coutVus, Double[][] cout, 
            Integer[] duree, long tpsDebut, Integer tpsLimite,
            TreeMap<Integer,Integer> mapPredecesseur) {
 
        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
            tempsLimiteAtteint = true;
            return;
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
}
