package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class TemplateTSP implements TSP {

    private Integer[] meilleureSolution;
    private Double coutMeilleureSolution = 0.0;
    private Boolean tempsLimiteAtteint;
    public boolean nouvelleSolution=false;
    private boolean stop=false;
    
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
            //System.out.println("ajout elt dans nonVus"+i);
            nonVus.add(i);
        }
        ArrayList<Integer> vus = new ArrayList<Integer>(nbSommets);
        vus.add(0); // le premier sommet visite est 0
        //while(meilleureSolution)
        while(!nouvelleSolution){
            branchAndBound(0, nonVus, vus, 0.0, cout, duree, System.currentTimeMillis(), tpsLimite);
        }
        System.out.print("Meilleure Solution : [");
        for (int i = 0; i < meilleureSolution.length; i++) {
            System.out.print(meilleureSolution[i]+", ");
        }
        System.out.print("]");
            //System.out.println("SOLUTION "+meilleureSolution.toString());
            //System.out.println("STOP1 "+stop);
    }
    public void setStop(boolean s){
        stop=s;
    }
    public Integer getMeilleureSolution(int i) {
        if ((meilleureSolution == null) || (i < 0) || (i >= meilleureSolution.length)) {
            return null;
        }
        return meilleureSolution[i];
    }
    
    public void setMeilleureSolution(Integer [] solution){
        meilleureSolution=solution;
    }
    
    public Integer[] getSolution(){
        return meilleureSolution;
    }
    
    public boolean getStop(){
        return stop;
    }
    
    public boolean getNouvelleSolution(){
        return nouvelleSolution;
    }
    
    public void setNouvelleSolution(boolean b){
        nouvelleSolution=b;
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
    public void branchAndBound(int sommetCrt, ArrayList<Integer> nonVus, ArrayList<Integer> vus, Double coutVus, Double[][] cout, Integer[] duree, long tpsDebut, Integer tpsLimite) {
        
        //while(stop==false){
            if (System.currentTimeMillis() - tpsDebut > tpsLimite) {
                tempsLimiteAtteint = true;
                //return meilleureSolution;
            }
            if (nonVus.size() == 0) { // tous les sommets ont ete visites
                coutVus += cout[sommetCrt][0];
               // System.out.println("TemplateTSP ligne 96");
                if (coutVus < coutMeilleureSolution) { // on a trouve une solution meilleure que meilleureSolution
                    vus.toArray(meilleureSolution);
                    coutMeilleureSolution = coutVus;
                    //System.out.println("TemplateTSP ligne 100, nouvelle meilleure solution de cout : "+coutMeilleureSolution);
                    System.out.println("TemplateTSP ligne 101, nouvelle meilleure solution  : "+vus.toString());
                    nouvelleSolution=true;
                    
                    setMeilleureSolution(meilleureSolution);
                    //return meilleureSolution;
                }
   
            } else if (coutVus + bound(sommetCrt, nonVus, cout, duree) < coutMeilleureSolution) {
                Iterator<Integer> it = iterator(sommetCrt, nonVus, cout, duree);
                while (it.hasNext()) {
                    Integer prochainSommet = it.next();
                    vus.add(prochainSommet);
                    nonVus.remove(prochainSommet);
                    branchAndBound(prochainSommet, nonVus, vus, coutVus + cout[sommetCrt][prochainSommet] + duree[prochainSommet], cout, duree, tpsDebut, tpsLimite);
                    vus.remove(prochainSommet);
                    nonVus.add(prochainSommet);
                }
                //nouvelleSolution=true;
                    
                //setMeilleureSolution(meilleureSolution);
                //return meilleureSolution;
                //System.out.println("TemplateTSP ligne 112, cout : "+coutMeilleureSolution);
                //System.out.println("iciiiii");
                ///stop=true; 
            //}
            
        }
            //if(vus.size()==meilleureSolution.length && nouvelleSolution==true )System.out.println("      "+vus.toString());
            stop=true;
            //return meilleureSolution;   
    }
}
