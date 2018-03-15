package aier.com.getid;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 20:56
 */
public class Thread1 extends Thread {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+" "+thread.getId());
        //main 1
    }
}
