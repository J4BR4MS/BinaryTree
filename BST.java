import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Your Name Here
 * @version: Date
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        // If this recursive method is true, the value exists, and we return true.
        if(exists(val, getRoot())){
            return true;
        }
        // If the value doesn't exist, return false.
        return false;
    }

    public boolean exists(int target, BSTNode b){
        // If the value exists in this node, return true.
        if(b.getVal() == target) {
            return true;
        }
        // If this node's value is larger than our target and the left node is available, go left.
        if(b.getVal() > target && b.getLeft() != null) {
            return exists(target, b.getLeft());
        }
        // If this node's value is smaller than our target and the right node is available, go right.
        else if(b.getVal() < target && b.getRight() != null) {
            return exists(target, b.getRight());
        }
        return false;
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        // Call to a recursive method that returns a sorted ArrayList.
        return sortInorder(new ArrayList<BSTNode>(), getRoot());
    }

    public ArrayList<BSTNode> sortInorder(ArrayList<BSTNode> arr, BSTNode b) {
        // If the node exists
        if (b != null) {
            // Recurse left
            sortInorder(arr, b.getLeft());
            // Add to ArrayList in the middle to get inorder
            arr.add(b);
            //Recurse right
            sortInorder(arr, b.getRight());
        }
        return arr;
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        // Call to a recursive method that returns an ArrayList of the order which it was traversed.
        return sortPreorder(new ArrayList<BSTNode>(), getRoot());
    }

    private ArrayList<BSTNode> sortPreorder(ArrayList<BSTNode> arr, BSTNode b) {
        // If the node exists
        if (b != null) {
            // Add to ArrayList first to get preorder
            arr.add(b);
            // Recurse both left and right
            sortPreorder(arr, b.getLeft());
            sortPreorder(arr, b.getRight());
        }
        return arr;
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        return sortPostorder(new ArrayList<BSTNode>(), getRoot());
    }

    private ArrayList<BSTNode> sortPostorder(ArrayList<BSTNode> arr, BSTNode b) {
        // If the node exists
        if (b != null) {
            // Recurse both left and right
            sortPostorder(arr, b.getLeft());
            sortPostorder(arr, b.getRight());
            // Add to ArrayList last to get postorder
            arr.add(b);
        }
        return arr;
    }


    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        // Call to a recursive method that will insert the value.
        insertHelper(val, getRoot());
    }
    private BSTNode insertHelper(int value, BSTNode b) {
        // Base case: if there is no node, this is where we put the value.
        if (b == null) {
            return new BSTNode(value);
        }
        // Recursive case: if the value is less than this node, we continue along left.
        else if (value < b.getVal()) {
            b.setLeft(insertHelper(value, b.getLeft()));
        }
        // Recursive case: if the value is more than this node, we continue along right.
        else if (value > b.getVal()) {
            b.setRight(insertHelper(value, b.getRight()));
        }
        return b;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        return recurse(Integer.MIN_VALUE, Integer.MAX_VALUE, getRoot());
    }
    private boolean recurse(int min, int max, BSTNode b) {
        // Base case: ff the node does not exist
        if (b == null) {
            return true;
        }
        // Base case #2: If the value of the node is equal/less than the minimum value 
        // (or vice versa with the max), return false
        if (b.getVal() <= min || b.getVal() >= max) {
            return false;
        }

        // Recurse: explore both the left and right paths with the minimum and maximum values
        if(recurse(min, b.getVal(), b.getLeft()) && recurse(b.getVal(), max, b.getRight())){
            return true;
        }
        // If they both are not true, the tree is incorrect
        else
        {
            return false;
        }
    }


    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
