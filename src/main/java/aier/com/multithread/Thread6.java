package aier.com.multithread;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 16:09
 */
public class Thread6 extends Thread {
    /*public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
    }*/
    //mian
    public Thread6(){
        System.out.println("构造方法的打印: "+ Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run method print: "+Thread.currentThread().getName());
    }
}
