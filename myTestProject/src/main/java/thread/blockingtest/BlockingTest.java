package thread.blockingtest;

public class BlockingTest {

    public static void main(String args[]){

        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                // do more work.
            }
        });
        thread.start();


// һ��ʱ���Ժ�
        thread.interrupt();
    }

}
