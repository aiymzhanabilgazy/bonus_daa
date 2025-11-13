#  Bonus Task: Edge Removal from an MST (Minimum Spanning Tree)

## This project implements:
1. Build the **Minimum Spanning Tree (MST) from a given graph** using **Kruskal's algorithm**.
2. Display the MST edges before removing any edge.
3. Remove one edge from the MST.
4. Show the resulting components after the removal.
5. Find a **replacement edge** to reconnect the components and display the new MST.

## Project Structure
- **`Edge.java`** — represents a weighted undirected edge
- **`Graph.java`** — stores vertices, edges, and adjacency list
- **`Dsu.java`** — disjoint set union (Union–Find) structure
- **`KruskalMST.java`** — builds MST using Kruskal’s algorithm  
- **`EdgeRemove.java`** — removes an MST edge and finds connected components
- **`Main.java`** — runs an example demonstration of MST removal and replacement

## How It Works
1. A demonstration graph is created inside `Main.java`
2. The program builds the MST using Kruskal
3. MST edges are printed with indices
4. The user chooses a **single MST edge** to remove
5. Components are detected using **DFS on the MST graph**
6. A replacement edge is chosen using **minimum crossing edge (cut property)**
7. A new updated MST is printed

## Example Dataset 
The demo graph used 6 vertices(from,to,weight).
- 0–1 (4)
- 1–2 (1)
- 0–2 (3)
- 2–3 (2)
- 3–4 (5)
- 1–4 (6)

## Running the Project
1. Clone the repository

   **git clone https://github.com/aiymzhanabilgazy/bonus_daa.git**

2. Compile the project **mvn compile**
3. Run the program  **mvn exec:java -Dexec.mainClass=Main**

## Summary
This project demonstrates how to maintain a Minimum Spanning Tree after removing one of its edges.
The program first constructs an MST using Kruskal’s algorithm and displays all MST edges with their weights.

After the user selects an edge to remove, the program identifies the resulting connected components using DFS on the reduced MST.
It then finds the minimum-weight replacement edge that reconnects these components, following the cut property.

Finally, the updated MST and its total weight are printed, clearly showing which edge was removed and which one was added.
