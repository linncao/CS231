/*
 * File: MyQueue.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 23rd, 2021
 * Lab 5: Queues
 * CS231
 */

import java.util.Iterator;
import java.util.Queue;

public class MyQueue<T> implements Iterable<T> {
    
    // container class
    private class Node {
        
        // holds the next Node object
        private Node next;

        // holds the previous Node object
        private Node previous;

        // holds a T object
        private T data;

        // constructor
        public Node(T item) {
            this.next = null;
            this.previous = null;
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

        // sets previous to the given node
        public void setPrevious(Node p) {
            this.previous = p;
        }

        // returns the previous field
        public Node getPrevious() {
            return this.previous;
        }
    }

    // handles traversing the queue
    private class QIterator implements Iterator<T> {

        // holds the next node
        private Node next;

        // constructor
        public QIterator(Node next) {
            this.next = next;
        }

        // returns true if there are still values to traverse
        public boolean hasNext() {
            if (this.next != null) {
                return true;
            }
            else {
                return false;
            }
        }

        // returns the next item in the list
        public T next() {
            T current = this.next.getThing();
            this.next = this.next.getNext();
            return current;
        }
    }

    // holds the head node
    private Node head;

    // holds the end node
    private Node tail;

    // holds the size of the list
    private int numItems;

    // constructor
    public MyQueue() {
        this.head = null;
        this.tail = null;
        this.numItems = 0;
    }

    // returns the size of the queue
    public int size() {
        return this.numItems;
    }

    // inserts item into the end of the queue if possible
    // returns true if successful
    public boolean offer(T item) {
        Node tempNode = new Node(item);
        if (tempNode != null) {
            if (this.head == null) {
                this.tail = tempNode;
                this.head = this.tail;
                this.numItems++;
                return true;
            }
            else {
                this.tail.setNext(tempNode);
                tempNode.setPrevious(this.tail);
                this.tail = tempNode;
                this.numItems++;
                return true;
            }
        } 
        else {
            return false;
        }
    }

    // returns, but not remove, the head of the queue
    // returns null if the queue is empty
    public T peek() {
        if (this.head == null) {
            return null;
        }
        else {
            return this.head.getThing();
        }
    }

    // returns and removes the head of the queue
    // returns null if the queue is empty
    public T poll() {
        T polled = this.head.getThing();
        if (this.head == null) {
            return null;
        }
        if (this.size() == 1) {
            this.head = null;
            this.tail = null;
            this.numItems = 0;
            return polled;
        }
        else {
            Node headNext = this.head.getNext();
            this.head.next = null;
            this.head = headNext;
            this.head.previous = null;
            this.numItems--;
            return polled;
        }
    }

    // returns a new QIterator with head passed to the constructor
    public Iterator<T> iterator() {
        return new QIterator(this.head);
    }

    // unit test
    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<Integer>();
        for (int i = 0; i < 5; i++) {
            System.out.println(myQueue.offer(i));
        }
        int size = myQueue.size();
        for (int i = 0; i < size; i++) {
            System.out.println("Peek " + myQueue.peek());
            System.out.println("Poll " + myQueue.poll());
        }
        for (int i = 0; i < 5; i++) {
            myQueue.offer(i);
        }
        for (int a: myQueue) {
            System.out.println(a);
        }
    }
}