package sortingalgorithms;

import java.time.*;

import java.util.*;

public class QuickSort implements ArraySort {

    @Override
    public boolean isEnabled() {
      return true;
    }

    @Override
    public int[] sort(int[] arr) {
      return quicksort(arr, 0, arr.length - 1);
    }

    @Override
    public String getSortName() {
        return "Quick Sort";
    }

    private static int[] quicksort(int[] arr, int start, int end) {
      if(end - start < 2) {
        if(arr[start] > arr[end]) {
          swap(arr, start, end);
        }
        return arr;
      }

      int pivot = getPivot(arr, start, end); 

      swap(arr, start, pivot);

      int i = start + 1;
      int j = start + 1;

      while(j <= end) {
        if(arr[j] <= arr[start]) {
          swap(arr, i, j);
          i++;
        }
        j++;
      }

      //swap pivot to its place
      swap(arr, start, i - 1);
      
      //left side
      if(start < i - 1) {
        quicksort(arr, start, i - 2);
      }

      //right side
      if(i < end) {
        quicksort(arr, i, end);
      }

      return arr;
    }

    private static int[] swap(int[] arr, int a, int b) {
      int c = arr[a];
      arr[a] = arr[b];
      arr[b] = c;
      return arr;
    }

    private static int getPivot(int[] arr, int min, int max) {
      int pivot = 0;
      int mid = (int) (arr.length / 2);

      if(arr[min] < arr[mid] && arr[min] > arr[max]) {
        return min;
      } else if (arr[mid] > arr[max] && arr[mid] < arr[min]) {
        return mid;
      } 

      return max;
    }
}

