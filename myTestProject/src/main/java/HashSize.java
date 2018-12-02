import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class HashSize {


    public static int MAXIMUM_CAPACITY = 1073741824;
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        Double a = 0.4444;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    HashSize(){
//        int i  = 1;
        super();
    }

//    HashSize(int i ) {
//        this();
//        super();
//    }



    public static void main(String[] args) {

        File myFile = new File("1.txt");
        String s = null;           //s编译不通过
        System.out.println(tableSizeFor(17));
        System.out.println("s=" + s);
        int j = 0;
//        +++j;
        System.out.println("5" + 2);

//        InputStreamReader a = new InputStreamReader(new BufferedReader("1.txt"));
    }


}
