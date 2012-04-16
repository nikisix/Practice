package com.sort;

/** * User: ntomasino */
public class Merge {
    public static void main(String ... args){
        int size = 8;
        int [] arand = new int [size];
        System.out.println("Before:");
        for(int i = 0; i < size; i++){
            arand[i] = Math.round((float)Math.random() * 100);
            System.out.println(arand[i]);
        }
        arand = mergeSort(arand);
//        arand = mergeSort(new int[] {93,49});  FAIL CASE
        System.out.println("\n==============================================" +
                "\n=====================================================" +
                "\nAfter:");
        for(int i = 0; i < size; i++){
            System.out.println(arand[i]);
        }
    }

    static public int [] mergeSort(int [] a){
        int left = 0;
        int right = a.length-1;
        sort(a, left, right);
        return a;
    }

    static public void sort(int[] a, int left, int right){
        if(right-left <= 1)
            return ;
        int mid = (left+right)/2;
        sort(a, left, mid);
        sort(a, mid+1, right);
        merge(a, left, mid, right);
    }
    //in-place merge
    static public void merge(int[]a, int left, int mid, int right){
        int ldex = left;
        int rdex = mid;
        for(int i = left; i<right; i++){
            if(a[ldex] > a[rdex]){
                swap(a, i, rdex);
                ldex = rdex;
                rdex++;
            }
            else{
                swap(a,ldex,i);
                ldex = i;
                ldex++;
            }
        }
    }
    static public void swap(int [] a, int i1, int i2){
        if(i1==i2)
            return;
        int temp;
        temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }
}













