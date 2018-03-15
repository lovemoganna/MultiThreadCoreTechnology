package aier.com.interrupt;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 21:18
 */
public class MyThreadTest extends TestCase {
    public static void main(String[] args) {
        try {
            MyThread t = new MyThread();
            t.start();
            t.sleep(2000);
            t.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }
}