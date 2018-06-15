## 题目

1.编写一个通用的事件监听函数.

2.描述事件冒泡流程.

3.对于以下无限下拉加载图片的页面,如何给每个图片绑定事件.

### 知识点

1.通用事件绑定.

![](https://upload-images.jianshu.io/upload_images/7505161-ccb97c5d0f7baf17.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2.事件冒泡.
```text
DOM树形结构
事件冒泡(一层一层的触发事件)
阻止冒泡(stopPropogation)
冒泡的应用(代理)
```
3.代理.

使用代理的优点:
```text
1.不用绑定多次.
2.给浏览器的压力比较小.内存和渲染都比较小.
```

## 通用的事件绑定


![](https://upload-images.jianshu.io/upload_images/7505161-53f7ae57cf1d17f4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们尽量自己定义的通用的函数.

```text
//elem-哪个个元素.
//type-绑定什么事件.
//fn,调用哪个回调函数.
function bindEvent(elem,type,fn){
    elem.addEventListener(type,fn)
}

var a =document.getElementById('link1');

bindEvent(a,'click',function(e){
    //因为a是一个链接,所以我们要清除它本身的行为.
    e.preventDefault();//阻止默认行为(先不要做自己的事情.)
    alert('clicke');
})

```

## 关于IE低版本的兼容性

IE低版本使用attachEvent绑定事件,和W3C的标准不一样.

IE低版本使用量已经很少了,很多网站都早已经不支持了.

IE低版本的兼容性.

## 事件冒泡

见html.

## ajax

### 题目

1.手动编写一个ajax,不依赖第三方库.

2.跨域的集中实现方式.
```text
imooc.com   m.imooc.com   coding.imooc.com
```

### 知识点

XMLHTTPRequest

状态码说明

跨域

![](https://upload-images.jianshu.io/upload_images/7505161-7be05ab5489bb52f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## IE兼容性

IE低版本使用ActiveXObject,和W3C标准不一样.

## readyState
```text
0-未初始化,还没有调用send()方法.
1-载入,已经调用send()方法们正在发送请求.
2-载入完成,send()方法执行完成,已经接受到全部响应内容.
3-交互,正在解析响应内容.
4-完成,响应内容解析完成,可以在客户端调用了.
```

## 状态码(status)
```text
2xx-表示成功处理请求,如200.
3xx-需要重定向,浏览器直接跳转.
4xx-客户端请求错误,如404.
5xx-服务端错误.
```

## ajax跨域问题

解决方法:
```text
JSONP
服务器端设置http header
```
### 什么是跨域

浏览器具有同源策略,不允许ajax访问其他域接口.

我的网站,要访问慕课网的接口.不允许出现这种情况的.

跨域条件:协议,域名.端口,有一个不同就算跨域.

### 可以跨域的三个标签

但是有三个标签允许跨域加载资源

```text
<img src=xxx> //防盗链的处理就不可以了.
<link href=xxx> //加载CSS资源
<script src="xxx">//CDN
```

### 三个标签的场景
```text
<img>用于打点统计,统计网站可能是其他域.
<link><script>可以使用CDN,CDN的也是其他域.
<script>可以用于JSONP.
``` 
### 跨域注意事项
所有的跨域请求都必须经过信息提供方允许.

如果未经过许可就获取,那是浏览器同源策略出现漏洞.

### JSONP实现原理

可以加载一个https://www.baidu.com/a.html

不一定服务器端真正有一个classindex.html文件.

服务器可以根据请求,动态的生成一个文件,返回.

同理于<script src="https://www.baidu.com/a.js">

比如:

```text
你的网站需要跨域访问慕课网的一个接口.

慕课网给你一个地址https://www.baidu.com/a.js

返回内容格式 如: callback{{"x":2,"y",2}}   (数据是可以动态生成的.)
```

![](https://upload-images.jianshu.io/upload_images/7505161-31e342470efd1f3b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 服务端设置http header

需要服务端去做,这是一个解决跨域问题的一个趋势.

![](https://upload-images.jianshu.io/upload_images/7505161-9cc0735d85e6075e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 存储

### 题目

1.描述Cookie,sessionStorage和localStorage的区别?

### cookie

本身用于客户端和服务器端通信.

但是它有本地存储的功能,所以被借用了.

使用document.cookie=...获取和修改即可.

![](https://upload-images.jianshu.io/upload_images/7505161-ce635f32fc163c39.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### cookie的缺点

存储量太小,只能是4kB.

所有的HTTP请求都带着,会影响获取资源的效率.

API简单,需要封装才能使用,也就是Element.cookie...(获取和修改),但是这仅仅是一个字符串.

### locationStorage和SessionStorage

HTML5专门为了存储而设计的,最大容量5M

API简单易用:

```text
//存储用户名,密码,性别
localStorage.setItem(key,value);

//获取
localStorage.getItem(key);
```
5M的量,已经很大了.

SessionStorage和LocalStorage都是可以在本地存储在浏览器的.

SessionStorage如果浏览器关闭了,就会自动清理.

LocalStorage,存储在本地了,只要不是人为的清理,就会一直存在.


我们可以直观的在浏览器中看到.

![](https://upload-images.jianshu.io/upload_images/7505161-bb86b0d3b3d0e1f9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 注意事项
```text
IOS隐藏模式下
localStorage.getItem会报错.
廷议使用try-catch封装起来

如果在try块里有异常被抛出时执行的语句。
用于保存关联catch子句的异常对象的标识符。
在try语句块之后执行的语句块。无论是否有异常抛出或捕获这些语句都将执行。

```

#### 三者的区别

容量

是否会携带到ajax中.(cookie每次都会携带),Sessionstorage和LocalStorage是存储在本地的.

API的易用性,cookie需要自己封装一下剩下的就是setItem(key,value);就可以了.


