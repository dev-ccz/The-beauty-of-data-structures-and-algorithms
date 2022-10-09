package com.zcc._11_sort.temp;

import java.util.Random;

/**
 * @author Zcc
 * created on 22/10/1 10:58
 */
public class SortTest {

    static Integer[] unsortedArray;
    static final int default_max_element = 50;
    static final int default_array_size = 10;


    public static void main(String[] args) {
        Integer[] unsortedArrayForBubble = buildUnsortedArray(true);
        sortTest(new BubbleSort(), unsortedArrayForBubble);

        Integer[] unsortedArrayForSelection = buildUnsortedArray(true);
        sortTest(new SelectionSort(), unsortedArrayForSelection);

        Integer[] unsortedArrayForInsert = buildUnsortedArray(true);
        sortTest(new InsertSort(), unsortedArrayForInsert);

        Integer[] unsortedArrayForMerge = buildUnsortedArray(true);
        sortTest(new MergeSort(), unsortedArrayForMerge);

        Integer[] unsortedArrayForQuick = buildUnsortedArray(true);
        sortTest(new QuickSort(), unsortedArrayForQuick);


        Integer[] unsortedArrayForFindK = buildUnsortedArray(true);
        System.out.println(new QuickSort().findK(unsortedArrayForFindK, 4));

    }


    public static void sortTest(AbstractSort sort, Integer[] unsortedArray) {
        new Thread(() -> {
            sort.sortWithDuration(unsortedArray);
        }).start();
    }

    static Integer[] buildUnsortedArray(boolean isUseCache) {

        if (isUseCache && unsortedArray != null) {
            return getUnsortedArrayTemp(unsortedArray);
        }
        Random random = new Random();
        unsortedArray = new Integer[default_array_size];
        for (int i = 0; i < SortTest.default_array_size; i++) {
            unsortedArray[i] = random.nextInt(default_max_element);
        }
        return getUnsortedArrayTemp(unsortedArray);
    }

    static Integer[] getUnsortedArrayTemp(Integer[] unsortedArray) {
        Integer[] array = new Integer[unsortedArray.length];
        System.arraycopy(unsortedArray, 0, array, 0, unsortedArray.length);
        return array;
    }

}
