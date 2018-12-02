package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

    public static AtomicInteger race = new AtomicInteger();

//    public static volatile int race = 0;

    public static void increase() {
//        race++;
        race.incrementAndGet();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello world");
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        // 等待所有累加线程都结束
//        while (Thread.activeCount() > 1)
//            Thread.yield();

        for( int i = 0; i < THREADS_COUNT; i++) {
            threads[i].join();
        }
        System.out.println(race);
    }
}
