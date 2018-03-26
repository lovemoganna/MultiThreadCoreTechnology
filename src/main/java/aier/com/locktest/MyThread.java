package aier.com.locktest;

/**
 * @author: ligang
 * date: 2018/3/15
 * time: 11:26
 */
public class MyThread extends Thread{
    private SynchronizedObject  so;

    public MyThread(SynchronizedObject so){
        super();
        this.so=so;
    }
    @Override
    public void run() {
        so.printString("b","bb");
    }
}
