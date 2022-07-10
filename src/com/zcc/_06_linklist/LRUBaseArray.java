package com.zcc._06_linklist;

/**
 * @author Zcc
 * created on 22/6/25 19:31
 * 数据结构与算法之美
 */
public class LRUBaseArray<T> {
    private final int maxSize;
    private int currentSize=0;
    private int currentIndex;
    private final T[] array;

    public LRUBaseArray() {
        this(10);
    }

    public LRUBaseArray(int maxSize) {
        this.maxSize = maxSize;
        this.currentIndex=maxSize-1;
        array = (T[]) new Object[maxSize];
    }

    public void add(T data){
        int index = findIndex(data);
        if(index>0){
            deleteData(index);
            insertToIndex(data);
            return;
        }

        if(currentSize>=maxSize){
            deleteEnd();
        }
        insertToIndex(data);

    }

    public void show(){

        for (int i = Math.max(0,currentIndex); i <maxSize; i++) {
            System.out.print(array[i]+"->");
        }
        System.out.println("null");
    }

    //删除一个元素，currentIndex++
    public void deleteData(int index){
        for (int i = index-1; i >=0; i--) {
            array[i+1]=array[i];
        }
        currentSize--;
        currentIndex++;
    }

    //删除一个元素，currentIndex--
    public void insertToIndex(T data){
        array[currentIndex--]=data;
        currentSize++;
    }

    public void  deleteEnd(){
        for (int i = maxSize-2; i >=0; i--) {
            array[i+1]=array[i];
        }
        currentSize--;
        currentIndex++;
    }

    public int findIndex(T data){
        for (int i = 0; i < array.length; i++) {
            if(data.equals(array[i])) return i;
        }
        return -1;
    }
}
