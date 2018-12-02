package boxing;

import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

public class BoxingTest {
    public static void main(String[] args) {


        getObjectSize(new String("hello"));
        System.out.println(new Integer(2).equals(new Integer(2)));
    }
}
