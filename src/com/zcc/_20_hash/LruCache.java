package com.zcc._20_hash;


/**
 * @author Zcc
 * created on 22/10/24
 * 一个用链表+哈希表实现的LRU策略的缓存容器
 */
public class LruCache<T> {


    private final Node<T>[] table;
    private Node<T> head;
    private Node<T> end;
    private final int capacity;
    private int elementCount;

    public LruCache() {
        this(16);
    }

    public LruCache(int capacity) {
        this.capacity = capacity;
        elementCount = 0;
        int size;
        size = (size = capacity >>> 3) < 8 ? 8 : size;
        int i = -1 >>> Integer.numberOfLeadingZeros(size - 1);
        size = i < 0 ? 1 : (i >= (1 << 30)) ? 1 << 30 : i + 1;
        this.table = new Node[size];
    }

    public void add(T element) {
        T t = find(element);
        if (t != null) return;

        int index = hashIndex(element);
        if (elementCount >= capacity) {
            if (table[index].equals(end)) {
                table[index] = end.h_next;
            }
            removeEndNode();
            elementCount--;

        }
        Node<T> tNode = new Node<>(null, null, null, element);
        if (end == null) end = tNode;

        if (table[index] == null) {
            table[index] = tNode;
        } else {
            Node<T> tNode1 = table[index];
            while (tNode1.h_next != null) {
                tNode1 = tNode1.h_next;
            }
            tNode1.h_next = tNode;
        }
        elementCount++;
        //第一次添加得时候
        if (head == null) {
            head = tNode;
            return;
        }
        tNode.next = head;
        head.prev = tNode;
        head = head.prev;
    }


    public void delete(T element) {
        int index = hashIndex(element);
        if (table[index] != null) {
            Node<T> tNode = table[index];
            while (tNode != null) {
                if (tNode.data.equals(element)) {
                    //删除操作
                    table[index] = tNode.h_next;
                    //如果是头节点
                    if (head.equals(tNode)) {
                        head = head.next;
                        head.prev = null;

                    } else if (end.equals(tNode)) {
                        //如果是尾节点
                        removeEndNode();
                    } else {
                        //如果是中间节点
                        tNode.prev.next = tNode.next;
                        tNode.next.prev = tNode.prev;

                    }
                    tNode = null;
                    elementCount--;
                    break;
                }
                tNode = tNode.h_next;
            }
        }
    }

    public T find(T element) {
        int index = hashIndex(element);
        if (table[index] == null) {
            return null;
        }
        Node<T> node = table[index];
        while (node != null) {
            if (node.data.equals(element)) {
                if (!end.equals(head)) {
                    removeEndNode();
                }
                if (!node.equals(head)) {
                    node.next = head;
                    node.prev = null;
                    head.prev = node;
                    head = node;
                }


                break;
            }
            node = node.h_next;
        }
        return node == null ? null : node.data;
    }


    private void removeEndNode() {
        end = end.prev;
        end.next = null;
    }

    private int hashIndex(T element) {
        return hash(element) & (table.length) - 1;
    }

    private int hash(T element) {
        int h;
        return (element == null) ? 0 : (h = element.hashCode()) ^ (h >>> 16);
    }

    private static class Node<E> {
        public Node<E> prev;
        public Node<E> next;
        public Node<E> h_next;
        public E data;

        public Node(Node<E> prev, Node<E> next, Node<E> h_next, E data) {
            this.prev = prev;
            this.next = next;
            this.h_next = h_next;
            this.data = data;
        }
    }


}
