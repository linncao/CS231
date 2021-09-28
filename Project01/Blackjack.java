/*
 * File: Blackjack.java
 * Author: Linn Cao Nguyen Phuong
 * Date: 02/22/2021
 * Section B
 * Project 1: Monte-Carlo Simulation: Blackjack
 * CS231
 */

import Card.java;
import Hand.java;
import Deck.java;
import java.util.Scanner;

public class Blackjack {
    
    // new deck object for the game - 52 cards, 4 each of cards with values 2-9 and 11, and 16 cards with the value 10
    private Deck deck = new Deck();

    // player's and dealer's hand objects
    private Hand playerHand = new Hand();
    private Hand dealerHand = new Hand();

    // card object
    private Card card;

    // the reshuffle cutoff
    private int reshuffleCutoff = 26;

    // the user input object to pick card
    private Scanner pick;

    // the user input object to pick card
    private Scanner option;

    // resets the game
    public void reset() {
        this.deck.shuffle();
        if (this.deck.size() < this.reshuffleCutoff) {
            this.playerHand = new Hand();
            this.dealerHand = new Hand();
            this.deck = new Deck();
            this.deck.shuffle();
        }
        else {
            this.playerHand.reset();
            this.dealerHand.reset();
        }
    }

    // constructor - stores the reshuffleCutoff and set up a game
    public Blackjack(int reshuffleCutoff) {
        this.reshuffleCutoff = reshuffleCutoff;
        this.reset();
    }

    // deal out two cards to both players from the Deck
    public void deal() {
        this.playerHand.add(this.deck.deal());
        this.playerHand.add(this.deck.deal());
        this.dealerHand.add(this.deck.deal());
        this.dealerHand.add(this.deck.deal());
    }

    // returns false if the player goes over 21 
    public boolean playerTurn() {
        if (this.playerHand.getTotalValue() > 21) {
            return false;
        }
        else {
            return true;
        }
    }

    // returns false if the dealer goes over 21
    public boolean dealerTurn() {
        if (this.dealerHand.getTotalValue() > 21) {
            return false;
        }
        else {
            return true;
        }
    }

    // assigns the new cutoff value to the internal reshuffle cutoff field
    public void setReshuffleCutoff(int cutoff) {
        this.reshuffleCutoff = cutoff;
    }

    // returns the current value of the reshuffle cutoff field
    public int getReshuffleCutoff() {
        return this.reshuffleCutoff;
    }

    // player interactive method #1
    // allows player to choose the value of Ace (1 or 11) if they are dealt with Ace(s)
    public void aceOptionAfterDealt() {
        this.option = new Scanner(System.in);
        int aceOptionAfterDealt = 0;
        if (this.playerHand.getCard(0).getValue() == 11 || this.playerHand.getCard(1).getValue() == 11) {
            if (this.playerHand.getCard(0).getValue() == 11 && this.playerHand.getCard(1).getValue() == 11) {
                System.out.println(this.toString() + "\n"
                                    + "\nYou have been dealed with two Aces. Your hand's sum is currently 22."
                                    + "\nAce value options: 1 and 11."
                                    + "\nSum of your hand if one Ace is changed to 1: 12"
                                    + "\nSum of your hand if both Aces are changed to 1: 2"
                                    + "\nWould you like to change the value of one or both Aces? (type 1 or 2 to proceed)");
                aceOptionAfterDealt = option.nextInt();
                this.card = new Card(1);
                if (aceOptionAfterDealt == 1) {
                    this.playerHand.replace(0, this.card);
                }
                else {
                    this.playerHand.replace(0, this.card);
                    this.playerHand.replace(1, this.card);
                }
            }
            else {
                System.out.println(this.toString() + "\n"
                                    + "\nYou have been dealed with an Ace. Ace value options: 1 and 11."
                                    + "\nSum of your hand if Ace = 1: " + (this.playerHand.getTotalValue()-10)
                                    + "\nSum of your hand if Ace = 11: " + this.playerHand.getTotalValue()
                                    + "\nWhich value would you like to use? (type 1 or 11 to proceed)"); 
                aceOptionAfterDealt = option.nextInt();
                this.card = new Card(aceOptionAfterDealt);
                if (this.playerHand.getCard(0).getValue() == 11) {
                    this.playerHand.replace(0, this.card);
                }
                else {
                    this.playerHand.replace(1, this.card);
                }
            }
        }
    }

