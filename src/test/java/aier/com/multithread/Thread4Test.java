package aier.com.multithread;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 11:01
 */
public class Thread4Test extends TestCase {
    public static void main(String[] args) {
        Thread4 a = new Thread4("A");
        Thread4 b = new Thread4("B");
        Thread4 c = new Thread4("C");

        a.start();
        b.start();
        c.start();
    }
}