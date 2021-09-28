/*
 * File: Grid.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Feb. 23, 2021
 * Section B
 * Lab 2: 2D Arrays
 * CS231
 */

import java.util.Random;

public class Grid {
    
    // unit test
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("usage: java filename + your command line argument");
        }
        else {
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i] + " ");
            }
        }

        int rows = Integer.parseInt(args[0]);
        int columns = Integer.parseInt(args[1]);

        Random ran = new Random();
        String[][] ranger = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ranger[i][j] = new String();
                ranger[i][j] = String.valueOf((char) (ran.nextInt(26) + 97));
            }
        }

        for (int i = 0; i < ranger.length; i++) {
            for (int j = 0; j < ranger[i].length; j++) {
                System.out.print(ranger[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}