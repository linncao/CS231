/*
 * File: CatSocialAgentSimulation.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 22nd, 2021
 * Project 4: Agent-Based Simulations
 * CS231
 */

import java.util.Random;

public class CatSocialAgentSimulation {

    // runs simulation
    public static void main(String[] args) {
        if (args.length <= 4) {
            System.out.println("usage: java CatSocialAgentSimulation <landscape width> <landscape height> <agent's radius> <number of normal agents> <number of criminal agents>" +
                               "\nexample: java CatSocialAgentSimulation 500 500 20 100 5");
        }
        else {
            Landscape landscape = new Landscape(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            Random gen = new Random();
            LandscapeDisplay display = new LandscapeDisplay(landscape);
            for (int i = 0; i < Integer.parseInt(args[3]); i++) {
                landscape.addAgent(new CatSocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[2]), false, 1));
            }
            for (int i = 0; i < Integer.parseInt(args[3]); i++) {
                landscape.addAgent(new CatSocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[2]), false, 2));
            }
            for (int i = 0; i < Integer.parseInt(args[4]); i++) {
                landscape.addAgent(new CatSocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[2]), true, 1));
            }
            for (int i = 0; i < Integer.parseInt(args[4]); i++) {
                landscape.addAgent(new CatSocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[2]), true, 2));
            }
            for (int i = 0; i < 200; i++) {
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
