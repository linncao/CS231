/*
 * File: Wumpus.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: May 12th, 2021
 * Project 9: Game: Hunt the Wumpus
 * CS231
 */

import java.awt.Color;
import java.awt.Graphics;

public class Wumpus extends Cell {
    
    // holds the home Vertex
    private Vertex home;

    // holds whether the wumpus is visible
    private boolean isVisible;

    // holds whether the wumpus is alive
    private boolean alive;

    // constructor method - initializes the position of the wumpus
    public Wumpus(Vertex begin) {
        super(begin.getX(), begin.getY());
        this.home = begin;
        this.isVisible = false;
        this.alive = true;
    }

    // returns the home vertex 
    public Vertex getHome() {
        return this.home;
    }

    // sets the home vertex
    public void setHome(Vertex v) {
        this.home = v;
    }

    // returns whether the wumpus is visible
    public boolean isVisible() {
        return this.isVisible;
    }

    // sets the visibility
    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    // returns whether the wumpus is alive
    public boolean isAlive() {
        return this.alive;
    }

    // sets whether the wumpus is alive
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // updates the state of the wumpus
    public void updateState(Landscape scape) {
        return;
    }

    // draws the wumpus 
    public void draw(Graphics g, int x, int y, int scale) {
        if (! this.isVisible) {
            return;
        }
        int xpos = x + super.getX()*scale;
        int ypos = y + super.getY()*scale;
        if (this.alive) {
            g.setColor(Color.GREEN);
            g.fillOval(xpos, ypos, scale, scale);
        }
        else {
            g.setColor(Color.RED);
            g.fillOval(xpos, ypos, scale, scale);
        }
    }

    // unit test
    public static void main(String[] args) {
        Vertex v = new Vertex(0, 0);
        Wumpus w = new Wumpus(v);
        System.out.println(w.getHome());
        w.setHome(new Vertex(5, 25));
        System.out.println(w.getHome());
        System.out.println(w.isAlive());
        w.setAlive(false);
        System.out.println(w.isAlive());
        System.out.println(w.isVisible());
        w.setVisible(true);
        System.out.println(w.isVisible());
    }
}
