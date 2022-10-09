package com.zcc._11_sort.impl;

/**
 * @author Zcc
 * created on 22/8/27 14:22
 */
public class SelectionSort extends AbstractSort {
    @Override
    public <T extends Comparable<T>> void sort(T[] unsorted) {
        for (int i = 0; i < unsorted.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < unsorted.length; j++) {
                if (unsorted[j].compareTo(unsorted[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                super.swap(unsorted, minIndex, i);
            }
        }
    }

}
