package net.greg.examples.pool;

import java.util.*;

import java.util.concurrent.TimeUnit;



public final class WorkerThread implements Runnable {

  // spread determines Latency VARIABILITY
  // which encourages the thread scheduler
  // to interleave/time-slice when there
  // are enough contending threads that
  // each do long-lasting operations
  private static final int MIN = 3;
  private static final int MAX = 7;


  public WorkerThread() {

  }


  public WorkerThread(Runnable r, String identifier) {

  }


  @Override
  public void run() {

    long LATENCY =
      new Random().nextInt(
        (MAX - MIN)) + MIN;

    //System.err.println(
    //  "  WorkerThread reports its Latency  " + LATENCY +
    //  "\n  isDaemon() ? " + ((T) this).isDaemon());

    try {

      TimeUnit.SECONDS.sleep(LATENCY);

      System.err.println(
        "     Signal From -->  " +
        getClass().getName() +
        "  [ latency:" + LATENCY + " ]");

    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
