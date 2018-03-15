package aier.com.multithread;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 9:52
 */
public class MyRunnable1Test extends TestCase {
    public static void main(String[] args) {
        MyRunnable1 run1 = new MyRunnable1();
        Thread t1 = new Thread(run1,"Thread-01");
        //t1.start();
        System.out.println(Thread.currentThread().getName());
        System.out.println(t1.currentThread().getName());
        System.out.println(t1.getName()+"运行结束!");

        //下面将上面的Thread对象中的run()方法交给另外一个线程进行调用.
        Thread thread = new Thread(t1);
        System.out.println("看看能不能调用Thread-01线程的Run方法.");
        thread.start();
        System.out.println(thread.getName());

    }
}
//运行结束!
//运行中!