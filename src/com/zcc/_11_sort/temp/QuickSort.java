package com.zcc._11_sort.temp;

/**
 * @author Zcc
 * created on 22/10/10 1:45
 */
public class QuickSort extends AbstractSort {
    @Override
    public <T extends Comparable<T>> void sort(T[] unsortedArray) {
        doSort(unsortedArray, 0, unsortedArray.length - 1);
    }

    public <T extends Comparable<T>> void doSort(T[] unsortedArray, int left, int right) {
        if (left < right) {
            int partition = getPartition(unsortedArray, left, right);
            doSort(unsortedArray, left, partition - 1);
            doSort(unsortedArray, partition + 1, right);
        }
    }

    public <T extends Comparable<T>> int getPartition(T[] unsortedArray, int left, int right) {
        T compareElement = unsortedArray[right];
        int partition=left;
        for (int i = left; i < right ; i++) {
            if(unsortedArray[i].compareTo(compareElement)<0){
                if(partition!=left){
                    swap(unsortedArray,i,partition);
                }
                partition++;
            }
        }
        swap(unsortedArray,partition,right);
        return partition;
    }

    /**
     * 查找第k小的元素
     * @param unsortedArray
     * @param k
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> T findK(T[] unsortedArray,int k){
        int partition = getPartition(unsortedArray, 0, unsortedArray.length - 1);
        while (partition+1!=k){
            if(k>partition+1){
                partition = getPartition(unsortedArray, partition + 1, unsortedArray.length - 1);
            }else {
                partition=getPartition(unsortedArray,0,partition-1);
            }
        }
        return unsortedArray[partition];
    }


    @Override
    public boolean isShowUnSortedArray() {
        return super.isShowUnSortedArray();
    }

    @Override
    public boolean isShowSortedArray() {
        return true;
    }
}
