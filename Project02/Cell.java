/*
 * File: Cell.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Mar. 1, 2021
 * Section B
 * Project 2: Conway's Game of Life
 * CS231
 */

import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Line2D;

public class Cell {

    // state of the cell
    private boolean live;
    
    // constructor method
    // by default, the Cell is dead
    public Cell() {
        this.live = false;
    }

    // constructor method that specifies the Cell's state
    public Cell(boolean alive) {
        this.live = alive;
    }

    // returns whether the Cell is alive
    public boolean getAlive() {
        if (this.live == true) {
            return true;
        }
        else {
            return false;
        }
    }

    // sets the Cell state to its default value (not alive)
    public void reset() {
        this.live = false;
    }

    // sets the Cell's state
    public void setAlive(boolean alive) {
        if (alive == true) {
            this.live = true;
        }
        else {
            this.live = false;
        }
    }

    // draws the Cell on the Graphics object at location x, y with the size scaled by scale
    // draws pink oval when cell is alive, gray rectangle when cell is dead
    public void draw(Graphics g, int x, int y, int scale) {
        // draws pink oval when cell is alive
        if (this.getAlive() == true) {
            g.setColor(Color.PINK);
            g.fillOval(x, y, scale, scale);
        }
        // draws gray rectangle when cell is dead
        else {
            g.setColor(Color.GRAY);
            g.drawRect(x, y, scale, scale);
        }
    }

    // uses Graphics2D instead of Graphics to use double
    // draws the Cell on the Graphics object at location x, y with the size scaled by scale
    // draws pink smiley face when cell is alive, gray skull when cell is dead
    public void drawDouble(Graphics2D g, double x, double y, double scale) {
        // draws pink smiley face when cell is alive
        if (this.getAlive() == true) {
            g.setColor(Color.pink);
            g.fill(new Ellipse2D.Double(x, y, scale, scale));
            g.setColor(Color.BLACK);
            g.fill(new Ellipse2D.Double(x + scale*0.25, y + scale*0.3125, scale*0.125, scale*0.1875));
            g.fill(new Ellipse2D.Double(x + scale*0.625, y + scale*0.3125, scale*0.125, scale*0.1875));
            g.draw(new Arc2D.Double(x + scale*0.25, y + scale*0.5, scale*0.5, scale*0.25, 180, 180, Arc2D.OPEN));
        }
        // draws gray skull when cell is dead
        else {
            g.setColor(Color.BLACK);
            g.draw(new RoundRectangle2D.Double(x, y, scale, scale, scale*0.2, scale*0.2));
            g.setColor(Color.GRAY);
            g.fill(new RoundRectangle2D.Double(x, y, scale, scale, scale*0.2, scale*0.2));
            g.setColor(Color.BLACK);
            g.fill(new Ellipse2D.Double(x + scale*0.2, y + scale*0.3, scale*0.2, scale*0.3));
            g.fill(new Ellipse2D.Double(x + scale*0.6, y + scale*0.3, scale*0.2, scale*0.3));
            g.draw(new Line2D.Double(x + scale*0.44, y + scale*0.59, x + scale*0.38, y + scale*0.72));
            g.draw(new Line2D.Double(x + scale*0.56, y + scale*0.59, x + scale*0.62, y + scale*0.72));
            g.draw(new Line2D.Double(x + scale*0.3, y + scale*0.85, x + scale*0.3, y + scale));
            g.draw(new Line2D.Double(x + scale*0.44, y + scale*0.85, x + scale*0.44, y + scale));
            g.draw(new Line2D.Double(x + scale*0.56, y + scale*0.85, x + scale*0.56, y + scale));
            g.draw(new Line2D.Double(x + scale*0.7, y + scale*0.85, x + scale*0.7, y + scale));
        }
    }

    // updates whether or not the cell is alive in the next time step
    // given its neighbors in the current time step
    public void updateState(ArrayList<Cell> neighbors) {
        int count = 0;

        System.out.println("\nTested cell initial state: " + this.getAlive());
        
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getAlive() == true) {
                count++;
            }
        }

        if (this.getAlive() == true) {
            if (count == 2 || count == 3) {
                this.setAlive(true);
            }
            else {
                this.setAlive(false);
            }
        }

        else {
            if (count == 3) {
                this.setAlive(true);
            }
            else {
                this.setAlive(false);
            }
        }
        
        System.out.println("Number of neighboring cells alive: " + count);
        System.out.println("Tested cell updated state: " + this.getAlive());
    }

    // returns a string that indicates the alive state of the Cell as a one-character string
    public String toString() {
        String state;

        if (this.getAlive() == true) {
            state = "0";
        }

        else {
            state = " ";
        }

        return state;
    }

    // unit test
    public static void main(String[] args) {
        Cell cell = new Cell();
        System.out.println(cell.toString());
        cell.setAlive(true);
        System.out.println(cell.toString());
        cell.reset();
        System.out.println(cell.toString());
        Cell cell2 = new Cell(true);
        System.out.println(cell2.toString());
    }
}
