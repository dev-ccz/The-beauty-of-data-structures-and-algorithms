package com.zcc._08_stack;


import java.util.Stack;

/**
 * @author Zcc
 * created on 22/7/11 20:46
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 * 解体思路1： 中缀表达式转为后缀表达式 然后求值
 */
public class LeetCode_224 {

    public static void main(String[] args) {
        System.out.println(calculate("1-(-333+3)+3"));
    }

    /**
     * 利用正负符号将括号击穿，直接一遍就计算出来了
     * 遇到-号，运算符反转 遇到（ 运算符入栈
     * @param str string
     * @return integer
     */
    static int calculate(String str) {
        Stack<Integer> st = new Stack<>();
        int ans = 0, num = 0, op = 1;
        st.push(op);

        for (char c : str.toCharArray()) {
            if (c == ' ') continue;
            //多个连续数字
            else if (c >= '0') num = num * 10 - '0' + c;
            else {
                ans += op * num;
                num = 0;

                if (c == '+') op = st.peek();
                else if (c == '-') op = -st.peek();
                else if (c == '(') st.push(op);
                else st.pop();
            }
        }

        return ans + op * num;
    }




}
