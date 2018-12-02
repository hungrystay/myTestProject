package concurrent.logcounter.start;

import concurrent.logcounter.multiple.MultithreadedStatTask;

import java.io.*;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws Exception {
        int argc = args.length;
        // 根据指定的日志文件创建唯一一个输入流
        InputStream in = createInputStream();
        // 一对请求与响应之间的消息唯一标识的后3位值之差
        int traceIdDiff;
        // 待统计的操作名称
        String expectedOperationName;
        // 可选参数：采样周期（单位：秒）
        int sampleInterval;
        /*
         * 可选参数：指定一个以逗号分割的列表，仅发送给该列表中的设备的请求才会被统计在内。 默认值"*"表示不对外部设备做要求。
         */
        String expectedExternalDeviceList;

        traceIdDiff = argc >= 1 ? Integer.valueOf(args[0]) : 3;
        expectedOperationName = argc >= 2 ? args[1] : "sendSms";
        sampleInterval = argc >= 3 ? Integer.valueOf(args[2]) : 10;
        expectedExternalDeviceList = argc >= 4 ? args[3] : "*";

        // 创建执行统计的任务实例
        Runnable  multithreadedStatTask= new MultithreadedStatTask(in, sampleInterval, traceIdDiff,
                expectedOperationName, expectedExternalDeviceList);

        // 直接在main线程中执行统计任务
        multithreadedStatTask.run();
    }

    private static InputStream createInputStream() {
        final AtomicBoolean readerClosed = new AtomicBoolean(false);
        InputStream dataIn = Main.class
                .getResourceAsStream("/data/in.dat");

        final BufferedReader bfr = new BufferedReader(new InputStreamReader(
                dataIn)) {
            @Override
            public void close() throws IOException {
                super.close();
                readerClosed.set(true);
            }
        };
        SequenceInputStream si = new SequenceInputStream(
                new Enumeration<InputStream>() {
                    String fileName = null;

                    @Override
                    public boolean hasMoreElements() {
                        if (readerClosed.get()) {
                            return false;
                        }
                        try {
                            fileName = bfr.readLine();
                            if (null == fileName) {
                                bfr.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            fileName = null;
                        }
                        return null != fileName;
                    }

                    @Override
                    public InputStream nextElement() {
                        InputStream in = null;
                        if (null != fileName) {
                            try {
                                in = Main.class.getResourceAsStream("/data/InputFiles/"
                                        + fileName);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return in;
                    }
                });
        return si;
    }
}
