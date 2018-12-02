package thread;

public class MyRunnable implements Runnable {
    private int ticketsCount = 5; // 一共有5张火车票

    public void run() {
        while (ticketsCount > 0) {
            if(ticketsCount > 0){
                ticketsCount--; // 如果还有票，就卖掉一张票
                System.out.println(Thread.currentThread().getName()
                        + "卖掉了1张票，剩余票数为:" + ticketsCount);
            }
        }
    }
}
