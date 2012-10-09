package com.dojo.sort;

import java.util.ArrayList;

/** * User: ntomasino */
public class Merge1 {
    public static void main(String ... args){
        int size = 50;
        int [] arand = new int [size];
        System.out.println("Before:");
        for(int i = 1; i < size; i++){
            arand[i] = Math.round((float)Math.random() * 100);
            System.out.println(arand[i]);
        }
        arand = mergeSort(arand);
        System.out.println("\n==============================================" +
                "\n=====================================================" +
                "\nAfter:");
        for(int i = 1; i < size; i++){
            System.out.println(arand[i]);
        }
    }

    static public int [] mergeSort(int [] a){
        return merge(a);
    }

    static public int [] merge(int [] a){
        if(a.length<=1)
            return a;
        ArrayList<int[]> ret = halve(a);
        int [] b = merge(ret.remove(0));
        int [] c = merge(ret.remove(0));
        return join(b,c);
    }

    static public ArrayList<int[]> halve(int []a){
        int clen;
        if(a.length%2 == 0)
            clen = a.length/2;
        else
            clen = a.length/2 + 1;

        int [] b = new int[a.length/2];
        int [] c = new int[clen];

        for(int i = 0; i < a.length; i++){
            if(i<a.length/2)
                b[i] = a[i];
            else
                c[i - b.length] = a[i];
        }

        ArrayList<int[]> ret = new ArrayList<int[]>();
        ret.add(b);
        ret.add(c);
        return ret;
    }

    static public int [] join(int[]a, int[]b){
        if(a == null)
            return b;
        if(b == null)
            return a;
        int ax=0, bx = 0, cx = 0;
        int [] c = new int [a.length+b.length];
        while(ax<a.length && bx<b.length){
            if(b[bx] <= a[ax])
                c[cx++] = a[ax++];
            else
                c[cx++] = b[bx++];
        }
        if(ax<a.length)//b ran out first
            while(ax<a.length)
                c[cx++] = a[ax++];
        else if(bx<b.length)//a ran out first
            while(bx<b.length)
                c[cx++] = b[bx++];
        return c;
    }
}
