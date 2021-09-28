/*
 * File: IntBinaryTree.java
 * Authors: djskrien, lcaong
 * Date: 3/22/2021
 */

import java.util.Random;
import java.util.Stack;

import javax.lang.model.element.Element;

public class IntBinaryTree
{
    private TNode root;

    public IntBinaryTree() {
        this.root = null;
    }

    /* returns the sum of the integers in all the nodes of this tree
     * Recursive version
     */
    public int totalSum() {
        if (this.root == null) {
            return 0;
        }
        else {
            return this.root.totalSum();
        }
    }

    /* returns the sum of the integers in all the nodes of this tree.
     * Non-recursive version
     */
    public int totalSum2() {
        Stack<TNode> stack = new Stack<>();
        int total = 0;

        if (root != null) {
            stack.push(this.root);
        }
        while (!stack.empty()) {
            TNode node = stack.pop();
            total += node.data;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return total;
    }

    // returns the largest integer in the nodes of the tree
    // if the tree is empty, it returns Integer.MIN_VALUE
    public int max() {
        if (this.root == null) {
            return Integer.MIN_VALUE;
        }
        else {
            return this.root.max();
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

    // returns true if any node of this tree contains x as its data.
    // returns false otherwise.
    public boolean contains(int x) {    // in IntBinaryTree class
        if (this.root == null) {
            return false;
        }
        else {
            return this.root.contains(x);
        }
    }

    // returns the number of nodes in this binary tree
    public int size() {    // in IntBinaryTree class
        if (this.root == null) {
            return 0;
        }
        else {
            return this.root.size();
        }
    }

    public int count(int x) {
        if (this.root == null) {
            return 0;
        }
        else {
            return this.root.count(x);
        }
    }

    public boolean balanced() {
        if (this.root == null) {
            return true;
        }
        else {
            return this.root.balanced();
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

    @Override
    public String toString() {
        if (this.root == null) {
            return "";
        }
        else {
            return this.root.toString("");
        }
    }

    public static void main(String[] args) {
        IntBinaryTree tree = new IntBinaryTree();
        for (int i = 0; i < 9; i++) {
            tree.add(i);
        }
        tree.add(1);
        tree.add(1);
        System.out.println(tree.toString());
        System.out.println(tree.max());
        System.out.println(tree.count(1));
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

        public int totalSum() {
            int result = this.data;
            if (this.left != null) {
                result += this.left.totalSum();
            }
            if (this.right != null) {
                result += this.right.totalSum();
            }
            return result;
        }

        public int max() {
            int max = this.data;
            if (this.left != null && this.right != null) {
                int lmax = this.left.max();
                int rmax = this.right.max();
                if (lmax > max) {
                    max = lmax;
                }
                if (rmax > max) {
                    max = rmax;
                }
            }
            if (this.left != null && this.right == null) {
                int lmax = this.left.max();
                if (lmax > max) {
                    max = lmax;
                }
            }
            if (this.left == null && this.right != null) {
                int rmax = this.right.max();
                if (rmax > max) {
                    max = rmax;
                }
            }
            return max;
        }

        public int count(int x) {
            int count = 0;
            if (this.data == x) {
                count += 1;
            }
            if (this.left != null && this.left.data == x) {
                count += this.left.count(x);
            }
            if (this.right != null && this.right.data == x) {
                count += this.right.count(x);
            }
            return count;
        }

        public void add(int x) {
            Random rand = new Random();
            boolean goLeft = rand.nextBoolean();
            if (goLeft) {
                if (this.left == null) {
                    this.left = new TNode(x, null, null);
                }
                else {
                    this.left.add(x);
                }
            }
            else { // go right
                if (this.right == null) {
                    this.right = new TNode(x, null, null);
                }
                else {
                    this.right.add(x);
                }
            }
        }

        public boolean contains(int x) {    // in TNode class
            if (this.data == x) {
                return true;
            }
            else if (this.left != null && this.left.contains(x)) {
                return true;
            }
            else if (this.right != null && this.right.contains(x)) {
                return true;
            }
            else {
                return false;
            }
        }

        public int size() {
            if (this.left != null && this.right == null) {
                return 1 + this.left.size();
            }
            if (this.left == null && this.right != null) {
                return 1 + this.right.size();
            }
            return 1 + this.left.size() + this.right.size();
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

        public int depth() {
            int lDepth = 0;
            int rDepth = 0;
            if (this.left != null) {
                lDepth = this.left.depth();
            } 
            if (this.right != null) {
                rDepth = this.right.depth();
            }
            return 1 + Math.max(lDepth, rDepth);
        }

        public boolean balanced() {
            if (this.left == null && this.right == null) {
                return true;
            }
            else if (this.left == null && this.right != null) {
                return this.right.depth() == 1;
            }
            else if (this.right == null && this.left != null) {
                return this.left.depth() == 1;
            }
            else {
                return this.left.balanced() && this.right.balanced() 
                        && Math.abs(this.left.depth() - this.right.depth()) <= 1;
            }
        }
    }
}