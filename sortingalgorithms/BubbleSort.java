package sortingalgorithms;

import java.time.*;

import helpers.*;

public class BubbleSort implements ArraySort {

    @Override
    public int[] sort(int[] arr) {
        int[] sortedArray = new int[arr.length];
        return sortedArray;
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

