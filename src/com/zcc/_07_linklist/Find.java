package com.zcc._07_linklist;

/**
 * @author Zcc
 * created on 22/6/30 21:49
 */
public class Find {

    public static void main(String[] args) {
        int index1 = find(new int[]{1, 2, 3, 4, 5, 6}, 6, 5);
        index1 = find_(new int[]{1, 2, 3, 4, 5, 6}, 6, 5);
    }

    /**
     * @param array 数组
     * @param n     数组的长度
     * @param key   要找的key
     * @return index
     */
    public static int find(int[] array, int n, int key) {
        if (array == null || n <= 0) {
            return -1;
        }
        int i = 0;
        while (i < n) {
            if (array[i] == key) {
                return i;
            }
            i++;
        }
        return i;
    }

    /**
     * 使用哨兵实现
     *
     * @return index
     */
    public static int find_(int[] array, int n, int key) {
        if (array == null || n <= 0) {
            return -1;
        }
        if (array[n - 1] == key) {
            return n - 1;
        }
        //为了最后恢复，不改变数组结构
        int temp = array[n - 1];
        //这一步做哨兵
        array[n-1]=key;
        int i=0;
        //可以发现，比起find,while循环少了一个比较的操作
        while (array[i]!=key){
            i++;
        }
        array[n-1]=temp;
        if(i==n-1){
            return -1;
        }else {
            return i;
        }
    }
}
