class BinarySearchTree {
    private TreeNode root;
    
    public boolean contains(int x) { ... }
    
    public String pathTo(int x) {
        if ( ! this.contains(x)) {
            return null;
        }
        else {
            return this.root.pathTo(x);
        }
}

class TreeNode {
    public int data;
    public TreeNode left, right;

    public String pathTo(int x) {
        if (this.data == x) {
            return "";
        }
        if (this.data < x) {
            if (this.right != null) {
                return "R" + this.right.pathTo(x);
            }
        }
        else if (this.left != null) {
            return "L" + this.left.pathTo(x);
        }
        return "NOT FOUND";
    }
}