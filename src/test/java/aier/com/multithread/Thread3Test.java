package aier.com.multithread;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/2/28
 * time: 23:45
 */
public class Thread3Test extends TestCase {
    public static void main(String[] args) {
        Thread3 t1 = new Thread3(1);
        Thread3 t2 =new Thread3(2);
        Thread3 t3 =new Thread3(3);
        Thread3 t4 =new Thread3(4);
        Thread3 t5 =new Thread3(5);
        Thread3 t6 =new Thread3(6);
        Thread3 t7 =new Thread3(7);
        Thread3 t8 =new Thread3(8);
        Thread3 t9 =new Thread3(9);
        Thread3 t10 =new Thread3(10);
        Thread3 t11 =new Thread3(11);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
    }
}