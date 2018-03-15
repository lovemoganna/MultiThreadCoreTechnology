package aier.com.sleep;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 20:42
 */
public class Thread1Test extends TestCase {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        System.out.println("begin : "+ System.currentTimeMillis());
        thread1.start();
        System.out.println("end : "+ System.currentTimeMillis());
    }

}