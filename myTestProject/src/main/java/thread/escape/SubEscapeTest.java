package thread.escape;

public class SubEscapeTest extends EscapeTest {

    public void printA() {
        System.out.println("AAAA");
    }

    public static void main(String args) {
        SubEscapeTest test = new SubEscapeTest();
        test.printA();
    }
}
