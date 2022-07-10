package com.zcc._06_linklist;




/**
 * @author Zcc
 * created on 22/6/25 19:06
 * 数据结构与算法之美
 */
public class LRUBaseSingleLinkListV2<T> {

    private int maxSize=10;
    private int currentSize=0;
    private final SingleNode<T> head=new SingleNode<>();

    public LRUBaseSingleLinkListV2(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     *  add
     * @param data data
     */
    public void add(T data){
        //查找是否存在
        SingleNode<T> pre = findPre(data);
        //存在，先删除，再插入到头部
        if(pre!=null){
            deleteNode(pre);
            insertToHead(data);
            return;
        }
        //不存在
        //1-缓存满了，先删除最后一个阶段，再插入到头部
        if(currentSize>=maxSize){
            deleteEndNode();
        }
        //2-缓存未满，直接插入到头部
        insertToHead(data);
    }

    public void show(){
        SingleNode<T> cur=head;
        while (cur.next!=null){
            System.out.print(cur.next.data+"->");
            cur=cur.next;
        }
        System.out.println("null");
    }

    public void deleteEndNode(){
        SingleNode<T> current=head;
        if(current.next==null) return;
        while (current.next.next!=null){
            current=current.next;
        }
        current.next=null;
        currentSize--;

    }

    public SingleNode<T> findPre(T data){
        SingleNode<T> cur=head;
        while (cur.next!=null){
            if(data.equals(cur.next.data)){
                return cur;
            }
            cur=cur.next;
        }
        return null;
    }

    public void deleteNode(SingleNode<T> pre){
        SingleNode<T> node = pre.next;
        pre.next=node.next;
        node.next=null;
        currentSize--;
    }

    public void insertToHead(T data){
        head.next= new SingleNode<>(data, head.next);
        currentSize++;
    }
}
