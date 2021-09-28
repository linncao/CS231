/*
 * File: WordCounter.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: April 5th, 2021
 * Project 6: Word Frequency
 * CS231
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileWriter;
import java.util.Collections;

public class WordCounter {
    
    // holds the BSTMap with types <String, Integer>
    private BSTMap<String, Integer> bstMap;

    // holds the total word count
    private int countWord;

    // constructor method - makes an empty BSTMap, sets the total word count to zero
    public WordCounter() {
        this.bstMap = new BSTMap<>(new AscendingString());
        this.countWord = 0;
    }

    // generates the word counts from a file of words
    public void analyze(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            // split line into words
            // the regular expression can be interpreted as split on anything that is
            // not (^) (a-z or A-Z or 0-9 or ').
            while (line != null) {
                String[] words = line.split("[^a-zA-Z0-9']");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i].trim().toLowerCase();
                    // skips words with length 0
                    if (word.length() == 0) {
                        continue;
                    }
                    else {
                        Integer frequency = this.bstMap.get(word);
                        // checks if the word has already appeared in the Map
                        // if not, put the word into the Map with value 1
                        // if it already has, increment the value and put the word with the new value into the Map
                        if (frequency == null) {
                            this.bstMap.put(word, 1);
                        }
                        else {
                            frequency++;
                            this.bstMap.put(word, frequency);
                        }
                        this.countWord++;
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }

        catch(FileNotFoundException ex) {
            System.out.println("WordCounter.analyze():: unable to open file " + filename);
        }
        catch(IOException ex) {
            System.out.println("WordCounter.analyze():: error reading file " + filename);
        }
    }

    // returns the total word count
    public int getTotalWordCount() {
        return this.countWord;
    }

    // returns the number of unique words
    public int getUniqueWordCount() {
        return this.bstMap.keySet().size();
    }

    // returns the frequency value associated with this word
    public int getCount(String word) {
        Integer frequency = this.bstMap.get(word);
        return frequency == null ? 0 : frequency;
    }

    // returns the value associated with this word divided by the total word count
    public double getFrequency(String word) {
        double frequency = (double) this.getCount(word)/this.getTotalWordCount();
        return frequency;
    }

    // returns a string that gives an idea of the structure of the tree
    public String toString() {
        return this.bstMap.toString();
    }

    // writes the contents of the BSTMap to a word count file
    public void writeWordCountFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            fw.write("totalWordCount: " + this.getTotalWordCount());
            for (KeyValuePair<String, Integer> kVP: this.bstMap.entrySet()) {
                fw.write("\n" + kVP.toString());
            }
            fw.close();
        }

        catch(FileNotFoundException ex) {
            System.out.println("WordCounter.writeWordCountFile():: unable to open file " + filename);
        }
        catch(IOException ex) {
            System.out.println("WordCounter.writeWordCountFile():: error reading file " + filename);
        }
    }

    // reads the contents of a word count file and reconstructs
    // the fields of the WordCount object, including the BSTMap
    public void readWordCountFile(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int count = 0;

            while (true) {
                String line = bufferedReader.readLine();
                count++;
                // skips the first line
                if (count == 1) {
                    continue;
                }
                // breaks out of infinite while (true) loop
                if (line == null) {
                    break;
                }
                // splits the lines by spaces into pairs
                // puts the pairs into the temporary BST Map
                else {
                    String[] pairs = line.split("\\s+");
                    BSTMap<String, Integer> bstMapTemp = new BSTMap<>(new AscendingString());
                    bstMapTemp.put(pairs[0], Integer.valueOf(pairs[1]));
                }
            }
            fileReader.close();
            bufferedReader.close();
        }

        catch(FileNotFoundException ex) {
            System.out.println("WordCounter.readWordCountFile():: unable to open file " + filename);
        }
        catch(IOException ex) {
            System.out.println("WordCounter.readWordCountFile():: error reading file " + filename);
        }
    }

    // prints out the N most common words in the document
    public void findMostCommon(int N) {
        ArrayList<KeyValuePair<String, Integer>> arrTempEmp = new ArrayList<>();
        ArrayList<KeyValuePair<String, Integer>> arrTemp = this.bstMap.entrySet();
        // adds elements from entrySet with values in descending order to an empty ArrayList
        for (int i = 0; i < this.bstMap.size(); i++) {
            int frequency = arrTemp.get(0).getValue();
            int index = 0;
            for (int j = 0; j < arrTemp.size(); j++) {
                if (arrTemp.get(j).getValue() > frequency) {
                    frequency = arrTemp.get(j).getValue();
                    index = j;
                }
            }
            arrTempEmp.add(arrTemp.get(index));
            arrTemp.remove(index);
        }
        // prints out first N elements from the ArrayList with elements whose values are in descending order
        System.out.println(N + " most common words in the document:");
        for (int i = 0; i < N; i++) {
            System.out.println(arrTempEmp.get(i).toString());
        }
    }

    // prints the tree level by level from top to bottom
    public void printLevelOrder() {
        System.out.println("Level order print out:");
        this.bstMap.printLevelOrder();
    }

    // unit test
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        long start = System.currentTimeMillis();
        System.out.println("Start time: " + start);
        wordCounter.analyze(args[0]);
        wordCounter.writeWordCountFile(args[1]);
        long end = System.currentTimeMillis();
        System.out.println("End time: " + end);
        System.out.println("Total time: " + (end - start));
        System.out.println("Total word count: " + wordCounter.getTotalWordCount());
        System.out.println("Unique word count: " + wordCounter.getUniqueWordCount());
        wordCounter.findMostCommon(Integer.parseInt(args[1]));
        wordCounter.printLevelOrder();

        // System.out.println("Test total word count (should be 24): " + wordCounter.getTotalWordCount());
        // System.out.println("Test unique word count (should be 10): " + wordCounter.getUniqueWordCount());
        // System.out.println("Test get count of \"it\" (should be 4): " + wordCounter.getCount("it"));
        // System.out.println("Test get frequency of \"it\" (should be 0.1(6)): " + wordCounter.getFrequency("it"));
        // System.out.println("Test get count of \"best\" (should be 1): " + wordCounter.getCount("best"));
        // System.out.println("Test get frequency of \"best\" (should be 0.041(6)): " + wordCounter.getFrequency("best"));
        // System.out.println("Test get count of \"times\" (should be 2): " + wordCounter.getCount("times"));
        // System.out.println("Test get frequency of \"times\" (should be 0.08(3)): " + wordCounter.getFrequency("times"));
        // System.out.println(wordCounter.toString());
    }
}
