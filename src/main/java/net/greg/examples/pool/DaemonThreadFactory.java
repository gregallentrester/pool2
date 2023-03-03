package net.greg.examples.pool;

import java.util.*;

import java.util.concurrent.ThreadFactory;


public final class DaemonThreadFactory implements ThreadFactory {

  private int counter = 0;

  private static final List<Runnable> members =
    new ArrayList();


  /*
   * By default, the a daemon inherits its parent thread's daemon status.
   *
   * Lifecycle of a daemon thread:
   *
   *   Under normal shutdown conditions, thee JVM doesn't wait for Daemon threads
   *   when the main thread of execution (the daemon's parent thread) is exiting;
   *   however, the JVM waits for any/all the user (regular) threads to finish
   *   their execution before the JVM shuts down
   *
   * NB  A daemon loses the capablility for stdin/stdout (I/O)
   */
  @Override
  public Thread newThread(Runnable runnable) {

    WorkerThread worker =
      new WorkerThread(runnable, "Thread_" + counter++);

    worker.setDaemon(Boolean.valueOf(System.getenv("DAEMON")));



    if (counter == 20) {
      worker.setPriority(8);
System.err.println("\n\n VAL " + counter + ", " + worker.getPriority());
    }

    if (counter == 2) {
      worker.setPriority(2);
System.err.println("\n\n VAL " + counter + ", " + worker.getPriority());
    }

    members.add(worker);

    // type-safe downcastc
    return (Thread) worker;
  }

  public static final void publish(int capacity) {

    // affirm
    System.err.printf(
      "\n" + capacity +
      " Daemon Threads have been created w/these" +
      " characteristics:\n\n");

    members.stream().forEach(item -> {

      System.err.print("\n  Factory Created " + item);
    });


    // thread-safe complement to StringBuilder
    StringBuffer sb = new StringBuffer();

    sb.append("\n\n  Thread Readout Semantics ...\n").
      append("\n  Thread[Thread_3,5,main]").
      append("\n  Thread[Thread_3    | 5        | main] ").
      append("\n  Thread[Thread_name | priority | parent] ");

    // coerce toString
    System.err.println(sb + "\n\n");
  }
}
