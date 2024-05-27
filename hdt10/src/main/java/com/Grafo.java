package com;

import java.util.*;

public class Grafo {
    
    public static class Edge<T> {
        
        private T destination;
        private int weight;

        public Edge(T destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public T getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        

        
    }

    public static class Graph<T> {
        public Map<T, List<Edge<T>>> map = new HashMap<>();

        public void addVertex(T s) {
            map.put(s, new LinkedList<Edge<T>>());
        }

        public void addEdge(T source, T destination, int weight) {
            if (!map.containsKey(source)) {
                addVertex(source);
            }
            if (!map.containsKey(destination)) {
                addVertex(destination);
            }
            map.get(source).add(new Edge<T>(destination, weight));
        }

        public void deletearch(T source, T destination) {
            if (!map.containsKey(source) || !map.containsKey(destination)) {
                System.out.println("No se encontró la ciudad");
            } else {
                for (Edge<T> edge : map.get(source)) {
                    if (edge.getDestination().equals(destination)) {
                        map.get(source).remove(edge);
                        System.out.println("Ruta eliminada");
                        return;
                    }
                }
                System.out.println("No se encontró la ruta");
            }
        }

        public int getWeight(T source, T destination) {
            for (Edge<T> edge : map.get(source)) {
                if (edge.getDestination().equals(destination)) {
                    return edge.getWeight();
                }
            }
            return -1;
        }

        private String getPath(Map<T, T> parent, T source, T destination) {
            List<T> path = new ArrayList<>();
            T current = destination;
            while (current != null) {
                path.add(current);
                current = parent.get(current);
            }
            Collections.reverse(path);
            return path.toString();
        }

        public String getShortestPath(T source, T destination) {
            Map<T, Integer> distance = new HashMap<>();
            Map<T, T> parent = new HashMap<>();
            List<T> visited = new ArrayList<>();
            PriorityQueue<T> pq = new PriorityQueue<>(new Comparator<T>() {
                public int compare(T one, T two) {
                    return distance.get(one) - distance.get(two);
                }
            });

            for (T vertex : map.keySet()) {
                distance.put(vertex, Integer.MAX_VALUE);
                parent.put(vertex, null);
            }

            distance.put(source, 0);
            pq.add(source);

            while (!pq.isEmpty()) {
                T current = pq.poll();
                if (visited.contains(current)) {
                    continue;
                }
                visited.add(current);

                for (Edge<T> edge : map.get(current)) {
                    if (distance.get(current) + edge.getWeight() < distance.get(edge.getDestination())) {
                        distance.put(edge.getDestination(), distance.get(current) + edge.getWeight());
                        parent.put(edge.getDestination(), current);
                        pq.add(edge.getDestination());
                    }
                }
            }

            if (distance.get(destination) == Integer.MAX_VALUE) {
                return("No se encontró una ruta entre " + source + " y " + destination);
            } else {
                return("La ruta más corta entre " + source + " y " + destination + " es de " + distance.get(destination) + " unidades de distancia y pasa por " + getPath(parent, source, destination));

            }
        }

        public void printGraph() {
            for (T vertex : map.keySet()) {
                System.out.print("La ciudad " + vertex + " tiene rutas a: ");
                for (Edge<T> edge : map.get(vertex)) {
                    System.out.print(edge.getDestination() + "(" + edge.getWeight() + ") ");
                }
                System.out.println();
            }
        }
    }
}