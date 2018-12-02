package thread;

public class TicketsRunnable {
    public static void main(String[] args) {
        MyRunnable mt = new MyRunnable();
        //创建三个线程来模拟三个售票窗口
        Thread th1 = new Thread(mt, "窗口1");
        Thread th2 = new Thread(mt, "窗口2");
        Thread th3 = new Thread(mt, "窗口3");
        //启动三个线程，也即是三个窗口，开始卖票
        th1.start();
        th2.start();
        th3.start();
    }
}
