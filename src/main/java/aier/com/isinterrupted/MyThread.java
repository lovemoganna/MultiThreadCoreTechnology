package aier.com.isinterrupted;

/**
 * @author: ligang
 * date: 2018/3/7
 * time: 12:52
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i = "+(i+1));
        }
    }
}
