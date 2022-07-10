package com.zcc._07_linklist;

/**
 * @author Zcc
 * created on 22/7/8 23:59
 * 遍历法链表反转
 */
public class LeetCode_206 {

    private static ListNode reverseList(ListNode head){
        if(head==null){
            return null;
        }
        ListNode pre=null;
        while (head!=null){
            ListNode next = head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

}
