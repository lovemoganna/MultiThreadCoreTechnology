package aier.com.servlet;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 12:16
 */
public class BLogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("b","bbb");
    }
}
