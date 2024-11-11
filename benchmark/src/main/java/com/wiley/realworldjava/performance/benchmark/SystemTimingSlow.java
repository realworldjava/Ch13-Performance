package com.wiley.realworldjava.performance.benchmark;

public class SystemTimingSlow {

    public static void main(String[] args) {

        final int nanosPerSecond = 1_000_000_000;
        long start = System.nanoTime();
        long recursive = fibonacci(50);
        long end = System.nanoTime();

        System.out.println(recursive);
        System.out.printf("Took %d seconds", (end-start)/nanosPerSecond);
    }

    public static long fibonacci(int number) {
        if (number <= 1) return number;

        return fibonacci(number - 1) + fibonacci(number - 2);
    }
}
