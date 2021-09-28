/*
 * File: FileWriter.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: April 5th, 2021
 * Project 6: Word Frequency
 * CS231
 */

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileWriter {

    private WordCounter wordCounter;

    public void write(String filename) {
        throw IOException {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter();
            printWriter.print("totalWordCount: " + this.wordCounter.getTotalWordCount());
        }
    }
    
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        wordCounter.analyze("counttest.txt");
        wordCounter.writeWordCountFile("counttest.txt");
    }
}
