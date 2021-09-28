/*
 * File: IntBinaryTree.java
 * Author: djskrien
 * Date: 3/22/2021
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

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

    /* adds a new node with value x as a new child to a random leaf in the tree */
    public void add(int x) {
        if (this.root == null) {
            this.root = new TNode(x, null, null);
        }
        else {
            this.root.add(x);
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

    // returns true if tree has priority property
    // returns false otherwise
    public boolean hasPriorityProperty() {
        if (this.root == null) {
            return false;
        }
        else {
            return this.root.hasPriorityProperty();
        }
    }

    // returns true if tree has completeness property
    // returns false otherwise
    public boolean hasCompletenessProperty() {
        if (this.root == null) {
            return false;
        }
        else {
            // creates new queue to enqueue node and node's children
            // counts the levels that the tree has
            MyQueue<TNode> queueNode = new MyQueue<>();
            queueNode.offer(this.root);
            int level = 0;
            while (true) {
                int nodeCount = queueNode.size();
                int levelTemp = 0;
                if (nodeCount == 0) {
                    break;
                }
                while (nodeCount > 0) {
                    TNode node = queueNode.peek();
                    queueNode.poll();
                    if (node.left != null) {
                        queueNode.offer(node.left);
                    }
                    if (node.right != null) {
                        queueNode.offer(node.right);
                    }
                    nodeCount--;
                    levelTemp++;
                }
                level = levelTemp;
            }
            // creates new queue to enqueue node and node's children
            // checks TNodes at each level
            while (true) {
                int nodeCount = queueNode.size();
                int levelTemp = 0;
                if (nodeCount == 0) {
                    break;
                }
                while (nodeCount > 0) {
                    TNode node = queueNode.peek();
                    queueNode.poll();
                    if (node.left != null) {
                        queueNode.offer(node.left);
                    }
                    if (node.right != null) {
                        queueNode.offer(node.right);
                    }
                    nodeCount--;
                    level--;
                    if (level > 2) {
                        for (TNode nodeChild: queueNode) {
                            if (nodeChild.left == null || nodeChild.right == null) {
                                return false;
                            }
                        }
                    }
                    if (level == 2) {
                        ArrayList<TNode> arrNode = new ArrayList<>();
                        for (TNode nodeChild: queueNode) {
                            arrNode.add(nodeChild);
                        }
                        for (int i = 0; i < arrNode.size() - 1; i++) {
                            if (arrNode.get(i).left == null && arrNode.get(i).right != null) {
                                return false;
                            }
                            if (arrNode.get(i).right == null && (arrNode.get(i + 1).left != null || arrNode.get(i + 1).right != null) {
                                return false;
                            }
                            return true;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        IntBinaryTree tree = new IntBinaryTree();
        for (int i = 0; i < 9; i++) {
            tree.add(i);
        }
        System.out.println(tree.toString());
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

        // returns true if tree has priority property
        // returns false otherwise
        public boolean hasPriorityProperty() {
            if (this.left == null && this.right == null) {
                return true;
            }
            if (this.left != null && this.data >= this.left.data) {
                return true;
            }
            if (this.right != null && this.data >= this.right.data) {
                return true;
            }
            return this.left.hasPriorityProperty() && this.right.hasPriorityProperty();
        }
    }
}
