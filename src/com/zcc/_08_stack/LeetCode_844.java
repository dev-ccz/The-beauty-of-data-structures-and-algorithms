package com.zcc._08_stack;

import java.util.Stack;

/**
 * @author Zcc
 * created on 22/7/11 20:45
 */
public class LeetCode_844 {

    public static void main(String[] args) {
        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));
        System.out.println(backspaceCompare11("ab##", "c#d#"));
        System.out.println(new Solution().backspaceCompare("xywrrmp", "xywrrmu#p"));
    }
    public static boolean backspaceCompare(String s, String t) {
        Stack<Character> s1=new Stack<>();
        Stack<Character> t1=new Stack<>();
        for (char c : s.toCharArray()) {
            if(c=='#'){
                if(!s1.empty()){
                    s1.pop();
                }
                continue;
            }
            s1.push(c);
        }

        for (char c : t.toCharArray()) {
            if(c=='#'){
                if(!t1.isEmpty()){
                    t1.pop();
                }
                continue;
            }
            t1.push(c);
        }
        if(s1.size()!=t1.size()){
            return false;
        }
        while (!s1.isEmpty()){
            if(s1.pop()!=t1.pop()){
                return false;
            }
        }
        return true;
    }


    public static boolean backspaceCompare11(String s, String t){
        int indexS=s.length()-1;int indexT=t.length()-1;
        int removeS=0,removeT=0;
        while (indexS>=0 || indexT>=0){
            //确保indexS指向的用永远是字符
            while (indexS>=0){
                if(s.charAt(indexS)=='#'){
                    removeS++;
                    indexS--;
                }else if(removeS>0){
                    removeS--;
                    indexS--;
                }else {
                    break;
                }
            }

            while (indexT>=0){
                if(t.charAt(indexT)=='#'){
                    removeT++;
                    indexT--;
                }else if(removeT>0){
                    removeT--;
                    indexT--;
                }else {
                    break;
                }
            }
            if(indexS>=0 && indexT>=0){
                if(s.charAt(indexS) !=t.charAt(indexT)){
                    return false;
                }
            }else {
                if(indexS>=0 || indexT>=0){
                    return false;
                }
            }
            indexS--;
            indexT--;

        }
        return true;
    }


    static class Solution {
        public boolean backspaceCompare(String S, String T) {
            int i = S.length() - 1, j = T.length() - 1;
            int skipS = 0, skipT = 0;

            while (i >= 0 || j >= 0) {
                while (i >= 0) {
                    if (S.charAt(i) == '#') {
                        skipS++;
                        i--;
                    } else if (skipS > 0) {
                        skipS--;
                        i--;
                    } else {
                        break;
                    }
                }
                while (j >= 0) {
                    if (T.charAt(j) == '#') {
                        skipT++;
                        j--;
                    } else if (skipT > 0) {
                        skipT--;
                        j--;
                    } else {
                        break;
                    }
                }
                if (i >= 0 && j >= 0) {
                    if (S.charAt(i) != T.charAt(j)) {
                        return false;
                    }
                } else {
                    if (i >= 0 || j >= 0) {
                        return false;
                    }
                }
                i--;
                j--;
            }
            return true;
        }
    }

}
