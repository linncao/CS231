/*
 * File: Interactive.java
 * Author: Linn Cao Nguyen Phuong
 * Date: 02/22/2021
 * Section B
 * Project 1: Monte-Carlo Simulation: Blackjack
 * CS231
 */

import Blackjack.java;
import java.util.Scanner;

public class Interactive {
    
    // an int to count the number of games the dealer wins
    private int countDealer;

    // an int to count the number of games that are pushes
    private int countPush;

    // an int to count the number of games the player wins
    private int countPlayer;

    // blackjack object - stores the reshuffleCutoff and set up a game
    private Blackjack blackjack;

    // constructor - plays an interactive blackjack game
    public Interactive(int numOfGames) {
        this.blackjack = new Blackjack(26);
        System.out.println("\nRESHUFFLE CUTOFF: " + this.blackjack.getReshuffleCutoff());
        for (int i = 0; i < numOfGames; i++) {
            int one = this.blackjack.gameInteractive(true);
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

    // prints out the results of the game
    public void results() {
        int numOfGames = this.countPlayer + this.countDealer + this.countPush;
        System.out.println("\n     Results\nNumber of games the player wins: " + this.countPlayer
                            + "\nPercentage of the player winning: " + ((float) this.countPlayer*100/numOfGames) + "%"
                            + "\nNumber of games that are pushes: " + this.countPush
                            + "\nPercentage of pushes: " + ((float) this.countPush*100/numOfGames) + "%"
                            + "\nNumber of games the dealer wins: " + this.countDealer
                            + "\nPercentage of the dealer winning: " + ((float) this.countDealer*100/numOfGames) + "%");
    }

    // unit test
    public static void main(String[] args) {
        Scanner numGames = new Scanner(System.in);
        System.out.println("How many games of blackjack would you like to play?");
        int numOfGames = numGames.nextInt();
        Interactive interactive = new Interactive(numOfGames);
        interactive.results();
    }
}