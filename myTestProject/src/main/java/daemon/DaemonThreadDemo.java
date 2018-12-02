package daemon;

import java.util.Scanner;

public class DaemonThreadDemo {
    public static void main(String[] args) {
            System.out.println("进入主线程:" + Thread.currentThread().getName());
            DaemonRunnable daemonRunnable = new DaemonRunnable();
            Thread thread = new Thread(daemonRunnable);
            thread.setDaemon(true);
            thread.start();
            // 从键盘接收输入，
            Scanner sc = new Scanner(System.in);
            sc.next();  // 主线程就会阻塞住了
            // 一旦我们执行了输入操作，阻塞就会解除掉。主线程会继续执行。然后打印下面一行数据。
            System.out.println("退出主线程:" + Thread.currentThread().getName());
        }
}
