/*
 * File: Landscape.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 29th, 2021
 * Project 5: Decision-making Simulation: Checkout Lines
 * CS231
 */

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.lang.Math;

public class Landscape {
    
    // holds the width of the landscape
    private int width;

    // holds the height of the landscape
    private int height;

    // holds the list of checkout agents
    private ArrayList<CheckoutAgent> checkout;

    // holds the Customers who have completed checking out
    private LinkedList<Customer> completed;

    // constructor method
    public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts) {
        this.width = w;
        this.height = h;
        this.checkout = checkouts;
        this.completed = new LinkedList<Customer>();
    }

    // returns the height of the Landscape
    public int getHeight() {
        return this.height;
    }

    // returns the width of the Landscape
    public int getWidth() {
        return this.width;
    }

    // returns a string indicating how many checkouts and finished customers are in the landscape
    public String toString() {
        String result = "The number of checkouts: " + this.checkout.size() +
                        "\nThe number of finished customers: " + this.completed.size();
        return result;
    }

    // adds the Customer to the list of finished customers
    public void addFinishedCustomer(Customer c) {
        this.completed.addFirst(c);
    }

    // draws all CheckoutAgents
    public void draw(Graphics2D g) {
        for (CheckoutAgent c: this.checkout) {
            c.draw(g);
        }
    }

    // updates all CheckoutAgents
    public void updateCheckouts() {
        for (CheckoutAgent c: this.checkout) {
            c.updateState(this);
        }
    }

    // returns the total number of items of all checkout lines
    public int sumItemsCheckout() {
        int sumCheckout = 0;
        for (CheckoutAgent c: this.checkout) {
            sumCheckout += c.getSumItems();
        }
        return sumCheckout;
    }

    // computes and prints the average and standard deviation of the time-to-leave
    // for all of the Customers in the finished customer list
    public void printFinishedCustomerStatistics() {
        double sum = 0;
        double sumPow = 0;
        double avg = 0;
        double std = 0;
        for (Customer c: this.completed) {
            sum += c.timeSteps;
        }
        avg = sum/this.completed.size();
        for (Customer c: this.completed) {
            sumPow += Math.pow(c.timeSteps - avg, 2);
        }
        std = Math.sqrt(sumPow/this.completed.size());
        System.out.println("Average: " + avg);
        System.out.println("Standard Deviation: " + std);
    }
}
