package aier.com.multithread;

/**
 * @author: ligang
 * date: 2018/2/28
 * time: 23:19
 */
public class Thread2 extends Thread{
    /**
     * 生成10个随机数,作为线程睡眠的时间.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int time =(int)(Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("run = "+Thread.currentThread().getName());
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
