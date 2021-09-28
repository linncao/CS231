/*
 * File: IntPQ.java
 * Author: Dale Skrien
 * Date: April 4, 2021
 */
 
import java.util.*;

public class IntPQ
{
    private ArrayList<Integer> data;

    public IntPQ() {
        this.data = new ArrayList<>();
    }

    public int size() {
        return this.data.size();
    }

    // Adds a new item to the PQ
    // time complexity: O(1)
    public void add(int item) {
        this.data.add(item);
    }

    // Removes and returns the top priority item from the PQ.
    // The smallest integer in the PQ is the one with highest priority.
    // Returns Integer.MAX_VALUE if the PQ is empty
    // time complexity: O(n^2)
    public int remove() {
        if (this.size() != 0) {
            int smallest = this.data.get(0);
            int index = 0;
            for (int i = 0; i < this.size(); i++) {
                if (this.data.get(i) < smallest) {
                    smallest = this.data.get(i);
                    index = i;
                }
            }
            this.data.remove(index);
            return smallest;
        }
        return Integer.MAX_VALUE;
    }

    // unit test.  It should output the values 1, 3, 5, 6 in that order
    public static void main(String[] args) {
        IntPQ pq = new IntPQ();
        pq.add(3);
        pq.add(5);
        pq.add(1);
        pq.add(6);
        while(pq.size() > 0)
            System.out.print(pq.remove() + " ");
        System.out.println();
    }
}

/* 2.3. This is not a good implementation of a priority queue to use in PQSort
    for sorting integers because the time complexity for the method will be O(n^3)
    which is too long.
 */