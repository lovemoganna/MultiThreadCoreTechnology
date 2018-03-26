package aier.com.isinterrupted;

/**
 * @author: ligang
 * date: 2018/3/15
 * time: 10:28
 */
public class TestInterrupted {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


