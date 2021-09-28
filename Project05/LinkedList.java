/*
 * File: LinkedList.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 29th, 2021
 * Project 5: Decision-making Simulation: Checkout Lines
 * CS231
 */

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
 
public class LinkedList<T> implements Iterable<T> {
    
    // container class
    private class Node {

        // holds a Node object
        private Node next;

        // holds a T object
        private T data;

        // constructor - initializes next to null and the container field to item
        public Node(T item) {
            this.next = null;
            this.data = item;
        }

        // returns the value of the container field
        public T getThing() {
            return this.data;
        }

        // sets next to the given node
        public void setNext(Node n) {
            this.next = n;
        }

        // returns the next field
        public Node getNext() {
            return this.next;
        }

    }

    // handles traversing the list
    private class LLIterator implements Iterator<T> {

        // holds the next node
        private Node head;

        // constructor
        public LLIterator(Node head) {
            this.head = head;
        }

        // returns true if there are still values to traverse
        public boolean hasNext() {
            if (this.head != null) {
                return true;
            }
            else {
                return false;
            }
        }

        // returns the next item in the list
        public T next() {
            T current = this.head.getThing();
            this.head = this.head.getNext();
            return current;
        }

    }

    // holds the head node
    private Node headList;

    // holds the end node
    private Node endList;

    // holds the size of the list
    private int numItems;

    // constructor - initializes the fields so it is an empty list
    public LinkedList() {
        this.headList = null;
        this.endList = null;
        this.numItems = 0;
    }

    // empties the list
    public void clear() {
        this.numItems = 0;
        this.headList = null;
        this.endList = null;
    }

    // returns the size of the list
    public int size() {
        return this.numItems;
    }

    // inserts the item at the beginning of the list
    public void addFirst(T item) {
        Node tempFirst = new Node(item);
        if (this.headList == null) {
            this.headList = tempFirst;
            this.endList = this.headList;
        }
        else {
            tempFirst.setNext(this.headList);
            this.headList = tempFirst;
        }
        this.numItems++;
    }

    // appends the item at the end of the list
    public void addLast(T item) {
        Node tempLast = new Node(item);
        if (this.headList == null) {
            this.endList = tempLast;
            this.headList = this.endList;
        }
        else {
            this.endList.setNext(tempLast);
            this.endList = tempLast;
        }
        this.numItems++;
    }

    // inserts the item at the specified poistion in the list
    public void add(int index, T item) {
        Node added = new Node(item);
        Node current = this.headList;
        if (this.headList == null) {
            this.headList = added;
            this.endList = added;
        }
        else {
            if (index == 0) {
                this.addFirst(item);
                this.numItems--;
            }
            if (index == this.size()) {
                this.endList.setNext(added);
                this.endList = added;
            }
            else {
                for (int i = 1; i < index; i++) {
                    current = current.getNext();
                }
                added.setNext(current.getNext());
                current.setNext(added);
            }
        }
        this.numItems++;
    }
 
    // removes the item at the specified position in the list
    public T remove (int index) {
        T removedT = this.headList.getThing();
        if (this.headList == null) {
            System.out.println("Empty");
            return null;
        }
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            this.headList = this.headList.getNext();
        }
        if (index == this.size() - 1) {
            Node current = this.headList;
            removedT = this.endList.getThing();
            for (int i = 0; i < this.size() - 1; i++) {
                current = current.getNext();
            }
            this.endList = current;
        }
        else {
            Node current = this.headList;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            removedT = current.getThing();
        }
        this.numItems--;
        return removedT;
    }

    // returns a new LLIterator with head passed to the constructor
    public Iterator<T> iterator() {
        return new LLIterator(this.headList);
    }

    // returns an ArrayList of the list contents in order
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrConvert = new ArrayList<>();
        for (T item: this) {
            arrConvert.add(item);
        }
        return arrConvert;
    }

    // returns an ArrayList of the list contents in shuffled order
    public ArrayList<T> toShuffledList() {
        ArrayList<T> arrShuffle = new ArrayList<>();
        ArrayList<T> arrTempt = this.toArrayList();
        for (int i = 0; i < this.size(); i++) {
            arrShuffle.add(arrTempt.get(i));
        }
        Collections.shuffle(arrShuffle);
        return arrShuffle;
    }

}
