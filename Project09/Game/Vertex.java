/*
 * File: Vertex.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Apr 27th, 2021
 * Lab 9: Graphs
 * CS231
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Enum;

public class Vertex extends Cell implements Comparable<Vertex> {

    // enums for Direction
    public enum Direction {
        NORTH, EAST, WEST, SOUTH;
    }

    // holds the adjacency hashmap
    private HashMap<Direction, Vertex> adjacency;

    // holds whether the vertex is visible
    private boolean isVisible;

    // holds the distance from root node
    private double distance;

    // holds whether the node has been visited
    public boolean isVisited;

    // holds the parent of this node
    public Vertex parent;

    // constructor method - initializes the x and y coordinates of the node
    public Vertex(int x, int y) {
        super(x, y);
        this.adjacency = new HashMap<>();
        this.distance = (double) Math.sqrt(Math.pow(this.xcor, 2) + Math.pow(this.ycor, 2));
        this.isVisited = false;
        this.isVisible = false;
    }

    // returns the opposite direction
    public static Direction opposite(Direction d) {
        if (d.equals(Direction.NORTH)) {
            return Direction.SOUTH;
        }
        else if (d.equals(Direction.EAST)) {
            return Direction.WEST;
        }
        else if (d.equals(Direction.SOUTH)) {
            return Direction.NORTH;
        }
        else {
            return Direction.EAST;
        }
    }

    // sets the adjacency ArrayList
    public void setAdjacency(HashMap<Direction, Vertex> adjacency) {
        this.adjacency = adjacency;
    }

    // gets the x coordinate of node
    public int getX() {
        return this.xcor;
    }

    // gets the y coordinate of node
    public int getY() {
        return this.ycor;
    }

    // sets the x and y coordinates of node
    public void setPosition(int x, int y) {
        this.xcor = x;
        this.ycor = y;
    }

    // gets the visibility of the vertex
    public boolean getVisible() {
        return this.isVisible;
    }

    // sets the visibility of the vertex
    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    // gets the distance from root node
    public double getCost() {
        return this.distance;
    }

    // sets the distance from root node
    public void setCost(double distance) {
        this.distance = distance;
    }

    // gets whether the node has been visited
    public boolean getVisit() {
        return this.isVisited;
    }

    // sets whether the node has been visited
    public void setVisit(boolean visited) {
        this.isVisited = visited;
    }

    // gets the parent of this node
    public Vertex getParent() {
        return this.parent;
    }

    // sets the parent of this node
    public void setParent(Vertex parent) {
        this.parent = parent;
    }
    
    // returns the Euclidean distance between this vertex and the other vertex 
    // based on their x and y positions
    public double distance(Vertex other) {
        return (double) Math.sqrt(Math.pow((this.xcor - other.xcor), 2) + Math.pow((this.ycor - other.ycor), 2));
    }

    // updates this vertex' adjacency list/map 
    // so that it connects with the other Vertex
    public void connect(Vertex other, Direction direction) {
        this.adjacency.put(direction, other);

    }

    // returns the Vertex at position (x, y) if the Vertex is in the adjacency list
    // otherwise null
    public Vertex getNeighbor(Direction d) {
        return this.adjacency.get(d);
    }

    // returns an ArrayList of type Vertex which contains all of this Vertex' neighbors
    public ArrayList<Vertex> getNeighbors() {
        ArrayList<Vertex> neighbors = new ArrayList<>();
        for (Vertex v: this.adjacency.values()) {
            neighbors.add(v);
        }
        return neighbors;
    }

    // returns the number of connected vertices
    public int numNeighbors() {
        return this.adjacency.size();
    }

    // does nothing
    public void updateState(Landscape scape) {
        return;
    }

    // returns a String containing the number of neighbors, this Vertex' cost, and the marked flag
    public String toString() {
        String rep = "This Vertex\'s position: " + this.getX() + ", " + this.getY() +
                        "\nNumber of neighbors: " + this.numNeighbors() +
                        "\nThis Vertex\'s cost: " + this.getCost() +
                        "\nThe marked flag: " + this.getVisit();
        return rep;
    }

    // returns true if the x and y positions of the two vertices match
    public static boolean matchPosition(Vertex a, Vertex b) {
        return (a.xcor == b.xcor && a.ycor == b.ycor);
    }

    // compares the costs of the two vertices
    public int compareTo(Vertex other) {
        return Double.compare(this.distance, other.distance);
    }

    // shows possible exits from the room
    // indicates whether the Wumpus is two rooms away or closer
    public void draw(Graphics g, int x, int y, int scale) {
        if (! this.isVisible) {
            return;
        }
        int xpos = x + super.getX()*scale;
        int ypos = y + super.getY()*scale;
        int border = 2;
        int half = scale/2;
        int eighth = scale/8;
        int sixteenth = scale/16;

        // draws rectangle for the walls of the room
        if (this.getCost() <= 2) {
            // wumpus is nearby
            g.setColor(Color.RED);
        }
        else {
            // wumpus is not nearby
            g.setColor(Color.BLACK);
        }
        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2*border);

        // draws doorways as boxes
        g.setColor(Color.BLACK);
        // if (this.getNeighbor(this.getX(), this.getY() - 1) != null) {
        if (this.adjacency.containsKey(Direction.NORTH)) {
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        }
        // if (this.getNeighbor(this.getX(), this.getY() + 1) != null) {
        if (this.adjacency.containsKey(Direction.SOUTH)) {
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), eighth, eighth + sixteenth);
        }
        // if (this.getNeighbor(this.getX() - 1, this.getY()) != null) {
        if (this.adjacency.containsKey(Direction.WEST)) {
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        }
        // if (this.getNeighbor(this.getX() + 1, this.getY()) != null) {
        if (this.adjacency.containsKey(Direction.EAST)) {
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, eighth + sixteenth, eighth);
        }
    }

    // unit test
    public static void main(String[] args) {
        Vertex a = new Vertex(1, 1);
        Vertex b = new Vertex(4, 7);
        Vertex c = new Vertex(7, 11);
        a.connect(b, Direction.NORTH);
        a.connect(c, Direction.SOUTH);
        System.out.println("Position of a: " + a.getX() + ", " + a.getY());
        System.out.println("Visibility of a: " + a.getVisible());
        a.setVisible(true);
        System.out.println("Visibility of a: " + a.getVisible());
        System.out.println("Is a visited: " + a.getVisit());
        a.setVisit(true);
        System.out.println("Is a visited: " + a.getVisit());
        System.out.println("Distance between b and c: " + b.distance(c));
        System.out.println("Number of neighbors of a: " + a.numNeighbors());
        System.out.println(a.toString());
        System.out.println("Is a and b at the same position: " + matchPosition(a, b));
    }
}
