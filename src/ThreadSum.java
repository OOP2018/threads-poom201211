public class ThreadSum {
    public static void main(String[] args) {
        // upper limit of numbers to add/subtract to Counter
        final int LIMIT = 1000000;
        // The counter that accumulates a total.
        Counter counter1 = new Counter();
        Counter counter2 = new CounterWithLock();
        Counter counter3 = new SynchronousCounter();
        Counter counter4 = new AtomicCounter();

        System.out.println("Testing normal method");
        runThreads(counter1, LIMIT);
        System.out.println("-------------------------------------");
        System.out.println("Testing lock method");
        runThreads(counter2, LIMIT);
        System.out.println("-------------------------------------");
        System.out.println("Testing synchronized method");
        runThreads(counter3, LIMIT);
        System.out.println("-------------------------------------");
        System.out.println("Testing atomic method");
        runThreads(counter4, LIMIT);
    }

    public static void runThreads(Counter counter, final int limit) {

        // two tasks that add and subtract values using same Counter
        AddTask addtask = new AddTask(counter, limit);
        SubtractTask subtask = new SubtractTask(counter, limit);

        // threads to run the tasks
        Thread thread1 = new Thread(addtask);
        Thread thread2 = new Thread(subtask);

        // start the tasks
        System.out.println("Starting threads");
        long startTime = System.nanoTime();
        thread1.start();
        thread2.start();

        // wait for threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Threads interrupted");
        }
        double elapsed = 1.0E-9 * (System.nanoTime() - startTime);

        System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);

        // the sum should be 0. Is it?
        System.out.printf("Counter total is %d\n", counter.get());
    }
}
