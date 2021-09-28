/**
 * AdjListGraph.java
 * Adjacency list implementation of a undirected graph
 * CS231 Data structures and algorithms, Spring 2021
 *
 * @author Hannah Wolfe, Oliver W. Layton, Zadia Codabux, & Dale Skrien
 */

import java.util.ArrayList;

class Vertex
{
    private String name;
    private ArrayList<Vertex> neighbors;

    public Vertex(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public void join(Vertex b) {
        if (! this.neighbors.contains(b)) {
            this.neighbors.add(b);
        }
    }

    public ArrayList<Vertex> getNeighbors() {
        return this.neighbors;
    }

    public String toString() {
        return " " + this.name + " ";
    }
}


public class AdjListGraph
{
    private ArrayList<Vertex> vertices;

    public AdjListGraph() {
        this.vertices = new ArrayList<>();
    }

    /**
     * Joins two vertices together with an edge
     * Sets connections symmetrically (undirected graph)
     */
    public void join(Vertex a, Vertex b) {
        if (! this.vertices.contains(a)) {
            this.vertices.add(a);
        }
        if (! this.vertices.contains(b)) {
            this.vertices.add(b);
        }

        a.join(b);
        b.join(a);
    }

    // big-Oh notation: O(n)
    public boolean isATriangle(int v1, int v2, int v3) {
        Vertex a = this.vertices.get(v1);
        Vertex b = this.vertices.get(v2);
        Vertex c = this.vertices.get(v3);
        if (a.getNeighbors().contains(b) && a.getNeighbors().contains(c)
            && b.getNeighbors().contains(a) && b.getNeighbors().contains(c)
            && c.getNeighbors().contains(a) && c.getNeighbors().contains(b)) {
            return true;
        }
        return false;
    }

    // For each vertex, print its neighbors
    public String toString() {
        String returnStr = "";
        for (Vertex v : this.vertices) {
            returnStr += "Vertex " + v + " neighbors: ";
            for (Vertex n : v.getNeighbors()) {
                returnStr += n + " ";
            }
            returnStr += "\n";
        }
        return returnStr;
    }

    // Let's test our Graph!
    public static void main(String[] args) {
        AdjListGraph graph = new AdjListGraph();
        System.out.println(graph);

        Vertex v0 = new Vertex("a");
        Vertex v1 = new Vertex("b");
        Vertex v2 = new Vertex("c");
        Vertex v3 = new Vertex("d");

        graph.join(v0, v0);
        graph.join(v0, v1);
        graph.join(v1, v2);
        graph.join(v2, v0);

        System.out.println(graph.isATriangle(1, 2, 0));

        // System.out.println(graph);
    }
}

/*
 2. a. Instead of joining Vertex B to Vertex A, join Vertex A to itself join Vertex B to itself
    b. Join the two vertices again
    c. Remove the part that joins Vertex B to Vertex A so that it can connect A to B but not B to A
 */