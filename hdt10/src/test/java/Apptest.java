
import static org.junit.Assert.assertEquals;


import org.junit.Test;

import com.Grafo;


public class Apptest {
    @Test
    public void testAdd() {
        Grafo.Graph<String> graph = new Grafo.Graph<String>();
        graph.addEdge("A", "B", 1);
        assertEquals(1, graph.getWeight("A", "B"));
    }

    @Test
    public void testDelete() {
        Grafo.Graph<String> graph = new Grafo.Graph<String>();
        graph.addEdge("A", "B", 1);
        graph.deletearch("A", "B");
        assertEquals(-1, graph.getWeight("A", "B"));
    }
}
