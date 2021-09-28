/*
 * File: Hand.java
 * Author: Linn Cao Nguyen Phuong
 * Date: 02/22/2021
 * Section B
 * Project 1: Monte-Carlo Simulation: Blackjack
 * CS231
 */

import Card.java;
import java.util.ArrayList;

public class Hand {

    // the ArrayList for hand
    private ArrayList<Card> hand;

    // inititalizes array list
    public Hand() {
        this.hand = new ArrayList<>();
    }

    // resets the hand to empty
    public void reset() {
        this.hand.clear();
    }
    
    // adds the card object to the hand
    public void add(Card card) {
        this.hand.add(card);
    }

    // returns the number of cards in the hand
    public int size() {
        return this.hand.size();
    }

    // returns the card with index i
    public Card getCard(int i) {
        return this.hand.get(i);
    }

    // returns the sum of the values of the cards in the hand
    public int getTotalValue() {
        int sum = 0;
        for (int i = 0; i < this.hand.size(); i++) {
            sum += this.hand.get(i).getValue();
        }
        return sum;
    }

    // returns the index of the card in the hand
    public int lastIndexOf(Card card) {
        return this.hand.lastIndexOf(card);
    }

    // replace the card with the index 
    public void replace(int index, Card card) {
        this.hand.set(index, card);
    }

    // returns a String that has the contents of the hand
    public String toString() {
        String res = "Cards in hand:";
        for (int i = 0; i < this.size(); i++) {
            res += " " + this.getCard(i);
        }
        res += "\nNumber of cards in hand: " + this.size() 
                + "\nSum of cards in hand: " + this.getTotalValue();
        return res;
    }

    // unit test
    public static void main(String[] args) {
        Hand hand1 = new Hand();
        Card card1 = new Card(2);
        Card card2 = new Card(10);
        Card card3 = new Card(20);
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);
        System.out.println(hand1.toString());
        System.out.println("Card at index 1: " + hand1.getCard(1));
        hand1.reset();
        System.out.println(hand1.toString());
    }
}