package com.dojo.threading.Deadlock;
/* http://www.tutorialspoint.com/java/java_thread_deadlock.htm

* A special type of error that you need to avoid that relates specifically to multitasking is
* deadlock, which occurs when two threads have a circular dependency on a pair of synchronized objects.
For example, suppose one thread enters the monitor on object X and another thread enters the monitor
 on object Y. If the thread in X tries to call any synchronized method on Y, it will block
 as expected. However, if the thread in Y, in turn, tries to call any synchronized method on X,
 the thread waits forever, because to access X, it would have to release its own lock on Y so
 that the first thread could complete.

Example:
To understand deadlock fully, it is useful to see it in action. The next example creates two
 classes, A and B, with methods foo( ) and bar( ), respectively, which pause briefly before
  trying to call a method in the other class.

The main class, named Deadlock, creates an A and a B instance, and then starts a second thread
to set up the deadlock condition. The foo( ) and bar( ) methods use sleep( ) as a way to force
 the deadlock condition to occur

Because the program has deadlocked, you need to press CTRL-C to end the program.
You can see a full thread and monitor cache dump by pressing CTRL-BREAK on a PC .
You will see that RacingThread owns the monitor on b, while it is waiting for the monitor on a.
 At the same time, MainThread owns a and is waiting to get b. This program will never complete.
As this example illustrates, if your multithreaded program locks up occasionally, deadlock is
one of the first conditions that you should check for.
* */
class A {
   synchronized void foo(B b) {
      String name = Thread.currentThread().getName();
      System.out.println(name + " entered A.foo");
      try {
         Thread.sleep(1000);
      } catch(Exception e) {
         System.out.println("A Interrupted");
      }
      System.out.println(name + " trying to call B.last()");
      b.last();
   }
   synchronized void last() {
      System.out.println("Inside A.last");
   }
}
class B {
   synchronized void bar(A a) {
      String name = Thread.currentThread().getName();
      System.out.println(name + " entered B.bar");
      try {
         Thread.sleep(1000);
      } catch(Exception e) {
         System.out.println("B Interrupted");
      }
      System.out.println(name + " trying to call A.last()");
      a.last();
   }
   synchronized void last() {
      System.out.println("Inside A.last");
   }
}
class Deadlock implements Runnable {
   A a = new A();
   B b = new B();
   Deadlock() {
      Thread.currentThread().setName("MainThread");
      Thread t = new Thread(this, "RacingThread");
      t.start();
      a.foo(b); // get lock on a in this thread.
      System.out.println("Back in main thread");
   }
   public void run() {
      b.bar(a); // get lock on b in other thread.
      System.out.println("Back in other thread");
   }
   public static void main(String args[]) {
      new Deadlock();
   }
}
