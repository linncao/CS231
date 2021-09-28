/*
 * File: WCTester.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: April 5th, 2021
 * Project 6: Word Frequency
 * CS231
 */

public class WCTester {

    // unit test
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        wordCounter.analyze("counttest.txt");
        wordCounter.writeWordCountFile("counts_ct.txt");
        wordCounter.readWordCountFile("counts_ct.txt");
        wordCounter.writeWordCountFile("counts_ct_v2.txt");
    }
}
