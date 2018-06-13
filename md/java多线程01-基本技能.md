
## 进程和多线程的概念

[进程的概念](https://baike.baidu.com/item/%E8%BF%9B%E7%A8%8B/382503?fr=aladdin)

> 百度百科: 
进程（Process）是计算机中的程序关于某数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位，是操作系统结构的基础。在早期面向进程设计的计算机结构中，进程是程序的基本执行实体；在当代面向线程设计的计算机结构中，进程是线程的容器。程序是指令、数据及其组织形式的描述，进程是程序的实体。

一句话:打开win7任务管理器,可以把将一个正在操作系统中运行的exe程序理解为一个进程.

[线程的概念](https://baike.baidu.com/item/线程/103101?fr=aladdin)

> 百度百科:<br><br>
线程，有时被称为轻量级进程(Lightweight Process，LWP），是程序执行流的最小单元。
>>一个标准的线程由线程ID，当前指令指针(PC），寄存器集合和堆栈组成。
>>>另外，线程是进程中的一个实体，是被系统独立调度和分派的基本单位，线程自己不拥有系统资源,只拥有一点儿在运行中必不可少的资源,
但它可与同属一个进程的其它线程共享进程所拥有的全部资源。<br><br>
一个线程可以创建和撤消另一个线程，同一进程中的多个线程之间可以并发执行。<br><br>
由于线程之间的相互制约，致使线程在运行中呈现出间断性。<br>
线程也有就绪、阻塞和运行三种基本状态。<br>
就绪状态是指线程具备运行的所有条件，逻辑上可以运行，<br>
在等待处理机；运行状态是指线程占有处理机正在运行；<br>
阻塞状态是指线程在等待一个事件（如某个信号量），逻辑上不可执行。<br>
每一个程序都至少有一个线程，若程序只有一个线程，那就是程序本身。<br>
线程是程序中一个单一的顺序控制流程。<br>
>>>>进程内有一个相对独立的、可调度的执行单元，是系统独立调度和分派CPU的基本单位指令运行时的程序的调度单位。<br>
>>>>>多线程: 在单个程序中同时运行多个线程完成不同的工作，称为多线程。<br>

**形象点就是**: 线程就是在进程中独立运行的子任务.一个QQ进程中运行时有很多的子线程在同时运行.


## 单线程和多线程的区别

比如说,有2个任务,任务1:Task1和任务2:Task2.

Task1 和 Task2 是2个完全独立,互不相关的任务.

### 情景描述: 
```androiddatabinding
Task1 正在等待远程服务器返回数据,一遍进行后期的处理.此时CPU一直处于等待状态.相当于"空运行状态".

Task2 被设定为10s之后被运行.

```
### 单线程
不管执行Task2需要多少时间,Task2必须老老实实的等待10s后再执行.

Task2等待时间很长,系统的运行效率会大大降低.

单线程的缺点:排队执行,需要按照前后顺序执行(也就是我们所说的**同步**)),所以造成了CPU利用率大幅度降低的情况.
### 多线程

多线程就好多了,Task1 和 Task2 之间可以来回切换.也就是执行Task2 不必等待10s后在执行了.

优点: 也就提高了系统的执行效率了.所以可以归结为使用多线程就是使用**异步**.


## 多线程示例

一个进程正在运行时至少会有1个线程在运行.

比如我们输入:
```
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        //mian
    }
}
```
在控制台输出的main其实就是一个名称叫做**main的线程**在执行main()方法中的代码.

输出的main与main方法没关系,只不过是名字一样.

### 多线程的创建继承自Thread类

Thread类的结构
eg: `public class Thread implements Runnable`

Thread类继承Runnable接口,他们之间具有多态关系.

java语言的特点是单继承,所以不建议直接继承Thread类,为了支持多继承,我们可以用`implements Runnable`的形式,一边实现,一边继承.

再看下面的示例:

线程类:
```
public class Thread1 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("Thread1");
    }
}
```
线程测试类:
```androiddatabinding
public class Thread1Test extends TestCase {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        System.out.println("运行结束!");
    }
}
```
测试结果: 
```androiddatabinding
运行结束!
Thread1
```

**说明:** 

Thread1类中的Run()方法执行的比较晚,这说明,在**使用多线程技术时,代码的运行结果与代码执行顺序或调用顺序是无关的**.

线程是一个子任务,CPU以不确定的方式,或者以随机的时间来调用线程中的run()方法.所以会出现上面的打印结果.

**这也是线程调用的随机性的表现.**

具体如下:
线程类
```androiddatabinding
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
```

测试类
```androiddatabinding
public class Thread2Test extends TestCase {
    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        thread2.start();
        try {
            for (int i = 0; i < 10; i++) {
                int time =(int)(Math.random() * 1000 );
                Thread.sleep(time);
                System.out.println("main = "+ Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
默认情况下,**异步**执行结果如下:
```androiddatabinding
run = Thread-0
main = main
main = main
run = Thread-0
run = Thread-0
main = main
main = main
main = main
main = main
run = Thread-0
main = main
main = main
run = Thread-0
run = Thread-0
run = Thread-0
main = main
run = Thread-0
main = main
run = Thread-0
run = Thread-0
```
CPU执行线程的不确定性过程:

Thread2.java类中的start()方法通知"线程规划期"此线程已经准备就绪,等待调用对象的Run()方法.

这个过程其实就是让系统安排一个时间,来调用Thread中run()方法.也就是使线程得到运行,启动线程,具有异步执行的效果.

如果调用代码thread.run()就不是异步执行了,而是同步.也就是说此线程对象不交给"线程规划器"来进行处理,而是由main主线程来调用run()方法,

也就是必须等待run()方法中的代码执行后才可以执行后面的代码.

线程类不变.

测试代码:
```androiddatabinding
public class Thread2Test extends TestCase {
    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();
        thread2.run();//here do changed
        try {
            for (int i = 0; i < 10; i++) {
                int time =(int)(Math.random() * 1000 );
                Thread.sleep(time);
                System.out.println("main = "+ Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```

已同步的方式运行结果展示
```androiddatabinding
run = main
run = main
run = main
run = main
run = main
run = main
run = main
run = main
run = main
run = main
main = main
main = main
main = main
main = main
main = main
main = main
main = main
main = main
main = main
main = main
```
之所以打印的都是main,是因为你没有启动Thread2这个线程.

**执行start()方法的顺序不代表线程启动的顺序.**

我们可以测试一下:

线程类:
```androiddatabinding
public class Thread3 extends Thread {
    private int i;
    /**
     * 带有参数的构造方法
     * @param i
     */
    public Thread3(int i) {
        super();
        this.i =i;
    }
    @Override
    public void run() {
        System.out.println(i);
    }
}
```
测试类:

```androiddatabinding
public class Thread3Test extends TestCase {
    public static void main(String[] args) {
        Thread3 t1 = new Thread3(1);
        Thread3 t2 =new Thread3(2);
        Thread3 t3 =new Thread3(3);
        Thread3 t4 =new Thread3(4);
        Thread3 t5 =new Thread3(5);
        Thread3 t6 =new Thread3(6);
        Thread3 t7 =new Thread3(7);
        Thread3 t8 =new Thread3(8);
        Thread3 t9 =new Thread3(9);
        Thread3 t10 =new Thread3(10);
        Thread3 t11 =new Thread3(11);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
    }
}
```

测试结果:

````androiddatabinding
1
3
2
4
5
6
7
9
8
10
11
````

这样就可以印证我们的结论了.

### 多线程的创建--实现Runnable接口

线程类:
```androiddatabinding
public class MyRunnable1 implements Runnable{
    @Override
    public void run() {
        System.out.println("运行中!");
    }
}
```
测试类:
```androiddatabinding
public class MyRunnable1Test extends TestCase {
    public static void main(String[] args) {
        MyRunnable1 run1 = new MyRunnable1();
        Thread t1 = new Thread(run1);
        t1.start();
        System.out.println("运行结束!");
        执行结果
        /**
        运行结束!
        运行中!
        */
        
        如果使用下面的测试:
        MyRunnable1 run1 = new MyRunnable1();
        Thread t1 = new Thread(run1,"Thread-01");
        t1.start();
        System.out.println(Thread.currentThread().getName());
        System.out.println(t1.currentThread().getName());
        System.out.println(t1.getName()+"运行结束!");
        执行结果:
        /**
         main
         main
         运行中!
         Thread-01运行结束!
         */
        
    }
}
```
可以看下Thread.java的构造函数

![](http://upload-images.jianshu.io/upload_images/7505161-a16b139efa02a7ea.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Thread类也同样实现了Runnable接口,如下:

![](http://upload-images.jianshu.io/upload_images/7505161-0cbeabc34e53760d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这说明构造函数Thread(Runnable target)不光可以传入Runnable接口的对象,还可以传入一个Thread类的对象.

将测试类改成下面这样测试
```androiddatabinding
public static void main(String[] args) {
        MyRunnable1 run1 = new MyRunnable1();
        Thread t1 = new Thread(run1,"Thread-01");
        //t1.start();
        System.out.println(Thread.currentThread().getName());
        System.out.println(t1.currentThread().getName());
        System.out.println(t1.getName()+"运行结束!");

        //下面将上面的Thread对象中的run()方法交给另外一个线程进行调用.
        Thread thread = new Thread(t1);
        System.out.println("看看能不能调用Thread-01线程的Run方法.");
        thread.start();
        System.out.println(thread.getName());
    }
```
测试结果:
```androiddatabinding
main
main
Thread-01运行结束!
看看能不能调用Thread-01线程的Run方法.
Thread-0
运行中!
```
说明新建的线程能成功的调用里面线程对象的run()方法.

## 实例变量与线程安全

自定义线程类中的**实例变量针对其他线程可以有共享和不共享之分.**

这在多个线程之间进行交互时是一个很重要的技术点.

### 不共享数据的情况
线程类:
```androiddatabinding
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
```
测试类:
```androiddatabinding
public class Thread4Test extends TestCase {
    public static void main(String[] args) {
        Thread4 a = new Thread4("A");
        Thread4 b = new Thread4("B");
        Thread4 c = new Thread4("C");

        a.start();
        b.start();
        c.start();
    }
}
```
测试结果:
```androiddatabinding
Thread[A,5,main]
Thread[B,5,main]
Thread[C,5,main]
由A计算.cout= 4
由A计算.cout= 3
由A计算.cout= 2
由C计算.cout= 4
由C计算.cout= 3
由B计算.cout= 4
由B计算.cout= 3
由B计算.cout= 2
由B计算.cout= 1
由B计算.cout= 0
由A计算.cout= 1
由A计算.cout= 0
由C计算.cout= 2
由C计算.cout= 1
由C计算.cout= 0
```

总结:
```
启动了3个线程,每个线程都有各自的count变量,自己减少自己的count变量的值.

这种情况就是变量不共享.所以这并不是多个线程访问同一个实例变量的情况.
```
**如何实现3个线程共同对一个count变量进行减法操作的目的哪?**

### 共享数据的情况

共享数据的情况就是多个线程可以访问同一个变量,比如说在实现投票功能的软件时,多个线程可以同时处理同一个人的票数.
线程类:
```androiddatabinding
public class Thread5 extends Thread{
    private int count =5;

    @Override
    public void run() {
        super.run();
        count --;
        System.out.println("由"+this.currentThread().getName()+"计算.count= "+count);
    }
}
```
测试类:
```androiddatabinding
public class Thread5Test extends TestCase {
    public static void main(String[] args) {
        Thread5 t5 = new Thread5();
        Thread a = new Thread(t5, "A");
        Thread b = new Thread(t5, "B");
        Thread c = new Thread(t5, "C");
        Thread d = new Thread(t5, "D");
        Thread e = new Thread(t5, "E");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
```
测试结果:
```androiddatabinding
由D计算.count= 1
由A计算.count= 1
由C计算.count= 2
由E计算.count= 0
由B计算.count= 3
```

我们可以发现,线程D和线程A打印出的Count值都是1.说明D和A同时对count进行处理.产生了`非线程安全`问题.

我们想要让它规规矩矩的来,按照依次递减的方式来打印数据.

我们先搞懂i--的操作:
````
1. 取得原有的i值
2. 计算i-1
3. 对i进行赋值
````
经过这3个步骤,如果有多个线程同时访问,那么一定会出现非线程安全的问题.

举个例子:
```androiddatabinding
5个人销售员,每个销售员卖出一个商品不可以得到相同的剩余数量.

必须在每一个销售员卖完一个货物后,

其他的销售员才可以在新的剩余商品数上继续减1操作.

之前的那种操作就相当于,一个销售员卖完了货,剩余货物的数量还没有执行减1操作,

其他的售货员就开始卖货了,那么在统计剩余货物的数量的时候,自然会出现差错.

```

所以我们需要在run()方法上加上`Synchronized`关键字
`Synchronized public void run(){xxx};`

测试结果:
```androiddatabinding
由B计算.count= 4
由A计算.count= 3
由E计算.count= 2
由D计算.count= 1
由C计算.count= 0
```
这样就解决了 i 依次递减的问题. 但是执行线程的先后顺序是无法保障的,因为CPU在执行run()方法的时候就有随机性.

**总结:**

通过在run()方法前加入synchronized关键字,使多个线程在执行run()方法时,以排队的方式进行处理.

当一个线程调用run()前,先判断run()方法有没有被上锁,如果上锁,说明有其他线程正在调用run方法.

这也就实现了排队调用run方法的目的.也就实现了我们期望的目的:对count变量执行依次递减的操作.

**Synchronized可以在任何对象及方法上加锁,而加锁的代码成为"互斥区"或者"临界区".**

> 当一个线程想要执行同步方法里面的代码时,线程先去尝试去拿这把锁,如果能拿到这把锁,那么这个线程就可以执行synchronized中的代码.
如果不能拿到这把锁,那么这个线程会不断的尝试去拿这把锁.直到能拿到为止,而且是多个线程同时取争抢这把锁.

### 非线程安全的实例

非线程安全主要是指**多个线程对同一个对象中的同一个实例变量进行操作时会出现值被更改,值不同步的情况.**

进而会影响程序的执行流程.

下面就是一个非线程安全的实例:

LoginServlet.java(方法类)
```androiddatabinding
public class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;

    public static void doPost(String username, String password) {
        try {
            usernameRef = username;
            if (username.equals("a")) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username = "+usernameRef+"   " +"pasword ="+password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
ALogin.java (线程A)
```androiddatabinding
public class ALogin extends Thread{
    @Override
    public void run() {
        LoginServlet.doPost("a","aaa");
    }
}
```
BLogin.java (线程B)
```androiddatabinding
public class BLogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("b","bbb");
    }
}
```

测试类:
```androiddatabinding
public class Test {
    public static void main(String[] args) {
        ALogin a= new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}
```
执行结果:
```androiddatabinding
username = b   pasword =bbb
username = b   pasword =aaa
```

要想解决这种现象,还是要在方法类LoginServlet.java的方法声明最前面加关键字Synchronized.

`Synchronize public static void dopost(String username,String password){}`

测试结果
```androiddatabinding
username = a   pasword =aaa
username = b   pasword =bbb
```
### i--和system.out.println()的异常

下面是println()方法和i --联合使用可能会出现的异常问题.

i --操作由前面的项目中单独一行运行或者在项目中println()方法中直接进行打印.

虽然println()方法在内部是同步的,但i --的操作确是在进入println()之前发生的,所以有发生非线程安全的概率.


实例:

线程类:
```
public class Thread1 extends Thread {
    private int i = 5;
    @Override
    public void run() {
        System.out.println("i = " + (i--) + " threadName = " + Thread.currentThread().getName());
    }
}
```

测试类:
```androiddatabinding
public static void main(String[] args) {
        Thread1 thread = new Thread1();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);
        Thread t4 = new Thread(thread);
        Thread t5 = new Thread(thread);

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
```
执行结果有概率出现先的情况

```androiddatabinding
i = 4 threadName = Thread-4
i = 3 threadName = Thread-3
i = 2 threadName = Thread-1
i = 1 threadName = Thread-5
i = 1 threadName = Thread-2
```
所以为了防止非线程安全问题,还是要继续使用同步方法.

## currentThread()方法

currentThread()方法可以返回代码段正在被哪个线程调用的信息.

```androiddatabinding
public class Thread5 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
    }
    //mian
}
```
上面的实例说明main方法被名为main的线程调用.

线程类:
````
public class Thread6 extends Thread {
    public Thread6(){
        System.out.println("构造方法的打印: "+ Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run method print: "+Thread.currentThread().getName());
    }
}
````
测试类:
```androiddatabinding
public class Thread6Test {
    public static void main(String[] args) {
        Thread6 thread = new Thread6();
        thread.start();
    }
}
```
测试结果
```androiddatabinding
构造方法的打印: main
run method print: Thread-0
```
**我们可以知道Thread6.java类的构造函数是被main线程调用的,而run方法是被名称为Thread-0调用的,run方法是自动调用的方法.**

如果测试类变为如下:
```androiddatabinding
public class Thread6Test {
    public static void main(String[] args) {
        Thread6 thread = new Thread6();
        thread.run();
    }
}
```
测试结果如下:
```androiddatabinding
构造方法的打印: main
run method print: main
```
Thread6.java类的构造函数是被main线程调用的,这个类本身的线程没有被启动,main线程调用Thread6.java类对象,同样调用它里面的方法.返回的是关于main线程的名字.

### 下一个实例

线程类:

```androiddatabinding
public class CountOperate extends Thread {
    public CountOperate(){
        System.out.println("CountOperate --- begin");
        System.out.println("Thread.currentThread().getName(): " + Thread.currentThread().getName());
        System.out.println("this.getName(): "+this.getName());
        System.out.println("CountOperate --- end");
    }
    @Override
    public void run() {
        System.out.println("run --- begin");
        System.out.println("Thread.currentThread().getName(): " + Thread.currentThread().getName());
        System.out.println("this.getName(): "+this.getName());
        System.out.println("run --- end ");
    }
```

测试类:
```
public class CountOperateTest extends TestCase {
    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        Thread t = new Thread(c);
        t.setName("A");
        //t.start();
    }
}
```
测试结果
```androiddatabinding
不启动新实例化线程的结果
CountOperate --- begin
Thread.currentThread().getName(): main
this.getName(): Thread-0
CountOperate --- end

启动新实例化线程的结果
run --- begin
Thread.currentThread().getName(): A
this.getName(): Thread-0
run --- end
```
我们可以看出,无论启动不启动新建线程.当生成一个新的线程的时候,main线程都会加载线程类里的构造方法.而run方法是被名称为Thread-0的线程调用的.

新建线程启动之后,就会自动调用run方法.而run方法是被名称为Thread-0的线程调用的.

## isAlive()方法

isAlive()方法用来判断当前的线程是否处于活动状态.

线程类:
```androiddatabinding
public class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("run = "+ this.isAlive());
    }
}
```
测试类:
```androiddatabinding
public class Thread1Test extends TestCase {
    public static void main(String[] args) {
        Thread1 t = new Thread1();
        System.out.println("begin : " + t.isAlive());
        t.start();
        System.out.println("end : " + t.isAlive());

    }
}
```
测试结果:
```androiddatabinding
begin : false
end : true
run = true
```
isAlive()作用就是测试线程是否处于活动状态.活动状态就是线程已经启动且尚未终止.

线程处于正在运行或者准备开始运行的状态.就认为线程是"存活的".

值得注意的是**System.out.println("end : " + t.isAlive());**

如果前面添加`Thread.sleep(5000)`

那么输出如下:
````
begin : false
run = true
end : false
````
之所以`end: false`,是因为Thread1对象已经在1s之内执行完毕了.

在使用isAlive()方法的时候,如果将线程对象以构造参数的方式传递给Thread对象,进行start()启动时,

运行的结果和前面示例是有差异的.

**原因是Thread.currentThread()和this的差异.**

下面做出测试:

测试类:
```androiddatabinding
public class Thread2 extends Thread {
    public Thread2(){
        System.out.println("Thread2 --- begin");
        System.out.println("Thread.currentThread().getName()---"+Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()---"+Thread.currentThread().isAlive());
        System.out.println("this.getName()---"+this.getName());
        System.out.println("this.isAlive()---"+this.isAlive());
        System.out.println("Thread2 --- end");
    }

    @Override
    public void run() {
        System.out.println("run --- begin");
        System.out.println("run.currentThread().getName()---"+Thread.currentThread().getName());
        System.out.println("run.currentThread().isAlive()---"+Thread.currentThread().isAlive());
        System.out.println("this.getName()---"+this.getName());
        System.out.println("this.isAlive()---"+this.isAlive());
        System.out.println("run --- end");
    }
}

```
测试类:
```androiddatabinding
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
```
测试结果如下:
```androiddatabinding
实例化线程类之前
Thread2 --- begin
Thread.currentThread().getName()---main
Thread.currentThread().isAlive()---true
this.getName()---Thread-0
this.isAlive()---false
Thread2 --- end
实例化线程类之后

创建新的线程之后
main begin thread isAlive= false
AThread启动之前

main end thread isAlive= true

run --- begin
run.currentThread().getName()---A
run.currentThread().isAlive()---true
this.getName()---Thread-0
this.isAlive()---false
run --- end
```

这说明线程类在实例化的时候,一定会先加载构造函数,

生成新的线程对象时,启动线程,就会自然的启动run()方法.

**最重要的是main线程不一定是最后结束的线程.**

## sleep()方法

sleep()的作用就是在指定的毫秒数内让当前正在执行的线程休眠(暂停执行).

**正在执行的线程是this.currentThread返回的线程.**

 示例:
 
 线程类
 ```androiddatabinding
public class Thread1 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run threadName = "+ Thread1.currentThread().getName()+" begin");
            Thread.sleep(3000);
            System.out.println("run threadName = "+ Thread1.currentThread().getName()+" end");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
测试类:
```androiddatabinding
public class Thread1Test extends TestCase {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        System.out.println("begin : "+ System.currentTimeMillis());
        thread1.start();
        System.out.println("end : "+ System.currentTimeMillis());
    }
}
```
测试结果
```androiddatabinding
begin : 1519908742644
end : 1519908742879
run threadName = Thread-0 begin
run threadName = Thread-0 end
```
由于main线程和Thread1线程是异步执行的.所以首先打印的信息为begin和end.

## getId()方法
getId()方法作用是取得线程的唯一标识.

线程类:
```androiddatabinding
public class Thread1 extends Thread {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+" "+thread.getId());
        
    }
}
```
测试结果:
```androiddatabinding
线程名称: main 线程Id: 1
```
### 停止线程

停止一个线程意味着在线程处理完任务之前停掉正在做的操作.也就是放弃当前的操作.

停止一个线程可以使用Thread.stop()方法.但最好不用它.

虽然它确实可以停止一个正在运行的线程,但是这个方法是不安全的(unsafe),并且已经被弃用了的.

大多数停止一个线程可以使用interrupt()方法,尽管表面意思就是中断的意思,但是还需要加入一个判断才可以完成线程的停止.

下面有3种方法可以终止正在运行的线程:

1. 使用退出标志,是线程正常退出,也就是当run方法完成后线程终止.

2. 使用stop方法强行终止线程,但是不推荐使用这个方法,因为stop和suspend及resume一样,都是国企作废的方法.

3. 使用interrupt中断线程.

### 停止不了的线程

下面将使用interrupt()方法来停止线程,但interrupt()方法的使用并不想for+breeak语句那样,马上就停止循环.

调用interrupt()方法仅仅是当前线程中打了一个停止的标记,并不是真正的停止线程.

线程类:
```androiddatabinding
public class MyThread extends Thread
{
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("i = "+(i + 1));
        }
    }
}

```

测试类:
```androiddatabinding
public class MyThreadTest extends TestCase {
    public static void main(String[] args) {
        try {
            MyThread t = new MyThread();
            t.start();
            t.sleep(2000);
            t.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }
}
```
测试结果:
```androiddatabinding
从1打印到5000,你会发现interrupt()方法没有生效
```
### 判断线程是否是停止状态

Thread.java提供的方法:

1. this.interrupted():测试当前线程是否已经中断. 当前线程是指运行this.interrupt()方法.

2. this.isInterrupted():测试线程是否已经中断. 

this.interrupted()方法的解释:测试当前线程已经中断.当前线程指的是运行this.interrupted()方法的线程.

