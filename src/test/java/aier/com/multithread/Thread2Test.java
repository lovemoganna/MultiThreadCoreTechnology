package aier.com.multithread;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/2/28
 * time: 23:23
 */
public class Thread2Test extends TestCase {
    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        thread2.run();
        try {
            for (int i = 0; i < 10; i++) {
                int time =(int)(Math.random() * 1000 );
                Thread.sleep(time);
                System.out.println("main = "+ Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
