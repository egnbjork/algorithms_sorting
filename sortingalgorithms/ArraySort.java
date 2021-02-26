package sortingalgorithms;

import helpers.*;

public interface ArraySort {

    int[] sort(int arr[]);

    String getSortName();

    default long getBenchmark(int arraySize) {
        int[] randomNumbers = RandomNumbersGenerator.generate(arraySize);
        long start = System.currentTimeMillis();
        int[] sortedArray = sort(randomNumbers);
        long stop = System.currentTimeMillis();
        TestSorted.test(sortedArray, false);
        return stop - start;
    }
}
