package remoteExecution;

public class MultiplyTest {
    public static void main(String args[]) {

        int a = 0x0ABCDECD;
        System.out.println("====a===");
        System.out.println(a);

        System.out.println(a * 16);

        System.out.println(Integer.toHexString(a * 16));


        System.out.println(Integer.toHexString(a <<= 4));


        int b = 0xAB000000;

        int c = 0x000000AB;

        System.out.println("b=");
        System.out.println(b);
        System.out.println("c=");
        System.out.println(c);

        System.out.println("b + c =");
        System.out.println(b + c);

        System.out.println(Integer.toHexString(b + c).equals("ab0000ab"));
    }
}
