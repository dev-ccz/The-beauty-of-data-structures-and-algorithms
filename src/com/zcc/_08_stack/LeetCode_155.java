package com.zcc._08_stack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Zcc
 * created on 22/7/11 20:45
 */
public class LeetCode_155 {

    public static void main(String[] args) {

    }

}

class MinStack {

    private ListNode head;

    public MinStack() {
    }

    public void push(int val) {
        if(isEmpty()){
            head= new ListNode(val, val, null);
        }else {
            head= new ListNode(val, Math.min(val, head.min), head);
        }
    }

    public void pop() {
        head=head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }

    private boolean isEmpty(){
       return head==null;
    }

    private static class ListNode{
        public int value;
        public int min;
        public ListNode next;

        public ListNode(int value, int min, ListNode next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }

}
