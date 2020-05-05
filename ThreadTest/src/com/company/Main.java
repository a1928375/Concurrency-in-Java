package com.company;

public class Main {

    public static void main(String[] args) {

        BankAccount account = new BankAccount("12345-678", 1000.00);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300);
                account.withdraw(50);
                System.out.println("The balance after 1st thread: " + account.getBalance());
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                account.deposit(203.75);
                account.withdraw(100);
                System.out.println("The balance after 2st thread: " + account.getBalance());

            }
        });

        thread1.start();
        thread2.start();
    }
}
