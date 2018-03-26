
## 线程池的使用

在使用线程的时候去创建一个线程,但是这样会有问题:

并发的线程数量过多,并且每个线程都是执行一个时间很短的任务就结束了,这样频繁创建线程就会大大降低系统的效率,因为频繁的创建线程和销毁线程需要时间.

所以我们需要线程池.

### java中的ThreadPoolExecutor 

`ThreadPoolExecutor`的构造方法如下:
```
//创建一个新的 ThreadPoolExecutor与给定的初始参数和默认线程工厂和拒绝的执行处理程序
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
    }
  
//创建一个新的 ThreadPoolExecutor与给定的初始参数和默认线程工厂。
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory) {
}    

//使用给定的初始参数和默认拒绝的执行处理程序创建一个新的 ThreadPoolExecutor 。 
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              RejectedExecutionHandler handler) {
    }
//用给定的初始参数创建一个新的 ThreadPoolExecutor     
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
}    
```

ThreadPoolExecutor继承了AbstractExecutorService类，并提供了四个构造器，
但我们会发现前面三个构造器都是调用的第四个构造器进行的初始化工作。

各个参数的意思:

1.corePoolSize --核心池的大小
```
在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，

除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法.
也就是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。

默认情况下，在创建了线程池后，线程池中的线程数为0，
当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，
就会把到达的任务放到缓存队列当中；
```
2.maximumPoolSize --线程池最大线程数,它表示在线程池中最多能创建多少个线程；
```
表示在线程池中最多能创建多少个线程；
```
3.keepAliveTime --表示线程没有任务执行时最多保持多久时间会终止。
```androiddatabinding
默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用.

当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，
直到线程池中的线程数不超过corePoolSize。

如果调用了allowCoreThreadTimeOut(boolean)方法，
在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0；
```
4.TimeUnit --参数keepAliveTime的时间单位.
```
TimeUnit.DAYS;               //天
TimeUnit.HOURS;             //小时
TimeUnit.MINUTES;           //分钟
TimeUnit.SECONDS;           //秒
TimeUnit.MILLISECONDS;      //毫秒
TimeUnit.MICROSECONDS;      //微妙
TimeUnit.NANOSECONDS;       //纳秒
```
5.BlockingQueue<Runnable>-- 一个阻塞队列，用来存储等待执行的任务
```androiddatabinding
这里的阻塞队列有以下几种选择:
ArrayBlockingQueue;
LinkedBlockingQueue;
SynchronousQueue;

ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。
线程池的排队策略与BlockingQueue有关。
```

6.ThreadFactory-- 线程工厂，主要用来创建线程
 
7.RejectedExecutionHandler --拒绝处理任务时的策略

```
ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务.
```

### AbstractExecutorService

源码
```androiddatabinding

public abstract class AbstractExecutorService implements ExecutorService {

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
    }

    public Future<?> submit(Runnable task) {
    }

    public <T> Future<T> submit(Runnable task, T result) {
    }

    public <T> Future<T> submit(Callable<T> task) {
    }

    private <T> T doInvokeAny(Collection<? extends Callable<T>> tasks,
                              boolean timed, long nanos)
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
        throws InterruptedException, ExecutionException {
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                           long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
        throws InterruptedException {
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                         long timeout, TimeUnit unit)
        throws InterruptedException {
    }

    private static <T> void cancelAll(ArrayList<Future<T>> futures) {
    }

    private static <T> void cancelAll(ArrayList<Future<T>> futures, int j) {
}

```

### ExecutorService

源码
```androiddatabinding
public interface ExecutorService extends Executor {

    void shutdown();
    
    List<Runnable> shutdownNow();
    
    boolean isShutdown();
    
    boolean isTerminated();

    boolean awaitTermination(long timeout, TimeUnit unit)
        throws InterruptedException;
    
    <T> Future<T> submit(Runnable task, T result);
   
    Future<?> submit(Runnable task);
    
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
        throws InterruptedException;

    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
        throws InterruptedException;

    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
        throws InterruptedException, ExecutionException;

    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException,isShutdown(),
}

```

### Executor

源码
```androiddatabinding
public interface Executor {
    void execute(Runnable command);
}
```

