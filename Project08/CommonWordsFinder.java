/*
 * File: CommonWordsFinder.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Apr 26th, 2021
 * Project 8: Analysis: Word Trends
 * CS231
 */

import java.util.Comparator;

public class CommonWordsFinder {

    // holds a WordCounter2 object
    private WordCounter2 wordCounter;

    // holds a PQHeap object
    private PQHeap<KeyValuePair<String, Integer>> pqHeap;

    // constructor method - initializes the WordCounter2 and PQHeap objects
    public CommonWordsFinder() {
        this.wordCounter = new WordCounter2("bst");
        this.pqHeap = new PQHeap<>(new ComparatorKVP());
    }

    // returns the top value of the the heap
    public KeyValuePair<String, Integer> getTop() {
        return this.pqHeap.remove();
    }

    // reads the file into a BSTMap
    // inserts all the words into a PQHeap
    public void findCommonWords(String filename, int N) {
        this.pqHeap.clear();
        this.wordCounter.clearMap();
        this.wordCounter.analyze(filename);

        System.out.println("Statistics for file: " + filename);
        for (KeyValuePair<String, Integer> kVP: this.wordCounter.getMap().entrySet()) {
            this.pqHeap.add(kVP);
        }
        System.out.println(N + " most common words are: \n");
        for (int i = 0; i < N; i++) {
            KeyValuePair<String, Integer> top = this.getTop();
            System.out.println(top.toString());
            System.out.println("Frequency: " + (double) top.getValue()/this.wordCounter.totalWordCount());
        }
    }
    
    // unit test
    public static void main(String[] args) {
        if (args.length <= 2) {
            System.out.println("usage: java CommonWordsFinder <N> <file name 1> ..." +
                                "\nexample: java CommonWordsFinder 10 reddit_comments_2008.txt");
        }
        else {
            CommonWordsFinder commonWordsFinder = new CommonWordsFinder();
            for (int i = 1; i < args.length; i++) {
                commonWordsFinder.findCommonWords(args[i], Integer.parseInt(args[0]));
            }
        }
    }
}
