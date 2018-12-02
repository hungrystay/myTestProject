package concurrency;

public class ThreadLocalTest {
    private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

//    private static int value = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i)).start();
        }

    }

    static class MyThread implements Runnable {
        private int index;

        public MyThread(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("线程" + index + "的初始value:" + value.get());
//            System.out.println("线程" + index + "的初始value:" + value);
            for (int i = 0; i < 10; i++) {
                value.set(value.get() + i);
//                value = value + i;
            }
            System.out.println("线程" + index + "的累加value:" + value.get());
//            System.out.println("线程" + index + "的累加value:" + value);
        }
    }
}
