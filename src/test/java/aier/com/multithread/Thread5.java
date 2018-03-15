package aier.com.multithread;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 11:14
 * 共享变量的情况
 */
public class Thread5 extends Thread{
    private int count =5;

    @Override
    synchronized public void run() {
        super.run();
        count --;
        System.out.println("由"+this.currentThread().getName()+"计算.count= "+count);
    }
}
