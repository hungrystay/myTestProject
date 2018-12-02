package thread.escape;

public class EscapeTest {

    int i ;
    EscapeTest() {

        printA();

    }


    public void printA() {
        System.out.println("A");
    }

    public static void main(String args[]) {
        EscapeTest test = new EscapeTest();
    }

}
