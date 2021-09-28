/*
 * File: Hunter.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: May 12th, 2021
 * Project 9: Game: Hunt the Wumpus
 * CS231
 */

import java.awt.*;
import java.util.*;
import java.io.*;

public class Hunter extends Cell {
    
    // holds the current location of the hunter
    private Vertex location;

    // holds whether the hunter is ready
    private boolean ready;

    // constructor method - initializes the location
    public Hunter(Vertex begin) {
        super(begin.getX(), begin.getY());
        this.location = begin;
        this.location.setVisible(true);
        this.ready = false;
    }

    // returns the location of the hunter
    public Vertex getLocation() {
        return this.location;
    }

    // sets the location of the hunter
    public void setLocation(Vertex v) {
        this.location = v;
    }

    // returns whether the hunter is ready
    public boolean isReady() {
        return this.ready;
    }

    // sets whether the hunter is ready
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    // updates the cell's position
    public void updateState(Landscape scape) {
        if (scape.getDirection() == null ||
            this.location.getNeighbor(scape.getDirection()) == null) {
            return;
        }
        else {
            this.location = this.location.getNeighbor(scape.getDirection());
            super.setX(this.location.getX());
            super.setY(this.location.getY());
            this.location.setVisible(true);
        }
    }

    // draws the hunter 
    public void draw(Graphics g, int x, int y, int scale) {
        int xpos = x + super.getX()*scale;
        int ypos = y + super.getY()*scale;
        if (! this.ready) {
            g.setColor(Color.RED);
            g.fillRect(xpos, ypos, scale, scale);
        }
        else {
            g.setColor(Color.BLUE);
            g.fillRect(xpos, ypos, scale, scale);
        }
    }

    // unit test
    public static void main(String[] args) {
        Vertex v = new Vertex(0, 0);
        Hunter h = new Hunter(v);
        System.out.println(h.getLocation());
        h.setLocation(new Vertex(3, 5));
        System.out.println(h.getLocation());
        System.out.println(h.isReady());
        h.setReady(true);
        System.out.println(h.isReady());
    }
}
