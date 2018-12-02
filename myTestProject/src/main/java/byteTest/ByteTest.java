package byteTest;

import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

public class ByteTest {
    static int i = 1;
    public static void main(String[] args) {
        System.out.println(new Object().toString().getBytes().length);
        System.out.println(getObjectSize(new Object()));
        System.out.println(new Integer(1).toString().getBytes().length);

    }
}
