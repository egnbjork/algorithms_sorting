package sortingalgorithms;

import java.time.*;

import helpers.*;

import java.util.*;

public class BubbleSort implements ArraySort {

    @Override
    public int[] sort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int p = 0; p < arr.length - 1; p++) {
                if(arr[p] > arr[p + 1]) {
                    int k = arr[p];
                    arr[p] = arr[p + 1];
                    arr[p + 1] = k;
                }
            }
        }

        return arr;
    }

    @Override
    public long getBenchmark(int arraySize) {
        int[] randomNumbers = RandomNumbersGenerator.generate(arraySize);
        long start = System.currentTimeMillis();

        int[] sortedArray = sort(randomNumbers);

        long stop = System.currentTimeMillis();
        TestSorted.test(sortedArray, false);
        return stop - start;
    }

    @Override
    public String getSortName() {
        return "Bubble Sort";
    }
}

