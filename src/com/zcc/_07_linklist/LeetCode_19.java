package com.zcc._07_linklist;

/**
 * @author Zcc
 * created on 22/7/10 9:29
 * 快慢指针是链表的经典操作
 */
public class LeetCode_19 {
    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre=new ListNode();
        pre.next=head;
        ListNode slow=pre,fast=pre;
        int i=0;
        //加了头节点，多走一步
        while (i<=n){
            fast=fast.next;
            i++;
        }
        while (fast!=null){
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return pre.next;
    }
}
