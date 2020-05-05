package com.company;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private double balance;
    private String accountNumber;

    ReentrantLock lock = new ReentrantLock(true);

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {

        boolean status = false;

        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {

                try {

                    balance += amount;
                    status = true;

                } finally {

                    lock.unlock();
                }

            } else {

                System.out.println("Could not get the lock");
            }

        } catch (InterruptedException e) {

        }

        System.out.println("The transaction status: " + status);

    }

    public void withdraw(double amount) {

        boolean status = false;

        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }

            } else {

                System.out.println("Could not get the lock");
            }

        } catch (InterruptedException e) {

        }

        System.out.println("The transaction status: " + status);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
