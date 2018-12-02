package concurrent.logcounter.start;

import concurrent.logcounter.multiple.MultithreadedStatTask;

import java.io.*;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws Exception {
        int argc = args.length;
        // ����ָ������־�ļ�����Ψһһ��������
        InputStream in = createInputStream();
        // һ����������Ӧ֮�����ϢΨһ��ʶ�ĺ�3λֵ֮��
        int traceIdDiff;
        // ��ͳ�ƵĲ�������
        String expectedOperationName;
        // ��ѡ�������������ڣ���λ���룩
        int sampleInterval;
        /*
         * ��ѡ������ָ��һ���Զ��ŷָ���б������͸����б��е��豸������Żᱻͳ�����ڡ� Ĭ��ֵ"*"��ʾ�����ⲿ�豸��Ҫ��
         */
        String expectedExternalDeviceList;

        traceIdDiff = argc >= 1 ? Integer.valueOf(args[0]) : 3;
        expectedOperationName = argc >= 2 ? args[1] : "sendSms";
        sampleInterval = argc >= 3 ? Integer.valueOf(args[2]) : 10;
        expectedExternalDeviceList = argc >= 4 ? args[3] : "*";

        // ����ִ��ͳ�Ƶ�����ʵ��
        Runnable  multithreadedStatTask= new MultithreadedStatTask(in, sampleInterval, traceIdDiff,
                expectedOperationName, expectedExternalDeviceList);

        // ֱ����main�߳���ִ��ͳ������
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
