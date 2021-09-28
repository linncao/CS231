/*
 File: Die.java
 Author: Dale Skrien, Linn Cao Nguyen Phuong
 Date: Feb. 15, 2021
 Proj: 0
 */

import java.util.Random;

// This class represents a n-sided die.
public class Die03
{
    // the current side up of the die.
    private int sideUp = 1;

    // a Random object for generating random values.
    private Random rand;

    // the number of sides of the die.
    private int numberOfSides = 1;

    // constructor - initializes the fields
    public Die03(int n) {
        if (n >= 1) {
            this.sideUp = 1;
            this.rand = new Random();
            this.numberOfSides = n;
        }
        else {
            this.sideUp = 1;
            this.rand = new Random();
            this.numberOfSides = 1;
        }
    }
    
    // randomly selects a new side up for the die
    public void roll() {
        this.sideUp = (int) (this.rand.nextDouble()*this.numberOfSides + 1);
    }
    
    // returns the value on the current side up
    public int getSideUp() {
        return this.sideUp;
    }
    
    // returns the number of sides of the die
    public int getNumberOfSides() {
        return this.numberOfSides;
    }

    // unit test
    public static void main(String[] args) {
        // Die03 die1 = new Die03(12);
        // System.out.println("this die has " + die1.getNumberOfSides() + " sides");
        // die1.roll();
        // System.out.println("side up: " + die1.getSideUp());
        Die03 b = new Die03(12); 
        Object a = b;
        System.out.println(a);
    }
}

/*

2. 
for(int i = 0; i <= 4; i++) {
    System.out.println(i);
}
Answer:
0 
1
2
3

for(int i = 100; i > 0; i = i/2) {
    System.out.println(i);
}
Answer:
100
50
25
12
6
3
1

for(int i = 0; true; i += 2;) {
    System.out.println(i);
}
Answer:
0
2
4
6
8
10
...
2n (n in N)

*/