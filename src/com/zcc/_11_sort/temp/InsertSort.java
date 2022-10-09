package com.zcc._11_sort.temp;

import java.util.Arrays;

/**
 * @author Zcc
 * created on 22/10/3 9:40
 */
public class InsertSort extends AbstractSort {
    @Override
    public <T extends Comparable<T>> void sort(T[] unsortedArray) {

        for (int i = 1; i < unsortedArray.length; i++) {
            T min = unsortedArray[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (unsortedArray[j].compareTo(min) > 0) {
                    unsortedArray[j + 1] = unsortedArray[j];
                } else {
                    break;
                }
            }
            unsortedArray[j + 1] = min;
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
