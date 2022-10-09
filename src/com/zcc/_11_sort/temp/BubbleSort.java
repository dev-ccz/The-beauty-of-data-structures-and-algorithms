package com.zcc._11_sort.temp;

/**
 * @author Zcc
 * created on 22/10/1 11:10
 */
public class BubbleSort extends AbstractSort {
    @Override
    public <T extends Comparable<T>> void sort(T[] unsortedArray) {
        int length = unsortedArray.length;
        for (int i = 0; i < length; i++) {
            boolean swap=false;
            for (int j = 0; j < length - i - 1; j++) {
                if (unsortedArray[j].compareTo(unsortedArray[j + 1]) > 0) {
                    swap(unsortedArray, j, j + 1);
                    swap=true;
                }
            }
            if(!swap){
                break;
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
