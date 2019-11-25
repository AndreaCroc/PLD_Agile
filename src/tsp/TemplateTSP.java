package tsp;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TemplateTSP implements TSP {

    private Integer[] meilleureSolution;
    private Double coutMeilleureSolution = 0.0;
    private Boolean tempsLimiteAtteint;

    public Boolean getTempsLimiteAtteint() {
        return tempsLimiteAtteint;
    }

    public void chercheSolution(Integer tpsLimite, int nbSommets, Double[][] cout, Integer[] duree) {
        System.out.println("cherche solution");
        tempsLimiteAtteint = false;
        coutMeilleureSolution = Double.MAX_VALUE;
        meilleureSolution = new Integer[nbSommets];
        ArrayList<Integer> nonVus = new ArrayList<Integer>();
        for (int i = 1; i < nbSommets; i++) {
            System.out.println("ajout elt dans nonVus"+i);
            nonVus.add(i);
        }
        ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
        vus.add(0); // le premier sommet visite est 0
        branchAndBound(0, nonVus, vus, 0.0, cout, duree, System.currentTimeMillis(), tpsLimite);
    }

    public Integer getMeilleureSolution(int i) {
        if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length)) {
            return null;
        }
        return meilleureSolution[i];
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
     * nbSommets
	 * @retur
     * n une borne inferieure du cout des permutations commencant par
     * sommetCourant, contenant chaque sommet de nonVus exactement une fois et
     * terminant par le sommet 0
     */
    protected abstract int bound(Integer sommetCourant, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree);

    /**
     * Methode devant etre redefinie par les sous-classes de TemplateTSP
     *
     * @param sommetCrt
     * @param nonVus : tableau des sommets restant a visiter
     * @param cout : cout[i][j] = duree pour aller de i a j, avec 0 <= i <
     * nbSommets et 0 <= j < nbSommets @param
     * duree : duree[i] = duree pour visiter le sommet i, avec 0 <= i <
     * nbSommets
	 * @return
     * un iterateur permettant d'iterer sur tous les sommets de nonVus
     */
    protected abstract Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree);

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
     */
    void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, Double coutVus, Double[][] cout, Integer[] duree, long tpsDebut, Integer tpsLimite) {
        System.out.println("branch and bound");
        if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
            tempsLimiteAtteint = true;
            return;
        }
        if (nonVus.size() == 0) { // tous les sommets ont ete visites
            coutVus += cout[sommetCrt][0];
            if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
                vus.toArray(meilleureSolution);
                coutMeilleureSolution = coutVus;
            }
        } else if (coutVus + bound(sommetCrt, nonVus, cout, duree) < coutMeilleureSolution) {
            Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, duree);
            while (it.hasNext()) {
                System.out.println("boucle while");
                Integer prochainSommet = it.next();
                vus.add(prochainSommet);
                System.out.println(" prochain sommet : " + prochainSommet);
                nonVus.remove(prochainSommet);
              
                System.out.println(cout[sommetCrt][prochainSommet]);
                System.out.println(duree[prochainSommet]);
                branchAndBound(prochainSommet, nonVus, vus, coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet], cout, duree, tpsDebut, tpsLimite);
                vus.remove(prochainSommet);
                nonVus.add(prochainSommet);
            }
        }
    }
}
