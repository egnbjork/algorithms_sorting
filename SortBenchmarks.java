import java.time.*;

import helpers.*;
import sortingalgorithms.*;

public class SortBenchmarks {
    private static int SMALL = 100;
    private static int MEDIUM = 5000;
    private static int BIG = 100000;

    public static void main(String[] arg) {
        if(arg.length < 1) {
            System.out.println("Pass arguments for array size!");
            return;
        }
        int arraySize = Integer.parseInt(arg[0]);

        long sortScore = BubbleSort.sort(arraySize);
        print(arraySize, BubbleSort.class.getName(), sortScore);

//        long shortScore = BubbleSort.sort(SMALL);
//        print(SMALL, BubbleSort.class.getName(), shortScore);

//        long mediumScore = BubbleSort.sort(MEDIUM);
//        print(MEDIUM, BubbleSort.class.getName(), mediumScore);

//        long bigScore = BubbleSort.sort(BIG);
//        print(BIG, BubbleSort.class.getName(), bigScore);
    }

    private static void print(int elementCount, String sortName, long score) {
        System.out.println(elementCount + " elements. " +  sortName + " " + score);
    }
}

