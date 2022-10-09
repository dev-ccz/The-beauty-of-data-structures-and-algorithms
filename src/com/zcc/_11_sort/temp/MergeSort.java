package com.zcc._11_sort.temp;

/**
 * @author Zcc
 * created on 22/10/3 9:54
 */
public class MergeSort extends AbstractSort {
    @Override
    public <T extends Comparable<T>> void sort(T[] unsortedArray) {
        T[] temp = (T[]) new Comparable[unsortedArray.length];
        doSort(unsortedArray, 0, unsortedArray.length - 1, temp);
    }

    public <T extends Comparable<T>> void doSort(T[] unsortedArray, int left, int right, T[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            doSort(unsortedArray, 0, mid, temp);
            doSort(unsortedArray, mid + 1, right, temp);
            merge(unsortedArray, left, mid, right, temp);
        }
    }

    public <T extends Comparable<T>> void merge(T[] unsortedArray, int left, int mid, int right, T[] temp) {
        System.arraycopy(unsortedArray, left, temp, left, right - left + 1);
        int l = left;
        int m = mid + 1;
        int p = left;
        while (l <= mid && m <= right) {
            if (temp[l].compareTo(temp[m]) < 0) {
                unsortedArray[p++] = temp[l++];
            } else {
                unsortedArray[p++] = temp[m++];
            }
        }

        while (l <= mid) {
            unsortedArray[p++] = temp[l++];
        }

        while (p <= left) {
            unsortedArray[p++] = temp[m++];
        }
    }

    @Override
    public boolean isShowUnSortedArray() {
        return false;
    }

    @Override
    public boolean isShowSortedArray() {
        return true;
    }
}
