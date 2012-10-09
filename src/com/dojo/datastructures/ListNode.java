package com.dojo.datastructures;

/** * User: zen */
public class ListNode {
    String data = null;
    ListNode nextNode = null;
    ListNode(String data){
        this.data = data;
    }
    ListNode(String data, ListNode next){
        this.data = data;
        this.nextNode = next;
    }
    public ListNode insertBefore(ListNode newNode){
        newNode.nextNode = this;
        return newNode;
    }
    public ListNode insertAfter(ListNode newNode){
        newNode.nextNode = this.nextNode;
        this.nextNode = newNode;
        return this;
    }
    public String toString(){
        String ret = "";
        ListNode curNode = this;
        while(curNode != null){
            ret += curNode.data + ", ";
            curNode = curNode.nextNode;
        }
        return ret;
    }

    public static void main(String [] args){
        ListNode node2 = new ListNode("two");
        node2.insertAfter(new ListNode("three"));
        ListNode node1 = node2.insertBefore(new ListNode("one"));
        System.out.println(node1.toString());
    }
}

