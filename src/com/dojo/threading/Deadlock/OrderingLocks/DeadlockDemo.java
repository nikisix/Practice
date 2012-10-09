package com.dojo.threading.Deadlock.OrderingLocks;

/**
 Acommon threading trick to avoid the deadlock is to order the locks.
 By ordering the locks, it gives threads a specific order to obtain
 multiple locks.

 The problem with the LazyTeller class is that it does not consider
 the possibility of a race condition, a common occurrence in multithreaded programming.

 After the two threads are started, teller1 grabs the checking lock and teller2 grabs the
 savings lock. When teller1 tries to obtain the savings lock, it is not available. Therefore,
 teller1 blocks until the savings lock becomes available. When the teller1 thread blocks, teller1
 still has the checking lock and does not let it go.
 Similarly, teller2 is waiting for the checking lock, so teller2 blocks but does not let go of the
 savings lock. This leads to one result: deadlock!
 */
// File Name ThreadSafeBankAccount.java
class ThreadSafeBankAccount
{
   private double balance;
   private int number;
   public ThreadSafeBankAccount(int num, double initialBalance)
   {
      balance = initialBalance;
      number = num;
   }
   public int getNumber()
   {
      return number;
   }
   public double getBalance()
   {
      return balance;
   }
   public void deposit(double amount)
   {
      synchronized(this)
      {
        double prevBalance = balance;
        try
        {
           Thread.sleep(4000);
        }catch(InterruptedException e)
        {}
        balance = prevBalance + amount;
      }
   }
   public void withdraw(double amount)
   {
      synchronized(this)
      {
	     double prevBalance = balance;
         try
         {
            Thread.sleep(4000);
         }catch(InterruptedException e)
         {}
         balance = prevBalance - amount;
      }
   }
}

// File Name LazyTeller.java
class LazyTeller extends Thread
{
   private com.dojo.threading.Deadlock.transfer.ThreadSafeBankAccount source, dest;
   public LazyTeller(com.dojo.threading.Deadlock.transfer.ThreadSafeBankAccount a,
                     com.dojo.threading.Deadlock.transfer.ThreadSafeBankAccount b)
   {
      source = a;
      dest = b;
   }
   public void run()
   {
      transfer(250.00);
   }
   public void transfer(double amount)
   {
      System.out.println("Transferring from "
          + source.getNumber() + " to " + dest.getNumber());
      synchronized(source)
      {
          Thread.yield();
          synchronized(dest)
          {
             System.out.println("Withdrawing from "
                     + source.getNumber());
             source.withdraw(amount);
             System.out.println("Depositing into "
                     + dest.getNumber());
             dest.deposit(amount);
          }
       }
   }
}
public class DeadlockDemo
{
   public static void main(String [] args)
   {
      System.out.println("Creating two bank accounts...");
      com.dojo.threading.Deadlock.transfer.ThreadSafeBankAccount checking =
                    new com.dojo.threading.Deadlock.transfer.ThreadSafeBankAccount(101, 1000.00);
      com.dojo.threading.Deadlock.transfer.ThreadSafeBankAccount savings =
                    new com.dojo.threading.Deadlock.transfer.ThreadSafeBankAccount(102, 5000.00);

      System.out.println("Creating two teller threads...");
      Thread teller1 = new LazyTeller(checking, savings);
      Thread teller2 = new LazyTeller(savings, checking);
      System.out.println("Starting both threads...");
      teller1.start();
      teller2.start();
   }
}