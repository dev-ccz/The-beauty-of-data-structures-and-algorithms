package com.zcc._07_linklist;

/**
 * @author Zcc
 * created on 22/7/10 9:08
 * 合并两个有序链表
 */
public class LeetCode_21_MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode list1=new ListNode(1);
        list1.next=new ListNode(2);
        list1.next.next=new ListNode(4);

        ListNode list2=new ListNode(1);
        list2.next=new ListNode(3);
        list2.next.next=new ListNode(4);

        ListNode listNode = mergeTwoLists(list1, list2);
        System.out.println(listNode.toString());
        ListNode listNode1 = mergeTwoLists(null, null);
        System.out.println(listNode1);
        ListNode listNode2 = mergeTwoLists(list1, null);
        System.out.println(listNode2);

    }


    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head=new ListNode();
        ListNode p1=list1,p2=list2,p3=head;
        while (p1!=null || p2!=null){
            if(p1==null){
                p3.next=new ListNode(p2.val);
                p3=p3.next;
                p2=p2.next;
                continue;
            }
            if(p2==null){
                p3.next=new ListNode(p1.val);
                p3=p3.next;
                p1=p1.next;
                continue;
            }
            if(p1.val<=p2.val){
                p3.next=new ListNode(p1.val);
                p1=p1.next;
            }else {
                p3.next=new ListNode(p2.val);
                p2=p2.next;
            }
            p3=p3.next;
        }
        return head.next;

    }
}
