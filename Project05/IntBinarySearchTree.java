/*
 * File: IntBinarySearchTree.java
 * Author: djskrien
 * Date: 3/27/2021
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class IntBinarySearchTree
{
    private TNode root;

    public IntBinarySearchTree() {
        this.root = null;
    }

    /* returns true if x appears in at least one node of this tree */
    public boolean contains(int x) {
        if (this.root == null) {
            return false;
        }
        else {
            return this.root.contains(x);
        }
    }

    /* adds a new node with value x as a new child to a random leaf in the tree */
    public void add(int x) {
        if (this.root == null) {
            this.root = new TNode(x, null, null);
        }
        else {
            this.root.add(x);
        }
    }

    public int depth() {
        if (this.root == null) {
            return 0;
        }
        else {
            return this.root.depth();
        }
    }

    public String toString() {
        if (this.root == null) {
            return "";
        }
        else {
            return this.root.toString("");
        }
    }

    public static void main(String[] args) {
        IntBinarySearchTree tree = new IntBinarySearchTree();
        Random rand = new Random();
        for (int i = 0; i < 9; i++) {
            tree.add(rand.nextInt(20));
        }
        System.out.println(tree);
        System.out.println("tree contains 3: " + tree.contains(3));
    }

    private class TNode
    {
        public TNode left, right;
        public int data;

        public TNode(int d, TNode l, TNode r) {
            this.data = d;
            this.left = l;
            this.right = r;
        }

        public String toString(String indent) {
            String result = "";
            if (this.right != null) {
                result += this.right.toString(indent + "\t");
            }
            result += indent + this.data + "\n";
            if (this.left != null) {
                result += this.left.toString(indent + "\t");
            }
            return result;
        }

        /* adds a new node with value x as a new child to
         * the proper place so that the tree remains a BST
         */
        public void add(int x) {
            if (this.data > x) {
                if (this.left == null) {
                    this.left = new TNode(x, null, null);
                }
                else {
                    this.left.add(x);
                }
            }
            else {
                if (this.right == null) {
                    this.right = new TNode(x, null, null);
                }
                else {
                    this.right.add(x);
                }
            }
        }

        // returns true if this tree contains x and returns
        // false otherwise
        public boolean contains(int x) {
            if (this.data == x) {
                return true;
            }
            else if (this.data > x && this.left != null && this.left.contains(x)) {
                return true;
            }
            else if (this.data < x && this.right != null && this.right.contains(x)) {
                return true;
            }
            else {
                return false;
            }
        }

        public int depth() {
            int lDepth = this.left.depth();
            int rDepth = this.right.depth();
            if (lDepth > rDepth) {
                return lDepth + 1;
            } 
            else {
                return rDepth + 1;
            }
        }
    }
}