## HashMap的几个字段

```androiddatabinding
int threshold;             // 所能容纳的key-value对极限 
final float loadFactor;    // 负载因子
int modCount;  
int size;
```

Node[] table的初始长度是length=16

Load Factor---负载因子 =0.75

threshold是HashMap所能容纳的最大数据量Node(键值对)的个数.

threshold = length * Load.factor.

也就是说,在数组定义好长度之后，负载因子越大，所能容纳的键值对个数越多。

threshold就是在此负载因子和数组长度对应下允许的最大元素数目，

超过这个数目就重新resize(扩容)，扩容后的HashMap容量是之前容量的两倍。
```androiddatabinding
默认的负载因子0.75是对空间和时间效率的一个平衡选择，
建议大家不要修改，除非在时间和空间比较特殊的情况下，
如果内存空间很多而又对时间效率要求很高，
可以降低负载因子Load factor的值；
相反，如果内存空间紧张而对时间效率要求不高，可以增加负载因子loadFactor的值，这个值可以大于1。

size是HashMap中实际存在的键值对数量。

table的长度length、容纳最大键值对数量threshold。

modCount字段主要用来记录HashMap内部结构发生变化的次数,主要用于迭代的快速失败。

内部结构发生变化指的是结构发生变化，例如put新键值对，

但是某个key对应的value值被覆盖不属于结构变化。

HashMap中，哈希桶数组table的长度length大小必须为2的n次方(一定是合数)

常规的设计是把桶的大小设计为素数。相对来说素数导致冲突的概率要小于合数.


具体证明:

设有一个哈希函数
H( c ) = c % N;
当N取一个合数时，最简单的例子是取2^n，比如说取2^3=8,这时候
H( 11100(二进制） ) = H( 28 ) = 4
H( 10100(二进制) ) = H( 20 ）= 4


这时候c的二进制第4位（从右向左数）就”失效”了，也就是说，无论第c的4位取什么值，
都会导致H( c )的值一样．这时候c的第四位就根本不参与H( c )的运算，这样H( c )就无法完整地反映c的特性，增大了导致冲突的几率．


取其他合数时，都会不同程度的导致c的某些位”失效”，从而在一些常见应用中导致冲突．
但是取质数，基本可以保证c的每一位都参与H( c )的运算，从而在常见应用中减小冲突几率．


HashMap采用这种非常规设计，主要是为了在取模和扩容时做优化，
同时为了减少冲突，HashMap定位哈希桶索引位置时，也加入了高位参与运算的过程。

这里存在一个问题，即使负载因子和Hash算法设计的再合理，
也免不了会出现拉链过长的情况，一旦出现拉链过长，则会严重影响HashMap的性能。


在JDK1.8版本中，对数据结构做了进一步的优化，引入了红黑树。而当链表长度太长（默认超过8）时，
链表就转换为红黑树，

利用红黑树快速增删改查的特点提高HashMap的性能，其中会用到红黑树的插入、删除、查找等算法。
```

## HashMap的功能实现

### 确定哈希桶数组索引位置

不管增加,删除,查找键值对,定位到哈希桶数组的位置都是很关键的第一步.

```androiddatabinding
前面说过HashMap的数据结构是数组和链表的结合，
所以我们当然希望这个HashMap里面的元素位置尽量分布均匀些，
尽量使得每个位置上的元素数量只有一个，

那么当我们用hash算法求得这个位置的时候，
马上就可以知道对应位置的元素就是我们要的，不用遍历链表，大大优化了查询的效率。
```
源码的实现方法:
````
方法一：
static final int hash(Object key) {   //jdk1.8 & jdk1.7
     int h;
     // h = key.hashCode() 为第一步 取hashCode值
     // h ^ (h >>> 16)  为第二步 高位参与运算
     return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
方法二：
static int indexFor(int h, int length) {  //jdk1.7的源码，jdk1.8没有这个方法，但是实现原理一样的
     return h & (length-1);  //第三步 取模运算
}
````
这里的Hash算法本质上就是三步：取key的hashCode值、高位运算、取模运算。
````

只要它的hashCode()返回值相同，那么程序调用方法一所计算得到的Hash码值总是相同的。

我们首先想到的办法是把hash值对数组长度取模运算.这样一来，元素的分布相对来说是比较均匀的.

模运算的消耗还是比较大的，

在HashMap中是这样做的：调用方法二来计算该对象应该保存在table数组的哪个索引处。

这个方法非常巧妙，它通过h & (table.length -1)来得到该对象的保存位，

而HashMap底层数组的长度总是2的n次方，这是HashMap在速度上的优化。

当length总是2的n次方时，h& (length-1)运算等价于对length取模，也就是h%length，但是&比%具有更高的效率。
````

过程是: key调用hashcode()方法计算出hash值,然后通过高位运算,计算出下标.就能找到数组的索引位置了.

## HashMap的put方法.

```androiddatabinding
1.判断键值对数组table[i]是否为空或者null,否则执行resize()扩容.

null一般是指对象为空，即obj = null;
空值一般是指对象的参数值为空字符串，例obj.value='';

2.
```
