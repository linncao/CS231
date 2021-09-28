/*
 * File: Hashmap.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Apr 6th, 2021
 * Lab 7: Hash Tables
 * CS231
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.lang.Math;

public class Hashmap<K, V> implements MapSet<K, V> {

    // holds a Comparator object
    private Comparator<K> comp;

    // counts the number of collisions that have occurred
    private int collision;

    // holds an array of Object type
    private BSTMap<K,V>[] arrHash;
    
    // constructor method - starts with default size hash table
    @SuppressWarnings("unchecked")
    public Hashmap(Comparator<K> incomp) {
        this.comp = incomp;
        this.arrHash = new BSTMap[16];
        for (int i = 0; i < this.arrHash.length; i++) {
            this.arrHash[i] = new BSTMap<K,V>(this.comp);
        }
        this.collision = 0;
    }

    // constructor method - starts with the suggested capacity hash table
    @SuppressWarnings("unchecked")
    public Hashmap(Comparator<K> incomp, int capacity) {
        this.comp = incomp;
        this.arrHash = new BSTMap[capacity];
        for (int i = 0; i < this.arrHash.length; i++) {
            this.arrHash[i] = new BSTMap<K,V>(this.comp);
        }
        this.collision = 0;
    }

    // returns the hash code of the key
    public int hash(K key) {
        return Math.abs(key.hashCode()) % this.arrHash.length;
        // String hashString = Integer.toString(key.toString().length());
        // return hashString.hashCode() % (this.arrHash.length/2);
    }

    // adds or updates a key-value pair
    // If there is already a pair with new_key in the map, then update
    // the pair's value to new_value.
    // If there is not already a pair with new_key, then
    // add pair with new_key and new_value.
    // returns the old value or null if no old value existed
    @SuppressWarnings("unchecked")
    public V put(K new_key, V new_value) {
        if (this.arrHash[this.hash(new_key)].size() < 5) {
            V put = this.arrHash[this.hash(new_key)].put(new_key, new_value);
            if (put == null && this.arrHash[this.hash(new_key)].size() > 1) {
                this.collision++;
            }
            return put;
        }
        else {
            this.collision = 0;
            BSTMap<K,V>[] arrHashTemp = new BSTMap[arrHash.length*2];
            for (int i = 0; i < arrHashTemp.length; i++) {
                arrHashTemp[i] = new BSTMap<K,V>(this.comp);
            }
            ArrayList<KeyValuePair<K,V>> data = this.entrySet();
            this.arrHash = arrHashTemp;
            for (KeyValuePair<K,V> kVP: data) {
                this.put(kVP.getKey(), kVP.getValue());
            }
            return this.arrHash[this.hash(new_key)].put(new_key, new_value);
        }
    }

    // Returns true if the map contains a key-value pair with the given key
    public boolean containsKey(K key) {
        return this.arrHash[this.hash(key)].containsKey(key);
    }

    // Returns the value associated with the given key.
    // If that key is not in the map, then it returns null.
    public V get(K key) {
        return this.arrHash[this.hash(key)].get(key);
    }

    // Returns an ArrayList of all the keys in the map. There is no
    // defined order for the keys.
    public ArrayList<K> keySet() {
        ArrayList<K> arrKey = new ArrayList<>();
        for (BSTMap<K,V> bst: this.arrHash) {
            for (KeyValuePair<K,V> kVP: bst.entrySet()) {
                arrKey.add(kVP.getKey());
            }
        }
        return arrKey;
    }

    // Returns an ArrayList of all the values in the map. These should
    // be in the same order as the keySet.
    public ArrayList<V> values() {
        ArrayList<V> arrValue = new ArrayList<>();
        for (BSTMap<K,V> bst: this.arrHash) {
            for (int i = 0; i < bst.values().size(); i++) {
                arrValue.add((V) bst.values().get(i));
            }
        }
        return arrValue;
    }

    // return an ArrayList of pairs.
    public ArrayList<KeyValuePair<K,V>> entrySet() {
        ArrayList<KeyValuePair<K,V>> arrKVP = new ArrayList<>();
        for (BSTMap<K,V> bst: this.arrHash) {
            for (int i = 0; i < bst.entrySet().size(); i++) {
                arrKVP.add((KeyValuePair<K,V>) bst.entrySet().get(i));
            }
        }
        return arrKVP;
    }

    // Returns the number of key-value pairs in the map.
    public int size() {
        return this.entrySet().size();
    }

    // removes all mappings from this MapSet
    public void clear() {
        for (BSTMap<K,V> bst: this.arrHash) {
            bst.clear();
        }
    }

    // counts the number of collisions
    public int numCollisions() {
        return this.collision;
    }

    // clears the number of collisions
    public void clearNumCollisions() {
        this.collision = 0;
    }

    // returns a representation of the entire Hashmap 
    // that shows what is at each table entry
    public String toString() {
        String hash = "Hashmap Representation:\n";
        for (BSTMap<K,V> bst: this.arrHash) {
            hash += bst.toString() + "\n";
        }
        return hash;
    }

    // unit test
    public static void main(String[] args) {
        Hashmap<String, Integer> hashMap = new Hashmap<String, Integer>(new AscendingString(), 1);

        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("two", 3);
        hashMap.put("three", 3);
        hashMap.put("four", 4);
        hashMap.put("five", 5);
        hashMap.put("six", 6);
        hashMap.put("seven", 7);
        hashMap.put("eight", 8);
        hashMap.put("nine", 9);
        hashMap.put("ten", 10);
        hashMap.put("eleven", 11);
        hashMap.put("twelve", 12);

        System.out.println("Check containsKey (should be true): " + hashMap.containsKey("one"));
        System.out.println("Check containsKey (should be true): " + hashMap.containsKey("two"));
        System.out.println("Check containsKey (should be false): " + hashMap.containsKey("twenty"));
        System.out.println("Check get (should be 2): " + hashMap.get("two"));
        System.out.println(hashMap.keySet());
        System.out.println(hashMap.values());
        System.out.println(hashMap.entrySet());
        System.out.println("Check number of kVP (should be 12): " + hashMap.size());
        System.out.println(hashMap.numCollisions());
        System.out.println(hashMap.toString());
        hashMap.clear();
        System.out.println(hashMap.size());
    }
}
