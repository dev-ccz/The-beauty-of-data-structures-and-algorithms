package com.zcc._11_sort.impl;

import com.zcc._11_sort.AbstractSort;

/**
 * @author Zcc
 * created on 22/8/27 14:35
 */
public class MergeSort extends AbstractSort {

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort(T[] unsorted) {
        T[] temp = (T[]) new Comparable[unsorted.length];
        doSort(unsorted, 0, unsorted.length - 1, temp);
    }


    public <T extends Comparable<T>> void doSort(T[] unsorted, int left, int right, T[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            doSort(unsorted, 0, mid, temp);
            doSort(unsorted, mid + 1, right, temp);
            merge(unsorted, left, mid, right, temp);
        }
    }


    public <T extends Comparable<T>> void merge(T[] unsorted, int left, int mid, int right, T[] temp) {
        System.arraycopy(unsorted, left, temp, left, right - left + 1);
        int i = left;
        int k = mid + 1;
        int p = left;
        while (i <= mid && k <= right) {
            if (temp[i].compareTo(temp[k]) <=0) {
                unsorted[p++] = temp[i++];
            } else {
                unsorted[p++] = temp[k++];
            }
        }

        while (i <= mid) {
            unsorted[p++] = temp[i++];
        }

        while (k <= right) {
            unsorted[p++] = temp[k++];
        }
    }
}
