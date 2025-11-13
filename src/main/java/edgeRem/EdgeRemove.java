package edgeRem;

import java.util.*;
import models.*;

public class EdgeRemove {
    public static Map<Integer, List<Integer>> buildAdjFromMST(List<Edge> mst, int n, Edge removed) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) adj.put(i, new ArrayList<>());
        for (Edge e : mst) {
            if (e == removed) continue;
            adj.get(e.from).add(e.to);
            adj.get(e.to).add(e.from);
        }
        return adj;
    }
    public static void dfs(int node, int compId, int[] comp, Map<Integer, List<Integer>> adj) {
        comp[node] = compId;
        for (int nei : adj.get(node)) {
            if (comp[nei] == -1) {
                dfs(nei, compId, comp, adj);
            }
        }
    }
    public static int[] getComponentsAfterRemoval(List<Edge> mst, Edge removed, int n) {
        Map<Integer, List<Integer>> adj = buildAdjFromMST(mst,n,removed);
        int[] comp = new int[n];
        Arrays.fill(comp, -1);

        int compId = 0;
        for (int i = 0; i < n; i++) {
            if (comp[i] == -1) {
                dfs(i, compId++, comp, adj);
            }
        }
        return comp;
    }
    public static Edge findReplacement(List<Edge> allEdges, List<Edge> mst, int[] comp) {
        Edge best = null;
        for (Edge e : allEdges) {
            if (mst.contains(e)) {
                continue;
            }
            if (comp[e.from] != comp[e.to]) {
                if (best == null || e.weight < best.weight) {
                    best = e;
                }
            }
        }
        return best;
    }
}
