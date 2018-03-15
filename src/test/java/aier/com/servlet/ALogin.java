package aier.com.servlet;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 12:14
 */
public class ALogin extends Thread{
    @Override
    public void run() {
        LoginServlet.doPost("a","aaa");
    }
}
