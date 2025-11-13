import models.Graph;
import models.Edge;
import algorithm.KruskalMST;
import edgeRem.EdgeRemove;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(0, 2, 3);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(1, 4, 6);

        System.out.println("Original graph edges:");
        graph.getEdges().forEach(System.out::println);
        System.out.println("\nBuilding MST...");
        List<Edge> mst = KruskalMST.buildMST(graph);

        System.out.println("MST edges (with indices):");
        for (int i = 0; i < mst.size(); i++) {
            System.out.println("[" + i + "] " + mst.get(i));
        }
        System.out.println("Total MST: " + totalWeight(mst));

        Scanner sc = new Scanner(System.in);
        int idxToRemove = -1;
        while (true) {
            System.out.print("\nEnter index of MST edge to remove (0.." + (mst.size()-1) +"): ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter an integer.");
                sc.next();
                continue;
            }
            idxToRemove = sc.nextInt();
            if (0 <= idxToRemove && idxToRemove < mst.size()) break;
            System.out.println("Index out of range. Try again.");
        }
        Edge removed = mst.get(idxToRemove);
        System.out.println("\nRemoving edge: " + removed);
        int[] comp = EdgeRemove.getComponentsAfterRemoval(mst, removed, graph.size());

        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int v = 0; v < comp.length; v++) {
            groups.computeIfAbsent(comp[v], k -> new ArrayList<>()).add(v);
        }
        System.out.println("\nComponents after removal (" + groups.size() + "):");
        for (Map.Entry<Integer, List<Integer>> e : groups.entrySet()) {
            System.out.println("Component " + e.getKey() + ": " + e.getValue());
        }
        Edge replacement = EdgeRemove.findReplacement(graph.getEdges(), mst, comp);
        if (replacement == null) {
            System.out.println("\nNo replacement edge found — graph becomes disconnected after removal.");
            return;
        }

        System.out.println("\nReplacement edge found: " + replacement);
        List<Edge> newMst = new ArrayList<>(mst);
        newMst.remove(removed);
        newMst.add(replacement);

        System.out.println("\nNew MST edges:");
        for (Edge e : newMst) System.out.println(e);
        System.out.println("New total MST: " + totalWeight(newMst));

        System.out.println("\nSummary:");
        System.out.println("Removed edge: " + removed);
        System.out.println("Added edge:   " + replacement);
    }

    private static long totalWeight(List<Edge> edges) {
        long sum = 0;
        for (Edge e : edges) sum += e.weight;
        return sum;
    }
}
