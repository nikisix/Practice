package com.dojo;

public class Currency2 {
    public static void main(String ... args){
        int [] set = {1,2,3};
        int remainder = 9;
        int [] store = new int [set.length];

//        System.out.println("total: "+numCombinations(set, 0, remainder));
        System.out.println("total: "+numCombinationsVerbose(set, 0, remainder, store));
    }
    static public int numCombinations(int[] set, int index, int remainder){
        if(remainder == 0){
            return 1;
        } else if (remainder < 0){
            return 0;
        }
        int sum = 0;
        for(int i = index; i < set.length; i++){
            sum += numCombinations(set, i, remainder - set[i]);
        }
        return sum;
    }

    static public int numCombinationsVerbose(int[] set, int index,
                                             int remainder, int [] store){
        if(remainder == 0){
            for (int i=0; i < set.length; i++)
                System.out.print(store[i]+"*"+set[i] + " + ");
            System.out.println();
            return 1;
        } else if (remainder < 0){
            return 0;
        }
        int sum = 0; int [] tempStore = new int[store.length];
        for(int i = index; i < set.length; i++){
            tempStore = store.clone();
            tempStore[i] += 1;
            sum += numCombinationsVerbose(set, i, remainder - set[i], tempStore);
        }
        return sum;
    }
}
