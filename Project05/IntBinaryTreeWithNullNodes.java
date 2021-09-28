/*
 * File: IntBinaryTreeEnhanced.java
 * Author: djskrien
 * Date: 3/24/2021
 */

import java.util.Random;

public class IntBinaryTreeWithNullNodes
{
    private TNode root;
    private final NullTNode NULLNODE = new NullTNode();

    public IntBinaryTreeWithNullNodes() {
        this.root = NULLNODE;
    }

    public int totalSum() {
        return this.root.totalSum();
    }

    public void add(int x) {
        this.root = this.root.add(x);
    }

    public boolean contains(int x) {
        return this.root.contains(x);
    }

    public int size() {
        return this.root.size();
    }

    public int max() {
        return this.root.max();
    }

    @Override
    public String toString() {
        return this.root.toString("");
    }

    public static void main(String[] args) {
        IntBinaryTreeWithNullNodes tree = new IntBinaryTreeWithNullNodes();
        for (int i = 0; i < 9; i++) {
            tree.add(i);
        }
        System.out.println(tree.toString());
        System.out.println("Sum = " + tree.totalSum());
        System.out.println("Max = " + tree.max());
        System.out.println("Contains 5: " + tree.contains(5));
        System.out.println("Size = " + tree.size());
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
            return this.data + this.left.totalSum() + this.right.totalSum();
        }

        public TNode add(int x) {
            Random rand = new Random();
            boolean goLeft = rand.nextBoolean();
            if (goLeft) {
                this.left = this.left.add(x);
            } else { // go right
                this.right = this.right.add(x);
            }
            return this;
        }

        public boolean contains(int x) {
            if (this.data == x) {
                return true;
            }
            if (this.left.contains(x)) {
                return true;
            }
            if (this.right.contains(x)) {
                return true;
            }
            else {
                return false;
            }
        }

        public int size() {
            return 1 + this.left.size() + this.right.size();
        }     
        
        public int max() {
            int max = this.data;
            int lmax = this.left.max();
            int rmax = this.right.max();
            if (lmax > max) {
                max = lmax;
            }
            if (rmax > max) {
                max = rmax;
            }
            return max;
        }

        public String toString(String indent) {
            return this.right.toString(indent + "\t")
                    + indent + this.data + "\n"
                    + this.left.toString(indent + "\t");

        }
    }

    private class NullTNode extends TNode
    {
        public NullTNode() {
            super(0, null, null);
        }

        @Override
        public String toString(String indent) {
            return "";
        }

        @Override
        public int totalSum() {
            return 0;
        }

        public TNode add(int x) {
            return new TNode(x, NULLNODE, NULLNODE);
        }

        public int size() {
            return 0;
        }

        public int max() {
            return Integer.MIN_VALUE;
        }

        public boolean contains(int x) {
            return false;
        }
    }

}