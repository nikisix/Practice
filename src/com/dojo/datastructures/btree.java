package com.dojo.datastructures;

import com.dojo.util.StringUtilz;

/** * User: zen */
public class btree {
    static final String t = "\t";
    static final String n = "\n";
    static final String nt = "\n\t";
    String data = "";
    btree left = null;
    btree right = null;

    btree Clone(){
        return new btree(this.data, this.left, this.right);
    }
    btree(String data, btree left, btree right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
    btree(String data){
        this.data = data;
    }
    btree(int data){
        this.data = ""+data;
    }
    public String toString(){
        return toStringHelperLinux(this, 1);
    }
    private String tab(int n){
        String ret = "";
        while(n-- > 0)
            ret+=t;
        return ret;
    }

    private String toStringHelperLinux(btree tree, int depth){
        if(tree.left == null && tree.right == null)
            return tree.data; //leaf
        else if(tree.left != null && tree.right == null)
            return tree.data +n+tab(depth)+ toStringHelperLinux(tree.left, depth + 1);
        else if(tree.left == null && tree.right != null)
            return tree.data +n+tab(depth)+ toStringHelperLinux(tree.right, depth + 1);
        else
            return tree.data
                    +n+tab(depth)+ toStringHelperLinux(tree.left, depth + 1)
                    +n+tab(depth)+ toStringHelperLinux(tree.right, depth + 1);
    }

    public static void main(String [] args){
        btree tree = new btree(1);
//        tree = tree.spopulate(tree, "2(1)(3)");
        tree = tree.spopulate(tree, "9(2(1)(3))(5(4)(6))");
        System.out.println(tree.toString());
    }

    /**populates a tree from a string
     * take data
     * pass left
     * pass right*/
    public static btree spopulate(btree tree, String s){
        if(!s.isEmpty()){
            if(s.contains("(")){
                tree = new btree(s.substring(0, s.indexOf('(')));
                tree.left = spopulate(tree.left, StringUtilz.getToken(s,0));
                tree.right = spopulate(tree.left, StringUtilz.getToken(s,1));
            } else {
                tree = new btree(s);
            }
        }
        return tree;
    }

    public btree populate2(int depth){
        if(depth == 0 ) {
            return new btree(depth);
        }
        btree left = new btree(depth);
        btree right = new btree(depth);
        this.data = ""+depth;
        this.left = left.populate2(depth-1);
        this.right = right.populate2(depth-1);
        return this;
    }

    public static btree populate(btree tree, int depth){
        if(depth <= 0 ) {
            return new btree(depth);
        }
        tree = new btree(depth);
        tree.left = populate(tree, depth-1);
        tree.right = populate(tree, depth-1);
        return tree;
    }
}