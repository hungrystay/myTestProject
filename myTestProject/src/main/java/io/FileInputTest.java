package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileInputTest {

    public static void main(String args[]) throws IOException {

        InputStream inputStream = FileInputTest.class
                .getResourceAsStream("/data/in.dat");
        byte[] b = new byte[inputStream.available()];
        inputStream.read(b);

        System.out.println(new String(b));

    }
}
