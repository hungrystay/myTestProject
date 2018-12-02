package RandomTest;

public class RandomTest {

    public static void main(String[] args) {


//        int x = 0xFFAABBCC;
//        System.out.println(Integer.toHexString(x >>> 24));


//        int i = -1;
//
//        byte[] buf = new byte[4];

//        byte x = (byte) 0xbb;
//
//        System.out.println(Integer.toHexString(x));
//
//        System.out.println(Integer.toString(x));

        byte[] strBytes = "fafdafdafaŒ“ «".getBytes();
        for(byte b: strBytes)
            System.out.println(Integer.toHexString(b));
    }
}
