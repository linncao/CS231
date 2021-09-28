/*
 * File: Landscape.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Mar. 1, 2021
 * Section B
 * Project 2: Conway's Game of Life
 * CS231
 */

import java.util.ArrayList;
import java.awt.*;

public class Landscape {

    // the number of rows
    private int rows;

    // the number of columns
    private int cols;

    // the array of Cell object
    private Cell[][] cellGrid;
    
    // constructor method
    // sets the number of rows and columns to the specified values and allocates the grid of Cell references
    // allocates a Cell for each location in the Grid
    public Landscape(int rows, int cols) {
        // this.cell = new Cell();
        this.rows = rows;
        this.cols = cols;
        this.cellGrid = new Cell[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.cellGrid[i][j] = new Cell();
            }
        }
    }

    // calls the reset method for each cell
    public void reset() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.cellGrid[i][j].reset();;
            }
        }
    }

    // returns the number of rows in the Landscape
    public int getRows() {
        return this.rows;
    }

    // returns the number of columns in the Landscape
    public int getCols() {
        return this.cols;
    }

    // returns a reference to the Cell located at position (row, col)
    public Cell getCell(int row, int col) {
        return this.cellGrid[row][col];
    }

    // draws the cells in their positions in the grid
    public void draw(Graphics g, int gridScale) {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                this.cellGrid[i][j].draw(g, i*gridScale, j*gridScale, gridScale);
            }
        }
    }

    // uses Graphics2D instead of Graphics to use double
    // draws the cells in their positions in the grid
    public void drawDouble(Graphics2D g, double gridScale) {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                this.cellGrid[i][j].drawDouble(g, i*gridScale, j*gridScale, gridScale);
            }
        }
    }

    // converts the Landscape into a text-based string representation
    public String toString() {
        String landscapeString = "\nNumber of rows: " + this.getRows();
        landscapeString += "\nNumber of columns: " + this.getCols();
        landscapeString += "\nContents of landscape: \n";

        for (int i = 0; i < this.cellGrid.length; i++) {
            for (int j = 0; j < this.cellGrid[i].length; j++) {
                landscapeString += this.cellGrid[i][j] + " ";
            }
            landscapeString += "\n";
        }

        return landscapeString;
    }

    // returns a list of references to the neighbors of the Cell at location (row, col)
    public ArrayList<Cell> getNeighbors(int row, int col) {
        ArrayList<Cell> arrNeighbors = new ArrayList<>();

        if (row > 0) {
            if (col > 0) {
                arrNeighbors.add(cellGrid[row - 1][col - 1]);
            }
            arrNeighbors.add(cellGrid[row - 1][col]);
            if (col < (this.getCols() - 1)) {
                arrNeighbors.add(cellGrid[row - 1][col + 1]);
            }
        }

        if (col > 0) {
            arrNeighbors.add(cellGrid[row][col - 1]);
        }

        if (col < (this.getCols() - 1)) {
            arrNeighbors.add(cellGrid[row][col + 1]);
        }

        if (row < (this.getRows() - 1)) {
            if (col > 0) {
                arrNeighbors.add(cellGrid[row + 1][col - 1]);
            }
            arrNeighbors.add(cellGrid[row + 1][col]);
            if (col < (this.getCols() - 1)) {
                arrNeighbors.add(cellGrid[row + 1][col + 1]);
            }
        }

        return arrNeighbors;
    }

    // moves all Cells forward one generation
    public void advance() {
        Cell[][] cellGridTemp = new Cell[this.rows][this.cols];
        for (int i = 0; i < cellGridTemp.length; i++) {
            for (int j = 0; j < cellGridTemp[i].length; j++) {
                cellGridTemp[i][j] = new Cell();
                cellGridTemp[i][j].setAlive(cellGrid[i][j].getAlive());
                cellGridTemp[i][j].updateState(this.getNeighbors(i, j));
            }
        }
        this.cellGrid = cellGridTemp;
    }

    // unit test
    public static void main(String[] args) {
        // test grid
        Landscape landscape = new Landscape(4, 4);
        System.out.println(landscape.toString());
        System.out.println(landscape.getCell(2, 0).toString());

        // test border
        System.out.println(landscape.getNeighbors(2, 1));
        System.out.println(landscape.getNeighbors(0, 0));
        System.out.println(landscape.getNeighbors(2, 0));

        // test dead cell 3 neighbors alive
        landscape.getCell(2, 0).setAlive(true);
        landscape.getCell(2, 2).setAlive(true);
        landscape.getCell(1, 1).setAlive(true);
        landscape.getCell(2, 1).updateState(landscape.getNeighbors(2, 1));
        System.out.println(landscape.toString());

        // test dead cell 2 neighbors alive
        landscape.getCell(2, 1).setAlive(false);
        landscape.getCell(2, 0).setAlive(false);
        landscape.getCell(2, 1).updateState(landscape.getNeighbors(2, 1));
        System.out.println(landscape.toString());

        // test alive cell 2 neighbors alive
        landscape.getCell(2, 1).setAlive(true);
        landscape.getCell(2, 1).updateState(landscape.getNeighbors(2, 1));
        System.out.println(landscape.toString());

        // test alive cell 1 neighbors alive
        landscape.getCell(2, 1).setAlive(true);
        landscape.getCell(2, 2).setAlive(false);
        landscape.getCell(2, 1).updateState(landscape.getNeighbors(2, 1));
        System.out.println(landscape.toString());

        // test reset method
        landscape.reset();
        System.out.println(landscape.toString());
    }
}
