package tsp;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class IteratorSeqPredecesseur implements Iterator<Integer> {

	private Integer[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
         * dont les predecesseurs ne sont pas dans nonVus
	 * @param nonVus
	 * @param sommetCrt
         * @param mapPredecesseur : contraintes de precedences entre les sommets
	 */
	public IteratorSeqPredecesseur(Collection<Integer> nonVus, int sommetCrt, TreeMap<Integer, Integer> mapPredecesseur){
		Integer[] candidatsBis = new Integer[nonVus.size()];
		nbCandidats = 0;
                int num = 0;
		for (Integer s : nonVus){
                        Boolean estPresent=mapPredecesseur.containsKey(s);
                        if (estPresent)
                        {
                            Integer predecesseur = mapPredecesseur.get(s);

                            if (!nonVus.contains(predecesseur))
                            {
                                candidatsBis[num++] = s;
                            }
                        }
                        else
                        {
                             candidatsBis[num++] = s;
                        }
		}

                
                this.candidats = new Integer[num];
                for (int j = 0; j < num; j++)
                {
			candidats[nbCandidats++] = candidatsBis[j];
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
