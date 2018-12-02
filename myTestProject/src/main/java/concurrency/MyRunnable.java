package concurrency;

public class MyRunnable implements Runnable {

    private int num;

    public MyRunnable(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行的MyRunnable " + num);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyRunnable " + num + "执行完毕");

    }

}
