/*
 * File: Cell.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Mar. 15, 2021
 * Section B
 * Lab 3: Stack
 * CS231
 */

import java.awt.Color;
import java.awt.Graphics;

public class Cell {

    // holds the row index
    private int rowIndex;

    // holds the column index
    private int colIndex;

    // holds the value
    private int value;

    // holds whether the value is locked
    private boolean locked;
    
    // initializes all valus to 0 or false
    public Cell() {
        this.rowIndex = 0;
        this.colIndex = 0;
        this.value = 0;
        this.locked = false;
    }

    // initializes the row, column, and value fields to the given parameter values
    public Cell(int row, int col, int value) {
        this.rowIndex = row;
        this.colIndex = col;
        this.value = value;
        this.locked = false;
    }

    // initializes all of the Cell fields given the parameters
    public Cell(int row, int col, int value, boolean locked) {
        this.rowIndex = row;
        this.colIndex = col;
        this.value = value;
        this.locked = locked;
    }

    // returns the Cell's row index
    public int getRow() {
        return this.rowIndex;
    }

    // returns the Cell's column index
    public int getCol() {
        return this.colIndex;
    }

    // returns the Cell's value
    public int getValue() {
        return this.value;
    }

    // sets the Cell's value
    public void setValue(int newval) {
        this.value = newval;
    }

    // returns the value of the locked field
    public boolean isLocked() {
        return this.locked;
    }

    // sets the Cell's locked field to the new value
    public void setLocked(boolean lock) {
        this.locked = lock;
    }

    // returns a new Cell with the same values as the existing Cell
    public Cell clone() {
        return new Cell(this.rowIndex, this.colIndex, this.value, this.locked);
    }

    // generates and returns a representing String
    public String toString() {
        String cellRep = "position: " + this.getRow() + ", " + this.getCol() + " value: " + this.getValue() + " locked: " + this.isLocked();
        return cellRep;
    }

    // draws the Cell's number
    public void draw(Graphics g, int x0, int y0, int scale) {
        char[] c = {(char)('0' + this.value), 0};
        if (c[0] == (char)('0' + 0)) {
            g.setColor(Color.LIGHT_GRAY);
        }
        if (c[0] == (char)('0' + 1)) {
            g.setColor(Color.BLUE);
        }
        if (c[0] == (char)('0' + 2)) {
            g.setColor(Color.CYAN);
        }
        if (c[0] == (char)('0' + 3)) {
            g.setColor(Color.GREEN);
        }
        if (c[0] == (char)('0' + 4)) {
            g.setColor(Color.MAGENTA);
        }
        if (c[0] == (char)('0' + 5)) {
            g.setColor(Color.ORANGE);
        }
        if (c[0] == (char)('0' + 6)) {
            g.setColor(Color.PINK);
        }
        if (c[0] == (char)('0' + 7)) {
            g.setColor(Color.RED);
        }
        if (c[0] == (char)('0' + 8)) {
            g.setColor(Color.GRAY);
        }
        if (c[0] == (char)('0' + 9)) {
            g.setColor(Color.BLACK);
        }
        g.drawChars(c, 0, 1, x0 + this.colIndex*scale + scale, y0 + this.rowIndex*scale + scale);
    }

}
