/*
 * File: Simulation.java
 * Author: Linn Cao Nguyen Phuong
 * Date: 02/22/2021
 * Section B
 * Project 1: Monte-Carlo Simulation: Blackjack
 * CS231
 */

import Blackjack.java;

public class Simulation {

    // the blackjack object
    private Blackjack blackjack;

    // an int to count the number of games the dealer wins
    private int countDealer;

    // an int to count the number of games that are pushes
    private int countPush;

    // an int to count the number of games the player wins
    private int countPlayer;

    // constructor - a simulation of numOfGames blackjack games
    // from one single blackjack object
    public Simulation(int numOfGames) {
        this.blackjack = new Blackjack(26);
        for (int i = 0; i < numOfGames; i++) {
            int one = this.blackjack.game(true);
            if (one == 1) {
                this.countPlayer++;
            }
            if (one == 0) {
                this.countPush++;
            }
            if (one == -1) {
                this.countDealer++;
            }
        }
    }

    // unit test
    public static void main(String[] args) {
        Simulation simulation = new Simulation(1000);
        int numOfGames = simulation.countPlayer + simulation.countDealer + simulation.countPush;
        System.out.println("\n     Results\nNumber of games the player wins: " + simulation.countPlayer
                            + "\nPercentage of the player winning: " + ((float) simulation.countPlayer*100/numOfGames) + "%"
                            + "\nNumber of games that are pushes: " + simulation.countPush
                            + "\nPercentage of pushes: " + ((float) simulation.countPush*100/numOfGames) + "%"
                            + "\nNumber of games the dealer wins: " + simulation.countDealer
                            + "\nPercentage of the dealer winning: " + ((float) simulation.countDealer*100/numOfGames) + "%");
    }
}