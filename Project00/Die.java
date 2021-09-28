/*
 File: Die.java
 Author: Dale Skrien, Linn Cao Nguyen Phuong
 Date: Feb. 10, 2021
 Proj: 0
 */

// This class represents a 7-sided die.
public class Die
{
    // the current side up of the die.
    private int sideUp = 1;
        
    
    // randomly selects a new side up for the die
    public void roll() {
        this.sideUp = (int) (Math.random()*7 + 1);
    }
    
    // returns the value on the current side up
    public int getSideUp() {
        return this.sideUp;
    }
    
    // prints "6" if the current side is 6
    // otherwise prints "not 6"
    public String isSix() {
        if (this.sideUp == 6) {
            // System.out.println("6");
            isSix() = "6";
        }
        else {
            // System.out.println("not 6");
            isSix() = "not 6";
        }
    }

    // unit test
    public static void main(String[] args) {
        Die die1 = new Die();
        Die die2 = new Die();
        die1.roll();
        die2.roll();
        System.out.println("Die 1's side up: " + die1.getSideUp() + "->" + die1.isSix());
        System.out.println("Die 2's side up: " + die2.getSideUp() + "->" + die2.isSix());
        System.out.println("Sum of 2 dice's sides: " + Integer.sum(die1.getSideUp(), die2.getSideUp()));
    }
}

/* 
 I currently have no questions about the video, and I would like future class sessions to be recorded. Thank you so much! 
 */