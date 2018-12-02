package remoteExecution;

import java.io.UnsupportedEncodingException;

/**
 * Bytes数组处理工具
 * @author
 */
public class ByteUtils {

    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff;

            System.out.println("======n======");
            System.out.println(n);
            System.out.println("======n << (--len)*8=====");
            System.out.println(n << (--len) * 8);
            System.out.println();

            int m = (int) b[i];
            System.out.println("======m=======");
            System.out.println(m);

            System.out.println("======m << (len) * 8=====");
            System.out.println(m << (len) * 8);
            System.out.println();

            System.out.println();

            n <<= (len) * 8;
            sum = n + sum;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }

    public static String bytes2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    public static byte[] string2Bytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8");
    }

    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length, originalBytes.length - offset - len);
        return newBytes;
    }

    public static void main(String args[]) {


        byte[] intBytes = int2Bytes(-20, 4);

//        System.out.println("=============bytes2Int==========");
//        int byteInt = bytes2Int(intBytes, 0, 4);
//        System.out.println("=============bytes2Int==========");

        for(byte b: intBytes) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        System.out.println();

        byte[] intBytes2 = int2Bytes(-89999, 4);
//        for(byte b: intBytes2) {
//            System.out.print(Integer.toHexString( b & 0xff) + " ");
//        }

        byte[] intBytes3 = int2Bytes(-123456, 4);
//        for(byte b: intBytes3) {
//            System.out.print(Integer.toHexString(b & 0xff) + " ");
//        }

        System.out.println();
        byte[] intBytes4 = new byte[8];
        for(int i = 0; i < 4; i++){
            intBytes4[i] = intBytes2[i];
            intBytes4[i + 4] = intBytes3[i];
        }

        for(byte b: intBytes4) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        System.out.println();

        byte[] bytesReplace = bytesReplace(intBytes4, 1, 3, intBytes);
        for(byte b: bytesReplace) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
//        System.out.println();
//        for(byte b: intBytes) {
//            System.out.print(Integer.toHexString(b & 0xff) + " ");
//        }

//        System.out.println();
//        System.out.println("=============");
//        System.out.println(byteInt);



    }
}



