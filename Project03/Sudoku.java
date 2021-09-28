/*
 * File: Sudoku.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Mar. 15, 2021
 * Section B
 * Project 3: Search Simulation: Sudoku
 * CS231
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Sudoku {

    // holds a Board object
    private Board board;

    // holds a LandscapeDisplay object
    private LandscapeDisplay display;

    // private int timeDisplay;

    // constructor - creates a new Board that is all zeros by default
    public Sudoku() {
        this.board = new Board();
        this.display = new LandscapeDisplay(this.board, 30);
    }

    // constructor - takes in an int parameter that is the number of populated starting values N
    public Sudoku(int N) {
        this.board = new Board();
        this.display = new LandscapeDisplay(this.board, 30);
        Random ran = new Random();
        int i = 0;
        while (i < N) {
            int row = ran.nextInt(9);
            int col = ran.nextInt(9);
            int value = ran.nextInt(9) + 1;
            if (this.board.value(row, col) == 0) {
                if (this.board.validValue(row, col, value) == true) {
                    this.board.set(row, col, value, true);
                    i++;
                }
            }
        }
    }

    // finds and returns the next cell to check
    public Cell findBestCellEasy() {
        Cell bestCell = new Cell();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board.get(i, j).getValue() == 0) {
                    for (int value = 1; value < 10; value++) {
                        if (this.board.validValue(i, j, value) == true) {
                            return this.board.get(i, j);
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

    // finds and returns the next cell to check
    public Cell findBestCell() {
        ArrayList<Integer> arrValid = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board.countValidOptions(i, j) == 0) {
                    continue;
                }
                arrValid.add(this.board.countValidOptions(i, j));
            }
        }
        if (arrValid.size() != 0) {
            int minValid = Collections.min(arrValid);
            Cell bestCell = new Cell();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (this.board.countValidOptions(i, j) == minValid && this.board.value(i, j) == 0 && this.board.isLocked(i, j) == false) {
                        bestCell = new Cell(i, j, this.board.value(i, j));
                        return bestCell;
                    }
                }
            }
            return bestCell;
        }
        else {
            return null;
        }
    }

    // uses a stack to keep track of the solution and allow backtracking when it gets stuck
    public boolean solve(int delay) {
        int timeStep = 0;
        CellStack cellStack = new CellStack();
        int numLockedCells = this.board.numLocked();
        while (cellStack.size() < (81 - numLockedCells)) {
            Cell nextCell = this.findBestCellEasy();
            if (nextCell != null) {
                int value = this.board.getValidValue(nextCell.getRow(), nextCell.getCol());
                nextCell.setValue(value);
                cellStack.push(nextCell);
                this.board.set(nextCell.getRow(), nextCell.getCol(), value, true);
                timeStep++;
                if (delay > 0) {
                    try {
                        Thread.sleep(delay);
                    }
                    catch(InterruptedException ex) {
                        System.out.println("Interrupted");
                    }
                    display.repaint();
                }
            }
            else {
                while (cellStack.size() > 0) {
                    Cell popped = cellStack.pop();
                    int untestedValue = this.board.getValidValue(popped.getRow(), popped.getCol());
                    if (untestedValue != -1) {
                        popped.setValue(untestedValue);
                        cellStack.push(popped);
                        this.board.set(popped.getRow(), popped.getCol(), untestedValue, true);
                        timeStep++;
                        if (delay > 0) {
                            try {
                                Thread.sleep(delay);
                            }
                            catch(InterruptedException ex) {
                                System.out.println("Interrupted");
                            }
                            display.repaint();
                        }
                        break;
                    }
                    else {
                        this.board.set(popped.getRow(), popped.getCol(), 0, false);
                        timeStep++;
                    }
                }
                if (cellStack.empty() == true) {
                    return false;
                }
            }
        }
        System.out.println("Number of time steps: " + timeStep);
        return true;
    }

    // unit test
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(Integer.parseInt(args[0]));
        System.out.println(sudoku.board);
        sudoku.solve(Integer.parseInt(args[1]));
        System.out.println(sudoku.board);
    }
}
