package helpers;

import java.util.*;

public class TestSorted {

    public static int test(int[] arr, boolean isVerbose) {
        int errors = 0;
        Set<Integer> distinctNumbers = new HashSet<>();

        for(int i = 0; i < arr.length - 1; i++ ) {
            if (arr[i] > arr[i + 1]) {
                print(arr[i] + " (element " + i + ") is bigger than next element " + arr[i + 1], isVerbose);
                errors++;
            }
            distinctNumbers.add(arr[i]);
        }
        if (errors == 0 || distinctNumbers.size() < 2) {
            print("sorting looks good", isVerbose);
        } else {
            print("total errors: " + errors, isVerbose);
        }

        return errors;
    }

    private static void print(String string, boolean isVerbose) {
        if(isVerbose) {
            System.out.println(string);
        }
    }
}
