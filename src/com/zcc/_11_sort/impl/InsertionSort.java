package com.zcc._11_sort.impl;

/**
 * @author Zcc
 * created on 22/8/27 14:25
 */
public class InsertionSort extends AbstractSort {

    @Override
    public <T extends Comparable<T>> void sort(T[] unsorted) {
        for (int i = 1; i < unsorted.length; i++) {
            T value = unsorted[i];
            int k = i - 1;
            for (; k >= 0; k--) {
                if (unsorted[k].compareTo(value) > 0) {
                    unsorted[k + 1] = unsorted[k];
                } else {
                    break;
                }
            }
            unsorted[k + 1] = value;
        }
    }
}
