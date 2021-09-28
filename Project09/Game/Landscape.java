/*
 * File: Landscape.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 22nd, 2021
 * Project 4: Agent-Based Simulations
 * CS231
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.Math;

public class Landscape {

    // holds the landscape width
    private int width;

    // holds the landscape height
    private int height;

    // holds a list of all the agents in the landscape
    private ArrayList<Cell> agents;

    // holds the direction the hunter is moving
    private Vertex.Direction direction;
    
    // constructor - sets the width and height fields, and initializes the agent list
    public Landscape(int w, int h) {
        this.width = w;
        this.height = h;
        this.agents = new ArrayList<>();
        this.direction = null;
    }

    // returns the height
    public int getHeight() {
        return this.height;
    }

    // returns the width
    public int getWidth() {
        return this.width;
    }

    // clears the landscape of agents
    public void reset() {
        this.agents.clear();
    }

    // inserts a cell to the landscape
    public void addBackgroundAgent(Cell c) {
        this.agents.add(c);
    }

    // removes a cell from the landscape
    public void removeAgent(Cell c) {
        this.agents.remove(c);
    }

    // returns the list of agents
    public ArrayList<Cell> getAgents() {
        return this.agents;
    }

    // returns the current direction the hunter is moving
    public Vertex.Direction getDirection() {
        return this.direction;
    } 

    // sets the direction the hunter is moving
    public void setDirection(Vertex.Direction d) {
        this.direction = d;
    }

    // calls updateState on each cell in the landscape
    public void advance() {
        for (Cell c: this.agents) {
            c.updateState(this);
        }
    }

    // // calls the draw method of all the vertices on the Landscape
    // public void draw(Graphics g, int scale) {
    //     for (Cell v: this.vertexList) {
    //         v.draw(g, scale);
    //     }
    // }

    // returns a String representing the Landscape
    public String toString() {
        String represent = "The number of Agents on the Landscape: " + this.agents.size();
        return represent;
    }

    // unit test
    public static void main(String[] args) {
        Landscape landscape = new Landscape(10, 10);
        System.out.println(landscape.getHeight());
        System.out.println(landscape.getWidth());
    }
}
