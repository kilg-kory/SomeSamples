package ru.kilg.ss.run.sync;

/**
 * SomeSamples
 * WithoutSync - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 17.05.19
 */
public class WithoutSync {
    public static final int NUM_EXECUTIONS = 100000000;

    public static void someOperation() {
        //Do something...
    }

    public static void main(String[] args) {
        final long[] numElements = {0};

        Thread incThread = new Thread(() -> {
            for (long i = 0; i < NUM_EXECUTIONS; i++) {
                someOperation();
                numElements[0] += 1;
            }
        });

        Thread decThread = new Thread(() -> {
            for (long i = 0; i < NUM_EXECUTIONS; i++) {
                someOperation();
                numElements[0] -= 1;
            }
        });

        incThread.start();
        decThread.start();

        try {
            for (long i = 0; i < NUM_EXECUTIONS; i++) {
                incThread.join();
                decThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Result: " + numElements[0]);
    }
}
