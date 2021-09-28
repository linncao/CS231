/*
 * File: PQHeap.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Apr 20th, 2021
 * Lab 8: Priority Queues: Heaps
 * CS231
 */

import java.util.Arrays;
import java.util.Comparator;

public class PQHeap<T> {

    // holds the number of elements in the heap
    private int size;

    // holds comparator
    private Comparator<T> comparator;

    // holds an array of Object type
    private Object[] heap;

    // constructor method - initializes the empty heap, 
    // sets the size to zero,
    // stores the comparator
    public PQHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.size = 0;
        this.heap = new Object[0];
    }

    // returns the number of elements in the heap
    public int size() {
        return this.size;
    }

    // adds the value to the heap and increments the size
    @SuppressWarnings("unchecked")
    public void add(T obj) {
        this.size++;
        int addedInd = this.size - 1;

        if (this.size > this.heap.length) {
            Object[] heapTemp = new Object[this.heap.length + 1];
            for (int i = 0; i < this.heap.length; i++) {
                heapTemp[i] = this.heap[i];
            }
            this.heap = heapTemp;
        }

        this.heap[addedInd] = obj;
        if (parent(addedInd) >= 0) {
            while (this.comparator.compare((T) this.heap[addedInd], (T) this.heap[this.parent(addedInd)]) > 0) {
                this.swap(addedInd, this.parent(addedInd));
                addedInd = this.parent(addedInd);
            }
        }
    }

    // returns the index of the parent
    private int parent(int i) {
        return (i - 1)/2;
    }

    // returns the index of the left child
    private int leftChild(int i) {
        return 2*i + 1;
    }

    // returns the index of the right child
    private int rightChild(int i) {
        return 2*i + 2;
    }

    // swap 2 indices in the heap
    @SuppressWarnings("unchecked")
    private void swap(int i1, int i2) {
        Object swap = this.heap[i1];
        this.heap[i1] = this.heap[i2];
        this.heap[i2] = (T) swap;
    }

    // handles the reheap process
    @SuppressWarnings("unchecked")
    private void reheap(int index) {
        if (index >= (this.size/2) && index <= this.size) {
            return;
        }
        if (this.comparator.compare((T) this.heap[index], (T) this.heap[this.leftChild(index)]) < 0 || 
            this.comparator.compare((T) this.heap[index], (T) this.heap[this.rightChild(index)]) < 0) {
            if (this.comparator.compare((T) this.heap[this.leftChild(index)], (T) this.heap[this.rightChild(index)]) > 0) {
                this.swap(index, this.leftChild(index));
                this.reheap(this.leftChild(index));
            }
            else {
                this.swap(index, this.rightChild(index));
                this.reheap(this.rightChild(index));
            }
        }
    }

    // removes and returns the highest priority element from the heap
    @SuppressWarnings("unchecked")
    public T remove() {
        if (this.size == 0) {
            return null;
        }
        T removed = (T) this.heap[0];
        this.size--;
        this.heap[0] = this.heap[this.size];
        this.reheap(0);
        this.heap = Arrays.copyOfRange(this.heap, 0, this.size);
        return removed;
    }

    // clears the heap
    public void clear() {
        int size = this.size;
        for (int i = 0; i < size; i++) {
            this.remove();
        }
    }

    // returns a string representation of the heap
    public String toString() {
        String heapString = "The heap contains: ";
        for (Object o: this.heap) {
            heapString += "\n" + o.toString();
        }
        return heapString;
    }

    // unit test
    public static void main(String[] args) {
        PQHeap<KeyValuePair<String, Integer>> pqHeap = new PQHeap<KeyValuePair<String, Integer>>(new ComparatorKVP());

        KeyValuePair<String, Integer> pair1 = new KeyValuePair<String, Integer>("Yay", 1);
        KeyValuePair<String, Integer> pair2 = new KeyValuePair<String, Integer>("Yo", 2);
        KeyValuePair<String, Integer> pair3 = new KeyValuePair<String, Integer>("Yau", 3);
        KeyValuePair<String, Integer> pair4 = new KeyValuePair<String, Integer>("Yep", 4);
        KeyValuePair<String, Integer> pair5 = new KeyValuePair<String, Integer>("Yeep", 5);
        KeyValuePair<String, Integer> pair6 = new KeyValuePair<String, Integer>("Yap", 6);

        pqHeap.add(pair1);
        System.out.println(pqHeap.toString());
        pqHeap.add(pair3);
        System.out.println(pqHeap.toString());
        pqHeap.add(pair2);
        System.out.println(pqHeap.toString());
        pqHeap.add(pair4);
        System.out.println(pqHeap.toString());
        pqHeap.add(pair5);
        System.out.println(pqHeap.toString());
        pqHeap.add(pair6);
        System.out.println(pqHeap.toString());
        pqHeap.remove();
        System.out.println(pqHeap.toString());
        pqHeap.remove();
        System.out.println(pqHeap.toString());
        pqHeap.clear();
        System.out.println(pqHeap.toString());
    }
}