    // player interactive method #2
    // allows player to choose the index of card in the deck they would want to pick
    // allows player to choose the value of Ace (1 or 11) after picking
    public void playerTurnInteractive() {
        this.pick = new Scanner(System.in);
        this.option = new Scanner(System.in);
        int pickOption = 0;
        int aceOption = 0;
        while (this.playerHand.getTotalValue() < 16 && this.playerTurn() == true && this.dealerTurn() == true) {
            System.out.println("\nWhich index of card would you like to pick? (from 0 to " + (this.deck.size()-1) + ")");
            pickOption = pick.nextInt();
            Card picked = this.deck.pick(pickOption);
            System.out.println("\nCard picked: " + picked);
            this.playerHand.add(picked);
            if (picked.getValue() == 11) {
                System.out.println("You have picked an Ace. Ace value options: 1 and 11."
                                    + "\nSum of your hand if Ace = 1: " + (this.playerHand.getTotalValue()-10)
                                    + "\nSum of your hand if Ace = 11: " + this.playerHand.getTotalValue()
                                    + "\nWhich value would you like to use? (type 1 or 11 to proceed)");     
                aceOption = option.nextInt();
                int pickIndex = this.playerHand.lastIndexOf(picked);
                this.card = new Card(aceOption);
                this.playerHand.replace(pickIndex, this.card);
                System.out.println("\n" + this.toString());
            }
            else {
                System.out.println(this.toString() + "\n");
            }
        }
    }

    // automatically replaces Ace(s) of value 11 for value 1 if the hand's sum is greater than 21
    // in auto mode, corrects both hands
    // in interactive mode, only corrects dealer's hand
    public void autoAce(boolean interactive) {
        this.card = new Card(1);
        if (interactive == false) {
            if (this.playerHand.getTotalValue() > 21) {
                for (int i = 0; i < this.playerHand.size(); i++) {
                    if (this.playerHand.getCard(i).getValue() == 11) {
                        this.playerHand.replace(this.playerHand.lastIndexOf(this.playerHand.getCard(i)), this.card);
                    }
                }
            }
            if (this.dealerHand.getTotalValue() > 21) {
                for (int i = 0; i < this.dealerHand.size(); i++) {
                    if (this.dealerHand.getCard(i).getValue() == 11) {
                        this.dealerHand.replace(this.dealerHand.lastIndexOf(this.dealerHand.getCard(i)), this.card);
                    }
                }
            }
        }
        else {
            if (this.dealerHand.getTotalValue() > 21) {
                for (int i = 0; i < this.dealerHand.size(); i++) {
                    if (this.dealerHand.getCard(i).getValue() == 11) {
                        this.dealerHand.replace(this.dealerHand.lastIndexOf(this.dealerHand.getCard(i)), this.card);
                    }
                }
            }
        }
    }

    // returns a String that has represents the state of the game
    public String toString() {
        String game = "\nCards in player's hand:";
        for (int i = 0; i < this.playerHand.size(); i++) {
            game += " " + this.playerHand.getCard(i);
        }
        game += "\nSum of player's hand: " + this.playerHand.getTotalValue()
                + "\nCards in dealer's hand:";
        for (int i = 0; i < this.dealerHand.size(); i++) {
            game += " " + this.dealerHand.getCard(i);
        }
        game += "\nSum of dealer's hand: " + this.dealerHand.getTotalValue();
        game += "\n     Cards remaining in deck: " + this.deck.size(); 
        return game;
    }

