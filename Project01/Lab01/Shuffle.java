/*
 * File: Shuffle.java
 * Author: Linn Cao Nguyen Phuong
 * Date: 02/16/2021
 * Section B
 * Lab 1: Java and ArrayLists
 * CS231
 */

import java.util.ArrayList;
import java.util.Random; 

public class Shuffle {
    
    // randomly removes elements from 1st ArrayList
    // adds those removed elements to 2nd ArrayList
    public static void randomize(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            Integer x = arr1.remove(ran.nextInt(arr1.size()));
            arr2.add(x);
            System.out.print(" " + x);
        }
        
    }

    // unit test
    public static void main(String[] args) {
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        Random ran = new Random();
        for (int i = 1; i < 11; i++) {
            // int val = ran.nextInt(100);
            // arr.add(val);
            // System.out.println(val);
            arr1.add(i);
            System.out.println(i);
        }
        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        System.out.print("\nShuffle: ");
        Shuffle.randomize(arr1, arr2);
        for (int i = 0; i < 10; i++) {
            Integer y = arr2.remove(ran.nextInt(arr2.size()));
            System.out.println("\nRemoved value: " + y);
            if (arr2.size() > 1) {
                System.out.print("Remaining values:");
                for (int j = 0; j < arr2.size(); j++) {
                    Integer z = arr2.get(j);
                    System.out.print(" " + z);
                }
            }
            else {
                System.out.print("Remaining value:");
                for (int j = 0; j < arr2.size(); j++) {
                    Integer z = arr2.get(j);
                    System.out.print(" " + z);
                }
            }
        }
    }
}