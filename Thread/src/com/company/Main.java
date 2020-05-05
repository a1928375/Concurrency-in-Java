package com.company;

public class Main {

    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable);

        thread.start();

        try {

            Thread.sleep(10L * 1000L);      // main thread

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        myRunnable.doStop();
    }
}

class MyRunnable implements Runnable {

    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    @Override
    public void run() {

        while(keepRunning()) {

            System.out.println("Running");

            try {

                Thread.sleep(3L * 1000L);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}

