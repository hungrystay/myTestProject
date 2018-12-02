package daemon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DaemonRunnable implements Runnable{
    @Override
     public void run() {
        // TODO Auto-generated method stub
        System.out.println("进入守护线程:" + Thread.currentThread().getName());
        try {
            writeToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("退出守护线程:" + Thread.currentThread().getName());
    }
    private void writeToFile() throws Exception {
        File filename = new File("/Users/nihan/myTestProject/test_dir" + File.separator + "daemon.txt");
        OutputStream os = new FileOutputStream(filename, true);
        int count = 0;
        while(count < 999) {
            os.write(("\r\nword" + count).getBytes());
            System.out.println("守护线程" + Thread.currentThread().getName()
                    + "向文件中写入word" + count++);
            Thread.sleep(1000);
        }
    }
}
