/*
 * File: Card.java
 * Author: Linn Cao Nguyen Phuong
 * Date: 02/22/2021
 * Section B
 * Project 1: Monte-Carlo Simulation: Blackjack
 * CS231
 */

import java.util.Random;

public class Card {

    // the numeric value of the card
    private int cardValue;

    // constructor - checks the range of the card
    public Card(int v) {
        if (1 <= v && v <= 11) {
            this.cardValue = v;
        }
        else {
            System.out.println("Warning: Card value out of range, cast to 10.");
            this.cardValue = 10;
        }
    }

    // returns the numeric value of the card
    public int getValue() {
        return this.cardValue;
    }

    // represents the Card object
    public String toString() {
        String s = String.valueOf(cardValue);
        return s;
    }

    // unit test
    public static void main(String[] args) {
        Card card1 = new Card(11);
        Card card2 = new Card(20);
        System.out.println("getValue method test: " + card1.getValue());
        System.out.println("toString method test: " + card1.toString());
        System.out.println("getValue method test: " + card2.getValue());
        System.out.println("toString method test: " + card2.toString());
    }
}