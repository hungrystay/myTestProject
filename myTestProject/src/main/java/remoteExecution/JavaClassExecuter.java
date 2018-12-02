package remoteExecution;

import java.io.*;
import java.lang.reflect.Method;

/**
 * JavaClass执行工具
 *
 * @author zzm
 */
public class JavaClassExecuter {
    /**
     * 执行外部传过来的代表一个Java类的Byte数组<br>
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
     * @param classByte 代表一个Java类的Byte数组
     * @return 执行结果
     */
    public static String execute(byte[] classByte) throws IOException {
        HackSystem.clearBuffer();

        ClassModifier cm = new ClassModifier(classByte);

        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "remoteExecution/HackSystem");

        String outputFile = "/Users/nihan/myTestProject/src/main/java/remoteExecution/SystemTestChanged.class";
        byteToFile( outputFile , modiBytes);

        HotSwapClassLoader loader = new HotSwapClassLoader();

        Class clazz = loader.loadByte(modiBytes);
        try {
            Method method = clazz.getMethod("main", new Class[] { String[].class });
            method.invoke(null, new String[] { null });
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }

    public static void byteToFile(String fileName, byte[] bytes) throws IOException {
        OutputStream out = null;
        out = new FileOutputStream(fileName);

        out.write(bytes,0, bytes.length);
        out.flush();
        out.close();
    }

    public static void main(String args[]) throws IOException {
        InputStream is = null;

        is = new FileInputStream("/Users/nihan/myTestProject/src/main/java/remoteExecution/SystemTest.class");
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();


        System.out.println(JavaClassExecuter.execute(b));

        
    }
}


