# ejemplo. Encontrar ruta mas corta
# con algoritmo de Floyd

import networkx as nx


def txtreader(filename):
    result = []
    with open(filename, "r") as f:
        for line in f:
            words = line.split(" ")
            words[2] = words[2].replace("\n", "")
            result.append(words)
    f.close()
    return result



g = nx.DiGraph()
path = input("Ingrese el path del archivo: ")
Cities = txtreader(path)

for i in range(len(Cities)): #agregar nodos y aristas al grafo
    g.add_node(Cities[i][0])
    
for i in range(len(Cities)):
    g.add_edge(Cities[i][0], Cities[i][1], weight = int(Cities[i][2]))




distancia = [[0,0,0],[0,0,0],[0,0,0]] #matriz 3 x 3 para resultado
predecesor, distance = nx.floyd_warshall_predecessor_and_distance(g)

print("1. Distancia mas corta entre ciudades")
print("2. Centro del grafo")
print("3. Modificar grafo")
print("4. Salir")

opcion = int(input("Ingrese una opcion: "))

if opcion == 1:
    inicio = input("Ingrese la ciudad de inicio: ")
    fin = input("Ingrese la ciudad de destino: ")
    print("La distancia mas corta entre " + inicio + " y " + fin + " es: " + str(distance[inicio][fin]))
elif opcion == 2:
    for i in range(len(Cities)):
        for j in range(len(Cities)):
            distancia[i][j] = distance[Cities[i][0]][Cities[j][0]]
            print (distancia[i][j], end = " ")
        print ("")

    #get center with floyd
    try:
        center = nx.center(g)
        print ("Centro: ")
        print (center)
    except:
        print ("No hay centro")

    print ("predecesores: ")
    print (predecesor)

elif opcion == 3:
    print("1. Agregar ciudad")
    print("2. Eliminar ciudad")
    print("3. Agregar ruta")
    print("4. Eliminar ruta")
    print("5. Salir")
    opcion = int(input("Ingrese una opcion: "))
    if opcion == 1:
        ciudad = input("Ingrese el nombre de la ciudad: ")
        g.add_node(ciudad)
    elif opcion == 2:
        ciudad = input("Ingrese el nombre de la ciudad: ")
        g.remove_node(ciudad)
    elif opcion == 3:
        ciudad1 = input("Ingrese el nombre de la ciudad de inicio: ")
        ciudad2 = input("Ingrese el nombre de la ciudad de destino: ")
        distancia = int(input("Ingrese la distancia entre las ciudades: "))
        g.add_edge(ciudad1, ciudad2, weight = distancia)
    elif opcion == 4:
        ciudad1 = input("Ingrese el nombre de la ciudad de inicio: ")
        ciudad2 = input("Ingrese el nombre de la ciudad de destino: ")
        g.remove_edge(ciudad1, ciudad2)
    elif opcion == 5:
        pass
    else:
        print("Opcion no valida")
