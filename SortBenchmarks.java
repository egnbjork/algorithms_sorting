import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.Map.Entry;

import helpers.*;
import sortingalgorithms.*;


public class SortBenchmarks {
    private static int TINY = 5000;
    private static int SMALL = 100000;
    private static int MEDIUM = 500000;
    private static int BIG = 1000000;

    public static void main(String[] arg) {
        if(arg.length > 0) {
            int arraySize = Integer.parseInt(arg[0]);

            runSingleBenchmark(arraySize);
        } else {
            runMultipleBenchmarks();
        }
    }

    private static void print(int elementCount, String sortName, long score) {
        System.out.println(elementCount + " elements. " +  sortName + " " + convertTime(score));
    }

    private static void print(Map<Long, String> treeMap) {
        Set<Entry<Long, String>> entrySet = treeMap.entrySet();
        int place = 1;

        for(Entry<Long, String> entry : entrySet) {
            System.out.println(place++ + ". " +  entry.getValue() + " " + convertTime(entry.getKey()));
        }
    }

    private static String convertTime(long score) {
        String time;

        if(TimeUnit.MILLISECONDS.toHours(score) > 0) {
            time = String.valueOf(TimeUnit.MILLISECONDS.toHours(score)) + "h";
        } else if(TimeUnit.MILLISECONDS.toMinutes(score) > 0) {
            time = String.valueOf(TimeUnit.MILLISECONDS.toMinutes(score)) + "m";
        } else if(TimeUnit.MILLISECONDS.toSeconds(score) > 0) {
            time = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(score)) + "s";
        } else {
            time = String.valueOf(score) + "ms";
        }
        return time;
    }

    private static long runSort(ArraySort sort, int elementCount) {
        if(sort.isEnabled()) {
            long sortBenchmark = sort.getBenchmark(elementCount);
            //print(elementCount, sort.getSortName(), sortBenchmark);
            //System.out.println("\n" + sort.getSortName());

            return sortBenchmark;
        }
        return -1;
    }

    private static void runSingleBenchmark(int arraySize) {
            Map<Long, String> sortMap = new ConcurrentSkipListMap<>();
            List<ArraySort> sortList = SortLoader.getArraySorts();
            int sortCount = sortList.size();
            CountDownLatch cdl = new CountDownLatch(sortCount);
            ExecutorService es = Executors.newCachedThreadPool();

            for(ArraySort sort : sortList) {
                
                es.execute(() -> {
                    long sortBenchmark = runSort(sort, arraySize);

                    if(sortBenchmark >= 0) {
                        sortMap.merge(sortBenchmark, sort.getSortName(), (o, n) -> o + ", " + n);
                    }
                    cdl.countDown();
                });
            }
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            es.shutdownNow();
            System.out.println("\n" + arraySize + " elements");
            print(sortMap);
    }

    private static void runMultipleBenchmarks() {
            runSingleBenchmark(TINY);
            runSingleBenchmark(SMALL);
            runSingleBenchmark(MEDIUM);
            runSingleBenchmark(BIG);
    }
}

