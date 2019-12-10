package tsp;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class IteratorSeq3 implements Iterator<Integer> {

    
    
	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
	 * @param nonVus
	 * @param sommetCrt
	 */
	public IteratorSeq3(Collection<Integer> nonVus, int sommetCrt, TreeMap<Integer, Integer> mapPredecesseur){
		Integer[] candidatsBis = new Integer[nonVus.size()];
		nbCandidats = 0;
                int num = 0;
		for (Integer s : nonVus){
                        Integer predecesseur=mapPredecesseur.get(s);
                        if (predecesseur!=null && !nonVus.contains(predecesseur))
                        {

			candidatsBis[num++] = s;
                        }
		}

                
                this.candidats = new Integer[num];
                //System.out.println(num);
                for (int j = 0; j < num; j++)
                {
			candidats[nbCandidats++] = candidatsBis[j];
                        
                }
                
	}
        
        private boolean  containsDouble(Collection<Integer> nonVus, double predecesseurs)
        {
            int intPredecesseurs = (int) Math.floor(predecesseurs);
            boolean appartient = false;
            for (Integer PI : nonVus)
            {
                int pis= (int) PI;
               
                if (intPredecesseurs==pis)
                {
                    appartient=true;
                }
            }
            
            return(appartient);
        }
	
	@Override
	public boolean hasNext() {
		return nbCandidats > 0;
	}

	@Override
	public Integer next() {
		return candidats[--nbCandidats];
	}

	@Override
	public void remove() {}

}
