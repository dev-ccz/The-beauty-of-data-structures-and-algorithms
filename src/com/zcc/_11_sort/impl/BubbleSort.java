package com.zcc._11_sort.impl;

/**
 * @author Zcc
 * created on 22/8/27 14:02
 */
public class BubbleSort extends AbstractSort {


    @Override
    public <T extends Comparable<T>> void sort(T[] unsorted) {
        for (int i = 0; i < unsorted.length; i++) {
            boolean swapFlag = false;
            for (int j = 0; j < unsorted.length - i - 1; j++) {
                if (unsorted[j].compareTo(unsorted[j + 1]) > 0) {
                    super.swap(unsorted, j, j + 1);
                    swapFlag = true;
                }
            }
            if (!swapFlag) {
                break;
            }
        }
    }
}