### 总结
```androiddatabinding
ThreadPoolExecutor继承AbstractExecutorService,
AbstractExecutorService实现了ExecutorService接口
ExecutorService继承了了Runnable接口.

Executor是一个顶层接口,里面只有一个 void execute(Runnable command).
返回值void,参数Runnable,就是用来执行传进去的任务.

ExecutorService声明了一些方法,主要有shutdownNow(),submit(),invokeAll(),invokeAny(),awaitTermination(),isTerminated().

AbstractExecutorService基本实现了ExecutorService里面所有方法.

ThreadPoolExecutor继承了AbstractExecutorServiceService

ThreadPoolExecutor里面有几个中要的方法:


1.execute()
execute()方法实际上是Executor中声明的方法，在ThreadPoolExecutor进行了具体的实现，这个方法是ThreadPoolExecutor的核心方法，
通过这个方法可以向线程池提交一个任务，交由线程池去执行。

2.submit()

submit()方法是在ExecutorService中声明的方法，在AbstractExecutorService就已经有了具体的实现，在ThreadPoolExecutor中并没有对其进行重写，这个方法也是用来向线程池提交任务的，但是它和execute()方法不同，它能够返回任务执行的结果，
去看submit()方法的实现，会发现它实际上还是调用的execute()方法，只不过它利用了Future来获取任务执行结果.

3.shutdown()
启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
 
4.shutdownNow()
尝试停止所有主动执行的任务，停止等待任务的处理，并返回正在等待执行的任务列表。
```

## 线程池实现原理

### 线程池状态

```androiddatabinding
   / 符号会判断2个是否成立,无论第一个成立不成立,都要判断第二个,只要有一个成立,都通过.
   
   << 是左移的意思. n =2 ,n << 1 就是2左移1位.先把2转成2进制是10,左移1位,就变成2进制的100,转换成10进制是4.
   
    //定义一个原子整数对象
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    //定义一个常量COUNT_BITS=32-3=29
    private static final int COUNT_BITS = Integer.SIZE - 3;
    //定义一个容量=(1 左移29位) -1 =10^29-1=17179869183 约等于172亿
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    //运行状态存储在high-order bits中,定义几个状态
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // 打包和解包ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    //此函数会返回2个值
    private static int ctlOf(int rs, int wc) { return rs | wc; }
```
#### 注释的简单理解
```androiddatabinding
主池控制状态,ctl是一个原子整数包装

 两个概念领域
     workerCount，表示线程的有效数量.
     runState，指线程是否运行，关闭等.

 为了将它们打包到一个int中，我们将workerCount限制为
            （2 ^ 29）-1（约5亿）线程而不是（2 ^ 31）-1（2
            十亿）否则可表示。如果这在任务中是个问题
            未来，这个变量可以变成一个AtomicLong，
            并调整下面的shift / mask常量。但直到需要
            出现这种情况之前，使用int可以更快，更简单。
```

**线程池的各个状态:**

```
runState提供了主要的生命周期控制，取值为：

        (运行)Running：接受新任务并处理排队的任务
        (关机)SHUTDOWN：不接受新任务，但处理排队的任务
        (停止)STOP：不接受新任务，不处理排队的任务，
                  并中断正在进行的任务
        (整理)TIDYING：所有任务都已终止，workerCount(线程的有效数量,最大值是5亿)为零，
                  线程转换到状态TIDYING
                  将运行terminate（）挂接方法
        (终止)TERMINATED：整理已完成,进入终止状态.
     
      这些值之间的数字顺序很重要，以允许有序的比较。

runstate()的各个状态的发生:
       
      runState随着时间是单调递增，但不需要命中每个状态。顺序是：
      
        RUNNING -> SHUTDOWN
          在调用shutdown（）时，可能会隐含在finalize（）中,会被垃圾收集器调用.
        (RUNNING or SHUTDOWN) -> STOP
           在调用shutdownNow（）时
        SHUTDOWN -> TIDYING
           当队列和池都是空的
        STOP -> TIDYING
           池空时
        TIDYING -> TERMINATED
            当terminate（）钩子方法完成时
        在awaitTermination（）中等待的线程将返回
                    状态达到TERMINATED。
      
```
找到AtomicInteger
```androiddatabinding
 public AtomicInteger(int initialValue) {
        value = initialValue;
    }
    
寻找value

 private volatile int value;

也就是说value表示当前线程池的状态，它是一个volatile变量用来保证线程之间的可见性；    
```
### 任务的执行

