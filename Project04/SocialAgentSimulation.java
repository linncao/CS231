/*
 * File: SocialAgentSimulation.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 22nd, 2021
 * Project 4: Agent-Based Simulations
 * CS231
 */

import java.util.Random;

public class SocialAgentSimulation {
    
    // runs simulation
    public static void main(String[] args) {
        if (args.length <= 4) {
            System.out.println("usage: java SocialAgentSimulation <landscape width> <landscape height> <agent's radius> <number of normal agents> <number of criminal agents>" +
                               "\nexample: java SocialAgentSimulation 500 500 15 200 10");
        }
        else {
            Landscape landscape = new Landscape(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            Random gen = new Random();
            LandscapeDisplay display = new LandscapeDisplay(landscape);
            for (int i = 0; i < Integer.parseInt(args[3]); i++) {
                landscape.addAgent(new SocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[2]), false));
            }
            for (int i = 0; i < Integer.parseInt(args[4]); i++) {
                landscape.addAgent(new SocialAgent(gen.nextDouble()*landscape.getWidth(), gen.nextDouble()*landscape.getHeight(), Integer.parseInt(args[2]), true));
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
