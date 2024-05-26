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
path = input("Ingrese el nombre del archivo: ")
Cities = txtreader(path)

for i in range(len(Cities)): #agregar nodos y aristas al grafo
    g.add_node(Cities[i][0])
    
for i in range(len(Cities)):
    g.add_edge(Cities[i][0], Cities[i][1], weight = int(Cities[i][2]))




distancia = [[0,0,0],[0,0,0],[0,0,0]] #matriz 3 x 3 para resultado
predecesor, distance = nx.floyd_warshall_predecessor_and_distance(g)
print ("distancia mas corta: ")

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
