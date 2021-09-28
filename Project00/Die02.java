import java.util.ArrayList;

/*
 File: Die02.java
 Author: Dale Skrien, Linn Cao Nguyen Phuong
 Date: Feb. 12, 2021
 Proj: 0
 */

// This class represents a 6-sided die.
public class Die02
{
    // the current side up of the die.
    private int sideUp = 1;
        
    
    // randomly selects a new side up for the die
    public void roll() {
        this.sideUp = (int) (Math.random()*6 + 1);
    }
    
    // returns the value on the current side up
    public int getSideUp() {
        return this.sideUp;
    }
    
    // unit test
    public static void main(String[] args) {
        Die02 die1 = new Die02();
        die1.roll();
        System.out.print("side up: " + die1.getSideUp() + " -> is ");
        if (die1.sideUp == 6) {
            System.out.println("6");
        }
        else {
            System.out.println("not 6");
        }
        ArrayList list = new ArrayList();
        for (int i = 0; i < 12; i++) {
            die1.roll();
            list.add(die1.getSideUp());
            System.out.println(list.get(i));
        }
    }
}

/* I have no questions about the video. Thank you so much! */