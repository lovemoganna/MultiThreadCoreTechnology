package aier.com.multithread;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 10:53
 * 数据不共享的情况
 */
public class Thread4 extends Thread {
    //新建一个实例变量
    private int count = 5;
    //重写构造方法
    public Thread4(String name) {
        super();
        this.setName(name);//设置线程名称
        System.out.println(this);
    }

    @Override
    public void run() {
        super.run();
        while(count > 0){
            count --;
            System.out.println("由"+this.currentThread().getName()+"计算.cout= "+count);
        }
    }
}

