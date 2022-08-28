package com.zcc._11_sort.impl;

import com.zcc._11_sort.AbstractSort;

import java.util.Arrays;

/**
 * @author Zcc
 * created on 22/8/28 9:36
 * 计数排序其实是桶排序的一种特殊情况。
 * 当要排序的 n 个数据，所处的范围并不大的时候，比如最大值是 k，我们就可以把数据划分成 k 个桶。每个桶内的数据值都是相同的，省掉了桶内排序的时间
 * 只能排序int，正整数
 */
public class CountingSort extends AbstractSort {
    @Override
    public <T extends Comparable<T>> void sort(T[] unsorted) {
        doSort((Integer[]) unsorted);
    }

    private void doSort(Integer[] unsorted) {
        int max = 0;
        for (int i = 0; i < unsorted.length; i++) {
            if (unsorted[i] > max) {
                max = unsorted[i];
            }
        }
        //每个数值的个数，下标表示数值
        int[] temp = new int[max + 1];
        for (Integer integer : unsorted) {
            temp[integer]++;
        }
        //顺序求和，c[k]里存储 小与等于k的元素的个数
        for (int i = 1; i < temp.length; i++) {
            temp[i] = temp[i] + temp[i - 1];
        }

        Integer[] result = new Integer[unsorted.length];
        for (int i = unsorted.length - 1; i >= 0; i--) {
            Integer integer = unsorted[i];
            result[temp[integer] - 1] = integer;
            temp[integer]--;
        }
        System.arraycopy(result, 0, unsorted, 0, result.length);
    }


}
