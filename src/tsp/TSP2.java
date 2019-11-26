package tsp;

import java.util.ArrayList;
import java.util.Iterator;

public class TSP2 extends TSP1 implements TSP {

    @Override
    protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree) {
        return 0;
    }

    @Override
    protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, Double[][] cout, Integer[] duree) {
        
        return new IteratorSeq(nonVus, sommetCrt);
    }


}
