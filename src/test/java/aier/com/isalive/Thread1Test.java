package aier.com.isalive;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 17:01
 */
public class Thread1Test extends TestCase {
    public static void main(String[] args) throws InterruptedException {
        Thread1 t = new Thread1();
        System.out.println("begin : " + t.isAlive());
        t.start();
        Thread.sleep(5000);
        System.out.println("end : " + t.isAlive());

    }
}