import java.time.*;
import java.util.*;
import java.util.Map.Entry;

import helpers.*;
import sortingalgorithms.*;


public class SortBenchmarks {
    private static int SMALL = 100;
    private static int MEDIUM = 5000;
    private static int BIG = 100000;

    public static void main(String[] arg) {
        if(arg.length > 0) {
            int arraySize = Integer.parseInt(arg[0]);

            Map<Long, String> sortMap = new TreeMap<>();
            for(ArraySort sort : SortLoader.getArraySorts()) {
                System.out.println(sort.getSortName());
                long sortBenchmark = sort.getBenchmark(arraySize);
                sortMap.put(sortBenchmark, sort.getSortName());

                print(arraySize, sort.getSortName(), sortBenchmark);
            }
            print(sortMap);
        } else {
            Map<Long, String> smallSort = new TreeMap<>();
            Map<Long, String> mediumSort = new TreeMap<>();
            Map<Long, String> bigSort = new TreeMap<>();

            for(ArraySort sort: SortLoader.getArraySorts()) {
                System.out.println(sort.getSortName());
                long sortBenchmark = sort.getBenchmark(SMALL);
                smallSort.put(sortBenchmark, sort.getSortName());
                print(SMALL, sort.getSortName(), sortBenchmark);

                sortBenchmark = sort.getBenchmark(MEDIUM);
                mediumSort.put(sortBenchmark, sort.getSortName());
                print(MEDIUM, sort.getSortName(), sortBenchmark);

                sortBenchmark = sort.getBenchmark(BIG);
                bigSort.put(sortBenchmark, sort.getSortName());
                print(BIG, sort.getSortName(), sortBenchmark);
            }
            print(smallSort);
            print(mediumSort);
            print(bigSort);
        }
    }

    private static void print(int elementCount, String sortName, long score) {
        System.out.println(elementCount + " elements. " +  sortName + " " + score);
    }

    private static void print(Map<Long, String> treeMap) {
        Set<Entry<Long, String>> entrySet = treeMap.entrySet();
        int place = 1;

        System.out.println("");
        for(Entry<Long, String> entry : entrySet) {
            System.out.println(place++ + ". " +  entry.getValue() + " " + entry.getKey());
        }
    }
}

