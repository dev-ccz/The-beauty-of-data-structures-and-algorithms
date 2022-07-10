package com.zcc._06_linklist;


/**
 * @author Zcc
 * created on 22/6/25 18:17
 * 数据结构与算法之美
 */
public class SingleNode<T> {
    public T data;
    public SingleNode<T> next;

    public SingleNode(T data, SingleNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public SingleNode(T data) {
        this.data = data;
    }

    public SingleNode() {
    }

    public void reserve(){
        if( this.next==null){
            return;
        }
        SingleNode<T> cur=this.next;
        SingleNode<T> pre=null;
        while (cur!=null){
            SingleNode<T> next = cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        this.next=pre;

    }

    public static <T> void  reverse(SingleNode<T> head){
        SingleNode<T> cur=head.next;
        head.next=reverse1(cur);
    }


    private static <T> SingleNode<T> reverse1(SingleNode<T> node){
        if(node==null || node.next==null){
            return node;
        }
        SingleNode<T> temp = node.next;
        SingleNode<T> reserve = reverse1(temp);
        temp.next=node;
        node.next=null;
        return reserve;
    }

}
