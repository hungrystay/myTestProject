package thread.deadlock.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueTest {

    ReentrantLock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    final static int maxSize = 10;
    int[] array = new int[maxSize];

    int size = 0;
    int takeIndex = 0;
    int putIndex = 0;

    public int take() throws InterruptedException {
        lock.lock();
        while( size == 0) {
            System.out.println("queue is empty, please wait!");
            notEmpty.await();
        }
        size--;
        notFull.signal();
        lock.unlock();
        int result = array[takeIndex];
        takeIndex++;
        takeIndex = takeIndex%maxSize;
        return result;
    }

    public void put(int number) throws InterruptedException {
        lock.lock();
        while( size == maxSize){
            System.out.println("queue is full, please wait!");
            for(int i = 0; i < size; i++){
                System.out.print(array[i] + " ");
            }
            System.out.println();
            notFull.await();
        }
        size++;
        array[putIndex] = number;
        putIndex++;
        putIndex = putIndex%maxSize;
        notEmpty.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        BlockingQueueTest blockingQueue = new BlockingQueueTest();

        new Consume(blockingQueue).start();
        new Produce(blockingQueue, 2).start();

    }
}
