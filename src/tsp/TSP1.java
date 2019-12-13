package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class TSP1 extends TemplateTSP implements TSP {

    @Override
    protected float bound(Integer sommetCourant, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree, TreeMap<Integer,Integer> mapPredecesseur) {
        return 0;
    }

    @Override
    protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree, TreeMap<Integer,Integer> mapPredecesseur) {
        return new IteratorSeq(nonVus, sommetCrt);
    }


}
