package aier.com.isalive;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 17:11
 */
public class Thread2 extends Thread {
    public Thread2(){
        System.out.println("Thread2 --- begin");
        System.out.println("Thread.currentThread().getName()---"+Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()---"+Thread.currentThread().isAlive());
        System.out.println("this.getName()---"+this.getName());
        System.out.println("this.isAlive()---"+this.isAlive());
        System.out.println("Thread2 --- end");
    }

    @Override
    public void run() {
        System.out.println("run --- begin");
        System.out.println("run.currentThread().getName()---"+Thread.currentThread().getName());
        System.out.println("run.currentThread().isAlive()---"+Thread.currentThread().isAlive());
        System.out.println("this.getName()---"+this.getName());
        System.out.println("this.isAlive()---"+this.isAlive());
        System.out.println("run --- end");
    }
}
