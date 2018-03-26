package aier.com.ReentrantLockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ligang
 * date: 2018/3/21
 * time: 8:40
 */
public class UseReentrantLock {
     private Lock lock =new ReentrantLock();
     public void method1(){
         try {
             lock.lock();
             System.out.println("当前线程"+Thread.currentThread().getName()+"进入Method1");
             Thread.sleep(1000);
             System.out.println("当前线程"+Thread.currentThread().getName()+"退出Method1");
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
     }

    public void method2(){
        try {
            lock.lock();
            System.out.println("当前线程"+Thread.currentThread().getName()+"进入Method2");
            Thread.sleep(1000);
            System.out.println("当前线程"+Thread.currentThread().getName()+"退出Method2");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
       final UseReentrantLock urlock = new UseReentrantLock();
        Thread t1=new Thread(new Runnable(){
            @Override
            public void run() {
                urlock.method1();
                urlock.method2();
            }
        },"t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
