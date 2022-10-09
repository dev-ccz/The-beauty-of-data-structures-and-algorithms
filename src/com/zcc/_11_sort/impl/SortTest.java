package com.zcc._11_sort.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Zcc
 * created on 22/8/27 14:07
 */
public class SortTest {

    public static void main(String[] args) throws InterruptedException {


        BubbleSort bubbleSort = new BubbleSort();
        sortTest(bubbleSort);
        sortTest(new InsertionSort());
        sortTest(new SelectionSort());
        sortTest(new MergeSort());
        sortTest(new QuickSort());
//        sortTest(new CountingSort());
        System.out.println(1.0e-3);
    }



    private static void sortTest(Sort sort) {

        Integer[] initArrays = initArrays(sort.getClass().getSimpleName(),10 , 751);
        long l = System.nanoTime();
        sort.sort(initArrays);
        System.out.println("【" + sort.getClass().getSimpleName() + "】" + " sorted arrays:" + Arrays.toString(initArrays));
        System.out.println("【" + sort.getClass().getSimpleName() + "】" + " sorted time:" + (System.nanoTime()-l));

    }

    private static Integer[] initArrays(String sortName, int arrayLength, int max) {
        Integer[] integers = new Integer[arrayLength];
        Map<Integer, Integer> map = new HashMap<>(arrayLength * 2);
        int count = 0;
        while (count < arrayLength) {
            int anInt = new Random().nextInt(max);
            /*if (map.containsKey(anInt)) {
                continue;
            }
            map.put(anInt, 1);*/
            integers[count++] = anInt;
        }
//        System.out.println("【" + sortName + "】" + " init arrays:" + Arrays.toString(integers));
        return integers;
    }
}
