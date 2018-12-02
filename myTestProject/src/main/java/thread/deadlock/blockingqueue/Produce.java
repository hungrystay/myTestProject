package thread.deadlock.blockingqueue;

public class Produce extends Thread{
    BlockingQueueTest blockingQueue;
    int number;
    public Produce(BlockingQueueTest blockingQueue, int i) {
        this.blockingQueue = blockingQueue;
        this.number = i;
    }


    public void recyclePut() throws InterruptedException {
        blockingQueue.put(number);
    }

    @Override
    public void run() {
        for(;;){
            try {
                recyclePut();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
