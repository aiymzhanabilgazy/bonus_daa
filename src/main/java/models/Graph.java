package models;

import java.util.*;

public class Graph {
    private int vertices;
    private List<Edge> edges = new ArrayList<>();
    private Map<Integer, List<Integer>> adj = new HashMap<>();

    public Graph(int vertices) {
        this.vertices = vertices;
        for (int i = 0; i < vertices; i++) {
            adj.put(i, new ArrayList<>());
        }
    }
    public void addEdge(int from, int to, int weight) {
        edges.add(new Edge(from, to, weight));
        adj.get(from).add(to);
        adj.get(to).add(from);
    }
    public int size() {
        return vertices;
    }
    public List<Edge> getEdges() {
        return edges;
    }
    public Map<Integer, List<Integer>> getAdj() {
        return adj;
    }
}