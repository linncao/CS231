/*
 * File: HashMapSet.java
 * Author: djskrien
 * Date: 5/1/2021
 */

import java.util.Iterator;
import java.util.HashMap;

public class HashMapSet<T> implements Iterable<T>
{
    private HashMap<T,T> map;

    public HashMapSet() {
        this.map = new HashMap<>();
    }

    // returns true if obj is a HashMapSet containing the same elements as this set
    // otherwise it returns false
    public boolean equals(Object obj) {
        if (obj instanceof HashMapSet) {
            for (T element: this) {
                HashMapSet<T> obj1 = (HashMapSet) obj;
                if (! obj1.contains(element) || obj1.size() != this.size()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    // removes all the elements from this set
    public void clear() {
        map.clear();
    }

    // adds the item to this set if it is not already there
    // returns true if it added the item
    public boolean add(T item) {
        T oldVal = map.put(item,item);
        return oldVal == null;
    }

    // transforms this set into the union of this set and otherSet
    public void union(HashMapSet<T> otherSet) {
        this.map.putAll(otherSet.map);
    }

    // transforms this set into the intersection of this set with otherSet
    public void intersection(HashMapSet<T> otherSet) {
        HashMap<T,T> clone = (HashMap<T,T>) this.map.clone();
        for(T item: clone.keySet()) {
            if (! otherSet.map.containsKey(item)) {
                this.map.remove(item);
            }
        }
    }

    // transforms this set into the asymmetric difference of this set with otherSet
    // that is, all elements of otherSet are removed from this set
    public void difference(HashMapSet<T> otherSet) {
        HashMap<T,T> clone = (HashMap<T,T>) this.map.clone();
        for(T item: clone.keySet()) {
            if (otherSet.map.containsKey(item)) {
                this.map.remove(item);
            }
        }
    }

    // returns true if this set is a subset of otherSet
    public boolean isSubsetOf(HashMapSet<T> otherSet) {
        for (T item: this) {
            if (! otherSet.contains(item)) {
                return false;
            }
        }
        return true;
    }

    // removes the item from this set if it is in this set
    // returns true if the item was removed
    public boolean remove(T item) {
        T val = this.map.remove(item);
        return val != null;
    }

    // returns true if the item is a member of this set
    public boolean contains(T item) {
        return this.map.containsKey(item);
    }

    public int size() { return this.map.size(); }

    public static void main(String[] args) {
        // HashMapSet<String> set1 = new HashMapSet<>();
        // set1.add("Dale");
        // System.out.println("size: 1 = " + set1.size());
        // set1.add("Dale");
        // System.out.println("size: 1 = " + set1.size());
        // set1.add("Bill");
        // System.out.println("contains: true = " + set1.contains("Dale"));
        // //        System.out.println(set);
        // System.out.println("size of set1: 2 = " + set1.size());
        // set1.add("Sam");
        // set1.add("Joe");
        // set1.add("Peg");
        // System.out.println("contains: false = " + set1.contains("Pete"));
        // System.out.println("size of set1: 5 = " + set1.size());
        // //        System.out.println(set);
        // System.out.print("set1 members: ");
        // for(String s : set1) {
        //     System.out.print(s + ",");
        // }
        // System.out.println();

        // HashMapSet<String> set2 = new HashMapSet<>();
        // set2.add("Pete");
        // set2.add("Joe");
        // set2.union(set1);
        // System.out.println("set2 size: 6 = " + set2.size());
        // System.out.print("set2 members: ");
        // for(String s : set2) {
        //     System.out.print(s + ",");
        // }
        // System.out.println();
        // System.out.println("set2 is a subset of set1: false = " + set2.isSubsetOf(set1));

        // HashMapSet<String> set3 = new HashMapSet<>();
        // set3.intersection(set1);
        // System.out.println("set3 size: 0 = " + set3.size());
        // set3.add("Sam");
        // set3.add("Joe");
        // set3.add("Pete");
        // set3.intersection(set1);
        // System.out.println("set3 size: 2 = " + set3.size());
        // System.out.print("set3 members: ");
        // for(String s : set3) {
        //     System.out.print(s + ",");
        // }
        // System.out.println();
        // System.out.println("set3 is a subset of set1: true = " + set3.isSubsetOf(set1));

        HashMapSet<String> set1 = new HashMapSet<>();
        set1.add("Dale");
        HashMapSet<String> set2 = new HashMapSet<>();
        set2.add("Dale");
        System.out.println(set1.equals(set2));
        set2.add("Help");
        System.out.println(set1.equals(set2));
        set1.add("Help");
        System.out.println(set1.equals(set2));
    }
}

/*
 * 3. What is the grade range for the course? Will the grades be curved?
 *    Will there be any more extra credit opportunities?
 */
