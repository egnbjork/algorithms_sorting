package sortingalgorithms;

import helpers.*;

public interface ArraySort {

    int[] sort(int arr[]);

    String getSortName();

    boolean isEnabled();

    default long getBenchmark(int arraySize) {
        int[] randomNumbers = RandomNumbersGenerator.generate(arraySize);
        long start = System.currentTimeMillis();
        int[] sortedArray = sort(randomNumbers);
        long stop = System.currentTimeMillis();
        if(TestSorted.test(sortedArray, false) > 0) {
            System.out.println("=== " + getSortName() + " algorithm does not work===");
            return -1;
        }
        return stop - start;
    }
}
