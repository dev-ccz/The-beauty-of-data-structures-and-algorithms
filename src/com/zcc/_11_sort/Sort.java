package com.zcc._11_sort;

/**
 * @author Zcc
 * created on 22/8/27 13:56
 */
public interface Sort {

    /**
     * sort
     * @param unsorted always not empty array
     * @param <T> array element
     */
    <T extends Comparable<T>> void   sort(T[] unsorted);

}
