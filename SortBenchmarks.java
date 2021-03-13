import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.Map.Entry;

import helpers.*;
import sortingalgorithms.*;


public class SortBenchmarks {

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
        StringBuilder sb = new StringBuilder();

        Duration d = Duration.ofMillis(score);
        long hours = d.toHoursPart();
        if(hours > 0) {
            sb.append(hours + "h ");
        }

        long minutes = d.toMinutesPart();
        if(minutes > 0) {
            sb.append(minutes + "m ");
        }

        long seconds = d.toSecondsPart();
        if(seconds > 0) {
            sb.append(seconds + "s ");
        }

        long milliseconds = d.toMillisPart();
        if(milliseconds > 0) {
            sb.append(milliseconds + "ms ");
        }

        return sb.toString();
    }

    private static long runSort(ArraySort sort, int elementCount) {
        if(sort.isEnabled()) {
            long sortBenchmark = sort.getBenchmark(elementCount);

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
            System.out.println("");
    }

    private static void runMultipleBenchmarks() {
        int arraySize = 1;
        for(int i = 1; i < 7; i++) {
            arraySize *= 10;
            runSingleBenchmark(arraySize);
        }
    }
}

