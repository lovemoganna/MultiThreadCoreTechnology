package aier.com.isinterrupted;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/7
 * time: 12:54
 */
public class MyThreadTest extends TestCase {
    public static void main(String[] args) {
        try {
            MyThread t1 = new MyThread();
            t1.start();
            Thread.sleep(1000);
            t1.interrupted();
            System.out.println("是否停止1 ? ="+ t1.interrupted());
            System.out.println("是否停止2 ? ="+ t1.interrupted());

        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }

    }
}