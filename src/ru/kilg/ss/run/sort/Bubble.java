package ru.kilg.ss.run.sort;

/**
 * SomeSamples
 * Bubble - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 07.05.19
 */
public class Bubble {
    private static int[] source = {1, 2, 15, 6, 4, 33, 8, 41};

    public static void main(String[] args) {
        printArray(bubbleSort(source));
    }

    private static void printArray(int[] dest) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < dest.length; i++) {
            if (i > 0) builder.append(", ");
            builder.append(dest[i]);
        }
        System.out.println(builder.toString());
    }


    //Most slowly but easy sorting
    //sorting.at  - see for more
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
}
