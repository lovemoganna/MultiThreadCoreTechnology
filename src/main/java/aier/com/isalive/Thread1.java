package aier.com.isalive;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 17:00
 */
public class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("run = "+ this.isAlive());
    }
}
