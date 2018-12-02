package thread.blockingtest;

public class BlockingTest {

    public static void main(String args[]){

        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                // do more work.
            }
        });
        thread.start();


// 一段时间以后
        thread.interrupt();
    }

}
