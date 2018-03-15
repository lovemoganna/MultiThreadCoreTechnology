package aier.com.isalive;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 17:15
 */
public class Thread2Test extends TestCase {
    public static void main(String[] args) {
        System.out.println("实例化线程类之前");
        Thread2 t = new Thread2();
        System.out.println("实例化线程类之后");
        Thread thread = new Thread(t);
        System.out.println("创建新的线程之后");
        System.out.println("main begin thread isAlive= "+thread.isAlive());
        thread.setName("A");
        System.out.println("AThread启动之前");
        thread.start();
        System.out.println("main end thread isAlive= "+thread.isAlive());
    }
}