package aier.com.interrupt;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 21:27
 */
public class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            System.out.println("i = "+ (i + 1));
        }
    }
}
