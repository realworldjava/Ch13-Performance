package com.wiley.realworldjava.performance.benchmark;

public class SystemTimingFast {

    public static void main(String[] args) {

        final int nanosPerSecond = 1_000_000_000;
        long start = System.nanoTime();
        long recursive = fibonacci(50);
        long end = System.nanoTime();

        System.out.println(recursive);
        System.out.printf("Took %d seconds", (end-start)/nanosPerSecond);
    }

    public static long fibonacci(int number) {
        long previous = 0, current = 1;
        long result = 0;

        for (int i = 1; i < number; i++) {
            result = previous + current;
            previous = current;
            current = result;
        }
        return result;
    }
}
