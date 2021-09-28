/*
 * File: BSTMap.java
 * Author: Linn Cao Nguyen Phuong
 * Section: B
 * Date: Mar 30th, 2021
 * Lab 6: Binary Search Trees and Sets
 * CS231
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class BSTMap<K, V> implements MapSet<K, V> {
    
    // holds a root
    private TNode root;

    // holds a Comparator
    private Comparator<K> comparator;

    // constructor method - takes in a Comparator object
    public BSTMap(Comparator<K> comp) {
        this.root = null;
        this.comparator = comp;
    }

    // adds or updates a key-value pair
	// If there is already a pair with new_key in the map, then update
	// the pair's value to new_value.
	// If there is not already a pair with new_key, then
	// add pair with new_key and new_value.
	// returns the old value or null if no old value existed
    public V put(K key, V value) {
        if (this.root == null) {
            this.root = new TNode(key, value);
            return null;
        }
        else {
            return this.root.put(key, value, this.comparator);
        }
    }

    // gets the value at the specified key or null
    public V get(K key) {
        if (this.root == null) {
            return null;
        }
        else {
            return this.root.get(key, this.comparator);
        }
    }

    // Returns true if the map contains a key-value pair with the given key
    public boolean containsKey(K key) {
        if (this.root == null) {
            return false;
        }
        else {
            return this.root.containsKey(key, this.comparator);
        }
    }

    // Returns an ArrayList of all the keys in the map. There is no
    // defined order for the keys.
    public ArrayList<K> keySet() {
        ArrayList<K> arrKey = new ArrayList<>();
        if (this.root == null) {
            return arrKey;
        }
        else {
            return this.root.keySet(arrKey);
        }
    }

    // Returns an ArrayList of all the values in the map. These should
    // be in the same order as the keySet.
    public ArrayList<V> values() {
        ArrayList<V> arrValue = new ArrayList<>();
        if (this.root == null) {
            return arrValue;
        }
        else {
            return this.root.values(arrValue);
        }
    }
    
    // return an ArrayList of pairs.
    public ArrayList<KeyValuePair<K,V>> entrySet() {
        ArrayList<KeyValuePair<K,V>> arrPair = new ArrayList<>();
        if (this.root == null) {
            return arrPair;
        }
        else {
            return this.root.entrySet(arrPair);
        }
    }

    // Returns the number of key-value pairs in the map.
    public int size() {
        return this.entrySet().size();
    }
        
    // removes all mappings from this MapSet
    public void clear() {
        this.root = null;
    }

    // returns a string that gives an idea of the structure of the tree
    public String toString() {
        if (this.root == null) {
            return "";
        } 
        else {
            return "root " + this.root.toString();
        }
    }

    // prints the tree level by level from top to bottom
    public void printLevelOrder() {
        if (this.root == null) {
            return;
        }
        // creates new queue to enqueue node and node's children
        MyQueue<TNode> queueNode = new MyQueue<>();
        queueNode.offer(this.root);
        while (true) {
            int nodeCount = queueNode.size();
            if (nodeCount == 0) {
                break;
            }
            while (nodeCount > 0) {
                TNode node = queueNode.peek();
                System.out.println(node.data());
                queueNode.poll();
                if (node.left != null) {
                    queueNode.offer(node.left);
                }
                if (node.right != null) {
                    queueNode.offer(node.right);
                }
                nodeCount--;
            }
            System.out.println("");
        }
    }

    private class TNode {

        // holds the left and right children
        public TNode left, right;

        // holds the data at this node
        public KeyValuePair<K, V> pair;

        // constructor, given a key and a value
        public TNode(K k, V v) {
            this.left = null;
            this.right = null;
            this.pair = new KeyValuePair<K, V>(k, v);
        }

        // takes in a key, a value, and a comparator
        // inserts the TNode in the subtree rooted at this node
        // returns the value associated with the key in the subtree
        // rooted at this node or null if the key does not already exist
        public V put(K key, V value, Comparator<K> comp) {
            int compare = comp.compare(key, this.pair.getKey());
            if (compare == 0) {
                V oldValue = this.pair.getValue();
                this.pair.setValue(value);
                return oldValue;
            }
            else if (compare < 0) {
                if (this.left != null) {
                    return this.left.put(key, value, comp);
                }
                this.left = new TNode(key, value);
                return null;
            }
            else {
                if (this.right != null) {
                    return this.right.put(key, value, comp);
                }
                this.right = new TNode(key, value);
                return null;
            }
        }

        // takes in a key and a comparator
        // returns the value associated with the key or null
        public V get(K key, Comparator<K> comp) {
            int compare = comp.compare(key, this.pair.getKey());
            if (compare == 0) {
                return this.pair.getValue();
            }
            else if (compare < 0) {
                if (this.left == null) {
                    return null; 
                }
                return this.left.get(key, comp);
            }
            else {
                if (this.right == null) {
                    return null;
                }
                return this.right.get(key, comp);
            }
        }

        // Returns true if the map contains a key-value pair with the given key
        public boolean containsKey(K key, Comparator<K> comp) {
            int compare = comp.compare(key, this.pair.getKey());
            if (compare == 0) {
                return true;
            }
            else if (compare != 0 && this.left != null && this.left.containsKey(key, comp)) {
                return true;
            }
            else if (compare != 0 && this.right != null && this.right.containsKey(key, comp)) {
                return true;
            }
            else {
                return false;
            }
        }

        // Returns an ArrayList of all the keys in the map. There is no
        // defined order for the keys.
        public ArrayList<K> keySet(ArrayList<K> arrKey) {
            if (this.pair.getKey() != null) {
                arrKey.add(this.pair.getKey());
            }
            if (this.left != null) {
                this.left.keySet(arrKey);
            }
            if (this.right != null) {
                this.right.keySet(arrKey);
            }
            return arrKey;
        }

        // Returns an ArrayList of all the values in the map. These should
        // be in the same order as the keySet.
        public ArrayList<V> values(ArrayList<V> arrValue) {
            if (this.pair.getValue() != null) {
                arrValue.add(this.pair.getValue());
            }
            if (this.left != null) {
                this.left.values(arrValue);
            }
            if (this.right != null) {
                this.right.values(arrValue);
            }
            return arrValue;
        }

        // return an ArrayList of pairs.
        public ArrayList<KeyValuePair<K,V>> entrySet(ArrayList<KeyValuePair<K,V>> arrPair) {
            if (this.pair != null) {
                arrPair.add(this.pair);
            }
            if (this.left != null) {
                this.left.entrySet(arrPair);
            }
            if (this.right != null) {
                this.right.entrySet(arrPair);
            }
            return arrPair;
        }

        // returns a string that gives an idea of the structure of the root, left and right nodes
        public String toString() {
            String structure = "";
            String indent = "   ";
            if (this.pair != null) {
                structure += indent + this.pair.getKey() + ":" + this.pair.getValue();
            }
            if (this.left != null) {
                structure += "\nleft " + indent + this.left.toString();
            }
            if (this.right != null) {
                structure += "\nright" + indent + this.right.toString();
            }
            return structure;
        }

        // prints out the key and value of the 
        public String data() {
            if (this.pair != null) {
                return this.pair.getKey() + ": " + this.pair.getValue();
            }
            return "Empty";
        }
    }

    // unit test
    public static void main(String[] args) {
        BSTMap<String, Integer> bst = new BSTMap<String, Integer>(new AscendingString());

        bst.put("twenty", 20);
        bst.put("ten", 10);
        bst.put("eleven", 11);
        bst.put("five", 5);
        bst.put("six", 6);

        System.out.println(bst.get("eleven"));
        System.out.println("Test containsKey (should be true): " + bst.containsKey("twenty"));
        System.out.println("Test containsKey (should be true): " + bst.containsKey("ten"));
        System.out.println("Test containsKey (should be true): " + bst.containsKey("eleven"));
        System.out.println("Test containsKey (should be true): " + bst.containsKey("five"));
        System.out.println("Test containsKey (should be true): " + bst.containsKey("six"));
        System.out.println("Test containsKey (should be false): " + bst.containsKey("one"));
        System.out.println("ArrayList of keys: " + bst.keySet());
        System.out.println("ArrayList of values: " + bst.values());
        System.out.println("ArrayList of KeyValuePairs: " + bst.entrySet());
        System.out.println("Size (before clear): " + bst.size());
        System.out.println(bst.toString());
        System.out.println("Test level order: ");
        bst.printLevelOrder();
        bst.clear();
        System.out.println("Size (after clear): " + bst.size());
    }
}
