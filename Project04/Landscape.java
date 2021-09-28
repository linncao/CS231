/*
 * File: Landscape.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 22nd, 2021
 * Project 4: Agent-Based Simulations
 * CS231
 */

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.lang.Math;

public class Landscape {

    // holds the landscape width
    private int width;

    // holds the landscape height
    private int height;

    // holds the linked list of agent
    private LinkedList<Agent> agentList;
    
    // constructor - sets the width and height fields, and initializes the agent list
    public Landscape(int w, int h) {
        this.agentList = new LinkedList<>();
        this.width = w;
        this.height = h;
    }

    // returns the height
    public int getHeight() {
        return this.height;
    }

    // returns the width
    public int getWidth() {
        return this.width;
    }

    // inserts an agent at the beginning of its list of agents
    public void addAgent(Agent a) {
        this.agentList.addFirst(a);
    }

    // returns a String representing the Landscape
    public String toString() {
        String represent = "The number of Agents on the Landscape: " + this.agentList.size();
        return represent;
    }

    // returns a list of the Agents within radius distance of the location x0, y0
    public ArrayList<Agent> getNeighbors(double x0, double y0, double radius) {
        ArrayList<Agent> arrNeighbors = new ArrayList<>();
        for (Agent a: this.agentList) {
            if (Math.sqrt(Math.pow((a.getX() - x0), 2) + Math.pow(a.getY() - y0, 2)) <= radius){
                    arrNeighbors.add(a);
                    if (a.getX() == x0 && a.getY() == y0) {
                        arrNeighbors.remove(a);
                    }
                }
        }
        return arrNeighbors;
    }

    // updates the state of each agent, in a random order
    public void updateAgents() {
        ArrayList<Agent> updated = this.agentList.toShuffledList();
        for (int i = 0; i < updated.size(); i++) {
            updated.get(i).updateState(this);
        }
    }

    // calls the draw method of all the agents on the Landscape
    public void draw(Graphics2D g) {
        for (Agent a: this.agentList) {
            a.draw(g);
        }
    }

    // unit test
    public static void main(String[] args) {
        Landscape landscape = new Landscape(10, 10);
        System.out.println(landscape.getHeight());
        System.out.println(landscape.getWidth());
        Agent agent1 = new Agent(5, 5);
        Agent agent2 = new Agent(10, 10);
        Agent agent3 = new Agent(15, 15);
        Agent agent4 = new Agent(20, 20);
        landscape.addAgent(agent1);
        landscape.addAgent(agent2);
        landscape.addAgent(agent3);
        landscape.addAgent(agent4);
        agent1.updateState(landscape);
        System.out.println("agent 1 state: " + "(" + agent1.getX() + ", " + agent1.getY() + ")");
        System.out.println(landscape.getNeighbors(5, 5, 10));
        agent1.setX(5);
        agent1.setY(5);
        System.out.println(landscape.getNeighbors(5, 5, 15));
        agent1.setX(5);
        agent1.setY(5);
        System.out.println(landscape.getNeighbors(5, 5, 25));
    }
}
