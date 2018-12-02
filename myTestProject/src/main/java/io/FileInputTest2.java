package io;

import java.io.IOException;
import java.io.InputStream;

public class FileInputTest2 {

    public static void main(String args[]) throws IOException {

        InputStream inputStream2 = io.FileInputTest.class
                .getResourceAsStream("/data/in.dat");

        byte[] b2 = new byte[inputStream2.available()];
        inputStream2.read(b2);

        System.out.println(new String(b2));
    }
}

