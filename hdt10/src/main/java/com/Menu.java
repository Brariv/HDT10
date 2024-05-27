package com;


import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    

    public void menu() {

        Scanner sc = new Scanner(System.in);
        Grafo.Graph<String> graph = new Grafo.Graph<String>();


        System.out.println("Bienvenido al programa de rutas");
        System.out.println("Ingrese el nombre del path del archivo de texto:");
        String path = sc.nextLine();
        
        System.out.println("El path es: " + path);

        ArrayList<ArrayList<String>> cities = new ArrayList<ArrayList<String>>();
        cities = new Txtreader().readTxt(path);


        for (ArrayList<String> arrayList : cities) {
            graph.addEdge(arrayList.get(0), arrayList.get(1), Integer.parseInt(arrayList.get(2)));
        }

        String opcion = "";
    
        while (opcion != "4") {
            
            //print graph
            System.out.println("Grafo:");
            graph.printGraph();

            System.out.println("");
            System.out.println("");
            System.out.println("Menú:");
        
            System.out.println("1. Buscar ruta más corta");
            System.out.println("2. Mostrar centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");

            
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Ingrese la ciudad de origen:");
                    String origen = sc.nextLine();
                    System.out.println("Ingrese la ciudad de destino:");
                    String destino = sc.nextLine();
                    System.out.println(graph.getShortestPath(origen, destino));

                    break;
                case "2":
                    //Usar metodo de Floyd para encontrar el centro del grafo
                    //Imprimir centro del grafo
                    System.out.println(new Floyd().findCenter(graph));
                    
                    break;
                case "3":
                    System.out.println("1. Agregar ruta");
                    System.out.println("2. Eliminar ruta");
                    String mod = sc.nextLine();
                    switch (mod) {
                        case "1":
                            System.out.println("Ingrese la ciudad de origen:");
                            String origen1 = sc.nextLine();
                            System.out.println("Ingrese la ciudad de destino:");
                            String destino1 = sc.nextLine();
                            System.out.println("Ingrese la distancia:");
                            int distancia = sc.nextInt();
                            graph.addEdge(origen1, destino1, distancia);
                            break;
                        case "2":
                            System.out.println("Ingrese la ciudad de origen:");
                            String origen2 = sc.nextLine();
                            System.out.println("Ingrese la ciudad de destino:");
                            String destino2 = sc.nextLine();
                            graph.deletearch(origen2, destino2);
                            break;
                        default:
                            break;
                    }
                    
                    break;
                case "4":
                    System.exit(0);
                    break;
            
                default:
                    break;
                }
            }

        sc.close();
    }
}
