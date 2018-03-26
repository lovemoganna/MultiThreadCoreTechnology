package aier.com.isinterrupted;

/**
 * @author: ligang
 * date: 2018/3/7
 * time: 12:52
 */
public class MyThread extends Thread {
    @Override
    public void run() {
       while (true){
           if(this.isInterrupted()){
               System.out.println("停止了!");
               return;
           }
       }
    }
}
