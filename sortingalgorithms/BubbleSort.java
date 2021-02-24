package sortingalgorithms;

import java.time.*;

import helpers.*;

public class BubbleSort {
    public static long sort(int arraySize) {
        int[] randomNumbers = RandomNumbersGenerator.generate(arraySize);
        long start = System.currentTimeMillis();

        int[] sortedArray = run(randomNumbers);

        long stop = System.currentTimeMillis();
        TestSorted.test(sortedArray, false);
        return stop - start;
    }

    private static int[] run(int[] arr) {
        int[] sortedArray = new int[arr.length];
        return sortedArray;
    }
}

