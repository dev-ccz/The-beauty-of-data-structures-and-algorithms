package com.zcc._08_stack;

import java.util.Stack;

/**
 * @author Zcc
 * created on 22/7/11 20:45
 */
public class LeetCode_20 {
    public static void main(String[] args) {
        LeetCode_20 leetCode_20 = new LeetCode_20();
        System.out.println(leetCode_20.isValid("()"));
        System.out.println(leetCode_20.isValid("()[]{}"));
        System.out.println(leetCode_20.isValid("(]"));
        System.out.println(leetCode_20.isValid("([)]"));
        System.out.println(leetCode_20.isValid("{[]}"));
        System.out.println(leetCode_20.isValid("}"));
    }


    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (aChar == '(' || aChar == '{' || aChar == '[') {
                stack.push(aChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (!valid(pop, aChar)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    boolean valid(Character left, Character right) {
        return (left == '(' && right == ')') || (left == '[' && right == ']') || (left == '{' && right == '}');
    }

}