    // plays a single game of Blackjack 
    // returns a -1 if the dealer wins, 0 in case of a push, and a 1 if the player wins
    public int game(boolean verbose) {
        // initial game
        this.reset();
        this.deal();
        this.autoAce(false);
        if (verbose == true) {
            System.out.println("\n     Initial hands" + this.toString());
        }
        // mid game
        while ((this.playerHand.getTotalValue() < 16 || this.dealerHand.getTotalValue() < 17) 
                && this.playerTurn() == true && this.dealerTurn() == true) {
            while (this.playerHand.getTotalValue() < 16 && this.playerTurn() == true && this.dealerTurn() == true) {
                this.playerHand.add(this.deck.deal());
            }
            while (this.dealerHand.getTotalValue() < 17 && this.playerTurn() == true && this.dealerTurn() == true) {
                this.dealerHand.add(this.deck.deal());
            }
            this.autoAce(false);
        }
        // end game
        if (verbose == true) {
            if (this.playerHand.getTotalValue() <= 21 && this.playerHand.getTotalValue() == this.dealerHand.getTotalValue() &&
                !((this.playerHand.getTotalValue() == 21 && this.playerHand.size() == 2) || 
                (this.dealerHand.getTotalValue() == 21 && this.dealerHand.size() == 2)) ||
                this.playerHand.getTotalValue() > 21 && this.dealerHand.getTotalValue() > 21) {
                System.out.println("     Final hands" + this.toString());
                System.out.println("\nIt's a push!");
                return 0;
            }
            if (this.playerHand.getTotalValue() <= 21 && this.dealerHand.getTotalValue() > 21 ||
                this.playerHand.getTotalValue() <= 21 && this.playerHand.getTotalValue() > this.dealerHand.getTotalValue() ||
                this.playerHand.getTotalValue() == 21 && this.playerHand.size() == 2) {
                System.out.println("\n     Final hands" + this.toString());
                System.out.println("\nPlayer wins!");
                return 1;
            }
            if (this.dealerHand.getTotalValue() == 21 && this.dealerHand.size() == 2) {
                System.out.println("\n     Final hands" + this.toString());
                System.out.println("\nDealer wins!");
                return -1;
            }
            else {
                System.out.println("\n     Final hands" + this.toString());
                System.out.println("\nDealer wins!");
                return -1;
            }
        }
        else {
            return 2;
        }
    }

    // interactive version of Blackjack class's game method
    // plays a single game of Blackjack 
    // allows player to choose the index of card in the deck they would want to pick
    // allows player to choose the value of Ace (1 or 11)
    // returns a -1 if the dealer wins, 0 in case of a push, and a 1 if the player wins
    public int gameInteractive(boolean verbose) {
        // initial game
        this.reset();
        this.deal();
        this.autoAce(true);
        this.aceOptionAfterDealt();
        if (verbose == true) {
            System.out.println("\n     Initial hands" + this.toString());
        }
        // mid game
        while ((this.playerHand.getTotalValue() < 16 || this.dealerHand.getTotalValue() < 17) 
                && this.playerTurn() == true && this.dealerTurn() == true) {
            this.playerTurnInteractive();
            while (this.dealerHand.getTotalValue() < 17 && this.playerTurn() == true && this.dealerTurn() == true) {
                this.dealerHand.add(this.deck.deal());
            }
            this.autoAce(true);
        }
        // end game
        if (verbose == true) {
            if (this.playerHand.getTotalValue() <= 21 && this.playerHand.getTotalValue() == this.dealerHand.getTotalValue() ||
                this.playerHand.getTotalValue() > 21 && this.dealerHand.getTotalValue() > 21) {
                System.out.println("     Final hands" + this.toString());
                System.out.println("\nIt's a push!");
                return 0;
            }
            if (this.playerHand.getTotalValue() <= 21 && this.dealerHand.getTotalValue() > 21 ||
                this.playerHand.getTotalValue() <= 21 && this.playerHand.getTotalValue() > this.dealerHand.getTotalValue() ||
                this.playerHand.getTotalValue() == 21 && this.playerHand.size() == 2) {
                System.out.println("\n     Final hands" + this.toString());
                System.out.println("\nPlayer wins!");
                return 1;
            }
            if (this.dealerHand.getTotalValue() == 21 && this.dealerHand.size() == 2) {
                System.out.println("\n     Final hands" + this.toString());
                System.out.println("\nDealer wins!");
                return -1;
            }
            else {
                System.out.println("\n     Final hands" + this.toString());
                System.out.println("\nDealer wins!");
                return -1;
            }
        }
        else {
            return 2;
        }
    }

    // unit test
    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack(26);
        System.out.println("\nRESHUFFLE CUTOFF: " + blackjack.getReshuffleCutoff());
        for (int i = 0; i < 3; i++) {
            blackjack.game(true);
        }
        blackjack.setReshuffleCutoff(40);
        System.out.println("\n*****\nWARNING NEW RESHUFFLE CUTOFF: " + blackjack.getReshuffleCutoff() + "\n");
        blackjack.reset();
        System.out.println(blackjack.toString());
    }
}