package com.util;

/**          * User: zen         */
public class StringUtilz {
    public static void main(String ... args){
        System.out.println(getToken("2(1)(3)",0));
        System.out.println(getToken("2(1)(3)",1));
        System.out.println(getToken("9(2(1)(3))(5(4)(6))",0));
        System.out.println(getToken("9(2(1)(3))(5(4)(6))",1));
    }
    //IGNORES ANYTHING NOT IN A PARENTHESIS
    public static String getToken(String tokens, int tokenIndex){
        int parenCount = 0;        String ret = ""; boolean begin = false;
        for(char t : tokens.toCharArray()){
            if(t=='('){
                parenCount++;
                begin = true;
            }
            if(tokenIndex == 0 && begin)
                ret += t;
            if(t==')'){
                parenCount--;
                if(parenCount == 0 && tokenIndex == 0) {
                    break;
                } else if(parenCount == 0) {
                    tokenIndex--;
                }
            }
        }
        return ret.substring(1, ret.length()-1);  //strip parens
    }
}
