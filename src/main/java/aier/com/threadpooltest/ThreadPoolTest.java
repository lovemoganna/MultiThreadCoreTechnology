package aier.com.threadpooltest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: ligang
 * date: 2018/3/17
 * time: 16:47
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {
        //创建一个线程类对象
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        //ExecutorService executor = Executors.newFixedThreadPool(5);//线程池可以重复使用

        //往线程池里面放置15个任务
        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
           /* System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());*/
        }
        //并不会立即终止线程池,等所有任务缓存队列中的任务都执行完后才终止
        executor.shutdown();
    }
}
//线程类任务
class MyTask implements Runnable {
    //定义任务数量
    private int taskNum;
    //声明构造方法
    public MyTask(int num) {
        this.taskNum = num;
    }
    //执行线程的方法
    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
/**
 * 正在执行task 0
 线程池中线程数目：1，队列中等待执行的任务数目：0，已执行完的任务数目：0
 线程池中线程数目：2，队列中等待执行的任务数目：0，已执行完的任务数目：0
 线程池中线程数目：3，队列中等待执行的任务数目：0，已执行完的任务数目：0
 正在执行task 1
 线程池中线程数目：4，队列中等待执行的任务数目：0，已执行完的任务数目：0
 正在执行task 3
 正在执行task 2
 线程池中线程数目：5，队列中等待执行的任务数目：0，已执行完的任务数目：0
 正在执行task 4
 线程池中线程数目：5，队列中等待执行的任务数目：1，已执行完的任务数目：0
 线程池中线程数目：5，队列中等待执行的任务数目：2，已执行完的任务数目：0
 线程池中线程数目：5，队列中等待执行的任务数目：3，已执行完的任务数目：0
 线程池中线程数目：5，队列中等待执行的任务数目：4，已执行完的任务数目：0
 线程池中线程数目：5，队列中等待执行的任务数目：5，已执行完的任务数目：0
 线程池中线程数目：6，队列中等待执行的任务数目：5，已执行完的任务数目：0
 线程池中线程数目：7，队列中等待执行的任务数目：5，已执行完的任务数目：0
 正在执行task 11
 线程池中线程数目：8，队列中等待执行的任务数目：5，已执行完的任务数目：0
 正在执行task 12
 线程池中线程数目：9，队列中等待执行的任务数目：5，已执行完的任务数目：0
 正在执行task 13
 线程池中线程数目：10，队列中等待执行的任务数目：5，已执行完的任务数目：0
 正在执行task 10
 正在执行task 14
 task 0执行完毕
 正在执行task 5
 task 1执行完毕
 正在执行task 6
 task 3执行完毕
 正在执行task 7
 task 4执行完毕
 task 2执行完毕
 正在执行task 8
 正在执行task 9
 task 11执行完毕
 task 13执行完毕
 task 12执行完毕
 task 14执行完毕
 task 10执行完毕
 task 5执行完毕
 task 6执行完毕
 task 7执行完毕
 task 8执行完毕
 task 9执行完毕

 */

/**
 *  从上面看出,当线程池中的线程的数目大于5时,就将任务放入任务缓存队列里面了.当任务缓存队列满了之后,便创建新的线程.
 *  如果上面程序中，将for循环中改成执行20个任务，就会抛出任务拒绝异常了。原因就是,最多处理10个任务,缓存队列可以存储5个任务.
 *
 *  Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
    Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
    Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
    Executors.ScheduledThreadPoolExecutor(); //
    Executors.newWorkStealingPool();      //
 （1）newCachedThreadPool ：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。

 （2）newFixedThreadPool：创建一个固定数目的、可重用的线程池。

 （3）newScheduledThreadPool:创建一个定长线程池，支持定时及周期性任务执行。

 （4）newSingleThreadExecutor：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

 （5）newSingleThreadScheduledExcutor：创建一个单例线程池，定期或延时执行任务。

 （6）newWorkStealingPool:创建持有足够线程的线程池来支持给定的并行级别，并通过使用多个队列，减少竞争，它需要穿一个并行级别的参数，如果不传，则被设定为默认的CPU数量。

  (7) ForkJoinPool：支持大任务分解成小任务的线程池，这是Java8新增线程池，通常配合ForkJoinTask接口的子类RecursiveAction或RecursiveTask使用。
 */
/**
 public static ExecutorService newFixedThreadPool(int nThreads) {
     return new ThreadPoolExecutor(nThreads, nThreads,
     0L, TimeUnit.MILLISECONDS,
     new LinkedBlockingQueue<Runnable>());
 }
 public static ExecutorService newSingleThreadExecutor() {
     return new FinalizableDelegatedExecutorService
     (new ThreadPoolExecutor(1, 1,
     0L, TimeUnit.MILLISECONDS,
     new LinkedBlockingQueue<Runnable>()));
 }
 public static ExecutorService newCachedThreadPool() {
     return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     60L, TimeUnit.SECONDS,
     new SynchronousQueue<Runnable>());
 }


 从它们的具体实现来看，它们实际上也是调用了ThreadPoolExecutor，只不过参数都已配置好了。

 　　newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，它使用的LinkedBlockingQueue；

 　　newSingleThreadExecutor将corePoolSize和maximumPoolSize都设置为1，也使用的LinkedBlockingQueue；

 　　newCachedThreadPool将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，使用的SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程。

 　　实际中，如果Executors提供的三个静态方法能满足要求，就尽量使用它提供的三个方法，因为自己去手动配置ThreadPoolExecutor的参数有点麻烦，要根据实际任务的类型和数量来进行配置。

 　　另外，如果ThreadPoolExecutor达不到要求，可以自己继承ThreadPoolExecutor类进行重写。
 */