/*
 * File: Board.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Mar. 15, 2021
 * Section B
 * Project 3: Search Simulation: Sudoku
 * CS231
 */

import java.io.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Board {
    
    // holds a 2D array of Cells
    private Cell[][] board;

    // holds a constant value of 9
    public static final int Size = 9;

    // constructor - creates a new 2D array of Cells
    // initializes each location in the grid with a new Cell with value 0
    public Board() {
        this.board = new Cell[Board.Size][Board.Size];
        for (int i = 0; i < Board.Size; i++) {
            for (int j = 0; j < Board.Size; j++) {
                this.board[i][j] = new Cell(i, j, 0, false);
            }
        }
    }

    // generates a multi-line string representation of the board
    public String toString() {
        String boardRep = "\nMulti-line string representation of the board:\n";
        for (int i = 0; i < Board.Size; i++) {
            for (int j = 0; j < Board.Size; j++) {
                if (j == 2 || j == 5 || j == 8) {
                    boardRep += this.board[i][j].getValue() + "  ";
                }
                else {
                    boardRep += this.board[i][j].getValue() + " ";
                }
            }
            if (i == 2 || i == 5 || i == 8) {
                boardRep += "\n\n";
            }
            else {
                boardRep += "\n";
            }
        }
        return boardRep;
    }

    // returns the number of columns
    public int getCols() {
        return this.board[0].length;
    }

    // returns the number of rows
    public int getRows() {
        return this.board.length;
    }

    // returns the Cell at location r, c
    public Cell get(int r, int c) {
        return this.board[r][c];
    }

    // returns whether the Cell at r, c is locked
    public boolean isLocked(int r, int c) {
        return this.get(r, c).isLocked();
    }

    // returns the number of locked Cells on the board
    public int numLocked() {
        int countLocked = 0;
        for (int i = 0; i < Board.Size; i++) {
            for (int j = 0; j < Board.Size; j++) {
                if (this.isLocked(i, j) == true) {
                    countLocked++;
                }
            }
        }
        return countLocked;
    }

    // returns the value at Cell r, c
    public int value(int r, int c) {
        return this.get(r, c).getValue();
    }

    // sets the value of the Cell at r, c
    public void set(int r, int c, int value) {
        this.get(r, c).setValue(value);
    }

    // sets the value and locked fields of the Cell at r, c
    public void set(int r, int c, int value, boolean locked) {
        this.get(r, c).setValue(value);
        this.get(r, c).setLocked(locked);
    }

    // reads a file given file name
    public boolean read(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] array;
            int row = 0;
            while (line != null) {
                array = line.strip().split("[\n ]+");
                if (array.length == 9) {
                    for (int col = 0; col < 9; col++) {
                        int value = Integer.parseInt(array[col]);
                        boolean locked = value != 0;
                        this.set(row, col, value, locked);
                    }
                    row++;
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return true;
        }
        catch(FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        }
        catch(IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }
        return false;
    }

    // tests if the given value is a valid value at the given row and column of the board
    public boolean validValue(int row, int col, int value) {
        if (value >= 1 && value <= 9) {

            // Test row and col
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (j == col) {
                        continue;
                    }
                    if (value == this.value(row, j)) {
                        return false;
                    }
                }
                if (i == row) {
                    continue;
                }
                if (value == this.value(i, col)) {
                    return false;
                }
            }

            // Test local 3x3 square
            int rowInd = row / 3 * 3;
            int colInd = col / 3 * 3;
            for (int i = rowInd; i < rowInd + 3; i++) {
                for (int j = colInd; j < colInd + 3; j++) {
                    if (i == row && j == col) {
                        continue;
                    }
                    if (value == this.value(i, j)) {
                        return false;
                    }
                }
            }

            return true;
        }
        return false;
    }

    // counts the valid value options of a cell
    public int countValidOptions(int row, int col) {
        int countOptions = 0;
        if (this.value(row, col) == 0) {
            for (int i = 1; i < 10; i++) {
                if (this.validValue(row, col, i) == true) {
                    countOptions += 1;
                }
            }
        }
        return countOptions;
    }

    // returns the first valid value of a cell
    public int getValidValue(int row, int col) {
        int currentValue = value(row, col);
        for (int i = currentValue + 1; i < 10; i++) {
            if (this.validValue(row, col, i) == true) {
                return i;
            }
        }
        return -1;
    }

    // returns true if the board is solved
    public boolean validSolution() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.validValue(i, j, this.value(i, j)) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    // draws the board
    public void draw(Graphics g, int scale) {
        g.drawLine(scale*6, 0, scale*6, scale*18);
        g.drawLine(scale*12, 0, scale*12, scale*18);
        g.drawLine(0, scale*6, scale*18, scale*6);
        g.drawLine(0, scale*12, scale*18, scale*12);

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                this.board[i][j].draw(g, j*scale, i*scale, scale);
            }
        }
    }

    // unit test
    public static void main(String[] args) {
        Board board = new Board();
        // System.out.println(board.toString());
        // System.out.println("Number of columns: " + board.getCols());
        // System.out.println("Number of rows: " + board.getRows());
        // board.set(1, 2, 3);
        // board.set(3, 8, 1, true);
        // board.set(7, 6, 9, true);
        // System.out.println(board.toString());
        // System.out.println("Value of cell at (3, 8): " + board.value(3, 8));
        // System.out.println("Number of cells locked: " + board.numLocked());
        board.read("board20initial.txt");
        System.out.println("Checking reading: " + board.toString());
        // System.out.println(board.getValidValue(0, 0));
        //System.out.println(board.validValue(8, 5, 3));
        //System.out.println(board.validSolution());
        // System.out.println(board.countValidOptions(1, 1));
    }
}
