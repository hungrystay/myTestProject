import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class TestPrint implements  Runnable{

    private int id = 0 ;


    public TestPrint(int id) {
        this.id = id;
    }

    public void run(){

        System.out.println( this.id + "thread test");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
