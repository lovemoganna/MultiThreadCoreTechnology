package aier.com.exp;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 14:41
 */
public class Test {
    public static void main(String[] args) {
        Thread1 thread = new Thread1();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);
        Thread t4 = new Thread(thread);
        Thread t5 = new Thread(thread);

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
        //之前我们测试过
    }
}
