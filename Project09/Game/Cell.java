/*
 * File: Cell.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: May 12th, 2021
 * Project 9: Game: Hunt the Wumpus
 * CS231
 */


import java.util.*;
import java.awt.*;

public abstract class Cell {
    
    // holds the x coordinate of the cell
    protected int xcor;

    // holds the y coordinate of the cell
    protected int ycor;

    // constructor method - creates a cell located at the given coordinates
    public Cell(int x, int y) {
        this.xcor = x;
        this.ycor = y;
    }

    // returns the x coordinate of the cell
    public int getX() {
        return this.xcor;
    }

    // sets the x coordinate of the cell
    public void setX(int x) {
        this.xcor = x;
    }

    // returns the y coordinate of the cell
    public int getY() {
        return this.ycor;
    }

    // sets the y coordinate of the cell
    public void setY(int y) {
        this.ycor = y;
    }

    // updates the cell's position
    public abstract void updateState(Landscape scape);

    // draws the cell
    public abstract void draw(Graphics g, int x, int y, int scale);
}
