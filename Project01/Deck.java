/*
 * File: Deck.java
 * Author: Linn Cao Nguyen Phuong
 * Date: 02/22/2021
 * Section B
 * Project 1: Monte-Carlo Simulation: Blackjack
 * CS231
 */

import java.util.ArrayList;
import java.util.Random;
import Card.java;
import Hand.java;

public class Deck {

    // the ArrayList for deck
    private ArrayList<Card> deck;

    // builds a deck of 52 cards, 4 each of cards with values 2-9 and 11, and 16 cards with the value 10
    public void build() {
        this.deck = new ArrayList<>(52);
        for (int i = 0; i < 16; i++) {
            this.deck.add(new Card(10));
        }
        for (int i = 16; i < 20; i++) {
            this.deck.add(new Card(11));
        }
        for (int i = 20; i < 24; i++) {
            for (int j = 0; j < 8; j++) {
                this.deck.add(new Card(2 + j));
            }
        }
    }

    // constructor - builds a deck of 52 cards, 4 each of cards with values 2-9 and 11, and 16 cards with the value 10
    public Deck() {
        this.build();
    }

    // returns the number of cards in the deck
    public int size() {
        return this.deck.size();
    }

    // returns the top card (position zero) and removes it from the deck
    public Card deal() {
        Card topCard = this.deck.remove(0);
        return topCard;
    }

    // returns the card at position i and removes it from the deck
    public Card pick(int i) {
        Card cardi = this.deck.remove(i);
        return cardi;
    }

    // shuffles the deck
    public void shuffle() {
        Random ran = new Random();
        for (int i = 0; i < deck.size(); i++) {
            Card x = deck.remove(ran.nextInt(deck.size()-i));
            deck.add(x);
        }
    }

    // returns a String that has the contents of the deck
    public String toString() {
        String cont = "Size of deck: ";
        cont += this.size() + "\nCards in deck:";
        for (int i = 0; i < this.size(); i++) {
            cont += " " + this.deck.get(i);
        }
        return cont;
    }

    // unit test
    public static void main(String[] args) {
        Deck deck1 = new Deck();
        System.out.println(deck1.toString());
        deck1.deal();
        System.out.println(deck1.toString());
        deck1.pick(50);
        System.out.println(deck1.toString());
        deck1.shuffle();
        System.out.println(deck1.toString());
    }
}