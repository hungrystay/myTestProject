package thread.deadlock.blockingqueue;

public class Consume extends Thread{
    BlockingQueueTest blockingQueue;
    public Consume(BlockingQueueTest blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for(;;){
            try {
                blockingQueue.take();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
