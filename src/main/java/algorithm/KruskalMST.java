package algorithm;

import models.Edge;
import models.Graph;
import DSU.Dsu;
import java.util.*;
public class KruskalMST {

    public static List<Edge> buildMST(Graph g) {
        List<Edge> edges = new ArrayList<>(g.getEdges());
        Collections.sort(edges);

        Dsu dsu = new Dsu(g.size());
        List<Edge> mst = new ArrayList<>();

        for (Edge e : edges) {
            if (dsu.union(e.from, e.to)) {
                mst.add(e);
            }
        }
        return mst;
    }
}