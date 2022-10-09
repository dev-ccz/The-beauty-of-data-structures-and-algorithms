package com.zcc._11_sort.temp;

/**
 * @author Zcc
 * created on 22/10/1 11:16
 */
public class SelectionSort extends AbstractSort {
    @Override
    public <T extends Comparable<T>> void sort(T[] unsortedArray) {
        int length = unsortedArray.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i; j < length; j++) {
                if (unsortedArray[min].compareTo(unsortedArray[j]) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(unsortedArray, min, i);
            }
        }
    }

    @Override
    public boolean isShowUnSortedArray() {
        return true;
    }

    @Override
    public boolean isShowSortedArray() {
        return true;
    }
}
