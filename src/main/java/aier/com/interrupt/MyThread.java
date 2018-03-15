package aier.com.interrupt;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 21:09
 */
public class MyThread extends Thread
{
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("i = "+(i + 1));
        }
    }
}
