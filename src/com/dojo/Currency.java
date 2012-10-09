package com.dojo;

public class Currency {
    public static void main(String ... args){
        int [] set = {1,16};
        int remainder = 17;

        System.out.println(numPermutations(set, remainder));
    }
    static public int numPermutations(int [] set, int remainder){
        int sum = 0;
        if(remainder == 0){
            return 1;
        } else if (remainder < 0){
            return 0;
        }
        for(int i : set){
            sum += numPermutations(set, remainder - i);
        }
        return sum;
    }
}
//    static public int numCombinations(int [] set, int remainder){
//        return numCombinations(set, remainder)/
//                (factorial(set.length) * factorial(remainder));
//    }
//    static public int factorial(int num){
//        if(num == 0)
//            return 1;
//        return num * factorial(num-1);
//    }


