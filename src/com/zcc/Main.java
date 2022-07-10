package com.zcc;

import com.zcc._06_linklist.*;

public class Main {

    public static void main(String[] args) {
        LRUBaseSingleLinkList<Integer> cache = new LRUBaseSingleLinkList<>(5);
        cache.addToCache(1);
        cache.addToCache(2);
        cache.addToCache(3);
        cache.addToCache(4);
        cache.addToCache(5);
        cache.addToCache(1);
        cache.addToCache(6);
        cache.addToCache(7);
        cache.showCache();
        System.out.println("============v2");
        LRUBaseSingleLinkListV2<Integer> cachev2 = new LRUBaseSingleLinkListV2<>(5);
        cachev2.add(1);
        cachev2.add(2);
        cachev2.add(3);
        cachev2.add(4);
        cachev2.add(5);
        cachev2.add(1);
        cachev2.add(6);
        cachev2.add(7);
        cachev2.show();
        System.out.println("============base array");
        LRUBaseArray<Integer> lruBaseArray = new LRUBaseArray<>(5);
        lruBaseArray.add(1);
        lruBaseArray.add(2);
        lruBaseArray.add(3);
        lruBaseArray.add(4);
        lruBaseArray.add(5);
        lruBaseArray.add(1);
        lruBaseArray.add(6);
        lruBaseArray.add(7);
        lruBaseArray.show();

        SingleNode<String> head = new SingleNode<>();
        head.next=new SingleNode<>("1");
        head.next.next=new SingleNode<>("2");
        head.next.next.next=new SingleNode<>("3");
        head.next.next.next.next=new SingleNode<>("4");
        head.next.next.next.next.next=new SingleNode<>("5");
        head.next.next.next.next.next.next=new SingleNode<>("6");
        head.reserve();
        System.out.println(new SinglyLinkedList().judge(head));


    }
}
