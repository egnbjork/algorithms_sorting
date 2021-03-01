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
            int element = arr[i];
            int index = i;
            while(index > 0 && arr[index - 1] > element) {
                arr[index--] = arr[index];
            }
            arr[index] = element;
        }

        return arr;
    }

    @Override
    public String getSortName() {
        return "Insertion Sort";
    }
}

