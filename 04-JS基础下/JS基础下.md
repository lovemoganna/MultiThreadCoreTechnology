## JS三大山
```text
1.原型和原型链
2.闭包和作用域
3.异步和单线程
```
## 异步

### 题目

1.同步和异步的区别是什么?分别举一个同步和异步的例子.

2.一个关于setTimeout的笔试题.

3.前端使用异步的场景有哪些?

## 知识点

### 什么是异步(对比同步).

![](https://upload-images.jianshu.io/upload_images/7505161-5468439f66c54b59.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### 前端使用异步的场景.

```text
1.在可能发生等待的情况.
2.等待过程中不能像alert一样阻塞程序运行.
3.也就是说"等待的情况"需要异步.如下:


定时任务: setTimeout,setInverval

网络请求: ajax请求,动态<img>加载

事件绑定: 绑定一个单击事件,点击之后才会执行.(可以点击多次)

```
 ![](https://upload-images.jianshu.io/upload_images/7505161-63f1586de0b41700.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 异步和单线程

JS是异步,是因为它是单线程的行为.

```text
console.log(100);
//setTimeout是一个异步的场景.也就是会产生阻塞.
setTimeout(function(){
    console.log(200);
})
console.log(300);

结果:
100 
300
200

单线程就是一次只做一件事.

```

![](https://upload-images.jianshu.io/upload_images/7505161-e2d3bcdbef314890.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 问题

1.同步和异步的区别?举一个例子.
```text
最大的区别就是是否有阻塞代码.

同步会执行阻塞代码执行,异步不会.

alert是同步,setTimeout是异步.
```
2.一个关于setTimeout的试题.
我们已经知道了setTimeout是异步执行的了.所以SetTimeout的函数会先被暂存起来
```text
console.log(1);
setTimeout(function(){
    console.log(2);
},0)
console.log(3);

setTimeout(function(){
    console.log(4);
},1000)
console.log(5);

1 3 5 2 4

```
3.前端使用异步的场景有哪些?
```text
网络请求: ajax数据加载.动态<img>加载


事件绑定.

定时任务:setTimeout,setInterval(每隔几秒执行)
```

## 补充

### 题目

1.获取2018-06-14格式的日期.

2.获取随机数,要求是长度一致的字符串格式.

3.写一个能遍历对象和数组的通用forEach函数.

### 知识点

日期,Math,数组API,对象API

### 日期函数
![](https://upload-images.jianshu.io/upload_images/7505161-60a21ff801bdec57.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### Math

Math.random()//返回的是一个0-1之间的随机小数.

### 数组和对象

数组API

#### forEach 遍历所有的元素.

```text
var arr =[1,2,3]
arr.forEach(function(index,item){
    console.log(item,index)
})
```
#### every 判断所有元素是否都符合条件.
```text
var arr =[1,2,3]
arr.every(function(index,item){
	if(item < 4){
		return true;
	}
})
```
#### some 判断是否至少一个元素符合条件.
```text
var arr =[1,2,3]
arr.some(function(index,item){
	if(item < 2){
		return true;
	}
})
```
#### sort 排序.
```text
 var arr =[1,4,2,3,5]
 arr.sort(function(a,b){
    //从小到大排序
 	return a-b;
 })
 console.log(arr)
 
 
 VM1796:5 (5) [1, 2, 3, 4, 5]
```

#### map 对元素重新组装,生成新数组.

也就是将b标签包进数组元素里面去.
```text
var arr =[1,2,3,4]
arr.map(function(index,item){
	return '<b>' + index+ '</b>'
})

(4) ["<b>0</b>", "<b>1</b>", "<b>2</b>", "<b>3</b>"]
```

#### filter过滤符合条件的元素.
```text
var arr =[1,2,3,4]
arr.filter(function(index,item){
	if(item >= 2){
		return true
	}
})
(2) [3, 4]
```
#### json数据
```text
var obj ={
    x: 100,
    y: 200,
    z: 300
}
var key

for(key in obj){
    if(obj.hasOwnProperty(key){
        console.log(key,obj[key]);   
    }
}
```

### 题目解答请看html

```text
<!--目的是要获取 2018-06-06这样的数据类型-->
<script>

    //需要知道字符串拼接,并且注意几个点

    //月份需要+1,个位数字如果<10.前面补0.天数亦是如此.

    function formatDate(dt) {
        if (!dt) {
            dt = new Date();
        }
        var year = dt.getFullYear();
        var month = dt.getMonth() + 1;
        var date = dt.getDate();
        if (month < 10) {
            month = '0' + month;
        }
        if (date < 10) {
            date = '0' + date;
        }
        return year + '-' + month + '-' + date;
    }

    var date = new Date();
    var result = formatDate(date);
    console.log(result);
</script>

<!--获取随机数,要求长度一致的字符串格式-->
<script>
    function getRandom(randomMath) {
        //因为生成的默认随机数后面是有17位小数的.
        var randomChar = randomMath + '0000000000';//拼接字符串,如果随机数是0的情况,需要解决这个bug.
        var random = randomChar.slice(0, 10);//截取前10位
        return random;
    }

    var math = Math.random();
    getRandom(math);
</script>

<!--能遍历对象和数组-->
<script>
    function forEach(obj, fn) {
        var key;
        //obj是数组的情况
        if (obj instanceof Array) {
            obj.forEach(function (item, index) {
                fn(index, item)
            })
        } else {
            //obj是对象的情况
            for(key in obj){
                console.log(key,obj[key]);
            }
        }
    }
    
    var obj =[1,3,5,7]
    forEach(obj,function (index, item) {
        console.log(index,item);
    })

    var obj ={x:100,y:200}
    forEach(obj,function (key,value) {
        console.log(key,value);
    })

```

