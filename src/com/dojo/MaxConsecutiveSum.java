package com.dojo;

import java.util.Arrays;

/**  * User: zen */
public class MaxConsecutiveSum {
    public static void main(String [] args){
        int [] set = {1,3,-2,5};
        int index = 0;
        System.out.println(rMaxSum(set, index));
    }
    public static int maxSum(int set[]){
        int sum = 0;
        for(int i : set){
            if(i < 0)   continue;
            sum += i;
        }
        return sum;
    }
    public static int rMaxSum(int set [], int index){
        if(index==set.length)   return 0;
        if(set[index] < 0)      return rMaxSum(set, ++index);
                                return set[index] + rMaxSum(set, ++index);
    }
    public static int maxSumCheat(int set []){
        Arrays.sort(set);
        int sum = 0;
        for(int i = set.length-1; 0 < set[i]; i--){
            sum += set[i];
        }
        return sum;
    }
}
