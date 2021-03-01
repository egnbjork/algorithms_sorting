package sortingalgorithms;

import java.time.*;

import java.util.*;

public class InsertionSort implements ArraySort {

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int[] sort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < arr[i - 1]) {
                int element = arr[i];
                for(int p = i - 1; p >= 0; p--) {
                    if(arr[p] > element) {
                        arr[p + 1] = arr[p]; 
                    } else {
                        arr[p] = element;
                    }
                }
            }
        }

        return arr;
    }

    @Override
    public String getSortName() {
        return "Insertion Sort";
    }
}

