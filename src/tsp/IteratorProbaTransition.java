package tsp;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class IteratorProbaTransition implements Iterator<Integer> {

    
    
	private Integer[] candidats;
	private int nbCandidats;
        private double alpha=0.5;
        private double beta =0.5;
        private Double[] probaCandidats;
        private double[][] cout;
        
	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
         * ainsi que leur probabilité selon une variante de l'algorithme 
         * de colonie des fourmis
         * 
	 * @param nonVus
	 * @param sommetCrt
	 */
	public IteratorProbaTransition(Collection<Integer> nonVus, 
            int sommetCrt, TreeMap<Integer, Integer> mapPredecesseur,
            Double[][] matricePheromone,Double[][] cout ){
            Integer[] candidatsBis = new Integer[nonVus.size()];
            nbCandidats = 0;
            int num = 0;
            Integer candidatCrt;
            Double coutCrt;
            double somme;


            for (Integer s : nonVus){
                    Boolean estPresent = mapPredecesseur.containsKey(s);
                    if (estPresent)
                        {
                        Integer predecesseur=mapPredecesseur.get(s);
                        if (predecesseur!=null && !nonVus.contains(predecesseur))
                        {
                        candidatsBis[num++] = s;
                        }
                    }
                    else{
                        candidatsBis[num++] = s;
                    }
            }


            this.candidats = new Integer[num];
            this.probaCandidats = new Double[num];

            for (int j = 0; j < num; j++)
            {
                somme=0.1; // La somme ne doit jamais être égale à 0
                candidatCrt = candidatsBis[j];
                coutCrt=cout[sommetCrt][candidatCrt];
                candidats[nbCandidats++] = candidatsBis[j];
                double res = Math.pow(matricePheromone[sommetCrt][candidatCrt],
                        alpha)*Math.pow((1/coutCrt),beta);
                for (int i = 0; i < num; i++) {
                    if (i!=j){
                        somme+=Math.pow(matricePheromone[sommetCrt][candidatsBis[i]],
                                alpha)*Math.pow((1/cout[sommetCrt][candidatsBis[i]]),beta);

                    }
                }
                probaCandidats[nbCandidats-1]=res/somme;
            }
                
	}
        
	
	@Override
	public boolean hasNext() {
		return nbCandidats > 0;
	}

	@Override
	public Integer next() {
            Integer pointPMax=0;
            Double maxProba=probaCandidats[0];
            
            for(int i = 1; i <nbCandidats; i++) {
                    if(maxProba < probaCandidats[i]) {
                                pointPMax =i;
				maxProba = probaCandidats[i];
			}
		}    
            Integer tmp = candidats[pointPMax];
            Double tmp2 = probaCandidats[pointPMax];
            candidats[pointPMax]=candidats[--nbCandidats];
            probaCandidats[pointPMax]= probaCandidats[nbCandidats];
           
	return tmp;
	}

	@Override
	public void remove() {}

}
