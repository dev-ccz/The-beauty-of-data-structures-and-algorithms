package com.zcc._07_linklist;

import java.util.List;

/**
 * @author Zcc
 * created on 22/7/9 0:05
 * 快慢指针求解
 */
public class LeetCode_141 {

    public boolean hasCycle(ListNode head) {
        if(head==null || head.next==null || head.next.next==null){
            return false;
        }

        ListNode slow=head.next;
        ListNode fast=head.next.next;
        while (slow!=null && fast!=null){
            if(fast.next==null){
                return false;
            }
            if(slow==fast){
                return true;
            }
            slow=slow.next;
            fast=fast.next.next;
        }

        return false;
    }

    public boolean hasCycle1(ListNode head){
        if(head==null || head.next==null){
            return false;
        }
        ListNode slow=head;
        ListNode fast=head.next;
        while (slow!=fast){
            if(fast==null ||fast.next==null){
                return false;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
    }
}
