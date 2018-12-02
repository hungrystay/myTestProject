package thread;

public class TicketsThread {
    public static void main(String args[]){
        //创建三个线程，模拟三个窗口卖票
        MyThread mt1=new MyThread("窗口1");
        MyThread mt2=new MyThread("窗口2");
        MyThread mt3=new MyThread("窗口3");

        //启动三个线程，也即是窗口，开始卖票
        mt1.start();
        mt2.start();
        mt3.start();
    }
}
