/*
 @article{jgrapht,
  title={JGraphT--A Java library for graph data structures and algorithms},
  author={Michail, Dimitrios and Kinable, Joris and Naveh, Barak and Sichi, John V},
  journal={arXiv preprint arXiv:1904.08355},
  year={2019}
}
 */



package TestAlgo;


import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;
import org.jgrapht.util.*;

import java.util.*;
import java.util.function.*;
/*
 *
 * @author GRAZIA GIULIA
 */
public class TestDijrska {
    
    public static void main(String[] args)
    {
        // Create the VertexFactory so the generator can create vertices
        Supplier<String> vSupplier = new Supplier<String>()
        {
            private int id = 0;

            @Override
            public String get()
            {
                return "v" + id++;
            }
        };

        // Create the graph object
        Graph<String, DefaultEdge> completeGraph =
            new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);

        // Create the CompleteGraphGenerator object
        CompleteGraphGenerator<String, DefaultEdge> completeGenerator =
            new CompleteGraphGenerator<>(10);

        // Use the CompleteGraphGenerator object to make completeGraph a
        // complete graph with [size] number of vertices
        completeGenerator.generateGraph(completeGraph);

        // Print out the graph to be sure it's really complete
        Iterator<String> iter = new DepthFirstIterator<>(completeGraph);
        while (iter.hasNext()) {
            String vertex = iter.next();
            System.out.println(
                "Vertex " + vertex + " is connected to: "
                    + completeGraph.edgesOf(vertex).toString());
        }
    }
}


