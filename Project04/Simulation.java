/*
 * File: Simulation.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 22nd, 2021
 * Project 4: Agent-Based Simulations
 * CS231
 */

import java.util.Random;

public class Simulation {
    
    // runs simulation
    public static void main(String[] args) {
        if (args.length <= 9) {
            System.out.println("usage: java Simulation <landscape width> <landscape height> <number of social agents category 1> <number of social agents category 2>" +
                               "<number of criminal agents category 1> <number of criminal agents category 2> <number of normal social agents>" +
                               "<number of normal criminal agents> <agent's radius> <number of simulations>" + 
                               "\nexample: java Simulation 500 500 100 100 5 5 100 10 20 200");
        }
        else {
            Landscape landscape = new Landscape(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            Random gen = new Random();
            LandscapeDisplay display = new LandscapeDisplay(landscape);
            for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                landscape.addAgent(new CatSocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[8]), false, 1));
            }
            for (int i = 0; i < Integer.parseInt(args[3]); i++) {
                landscape.addAgent(new CatSocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[8]), false, 2));
            }
            for (int i = 0; i < Integer.parseInt(args[4]); i++) {
                landscape.addAgent(new CatSocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[8]), true, 1));
            }
            for (int i = 0; i < Integer.parseInt(args[5]); i++) {
                landscape.addAgent(new CatSocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[8]), true, 2));
            }
            for (int i = 0; i < Integer.parseInt(args[6]); i++) {
                landscape.addAgent(new SocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[8]), false));
            }
            for (int i = 0; i < Integer.parseInt(args[7]); i++) {
                landscape.addAgent(new SocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[8]), true));
            }
            for (int i = 0; i < Integer.parseInt(args[9]); i++) {
                landscape.updateAgents();
                display.repaint();
                try {
                    Thread.sleep(200);
                } 
                catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                }
            }
        }
    }

}
