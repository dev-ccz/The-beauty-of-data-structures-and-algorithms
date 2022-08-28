package com.zcc._11_sort.impl;

import com.zcc._11_sort.AbstractSort;

import java.util.Arrays;

/**
 * @author Zcc
 * created on 22/8/27 14:44
 */
public class QuickSort extends AbstractSort {
    @Override
    public <T extends Comparable<T>> void sort(T[] unsorted) {
        doSort(unsorted, 0, unsorted.length - 1);
    }


    public <T extends Comparable<T>> void doSort(T[] unsorted, int left, int right) {
        if (left < right) {
            int partitionIndex = getPartitionIndex(unsorted, left, right);
            doSort(unsorted, 0, partitionIndex - 1);
            doSort(unsorted, partitionIndex + 1, right);
        }
    }


    public <T extends Comparable<T>> int getPartitionIndex(T[] unsorted, int left, int right) {
        T compareElement=unsorted[right];
        int partition=left;
        for (int j = left; j < unsorted.length - 1; j++) {
            if(unsorted[j].compareTo(compareElement)<0){
                if(partition!=j){
                    //非必要不交换
                    super.swap(unsorted,partition,j);
                }
                partition++;
            }
        }
        super.swap(unsorted,partition,right);
        return partition;
    }
}
