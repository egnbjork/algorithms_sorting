package sortingalgorithms;

import java.time.*;

import java.util.*;

public class SelectionSort implements ArraySort {

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int[] sort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;

            for( int p = i + 1; p < arr.length; p++) {
                if(arr[p] < min) {
                    min = arr[p];
                    minIndex = p;
                }
            }
            
            if(minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

        return arr;
    }

    @Override
    public String getSortName() {
        return "Selection Sort";
    }
}

