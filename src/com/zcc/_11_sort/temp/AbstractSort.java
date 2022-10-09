package com.zcc._11_sort.temp;

import java.util.Arrays;

/**
 * @author Zcc
 * created on 22/10/1 0:57
 */
public abstract class AbstractSort {

    private final ThreadLocal<Long> start = new ThreadLocal<>();
    private final ThreadLocal<Long> end = new ThreadLocal<>();

    public <T extends Comparable<T>> void sort(T[] unsortedArray) {

    }



    public boolean isShowUnSortedArray() {
        return false;
    }

    public boolean isShowSortedArray() {
        return false;
    }

    protected final <T extends Comparable<T>> void sortWithDuration(T[] unsortedArray) {
        showUnSortedArray(unsortedArray);
        startSort();
        sort(unsortedArray);
        endSort();
        showDuration();
        showSortedArray(unsortedArray);
    }

    protected final <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void startSort() {
        this.start.set(System.nanoTime());
    }

    private void endSort() {
        this.end.set(System.nanoTime());
    }

    private void showDuration() {
        double duration = (this.end.get() - this.start.get()) * 1.0e-3;
        System.out.println("##### 【" + this.getClass().getSimpleName() + "】 duration: " + duration + "\n");
    }

    private <T extends Comparable<T>> void showSortedArray(T[] array) {
        if (this.isShowSortedArray()) {
            System.out.println("***** 【" + this.getClass().getSimpleName() + "】 sorted Array: " + Arrays.toString(array));
        }
    }

    private <T extends Comparable<T>> void showUnSortedArray(T[] array) {
        if (this.isShowUnSortedArray()) {
            System.out.println("***** 【" + this.getClass().getSimpleName() + "】 unSorted Array: " + Arrays.toString(array));
        }
    }

}
