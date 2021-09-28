/*
 * File: LifeSimulation.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Mar. 1, 2021
 * Section B
 * Project 2: Conway's Game of Life
 * CS231
 */


import java.util.Random;

public class LifeSimulation {

    // runs the simulation
    public static void main(String[] args) {
        if (args.length <= 5) {
            System.out.println("usage: java LifeSimulation <number of rows> <number of columns> <landscape's scale> <density> <number of iterations>" +
                                "\nexample: java LifeSimulation 20 20 40 0.3 50");
        }
        else {
            Landscape landscape = new Landscape(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            LandscapeDisplay lDisplay = new LandscapeDisplay(landscape, Integer.parseInt(args[2]));
            Random gen = new Random();
            double density = Double.parseDouble(args[3]);

            // initialize the grid to be 30% full
            for (int i = 0; i < landscape.getRows(); i++) {
                for (int j = 0; j < landscape.getCols(); j++ ) { 
                    landscape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
                }
            }
            
            for (int i = 0; i < Integer.parseInt(args[4]); i++) {
                landscape.advance();
                lDisplay.repaint();

                try {
                    Thread.sleep(250);
                } 
                catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                }
            }
        }
    }
}
