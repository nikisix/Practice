package com.datastructures;
import com.util.StringUtilz;

public class BinaryTree {
    private Node root;
    private static class Node{
        Node left;
        Node right;
        int data;

        Node(int number){
            data = number;
            left = null;
            right = null;
        }
        Node(String number){
            this(Integer.parseInt(number));
        }

        static final String n = "\n";
        static final String t = "\t";
        static final String nt = "\n\t";
        public String toString(){
            return toStringHelperLinux(this, 1);
        }
        private String tab(int n){
            String ret = "";
            while(n-- > 0)
                ret+=t;
            return ret;
        }

        private String toStringHelperLinux(Node tree, int depth){
            if(tree.left == null && tree.right == null)
                return Integer.toString(tree.data); //leaf
            else if(tree.left != null && tree.right == null)
                return tree.data +n+tab(depth)+ toStringHelperLinux(tree.left, depth + 1);
            else if(tree.left == null && tree.right != null)
                return tree.data +n+tab(depth)+ toStringHelperLinux(tree.right, depth + 1);
            else
                return tree.data
                        +n+tab(depth)+ toStringHelperLinux(tree.left, depth + 1)
                        +n+tab(depth)+ toStringHelperLinux(tree.right, depth + 1);
        }
    }

    public BinaryTree(){
        root = null;
    }
    public boolean lookup(int data){
        return lookup(root, data);
    }
    public boolean lookup(Node root, int data){
        if(root == null)
            return false;

        if(root.data == data)
            return true;
        if(root.data > data)
            return lookup(root.left, data);
        else
            return lookup(root.right, data);
    }
    public void insert(int data){
        root = insert(root, data);
    }
    private Node insert(Node node, int data) {
        if (node==null) {
            node = new Node(data);
        }
        else {
            if (data <= node.data) {
                node.left = insert(node.left, data);
            }
            else {
                node.right = insert(node.right, data);
            }
        }
        return(node); // in any case, return the new pointer to the caller
    }

    public static Node spopulate(Node node, String s){
        if(!s.isEmpty()){
            if(s.contains("(")){
                node = new Node(s.substring(0, s.indexOf('(')));
                node.left = spopulate(node.left, StringUtilz.getToken(s, 0));
                node.right = spopulate(node.left, StringUtilz.getToken(s,1));
            } else {
                node = new Node(s);
            }
        }
        return node;
    }

    /**TREE PROBLEMS*/

    /**PROBLEM 1 - build the 123 tree:
     *     2
     *    / \
     *   1   3
    */
    public Node build123_1(){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        this.root = node2;
        this.root.left = node1;
        this.root.right = node3;
        return root;
    }
    public Node build123_2(){
        this.root = new Node(2);
        this.root.left = new Node(1);
        this.root.right = new Node(3);
        return root;
    }
    public Node build123_3(){
        this.root = null;
        this.insert(2);
        this.insert(1);
        this.insert(3);
        return root;
    }

    /**PROBLEM 2 - count number of nodes in the tree*/
    public int size(){        return rsize(root);    }
    private int rsize(Node node){
        if(node == null)
            return 0;
        else
            return 1 + rsize(node.left) + rsize(node.right);
    }
    /**PROBLEM 3 - calc the tree's max depth. empty tree = 0*/
    public int maxDepth(){
        return rmaxDepth(root);
    }
    private int rmaxDepth(Node node){
        if(node == null)
            return 0;
        else
            return Math.max(1 + rmaxDepth(node.left), 1 + rmaxDepth(node.right));
    }
//4) Min Value
    public int minValue(){
        Node node = root;
        int min = Integer.MAX_VALUE;
        while(node!=null){
            min = node.data;
            node = node.left;
        }
        return min;
    }
    //5. print in-order, 6. post-order
    public void printTree(){        rprintTree(root);    }
    public void rprintTree(Node node){
        if(node==null)
            return;
        rprintTree(node.left);
        System.out.println(node.data);
        rprintTree(node.right);
//post order        rprintTree(node.left);rprintTree(node.right);System.out.println(node.data);
    }

    //6. has Path sum - is there a path that sums to a particular value?
    public boolean hasPathSum(int sum){ return rpathsum(root, sum); }
    public boolean rpathsum(Node node, int sum){
        if(node == null && sum == 0) //bingo
            return true;
        else if(node == null && sum != 0) //dead-end
            return false;
        return rpathsum(node.left, sum - node.data) ||
                rpathsum(node.right, sum - node.data);
    }
    public static void main(String ... args){
        Node node = new Node(2);
//        node = spopulate(node, "2(1)(3)");
        node = spopulate(node, "5(2(1)(3))(9(7)(10))");
        BinaryTree tree = new BinaryTree();
        tree.root = node;
        System.out.println(tree.root);
        System.out.println("Tree size = "+tree.size());
        System.out.println("Tree max depth = "+tree.maxDepth());
        System.out.println("Tree min = "+tree.minValue());
//        tree.printTree();
        System.out.println("Does tree have a path that sums to 8: " + tree.hasPathSum(8));
    }
}
