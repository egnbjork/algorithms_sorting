package sortingalgorithms;

import java.time.*;

import java.util.*;

public class BubbleSort implements ArraySort {

    @Override
    public boolean isEnabled() {
        return false;
    }

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
    public String getSortName() {
        return "Bubble Sort";
    }
}

