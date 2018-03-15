package aier.com.multithread;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/2/28
 * time: 22:42
 */
public class Thread1Test extends TestCase {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        System.out.println("运行结束!");
    }
}