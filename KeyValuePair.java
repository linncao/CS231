/*
 * File: KeyValuePair.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 30th, 2021
 * Lab 6: Binary Search Trees and Sets
 * CS231
 */

public class KeyValuePair<K, V> {

    // holds a key
    private K key;

    // holds a value
    private V value;

    // constructor method - initializes the key and value fields
    public KeyValuePair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    // returns the key
    public K getKey() {
        return this.key;
    }

    // returns the value
    public V getValue() {
        return this.value;
    }

    // sets the value
    public void setValue(V v) {
        this.value = v;
    }

    // returns a String containing both the key and value
    public String toString() {
        String s = this.key + " " + this.value;
        return s;
    }

    // unit test
    public static void main(String[] args) {
        KeyValuePair<String, Integer> pair = new KeyValuePair<String,Integer>("Yay", 1);
        System.out.println("The key (should be Yay): " + pair.getKey());
        System.out.println("The value (should be 1): " + pair.getValue());
        pair.setValue(2);
        System.out.println("The value (should be 2): " + pair.getValue());
        System.out.println(pair.toString());
    }
}
