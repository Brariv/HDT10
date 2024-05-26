package com;

public class Floyd {
    

    public String findCenter(Grafo.Graph<String> graph) {
        // Floyd's algorithm to find the center of the graph

        int[][] dist = new int[graph.map.size()][graph.map.size()];
        int[][] next = new int[graph.map.size()][graph.map.size()];
        for (int i = 0; i < graph.map.size(); i++) {
            for (int j = 0; j < graph.map.size(); j++) {
                dist[i][j] = Integer.MAX_VALUE;
                next[i][j] = -1;
            }
        }

        for (int i = 0; i < graph.map.size(); i++) {
            dist[i][i] = 0;
        }

        for (String vertex : graph.map.keySet()) {
            for (Grafo.Edge<String> edge : graph.map.get(vertex)) {
                dist[graph.map.keySet().toArray(new String[0]).toString().indexOf(vertex)][graph.map.keySet().toArray(new String[0]).toString().indexOf(edge.getDestination())] = edge.getWeight();
                next[graph.map.keySet().toArray(new String[0]).toString().indexOf(vertex)][graph.map.keySet().toArray(new String[0]).toString().indexOf(edge.getDestination())] = graph.map.keySet().toArray(new String[0]).toString().indexOf(edge.getDestination());
            }
        }

        for (int k = 0; k < graph.map.size(); k++) {
            for (int i = 0; i < graph.map.size(); i++) {
                for (int j = 0; j < graph.map.size(); j++) {
                    if (dist[i][k] < Integer.MAX_VALUE && dist[k][j] < Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int center = -1;
        for (int i = 0; i < graph.map.size(); i++) {
            int max = 0;
            for (int j = 0; j < graph.map.size(); j++) {
                if (dist[i][j] > max) {
                    max = dist[i][j];
                }
            }
            if (max < min) {
                min = max;
                center = i;
            }
        }

        return graph.map.keySet().toArray(new String[0])[center];
        



        
        
        

    }
}
