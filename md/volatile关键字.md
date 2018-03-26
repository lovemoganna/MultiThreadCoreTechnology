## volatile关键字

主要有2中特性:
```
1.保证了不同线程对变量操作的内存的可见性.

2.禁止指令重排.
```

## java内存模型

java内存模型(JMM),是用来屏蔽掉各种硬件和操作系统的内存访问差异,让java程序在各种平台上都能达到一致的内存访问效果.

由于CPU执行指令的速度是很快的,但是内存的访问速度就慢了很多.相差的不是一个数量级,所以搞处理器的那群人又在CPU里面加了好几层高速缓存.

在java内存模型里,对上述的优化又进行了一波抽象.

`JMM规定所有变量都是存在主存中的,类似于普通内存,每个线程又包含自己的工作内存,可以看成CPU上的寄存器或者高速缓存.`

`所以线程的操作都是以工作内存为主,他们自己只能访问自己的工作内存,且工作前后都要把值再同步回主存.`

大致图示:
![](https://upload-images.jianshu.io/upload_images/7505161-33c949cd0f255c53.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```androiddatabinding
线程在执行的时候,首先会从主存中读取变量值,
再Load到工作内存中的副本中,然后再传给处理器执行,
执行完毕后再给工作内存中的副本赋值,
随后工作内存再把值传回主存,主存的值才更新.
```

使用工作内存和主存,虽然加快速度,但是也存在一定问题.

如下:
```androiddatabinding
i =i + 1;
假设i的初始值为0,当只有一个线程执行它时,结果肯定为1.

当2个线程执行它时,可能不会得到2.

如下:

线程1: load i from 主存//i = 0;

i+1 // i =1

线程2: load i from 主存 //因为线程1还没将i的值写回主存

i = i+1;//i=1

线程1:save i to主存
线程2:save i to主存
```

如果两个线程按照上面的执行流程,那么i最后的值是1.

如果最后的写回生效的慢,读取i的值,都可能是0,这就是**缓存不一致的问题**.

## JMM如何解决缓存不一致的问题

JMM在并发过程中主要是通过处理原子性,可见性,有序性来解决缓存不一致的问题.

### 1.原子性(Atomicity)

java中,对基本数据类型的读取和赋值操作是原子性的操作,所谓原子性操作就是指这些操作时不可中断的,要么一定做完,要么就没有执行.

```androiddatabinding
i = 2;//第一步:读取i的值;赋值操作,是原子性操作;
j = i;//第二步:将值赋给j
i++;//与下面的i = i +1 是等效的.读取i的值,自增1,再写回主存.
i =i +1;
```
JMM只实现了基本的原子性,像上面i++的操作,必须借助synchronized和Lock来保障整块代码的原子性了.

线程在释放锁之前,必然会把i的值刷回主存的.

### 可见性(Visibility)

java就是利用volatile来提供可见性的.

**当一个变量被volatile修饰时候,那么对它的修改会立即刷新到主存,当其他的线程需要读取该变量的时候,会去内存读取新值.而普通变量不能保证这一点**

通过Synchronized和Lock都能保证这一点,线程在释放锁之前,会把共享变量都刷新到主存,但是二者的开销比较大.

### 有序性(Ordering)

java是允许编辑器和处理器对指令重排序的,但是规定了as-if-serial语义,即不管怎么重排序,程序的执行结果不能变,

如下:

```androiddatabinding
double pi =3.14; //A
double r =1;//
double s =pi * r * r//C
```
上面的语句,可以按照A->B->C执行,结果为3.14.

也可以按照B->A->C的顺序执行,A,B是独立的语句,而C依赖A,B,
所以A,B可以重排序,但是C不能排在A,B的前面.

**JMM保证了重排序不会影响到单线程的执行,但是在多线程这种会出现问题.**

```androiddatabinding
int a = 0;
boolean flag =false;

public void write(){
    a =2; //1步
    flag =true; //2步
}

public void multiply(){
    if(flag){ //3步
        int ret = a * a; //4步
    }
}
```
现在有2个线程,线程1执行write,随后线程2执再执行multiply,最后的结果不一定是4.

![image.png](https://upload-images.jianshu.io/upload_images/7505161-fa27c0e2b5eb4bcd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

write方法里面的1步和2步做了重排序,线程1先对flag赋值为true,
随后执行到线程2,ret直接计算出结果,再到线程1,这时候a才赋值为2,很显然迟了一步.

此时就可以为flag加上volatile关键字,禁止指令重排,可以确保程序的有序性.

也可以上重量级的synchronized和Lock来保证有序性,他们能保证那一块区域里的代码都是一次性执行完的.

## JMM的先天有序性

不需要任何手段就能保证有序性,称为happen-before原则.

如下:

```androiddatabinding
1.程序顺序规则:一个线程中的每个操作,happens-before于该线程中的后续操作.

2.监视器锁规则:对一个线程的解锁,happens-before于随后对这个线程的加锁.

3.volatile变量规则:对一个volatile域的写,happens-before于后续对这个volatile域的读.

4.传递性:如果A happens-before B 且 B happens-before C,那么A happens-before C

5.start()规则:如果线程A执行ThreadB.join()并成功返回,那么线程B中的任意操作happens-Before于线程A从ThreadB.join()操作成功返回.

7.interrupt()原则:对线程interrupt()方法的调用先行发生于被中断线程代码检测到中断事件的发生,可以通过Thread.interrupt()方法检测是否有终端发生.

8.finalize()原则:一个对象的初始化完成先行发生于它的finalize()方法的开始.

```

第一条:JMM保证单线程中的指令重排最后执行结果的正确性,但是多线程中不能保证.

第二条: 加锁之前,确保之前这个锁已经被释放了,才能继续加锁.

第三条: 对于Volatile关键字,如果一个线程去写一个变量,另外一个线程再去读,那么写操作一定在读操作之前.


## Volatile关键字是如何满足并发编程的三大特性的.

happens-before中有一条:

对一个volatile域的写,happens-before于后续对这个volatile域的读.

```androiddatabinding
就是说,一个变量声明式volatile的,那么当我读取变量时,总是能读到它的新值,

新值不管其他哪个线程对该变量做了写操作,都会立即更新到主存当中.我也能从主存中读到哪个刚写入的值.

所以volatile保证了可见性和有序性.
```

实例如下:
```
int a = 0;

boolean flag = false;

public void write(){
    a = 2;//1
    flag = true;//2
}

publci void multiply(){
    if(flag{//3
        int ret = a * a;//4 
    }
}


这段代码不仅受到重排序的困扰.
即使1,2没有重排序.3也不会顺利的执行.
线程1先执行write()操作,线程2再执行multiply()操作.
由于线程1是在工作内存把flag赋值给true,不一定立刻写回主存,
所以线程2执行时,multiply再从主存读flag值,仍然可能为false,那么括号里面的语句将不会执行.
```
**修改后**

```androiddatabinding
int a = 0;

boolean volatile flag = false;

public void write(){
    a = 2;//1
    flag = true;//2
}

publci void multiply(){
    if(flag{//3
        int ret = a * a;//4 
    }
}
```
```androiddatabinding
volatile关键字能保证线程1先执行写,在执行读操作.

根据happens-before原则:

这个过程会满足一下三个规则:

1.程序顺序执行规则:1 happens-before 2,2 happens-before 3,3 happens-before 4.
volatile限制了指令重排,所以1先于2执行.
2.volatile规则:2 happens-before 3,也就是写发生于读之前
3.传递性规则: 1 happens-before 4
```

从内存语义来说:

```androiddatabinding
当写一个volatile变量时,JMM会把该线程对应的本地内存中的共享变量刷新到主存当中.

当读取一个volatile变量的时候,JMM会把该线程对应的本地内存置为无效,线程接下来将从主内存中读取共享变量.

```

##Volatile不能保证原子性

对单个volatile变量的读/写具有原子性.但是对于类似volatile++这样的符合操作就无能为力.

```androiddatabinding
要想保证原子性,只能借助Synchronized和Lock下面的atomic的原子操作类.
```

## volatile的底层实现机制

如果把加入volatile关键字的代码和未加入volatile关键字的代码都生成汇编代码,会发现加入volatile关键字的代码会多出一个lock前缀指令.

lock前缀指令就是一个内存屏障,内存屏障有一下的功能:

1.重排序时补鞥呢把后面的指令重排序到内存屏障之前的位置.

2.使得本CPU的缓存写入内存

3.写入动作也会引起别的CPU或者别的内核无效化其缓存,相当于让新写入的值对别的线程可见.

## volatile的使用例子

1.状态量标记.

```androiddatabinding
int a = 0;

boolean volatile flag = false;

public void write(){
    a = 2;//1
    flag = true;//2
}

publci void multiply(){
    if(flag{//3
        int ret = a * a;//4 
    }
}
```

标记为volatile可以保证修改对线程立即可见.比synchronized和lock有一定的效率提升.

2.双重锁的单例模式
```androiddatabinding

public
```

使用时才创建对象,而且为了避免初始化操作的指令重排序,给instance加上了volatile.