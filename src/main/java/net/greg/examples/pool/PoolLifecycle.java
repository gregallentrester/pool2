package net.greg.examples.pool;


public final class PoolLifecycle {

  private static final DaemonThreadFactory FACTORY =
    new DaemonThreadFactory();


  /**
   *
   */
  public static void main(String any[]) {

    new PoolLifecycle().
      start(Integer.parseInt(any[0]));
  }


  private void start(int value) {

    // CLI in origin
    int CAPACITY = value;

    boolean isDaemon =
      Boolean.valueOf(System.getenv("DAEMON"));

    String KIND =
      isDaemon ? " Daemon " :  " Non-Daemon ";


    System.err.println(
      "\nPoolLifecycle class Starts " +
      CAPACITY + KIND + "Threads ...\n");


    // ask the factory
    for (int ndx = 0; ndx < CAPACITY; ndx++) {

      FACTORY.newThread(new WorkerThread()).start();
    }


    // affirm
    DaemonThreadFactory.publish(CAPACITY);
  }
}
