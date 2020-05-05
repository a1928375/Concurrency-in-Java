package com.company;

public class OnlyOneRunnableObject {

    public static void main(String[] args) {

        Runner runner = new Runner();

        Thread t1 = new Thread(runner, "Thread1");
        Thread t2 = new Thread(runner, "Thread2");
        Thread t3 = new Thread(runner, "Thread3");
        Thread t4 = new Thread(runner, "Thread4");
        Thread t5 = new Thread(runner, "Thread5");

        t3.start();
        t2.start();
        t5.start();
        t4.start();
        t1.start();

    }
}

class Runner implements Runnable {
    private int runCount = 1;
    private String threadColor;

    @Override
    public void run() {
        for(int i=0; i<5; i++) {
            synchronized (this) {
                System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
            }
        }
    }
}
