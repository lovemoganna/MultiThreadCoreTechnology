package aier.com.multithread;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 11:17
 */
public class Thread5Test extends TestCase {
    public static void main(String[] args) {
        Thread6 t5 = new Thread6();
        Thread a = new Thread(t5, "A");
        Thread b = new Thread(t5, "B");
        Thread c = new Thread(t5, "C");
        Thread d = new Thread(t5, "D");
        Thread e = new Thread(t5, "E");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}