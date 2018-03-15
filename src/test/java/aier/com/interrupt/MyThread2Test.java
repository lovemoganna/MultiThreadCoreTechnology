package aier.com.interrupt;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 21:28
 */
public class MyThread2Test extends TestCase {
    public static void main(String[] args) {
        try {
            MyThread2 thread2 = new MyThread2();
            thread2.start();
            thread2.sleep(2000);
            thread2.interrupt();
            thread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}