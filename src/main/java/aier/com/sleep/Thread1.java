package aier.com.sleep;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 17:30
 */
public class Thread1 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run threadName = "+ Thread1.currentThread().getName()+" begin");
            Thread.sleep(3000);
            System.out.println("run threadName = "+ Thread1.currentThread().getName()+" end");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