```androiddatabinding
    //任务缓存队列，用来存放等待执行的任务
    private final BlockingQueue<Runnable> workQueue;
    //线程池的主要状态锁，对线程池状态（比如线程池大小,runState等）的改变都要使用这个锁
    private final ReentrantLock mainLock = new ReentrantLock();
    //用来存放工作集
    private final HashSet<Worker> workers = new HashSet<>();
    //mainLock创建一个通信对象    
    private final Condition termination = mainLock.newCondition();
    //用来记录线程池中曾经出现过的最大线程数
    private int largestPoolSize;
    //用来记录已经执行完毕的任务个数
    private long completedTaskCount;
    //线程工厂，用来创建线程
    private volatile ThreadFactory threadFactory;
    //任务拒绝策略
    private volatile RejectedExecutionHandler handler;
    //线程存活时间,也就是无任务执行的线程的空闲时间,超过了这个时间,线程终止.(线程池中线程的数量 > corePoolSize的时候,keepAliveTime才会生效.)
    private volatile long keepAliveTime;
    //是否允许核心线程数超时  允许,keepAliveTime无效
    private volatile boolean allowCoreThreadTimeOut;
    //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列)
    private volatile int corePoolSize;
    //线程池最大能容忍的线程数
    private volatile int maximumPoolSize;
    //任务拒绝策略
    private static final RejectedExecutionHandler defaultHandler =
        new AbortPolicy();
    //运行时权限
    private static final RuntimePermission shutdownPerm =
        new RuntimePermission("modifyThread");
    //访问控制上下文
    private final AccessControlContext acc;
    //工人类
    private final class Worker
        extends AbstractQueuedSynchronizer
        implements Runnable
    {
    }
```
总结:

```androiddatabinding
说一下corePoolSize、maximumPoolSize、largestPoolSize三个变量。

corePoolSize翻译是核心池大小,我认为是线程池的大小,只不过线程池的大小会变化的.

举例:

一个工厂,10个人,每个人只能做一件任务.

只要10个人里面有人空闲,来了任务就分配给它.

10个人都有任务的时候,来了新任务,让它排队等待.



如果说新任务来的太快了,远远大于工人处理任务的速度,这个时候就要招4个人手,来解决人手不够的问题.把新任务给新招来人分配一下.

如果说速度还是赶不上,工厂就要考虑不接任务了,或者抛弃前面的一些任务.

也就是我们前面说的SHUTDOWN状态或者采取拒绝任务策略.

还有一种是新人和旧人里面有空闲,新任务增长的速度慢,利益减少,工厂这时候就要辞退新人了,毕竟需要成本.
(其实这也就是相当于线程池中线程数量的大小 > 核心池线程的数量大小,有一部分线程达到了keepAliveTime,自然会进入终止状态)

这个例子的corePoolSize大小是10,maxinumPoolSize是14.

就是说corePoolSize就是线程池大小，maximumPoolSize在我看来是线程池的一种补救措施，即任务量突然过大时的一种补救措施。

largestPoolSize只是一个用来起记录作用的变量，用来记录线程池中曾经有过的最大线程数目，跟线程池的容量没有任何关系。
```

在ThreadPoolExecutor类中，最核心的任务提交方法是execute()方法，虽然通过submit也可以提交任务，

但是实际上submit方法里面最终调用的还是execute()方法，所以我们只需要研究execute()方法的实现原理即可：

```androiddatabinding
public void execute(Runnable command) {
     //Runnable--传进来的任务
        if (command == null)
            throw new NullPointerException();
        //获得一个所有线程都可见的值.  
        int c = ctl.get();
                        
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        else if (!addWorker(command, false))
            reject(command);
    }


```

