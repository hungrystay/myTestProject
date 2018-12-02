package thread.runnabletest;

public class RunTest {

    public static int run1() {
        System.out.println("1");
        return 1;
    }

    public static int run() {
        System.out.println(Thread.currentThread().getName());

        try {
            return run1();
        } finally {
            System.out.println("2");
        }
    }

    public static void main(String args[]) throws InterruptedException {
//        new RunTest().run();
////
////        Thread t = new Thread(new RunTest());
////        t.start();
////        t.join();
////        t.interrupt();
        System.out.println(run());

    }

}
