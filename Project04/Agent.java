/*
 * File: Agent.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 22nd, 2021
 * Project 4: Agent-Based Simulations
 * CS231
 */

import java.awt.Graphics2D;

public class Agent {

    // holds the x position
    private double xpos;

    // holds the y position
    private double ypos;

    // holds a boolean indicating whether the agent is a criminal
    protected boolean isCriminal;

    // constructor - sets the position
    public Agent(double x0, double y0) {
        this.xpos = x0;
        this.ypos = y0;
    }

    // returns the x position
    public double getX() {
        return this.xpos;
    }

    // returns the y position
    public double getY() {
        return this.ypos;
    }

    // sets the x position
    public void setX(double newX) {
        this.xpos = newX;
    }

    // sets the y position
    public void setY(double newY) {
        this.ypos = newY;
    }

    // returns a String containing the x and y positions
    public String toString() {
        String position = "(" + this.xpos + ", " + this.ypos + ")";
        return position;
    }

    // empty
    public void updateState(Landscape scape) {

    }

    // empty
    public void draw(Graphics2D g) {

    }

    // returns the cell's criminal status
    public boolean getCriminal() {
        return this.isCriminal;
    }

    // sets the cell's criminal status to true
    public void turnCriminal() {
        this.isCriminal = true;
    }

    // unit test
    public static void main(String[] args) {
        Agent agent = new Agent(0.5, 0.5);
        System.out.println(agent.getX());
        System.out.println(agent.getX());
        System.out.println(agent.toString());
        agent.setX(1.5);
        agent.setY(1.5);
        System.out.println(agent.toString());
    }
}
