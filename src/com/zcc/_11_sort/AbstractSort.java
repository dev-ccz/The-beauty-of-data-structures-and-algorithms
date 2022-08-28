package com.zcc._11_sort;

/**
 * @author Zcc
 * created on 22/8/27 14:04
 */
public abstract class AbstractSort implements Sort {

    protected <T extends Comparable<T>> void swap(T[] unsorted, int i, int j) {
        T temp = unsorted[i];
        unsorted[i] = unsorted[j];
        unsorted[j] = temp;
    }

    private void test(){

    }

    public void test1(){

    }
}
