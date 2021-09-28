/*
 * File: Graph.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Apr 27th, 2021
 * Lab 9: Graphs
 * CS231
 */

import java.util.PriorityQueue;
import java.util.ArrayList;

public class Graph {

    // holds a list of vertices
    private ArrayList<Vertex> vertices;

    // constructor method - initializes the list of vertices
    public Graph() {
        this.vertices = new ArrayList<>();
    }
    
    // returns the number of vertices in the graph
    public int vertexCount() {
        return this.vertices.size();
    }

    // returns true if the query Vertex is in the graph's vertex list
    public boolean inGraph(Vertex query) {
        if (this.vertices.contains(query)) {
            return true;
        }
        else {
            return false;
        }
    }

    // adds v1 and v2 to the graph and adds an edge connecting v1 to v2
    // creating a uni-directional link
    public void addUniEdge(Vertex v1, Vertex v2) {
        if (! this.vertices.contains(v1)) {
            this.vertices.add(v1);
        }
        if (! this.vertices.contains(v2)) {
            this.vertices.add(v2);
        }
        v1.connect(v2);
    }

    // adds v1 and v2 to the graph, adds an edge connecting v1 to v2
    // adds a second edge connecting v2 to v1, creating a bi-directional link
    public void addBiEdge(Vertex v1, Vertex v2) {
        if (! this.vertices.contains(v1)) {
            this.vertices.add(v1);
        }
        if (! this.vertices.contains(v2)) {
            this.vertices.add(v2);
        }
        v1.connect(v2);
        v2.connect(v1);
    }

    // returns the list of vertices
    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    // implements a single-source shortest-path algorithm for the Graph, Dijkstra's algorithm
    public void shortestPath(Vertex v0) {
        for (Vertex v: this.vertices) {
            v.setVisit(false);
            v.setCost(Integer.MAX_VALUE);
            v.setParent(null);
        }
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
        v0.setCost(0);
        pq.add(v0);
        while (pq.size() != 0) {
            Vertex v = pq.poll();
            if (v.getVisit()) {
                continue;
            }
            v.setVisit(true);
            for (Vertex neighbor: v.getNeighbors()) {
                double distance = v.distance(neighbor);
                if ((! neighbor.getVisit()) && ((v.getCost() + distance) < neighbor.getCost())) {
                    neighbor.setCost(v.getCost() + distance);
                    neighbor.setParent(v);
                    pq.add(neighbor); 
                }
            }
        }
    }

    // returns the toString method of every Vertex in the graph
    public String toString() {
        String graph = "";
        for (int i = 0; i < this.vertexCount(); i++) {
            graph += "\n" + this.vertices.get(i).toString();
        }
        return graph;
    }

    // unit test
    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1, 1, false);
        Vertex v2 = new Vertex(3, 5, false);
        Vertex v3 = new Vertex(5, 25, false);
        Vertex v4 = new Vertex(2, 2, false);
        Vertex v5 = new Vertex(5, 3, false);
        Vertex v6 = new Vertex(0, 0, false);
        Vertex v7 = new Vertex(0, 2, false);
        Vertex v8 = new Vertex(0, 4, false);
        Vertex v9 = new Vertex(2, 4, false);
        Vertex v10 = new Vertex(3, 4, false);
        graph.addBiEdge(v1, v2);
        graph.addBiEdge(v1, v3);
        System.out.println("Number of vertices in graph: " + graph.vertexCount());
        System.out.println("Is v1 in graph: " + graph.inGraph(v1));
        System.out.println("Is v4 in graph: " + graph.inGraph(v4));
        graph.addBiEdge(v1, v4);
        graph.addBiEdge(v2, v5);
        graph.addBiEdge(v3, v6);
        graph.addBiEdge(v4, v7);
        graph.addBiEdge(v6, v8);
        graph.addBiEdge(v2, v9);
        graph.addBiEdge(v7, v10);
        graph.shortestPath(v1);
        System.out.println("\nRoot: (1, 1)");
        System.out.println(graph.toString());
        graph.shortestPath(v5);
        System.out.println("\nRoot: (5, 3)");
        System.out.println(graph.toString());
    }
}
