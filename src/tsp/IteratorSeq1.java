package tsp;

import java.util.Collection;
import java.util.Iterator;

public class IteratorSeq1 implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
	 * @param nonVus
	 * @param sommetCrt
	 */
	public IteratorSeq1(Collection<Integer> nonVus, int sommetCrt, Double[][] cout){
		Integer[] Randidats = new Integer[nonVus.size()];
		nbCandidats = 0;
                int num = 0;
		for (Integer s : nonVus){
                        if (cout[s][s]!=0.0 && nonVus.contains(Math.floor(cout[s][s])) )
                        {
			Randidats[num++] = s;
                        }
		}
                this.candidats = new Integer[num];
                for (Integer s : Randidats){
                        
			candidats[nbCandidats++] = s;
                        
		}
                
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
