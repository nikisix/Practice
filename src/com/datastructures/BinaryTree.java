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
    public Node minNode(){return minNode(root);}
    public Node minNode(Node node){
        while(node.left!=null){
            node = node.left;
        }
        return node;
    }
    public int minValue(){
        return minNode(root).data;
    }
    public int minValue(Node node){
        return minNode(node).data;
    }
//4b) Max Value
    public Node maxNode(){return maxNode(root);}
    public Node maxNode(Node node){
        while(node.right!=null){
            node = node.right;
        }
        return node;
    }
    public int maxValue(){
        return maxNode(root).data;
    }
    public int maxValue(Node node){
        return maxNode(node).data;
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

    //7. has Path sum - is there a path that sums to a particular value?
    public boolean hasPathSum(int sum){ return rpathsum(root, sum); }
    public boolean rpathsum(Node node, int sum){
        if(node == null && sum == 0) //bingo
            return true;
        else if(node == null && sum != 0) //dead-end
            return false;
        return rpathsum(node.left, sum - node.data) ||
                rpathsum(node.right, sum - node.data);
    }
    //8. print all paths, from root to leaf
    public void printPaths(){   rprintpaths(root, "");    }
    private void rprintpaths(Node node, String pathSoFar){
        if(node.left == null && node.right == null){ //leaf
            System.out.println(pathSoFar +" "+ node.data);
            return;
        }
            rprintpaths(node.left, pathSoFar +" "+ node.data);
            rprintpaths(node.right, pathSoFar +" "+ node.data);
    }
    //11. are two trees equal(have the same nodes in the same order)?
    public boolean equals(Node node2){        return requals(root, node2);    }
    public boolean requals(Node node1, Node node2){
        if(node1==null && node2==null)
            return true;
        else if(node1 == null || node2 == null) //one's null but not the other
            return false;
        else if(node1.data == node2.data)       //right && left
            return requals(node1.left, node2.left) &&
                    requals(node1.right, node2.right);
        else //data doesn't match
            return false;
    }
    //TODO 12. return number of possible trees with numbers 1...N
    public int countTrees(int numKeys){
        int sum = 0;
        if(numKeys <= 1){
            return 1;
        } else if(numKeys == 2){
            return 2;
        }
        else {
            for(int i = 1; i <= numKeys; i++){
                sum += countTrees(i) + countTrees(i-1);
            }
        }
        return sum;
    }
    //13. inefficient check if a tree is a bst tree
    public boolean isBST1(){ return isBST1(root);    }
    public boolean isBST1(Node node){
        if(node == null)
            return true;

        return  minValue(node)  <= node.data &&
                maxValue(node) >= node.data &&
                isBST1(node.left) && isBST1(node.right);
    }
    //14. efficient isBST
    public boolean isBST(){ return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);    }
    public boolean isBST(Node node, int min, int max){
        if(node == null)
            return true;

        return  min <= node.data &&
                max >= node.data &&
                isBST(node.left, min, node.data) &&
                isBST(node.right, node.data, max);
    }
    //15. the GREAT tree-list problem - turn a bst into a circular doublely linked list
    // the list should be ordered from small to large
    //return a pointer to the beginning of the list
    public void treeToList(){
        Node min = minNode();
        Node max = maxNode();
        rtreetolist(root, new Node(0), new Node(0));
        link(max, min);     //link beginning to end
        root = min;         //change root to the beginning of the list
    }
    public void rtreetolist(Node node, Node prev, Node next){
        if(node==null)
            return;
        rtreetolist(node.left, prev, node); //you're to my left, so i'm your next
        rtreetolist(node.right, node, next);//you're to my right, so i'm your prev
        if(null != node.left)
            prev = node.left;
        if(null != node.right)
            next = node.right;
        link(prev, node);
        link(node, next);
    }
    private void link(Node prev, Node next){
        prev.right = next;
        next.left = prev;
    }

    public void printList(){printList(root);}
    public void printList(Node node){
        Node cur = new Node(0);
        cur = node;
        while(cur.right!=node){
            System.out.print(cur.data + " ");
            cur = cur.right;
        }
        System.out.println(cur.data + " ");
    }
    public static void main(String ... args){
        Node node1 = new Node(2);
        Node node2 = new Node(5);
        node2 = spopulate(node1, "2(1)(3)");
//        node2 = spopulate(node2, "5(2(1)(3))(9(7)(10))");
//        node2 = spopulate(node2, "5(2(-1)(3))(9(7(6)(8))(10(9)(12)))");
        BinaryTree tree = new BinaryTree();
        tree.root = node2;
        System.out.println(tree.root);
        System.out.println("Tree size = "+tree.size());
        System.out.println("Tree max depth = "+tree.maxDepth());
        System.out.println("Tree min = "+tree.minValue());
        System.out.println("Tree max = "+tree.maxValue());
//        tree.printTree();
//        System.out.println("Does tree have a path that sums to 8: " + tree.hasPathSum(8));
//        tree.printPaths();
//        System.out.println("tree1 == tree2? "+tree.equals(tree.build123_1()));
        System.out.println(tree.isBST());
        tree.treeToList();
        tree.printList();
    }
}
