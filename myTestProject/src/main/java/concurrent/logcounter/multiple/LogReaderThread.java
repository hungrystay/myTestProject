/*
授权声明：
本源码系《Java多线程编程实战指南（核心篇）》一书（ISBN：978-7-121-31065-2，以下称之为“原书”）的配套源码，
欲了解本代码的更多细节，请参考原书。
本代码仅为原书的配套说明之用，并不附带任何承诺（如质量保证和收益）。
以任何形式将本代码之部分或者全部用于营利性用途需经版权人书面同意。
将本代码之部分或者全部用于非营利性用途需要在代码中保留本声明。
任何对本代码的修改需在代码中以注释的形式注明修改人、修改时间以及修改内容。
本代码可以从以下网址下载：
https://github.com/Viscent/javamtia
http://www.broadview.com.cn/31065
*/
package concurrent.logcounter.multiple;

import concurrent.logcounter.common.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 日志读取线程实现类
 *
 * @author Viscent Huang
 */
public class LogReaderThread extends Thread {
  // 线程安全的队列
  final BlockingQueue<RecordSet> channel = new ArrayBlockingQueue<RecordSet>(2);

  private final int batchSize;
  protected final BufferedReader logFileReader;
  public LogReaderThread(InputStream in, int inputBufferSize, int batchSize) {

    //文件输入缓冲区大小, inputBufferSize
    logFileReader = new BufferedReader(new InputStreamReader(in),
            inputBufferSize);

    this.batchSize = batchSize;
  }

  // 获取下一个记录集
  public RecordSet nextBatch()
      throws InterruptedException {
    RecordSet batch;
    // 从队列中取出一个记录集

    //如果拿出的速度比放入的速度快会怎么样？会阻塞还是会直接返回空？
    batch = channel.take();

    if (batch.isEmpty()) {
      System.out.println("========batch.capacity========");
      System.out.println(batch.capacity);
      batch = null;
    }
    return batch;
  }

  // 发布指定的记录集
  protected void publish(RecordSet recordBatch) throws InterruptedException {
    // 记录集存入队列
    channel.put(recordBatch);
  }

  // 表示日志文件是否读取结束
  protected volatile boolean isEOF = false;

  protected RecordSet getNextToFill() {
    return new RecordSet(batchSize);
  }

  @Override
  public void run() {
    RecordSet recordSet;
    boolean eof = false;
    try {
      while (true) {
        //得到下一个recordSet
        recordSet = getNextToFill();
        //将recordSet的所有元素reset，回置
        recordSet.reset();
        //填满recordSet
        eof = doFill(recordSet);
        //将recordSet放到channel中
        publish(recordSet);
        //如果为true，说明记录已经读完毕了
        if (eof) {
          if (!recordSet.isEmpty()) {
            publish(new RecordSet(1));
          }
          isEOF = eof;
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      Tools.silentClose(logFileReader);
    }
  }

  protected boolean doFill(final RecordSet recordSet) throws IOException {
    final int capacity = recordSet.capacity;
    String record;
    for (int i = 0; i < capacity; i++) {
      record = logFileReader.readLine();
      if (null == record) {
        return true;
      }
      // 将读取到的日志记录存入指定的记录集
      recordSet.putRecord(record);
    }
    return false;
  }
}