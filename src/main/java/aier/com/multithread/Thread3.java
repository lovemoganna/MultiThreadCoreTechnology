package aier.com.multithread;

/**
 * @author: ligang
 * date: 2018/2/28
 * time: 23:43
 */
public class Thread3 extends Thread {
    private int i;
    /**
     * 带有参数的构造方法
     * @param i
     */
    public Thread3(int i) {
        super();
        this.i =i;
    }
    @Override
    public void run() {
        System.out.println(i);
    }
}
