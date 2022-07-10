package com.zcc._06_linklist;

/**
 * @author Zcc
 * created on 22/6/25 20:18
 * 回文字符串判断
 */
public class SinglyLinkedList {


    /**
     * 判断用链表表示的字符串是否是回文字符串
     * 快慢指针法
     *
     * @param string str headNode;
     */
    public boolean judge(SingleNode<String> string) {
        if (string == null || string.next == null) {
            return false;
        }
        SingleNode<String> fastPointer = string.next, slowPointer = string.next, pre = null;
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            SingleNode<String> next = slowPointer.next;
            slowPointer.next=pre;
            pre=slowPointer;
            slowPointer=next;
        }

        if(fastPointer!=null){
            slowPointer=slowPointer.next;
        }

        while (slowPointer!=null && pre!=null){
            if(!slowPointer.data.equals(pre.data)){
                return false;
            }
            slowPointer=slowPointer.next;
            pre=pre.next;
        }
        return true;
    }
}
