package ru.kilg.ss.run.sort;

import java.util.Random;

/**
 * SomeSamples
 * Bubble - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 07.05.19
 */

public class Bubble {
    private static final int SIZE = 25;
    private static final int BOUND = 100;
    private static int[] source;

    static {
        source = randomize(SIZE, BOUND);
    }

    private static int[] randomize(int size, int bound) {
        int[] arr = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(bound);
        }

        return arr;
    }


    public static void main(String[] args) {
        printArray(source);
        printArray(bubbleSort(source.clone()));

        System.out.println("------------------------------" +
                "----------------------------------------------------");

        printArray(source);
        printArray(quickSort(source.clone()));

        System.out.println("generic random");
        int[] bigArr = randomize(10000, 10000);

        System.out.println("bubble sort begin:");
        watcher(() -> bubbleSort(bigArr.clone()));
        System.out.println("bubble sort end.");

        System.out.println();

        System.out.println("quick sort begin:");
        watcher(() -> quickSort(bigArr.clone()));
        System.out.println("quick sort end.");

    }


    //wrapper for work time measuring
    private static void watcher(Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.printf("[ %d ] \n", timeConsumedMillis);
    }


    private static void printArray(int[] arr) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (i > 0) builder.append(", ");
            builder.append(arr[i]);
        }
        System.out.println(builder.toString());

    }

    //Most slowly but easy sorting
    //sorting.at  - graph examples
    private static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[i] < arr[j]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
        return arr;
    }

    //just faster
    private static int[] quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    //partition recursive
    private static int[] quickSort(int[] arr, int low, int high) {
        int pivot = arr[low + (high - low) / 2];
        int i = low;
        int j = high;

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;
                j--;
            }
        }

        if (low < j)
            arr = quickSort(arr, low, j);

        if (high > i)
            arr = quickSort(arr, i, high);

        return arr;
    }


}
