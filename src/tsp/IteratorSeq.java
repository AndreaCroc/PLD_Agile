package tsp;

import java.util.Collection;
import java.util.Iterator;

public class IteratorSeq implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l ensemble des sommets de nonVus
	 * @param nonVus
	 * @param sommetCrt
	 */
	public IteratorSeq(Collection<Integer> nonVus, int sommetCrt){
		this.candidats = new Integer[nonVus.size()];
		nbCandidats = 0;
		for (Integer s : nonVus){
			candidats[nbCandidats++] = s;
		}
	}
	
        /**
	 * Renvoie si il y a un élément suivant dans l itérateur
	 * @return 
         * un booléen pour savoir s il y un élément suivant dans l'itérateur
	 */
	@Override
	public boolean hasNext() {
		return nbCandidats > 0;
	}

        /**
	 * Renvoie élément suivant dans itérateur
	 * @return 
         * élément suivant dans l'itérateur
	 */
	@Override
	public Integer next() {
		return candidats[--nbCandidats];
	}

	@Override
	public void remove() {}

}
