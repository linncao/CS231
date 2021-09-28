/*
 * File: recursion.java
 * Author: Linn Cao Nguyen Phuong
 * Date: Feb. 23, 2021
 */

public class recursion {

    // an auxiliary recursive function for sumAll to call
    public static int sumInternal(int[] data, int n) {
        if (n <= 0) {
            return 0; 
        }
        else {
            return (sumInternal(data, n - 1) + data[n - 1]); 
        } 
    }

    // returns the sum of all the integers in the data array
    public static int sumAll(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }
        else {
            return sumInternal(data, data.length);
        }
    }

    // unit test
    public static void main(String[] args) {
        int[] data = {1,2,3,4,5};
        System.out.println(sumAll(data));
    }
}

/* 2.
   a. 3, 2, 1,
   b. 1, 2, 3,
   c. 3, 2, 1,
      1, 2, 3,
   d. 1,
      2, 
      1,
      3,
      1,
      2,
      1,
   4. I have no questions about the videos.
 */