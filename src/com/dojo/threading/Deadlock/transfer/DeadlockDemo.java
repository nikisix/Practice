package com.dojo.threading.Deadlock.transfer;

/**
 Here transfer() method, in a class named OrderedTeller,
 in stead of arbitrarily synchronizing on locks, this transfer()
 method obtains locks in a specified order based on the number
 of the bank account.
 */


// File Name DeadlockDemo.java
public class DeadlockDemo
{
   public static void main(String [] args)
   {
      System.out.println("Creating two bank accounts...");
      ThreadSafeBankAccount checking =
                    new ThreadSafeBankAccount(101, 1000.00);
      ThreadSafeBankAccount savings =
                    new ThreadSafeBankAccount(102, 5000.00);

      System.out.println("Creating two teller threads...");
      Thread teller1 = new OrderedTeller(checking, savings);
      Thread teller2 = new OrderedTeller(savings, checking);
      System.out.println("Starting both threads...");
      teller1.start();
      teller2.start();
   }
}
