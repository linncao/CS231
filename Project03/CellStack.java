/*
 * File: CellStack.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Mar. 15, 2021
 * Section B
 * Lab 3: Stack
 * CS231
 */

public class CellStack {

    // holds array of Cells
    private Cell[] cellStack;

    // holds the next free location on the stack
    private int top;

    // initializes the stack to a default size
    public CellStack() {
        this.cellStack = new Cell[10];
        this.top = 0;
    }

    // initializes the stack to hold up to max elements
    public CellStack(int max) {
        this.cellStack = new Cell[max];
        this.top = 0;
    }

    // pushes c onto the stack if there is space
    public void push(Cell c) {
        if (this.top < this.cellStack.length) {
            this.cellStack[this.top++] = c;
        }
        else {
            Cell[] cellStackTemp = new Cell[this.cellStack.length*2];
            for (int i = 0; i < this.cellStack.length; i++) {
                cellStackTemp[i] = this.cellStack[i];
            }
            this.cellStack = cellStackTemp;
            this.cellStack[this.top++] = c;
        }
    }

    // removes and returns the top element from the stack
    // returns null if the stack is empty
    public Cell pop() {
        if (this.top >= 0) {
            Cell cell = this.cellStack[this.top-1];
            this.cellStack[this.top-1] = null;
            this.top--;
            return cell;
        }
        else {
            return null;
        }
    }
    
    // return the number of elements on the stack
    public int size() {
        return this.top;
    }

    // returns true if the stack is empty, otherwise false
    public boolean empty() {
        if (this.top <= 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
