/*
 * File: Customer.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 29th, 2021
 * Project 5: Decision-making Simulation: Checkout Lines
 * CS231
 */

import java.util.ArrayList;

abstract class Customer {
    
    // holds how many items remain in the basket
    private int remItems;

    // holds the number of time steps it takes to leave the store
    protected int timeSteps;

    // constructor method
    // by default, the number of time steps is zero
    public Customer(int num_items) {
        this.remItems = num_items;
        this.timeSteps = 0;
    }

    // constructor method
    public Customer(int num_items, int time_steps) {
        this.remItems = num_items;
        this.timeSteps = time_steps;
    }

    // increments the number of time steps
    public void incrementTime() {
        this.timeSteps++;
    }

    // returns the number of time steps
    public int getTime() {
        return this.timeSteps;
    }

    // decrements the number of items
    public void giveUpItem() {
        this.remItems--;
    }

    // returns the number of items
    public int getNumItems() {
        return this.remItems;
    }

    // no body
    public abstract int chooseLine(ArrayList<CheckoutAgent> checkouts);
}
