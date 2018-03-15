package aier.com.servlet;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 12:20
 */
public class Test {
    public static void main(String[] args) {
        ALogin a= new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}
