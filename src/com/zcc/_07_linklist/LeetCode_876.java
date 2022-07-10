package com.zcc._07_linklist;

/**
 * @author Zcc
 * created on 22/7/10 10:02
 */
public class LeetCode_876 {

    public ListNode middleNode(ListNode head) {
        ListNode pre=new ListNode();
        pre.next=head;
        ListNode fast=pre,slow=pre;
        while (!(fast==null || fast.next==null)){
            fast=fast.next.next;
            slow=slow.next;
        }
        if(fast==null){
            return slow;
        }
        return slow.next;
    }
}
