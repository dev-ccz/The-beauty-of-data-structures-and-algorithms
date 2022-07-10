package com.zcc._06_linklist;

/**
 * @author Zcc
 * created on 22/6/25 18:19
 * 数据结构与算法之美
 * 使用单链表实现LRU淘汰算法（least recently used） 头插法实现
 */
public class LRUBaseSingleLinkList<T> {

    private int maxSize=10;
    private int currentSize=0;
    private final SingleNode<T> head=new SingleNode<>();

    public LRUBaseSingleLinkList(int maxSize) {
        this.maxSize = maxSize;
    }

    public void  addToCache(T t){
        SingleNode<T> cur = head;
        while (cur.next!=null){
            if(cur.next.data.equals(t)){
                cur.next=cur.next.next;
                SingleNode<T> node = new SingleNode<>();
                node.next=head.next;
                node.data=t;
                head.next=node;
                return;
            }
            cur=cur.next;
        }
        //走到这里的就是链表里不包含t
        if(currentSize<maxSize){
            head.next= new SingleNode<>(t, head.next);
            currentSize++;
        }else {
            cur=head;
            while (cur.next.next!=null){
                cur=cur.next;
            }
            cur.next=null;
            head.next= new SingleNode<>(t, head.next);
        }
    }


    public void showCache(){
        SingleNode<T> cur = head;
        while (cur.next!=null){
            System.out.println(cur.next.data);
            cur=cur.next;
        }
    }
}
