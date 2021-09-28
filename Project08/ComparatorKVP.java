/*
 * File: ComparatorKVP.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Apr 20th, 2021
 * Lab 8: Heaps
 * CS231
 */

import java.util.Comparator;

public class ComparatorKVP implements Comparator<KeyValuePair<String, Integer>> {
    
    // compares the values of 2 KeyValuePairs
    public int compare(KeyValuePair<String, Integer> kVP1, KeyValuePair<String, Integer> kVP2) {
        return kVP1.getValue() - kVP2.getValue();
    }
}
