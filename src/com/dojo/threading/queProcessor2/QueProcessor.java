package com.dojo.threading.queProcessor2;

import java.util.LinkedList;
/**
 * Author:  ntomasino
 * Date:    10/8/12
 */
class Q {
    final LinkedList<Integer> q = new LinkedList<Integer>();
    void get() {
        if(q.isEmpty()) //wait if there's nothing to do
        try {
//            System.out.println("about to wait in get");
            synchronized (q){
                q.wait();
            }
        } catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        int i;
        while(!q.isEmpty()){
            i = q.pollFirst();
            System.out.println("Got: " + i);
        }
    }

    void put(int n) {
        try {
//            System.out.println("about to sleep in put");
            Thread.sleep(300);
        } catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        this.q.offerLast(n);
        System.out.println("Put: " + n);
        synchronized (q){
            q.notify();
        }
    }
}

class Producer implements Runnable {
    Q q;
    int numVids;
    Producer(Q q, int numVids) {
        this.q = q;
        this.numVids = numVids;
        System.out.println("Starting producer thread");
        Thread t1 = new Thread(this, "Producer");
        t1.start();
    }

    public void run() {
        while(numVids-- > 0) {   //do three jobs/video
            q.put(numVids*10 + 1);
            q.put(numVids*10 + 2);
            q.put(numVids*10 + 3);
//            q.notify(); //works but throws an error, lol
//            notify(); //works but error
        }
    }
}

class Consumer implements Runnable {
    Q q;
    Consumer(Q q) {
       this.q = q;
        System.out.println("Starting consumer thread");
        Thread t2 = new Thread(this, "Consumer");
        t2.start();
    }

    public void run() {
        while(true) {
            q.get();
        }
    }
}
class QueProcessor {
   public static void main(String args[]) {
      Q q = new Q();
      new Consumer(q);
      new Producer(q, 3);
      System.out.println("Press Control-C to stop.");
   }
}