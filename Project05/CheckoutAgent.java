/*
 * File: CheckoutAgent.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 29th, 2021
 * Project 5: Decision-making Simulation: Checkout Lines
 * CS231
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;
import java.util.Random;

public class CheckoutAgent {
    
    // holds the x-position
    private int xpos;

    // holds the y-position
    private int ypos;

    // holds a queue of Customers
    private MyQueue<Customer> myQueue;

    // constructor method
    public CheckoutAgent(int x, int y) {
        this.myQueue = new MyQueue<Customer>();
        this.xpos = x;
        this.ypos = y;
    }

    // add a Customer to its queue
    public void addCustomerToQueue(Customer c) {
        this.myQueue.offer(c);
    }

    // returns the number of Customers in its queue
    public int getNumInQueue() {
        return this.myQueue.size();
    }

    // returns the total number of items in the queue
    public int getSumItems() {
        int sum = 0;
        for (Customer c: this.myQueue) {
            sum += c.getNumItems();
        }
        return sum;
    }

    // draws the queues with their Customers
    // draws the total number of items in the queue
    public void draw(Graphics2D g) {
        // draws the CheckoutAgent with a height proportional to the number of Customers in the queue 
        // g.fill(new Rectangle2D.Double(this.xpos, this.ypos - 10*this.getNumInQueue(), 10, 10*this.getNumInQueue())); 

        // draws the queues with their Customers
        Random ran = new Random();
        for (int i = 0; i < this.getNumInQueue(); i++) {
            // draws face
            double ranColor1 = ran.nextDouble();
            if (ranColor1 <= 0.33) {
                g.setColor(Color.DARK_GRAY);
                g.fill(new Rectangle2D.Double(this.xpos, this.ypos - 16*i, 13, 13)); 
                g.setColor(Color.WHITE);
                g.fill(new Ellipse2D.Double(this.xpos + 3, this.ypos - 16*i + 5.5, 2, 2));
                g.fill(new Ellipse2D.Double(this.xpos + 8, this.ypos - 16*i + 5.5, 2, 2));
                g.draw(new Arc2D.Double(this.xpos + 5, this.ypos - 16*i + 9.5, 3, 2, 180, 180, Arc2D.OPEN));
            }
            if (ranColor1 > 0.33 && ranColor1 <= 0.66) {
                g.setColor(Color.ORANGE);
                g.fill(new Rectangle2D.Double(this.xpos, this.ypos - 16*i, 13, 13)); 
                g.setColor(Color.BLACK);
                g.fill(new Ellipse2D.Double(this.xpos + 3, this.ypos - 16*i + 5.5, 2, 2));
                g.fill(new Ellipse2D.Double(this.xpos + 8, this.ypos - 16*i + 5.5, 2, 2));
                g.draw(new Arc2D.Double(this.xpos + 5, this.ypos - 16*i + 9.5, 3, 2, 180, 180, Arc2D.OPEN));
            }
            if (ranColor1 > 0.66) {
                g.setColor(Color.PINK);
                g.fill(new Rectangle2D.Double(this.xpos, this.ypos - 16*i, 13, 13)); 
                g.setColor(Color.BLACK);
                g.fill(new Ellipse2D.Double(this.xpos + 3, this.ypos - 16*i + 5.5, 2, 2));
                g.fill(new Ellipse2D.Double(this.xpos + 8, this.ypos - 16*i + 5.5, 2, 2));
                g.draw(new Arc2D.Double(this.xpos + 5, this.ypos - 16*i + 9.5, 3, 2, 180, 180, Arc2D.OPEN));
            }

            // draws hair
            double ranColor2 = ran.nextDouble();
            if (ranColor2 < 0.5) {
                g.setColor(Color.LIGHT_GRAY);
            }
            if (ranColor2 >= 0.5 && ranColor2 < 1) {
                g.setColor(Color.BLACK);
            }
            g.fill(new Arc2D.Double(this.xpos - 6.5, this.ypos - 16*i - 5, 13, 10, 270, 90, Arc2D.PIE));
            g.fill(new Arc2D.Double(this.xpos + 6.5, this.ypos - 16*i - 5, 13, 10, 180, 90, Arc2D.PIE));
        }

        // draws the total number of items in the queue
        g.setColor(Color.RED.darker());
        String numItemString = String.valueOf(this.getSumItems());
        g.drawString(numItemString, this.xpos + 14, this.ypos + 12);
    }

    // update state at every time step
    public void updateState(Landscape scape) {
        // counts the total time steps of customers in the queue
        for (Customer c: this.myQueue) {
            c.incrementTime();
        }

        // checks out for the customer by decrementing the number of remaining items
        // adds finished customers to the completed list
        if (myQueue.peek() != null) {
            myQueue.peek().giveUpItem();
            if (myQueue.peek().getNumItems() == 0) {
                scape.addFinishedCustomer(myQueue.peek());
                myQueue.poll();
            }
        }
    }
}
