package aier.com.locktest;

/**
 * @author: ligang
 * date: 2018/3/15
 * time: 11:33
 */
public class runTest {
    public static void main(String[] args) {
        try {
            SynchronizedObject so = new SynchronizedObject();
            MyThread thread = new MyThread(so);
            thread.start();
            Thread.sleep(1000);
            thread.stop();
            System.out.println(so.getUsername()+" "+so.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//b a

}
