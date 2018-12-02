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
package thread.deadlock;


import thread.cooperation.common.Debug;
import thread.cooperation.common.Tools;

public class FixedPhilosopher extends AbstractPhilosopher {
  private final Chopstick one;
  private final Chopstick theOther;

  protected final int id;
  protected final Chopstick left;
  protected final Chopstick right;

  public FixedPhilosopher(int id, Chopstick left, Chopstick right) {
      super("Philosopher-" + id);
      this.id = id;
      this.left = left;
      this.right = right;
      // 对资源（锁）进行排序
      int leftHash = System.identityHashCode(left);
      int rightHash = System.identityHashCode(right);
      if (leftHash < rightHash) {
        one = left;
        theOther = right;
      } else if (leftHash > rightHash) {
        one = right;
        theOther = left;
      } else {
        // 两个对象的identityHashCode值相等是可能的，尽管这个几率很小
        one = null;
        theOther = null;
      }
  }

  public void eat() {
    if (null != one) {
      synchronized (one) {
        Debug.info("%s is picking up %s on his %s...%n", this, one,
            one == left ? "left" : "right");
        one.pickUp();
        synchronized (theOther) {
          Debug.info("%s is picking up %s on his %s...%n", this,
              theOther, theOther == left ? "left" : "right");
          theOther.pickUp();
          doEat();
          theOther.putDown();
        }
        one.putDown();
      }
    } else {
      // 退化为使用粗锁法
      synchronized (FixedPhilosopher.class) {
        Debug.info("%s is picking up %s on his left...%n", this, left);
        left.pickUp();

        Debug.info("%s is picking up %s on his right...%n", this, right);
        right.pickUp();
        doEat();
        right.putDown();

        left.putDown();
      }
    }// if语句结束
  }// eat方法结束


  protected void doEat() {
    Debug.info("%s is eating...%n", this);
    Tools.randomPause(10);
  }

  public void run() {
    for (;;) {
      think();
      eat();
    }
  }

  public void think() {
    Debug.info("%s is thinking...%n", this);
    Tools.randomPause(10);
  }

  public String toString() {
    return "Philosopher-" + id;
  }
}