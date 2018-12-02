import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTester {

    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executor.submit(new TestPrint(i));
        }

        System.out.println("10 tasks dispatched successfully !");

        executor.shutdown();

    }
}
