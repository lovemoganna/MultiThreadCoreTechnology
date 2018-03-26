package aier.com.VolatileTest;

/**
 * @author: ligang
 * date: 2018/3/22
 * time: 10:36
 */
public class Test {
    public  int inc = 0;

    public void increase(){
        inc ++;
    }

    synchronized public static void main(String[] args) {
        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000 ; j++) {
                        test.increase();
                    }
                }
            }).start();
        }
        while(Thread.activeCount() >1){
            Thread.yield();
            System.out.println(test.inc);
        }
    }
}
