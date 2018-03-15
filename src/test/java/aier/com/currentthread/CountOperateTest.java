package aier.com.currentthread;

import junit.framework.TestCase;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 16:33
 */
public class CountOperateTest extends TestCase {
    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        Thread t = new Thread(c);
        t.setName("A");
        //t.start();
    }
}