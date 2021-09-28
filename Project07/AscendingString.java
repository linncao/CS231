/*
 * File: AscendingString.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 30th, 2021
 * Lab 6: Binary Search Trees and Sets
 * CS231
 */

import java.util.Comparator;

public class AscendingString implements Comparator<String> {
    
    // compares two strings lexicographically
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
