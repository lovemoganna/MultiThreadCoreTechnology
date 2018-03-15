package aier.com.exp;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 14:39
 */
public class Thread1 extends Thread {
    private int i = 5;
    @Override
    public void run() {
        System.out.println("i = " + (i--) + " threadName = " + Thread.currentThread().getName());
    }
}
