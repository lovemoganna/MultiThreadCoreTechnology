## JSWEBAPI

### JS基础
```text
变量类型和计算
原型和原型链
闭包和作用域
异步和单线程
日期,Math,各种常用API
```

之前的内置函数:Object,number,null,undefined,string,boolean

内置对象:Math JSON

### JSWEBAPI

JS基础知识是基于 ECMA 262标准来的

JS-WEB-API是基于W3C标准来搞的.

其中JS-WEB-API包括以下:

```text
获取元素document.getElementById(id),浏览器需要:

1.定义一个document全局变量,对象类型

2.给它定义一个getElementById的属性,属性值是一个函数.


W3C标准没有规定任何JS基础相关的东西.

不管什么边浪类型,原型,作用域和异步.

只管定义与浏览器中JS操作页面的API和全局变量.
```

### 问题

1.JS内置的全局函数和对象有哪些?

像Object,Array,Boolean,String,Math,Json等.还设有window,document.

还有所有未定义的全局变量: navigator.userAgent(返回的是一个字符串)

### 总结

常说的JS(也就是浏览器执行的JS)包含两个部分:

JS基础(ECMA262标准)+JS-web-API(W3C标准)

## DOM

文档对象模型

### 题目

DOM是哪种基本的数据结构?

DOM操作的常用API有哪些?

DOM节点的attr和property有什么区别?

### 知识点

DOM本质

DOM节点操作

DOM结构操作

#### DOM本质

xml.

![](https://upload-images.jianshu.io/upload_images/7505161-6e1ebb5cebe2a51e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

html

![](https://upload-images.jianshu.io/upload_images/7505161-c51956b6ffc89c68.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


我的理解:将HTML结构化,变成浏览器,JS可识别的东西,就是DOM的本质.

他们的理解: 浏览器把拿到的HTML代码,结构化一个浏览器能识别并且js可操作的一个模型.


#### DOM节点操作
```text
1.获取DOM节点.

2.property

3.Attribute

```
![](https://upload-images.jianshu.io/upload_images/7505161-fc33262b16a75dee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![](https://upload-images.jianshu.io/upload_images/7505161-3b6e280e03f885f3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/7505161-7cef2c300f1be19b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#### DOM结构操作

DOM树

```text
1.新增节点
2.获取父节点
3.获取子节点
4.删除节点

遍历节点.

递归:自循环过程.
```
![](https://upload-images.jianshu.io/upload_images/7505161-704afed3bf4981ce.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 问题解答

1.DOM是哪种基本的数据结构?
```text
树.
```

2.DOM操作的常用API有哪些?
```text
获取DOM节点,以及节点的property和Attribute
获取父节点,获取子节点
新增节点,删除节点
```

DOM节点的attr和property有什么区别?
```text
property知识一个JS对象的属性的修改

Attribute是对HTML标签属性的的修改
```
### BOM操作

浏览器对象模型

#### 题目

1.如何检测浏览器的类型.

IOS,安卓系统(浏览器不一样).系统版本.

2.拆解url的各部分.

url每部分代表一个信息

#### 知识点

navigator(浏览器)

screen(屏幕)

location(地址栏)

history(历史:前进,后退)


![](https://upload-images.jianshu.io/upload_images/7505161-722fd8777250966f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 代码展示

```
//获取浏览器的型号
navigator.userAgent

"Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1"

//获取ID
location.hash
"#id=100"

//页面跳转

location.href ='https://www.baidu.com'
```

![](https://upload-images.jianshu.io/upload_images/7505161-a5f5e86a1e742df0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