解释:
```androiddatabinding
1. ctl之前我们提过: 原子整型数.
        private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        
2. AtomicInteger里面有一个构造方法:
public AtomicInteger(int initialValue) {
        value = initialValue;
    }
    
3.get方法
 public final int get() {
        return value; ----出现过 ----private volatile int value;}

4.isRunning()方法,判断线程池是否处于Running状态
private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }
    
5.public interface BlockingQueue<E> extends Queue<E> {
         boolean offer(E e);  
         //将指定的元素插入此队列中，如果它是立即可行且不会违反容量限制，返回 true在成功和 false如果当前没有空间可用。
    }
    
6.public boolean remove(Runnable task) {
        //从该队列中删除指定元素的单个实例（如果存在）。 
      }
7.ThreadPoolExecutor的方法
//拒绝执行策略
final void reject(Runnable command) {
        handler.rejectedExecution(command, this);
    }
    public interface RejectedExecutionHandler {
        void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
    }

8. addworker()方法
 private boolean addWorker(Runnable firstTask, boolean core){}
 在工人之后创建线程，运行和清理的方法.

    
下面是程序的步骤:

1.没任务执行,就会出现空指针异常
2.当有效线程数量小于核心池大小的时候,首先尝试用给定的命令开始一个新的线程任务.
  对addWorker的调用会自动检查runState和workerCount
                
3.如果线程池处于运行状态并且缓存队列继续为线程池提供任务.
仍然需要仔细检查我们是否应该添加一个线程.

如果有必要的话回滚队列,如果停止，或者如果没有的话就开始一个新的线程。

4.如果任务不能被接受执行,就采用拒绝执行策略.
          
```
注释翻译
```androiddatabinding

分三步进行：
         
   1.如果少于corePoolSize线程正在运行，请尝试
   首先用给定的命令开始一个新的线程
   任务。 对addWorker的调用会自动检查runState和
   workerCount，从而防止可能会添加的错误警报
   线程时它不应该通过返回false。
 
   2.如果一项任务能够成功排队，那么我们仍然需要
   仔细检查我们是否应该添加一个线程.
   （因为现有的自从上次检查后死亡）或者那个
   自进入该方法以来，该池关闭。 所以我们
   重新检查状态，如果有必要的话回滚队列if
   停止，或者如果没有的话就开始一个新的线程。
 
   3.如果我们不能排队任务，那么我们尝试添加一个新的
   线。 如果失败，我们知道我们已关闭或饱和
   因此拒绝任务。
```

总结:
```
    1）首先，要清楚corePoolSize和maximumPoolSize的含义；

　　2）其次，要知道Worker是用来起到什么作用的；

　　3）要知道任务提交给线程池之后的处理策略，这里总结一下主要有4点：

如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；

如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；

如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；

如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。

```

### 线程池中的线程初始化

默认情况下，创建线程池之后，线程池中是没有线程的，需要提交任务之后才会创建线程。

在实际中如果需要线程池创建之后立即创建线程，可以通过以下两个方法办到：
````
prestartCoreThread()：初始化一个核心线程；

prestartAllCoreThreads()：初始化所有核心线程
````

我们看一下这些方法:
```androiddatabinding
 public boolean prestartCoreThread() {
        return workerCountOf(ctl.get()) < corePoolSize &&
            addWorker(null, true);
    }
//注意传进去的参数是null    

 public int prestartAllCoreThreads() {
        int n = 0;
        while (addWorker(null, true))
            ++n;
        return n;
    }    
```
```androiddatabinding

如果传进去的参数为null，也就是说没有任务,则最后执行线程会阻塞在getTask方法中的

getTask()的描述:

try {
        Runnable r = timed ?
            workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
            workQueue.take();
        if (r != null)
            return r;
        timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
            
r = workQueue.take();
```
等待任务队列中有任务.

## 任务缓存队列及排队策略

```androiddatabinding
workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：

　　1）ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；

　　2）LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；

　　3）synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
```

## 任务拒绝策略

当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略，通常有以下四种策略：
```androiddatabinding
ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
```
## 线程池的关闭

```androiddatabinding
ThreadPoolExecutor提供了两个方法，用于线程池的关闭，分别是shutdown()和shutdownNow()，其中：

shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
```
## 线程池容量的动态调整
```androiddatabinding
　ThreadPoolExecutor提供了动态调整线程池容量大小的方法：setCorePoolSize()和setMaximumPoolSize()，

setCorePoolSize：设置核心池大小
setMaximumPoolSize：设置线程池最大能创建的线程数目大小
　　当上述参数从小变大时，ThreadPoolExecutor进行线程赋值，还可能立即创建新的线程来执行任务。
```

## 合理配置线程池的大小

一般需要根据任务的类型来配置线程池大小：

　　如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1

　　如果是IO密集型任务，参考值可以设置为2*NCPU

　　当然，这只是一个参考值，具体的设置还需要根据实际情况进行调整，

   比如可以先将线程池大小设置为参考值，再观察任务运行情况和系统负载、资源利用率来进行适当调整。
   
   
## 创建一个线程池

```androiddatabinding
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
测试结果如下:

 正在执行task 0
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
```   