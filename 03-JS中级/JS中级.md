## 题目
1.变量提升的理解.

2.this的几种不同的使用场景.

3.创建10个<a>标签,点击的时候弹出来对应的序号.(闭包和作用域问题)

4.如何理解作用域.

5.实际开发中闭包的作用.
### 知识点
执行上下文

this

作用域

作用域链

闭包

## 函数声明和函数表达式
```text
函数声明:
    function F(){}

函数表达式:
    var f2 = function (){};
```

## 执行上下文的理解

```text
执行上下文

范围:针对一段<script>或者一个函数之内.都会生成执行上下文.

全局:针对一段<script>会生成全域的执行权.也就是在执行之前,先去把变量定义,函数声明拿出来.再去执行函数.

函数:在函数执行之前,先把变量定义,函数声明,this,arguments拿出来,再去执行函数.

```
示例:
```text
console.log(a);//undefined
var a =100;
//1.把a拿出来,此时 a = undefined,占位.
//2.把100赋给a.


Foo("xiaoming");//xiaoming 20
//函数声明,先把整个函数拿出来.
function Foo(name) {
   //在代码执行之前,this就已经确定值了.
       console.log(this);
       console.log(arguments);//参数的集合体
       age =20;
       //程序执行之前对age这个参数进行占位.
       console.log(name,age);
       
       //这是在函数的环境变量中.
       bar(100);
       function bar(num){
           console.log("你没有"+num+"个bar")
       };
       var age;
}

// 在整个script执行之前,已经把fn这个函数拿出来了.
//然后进入Foo函数之中,把变量声明提前.也就是把var age;提前

//fn执行的时候,this要提前拿出来,arguments这个参数也就是name也要提前拿出来.
```

![](https://upload-images.jianshu.io/upload_images/7505161-dbd93ee701452f2d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## this

this 要在执行时才能确认值,定义时无法确认.

![](https://upload-images.jianshu.io/upload_images/7505161-c41835bde6c27357.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### this使用的场景
```text
作为构造函数执行
作为对象属性执行
作为普通函数执行 --- window
call apply bind 
```

### 代码演示
```text
//1.构造函数中的this
function Foo(name) {
    //this = {};
    this.name = name;
    //return this;
}

var f = new Foo("zhangsan");

//2.作为一个对象属性来执行.
var obj = {
    name: 'A',
    printName: function (name) {
        console.log(this.name);
    }
};
console.log(obj.name);//A
obj.printName();//A
//此时this就是obj.

//3.最为一个函数来执行
function fn(){
    console.log(this);
}
fn();
//Window {postMessage: ƒ, blur: ƒ, focus: ƒ, close: ƒ, frames: Window, …}

//4.函数中使用call来改变this的值.
function Foo(name,age) {
    alert(name);
    console.log(this);//{x:100}就是this.
    this.age = age;
}
Foo.call({x:100},'xiaoming',24);

//函数表达式中使用bind
var fn2=function(name,age) {
    alert(name);
    console.log(this);//{x:100}就是this.
}.bind({x:2});
fn2('KHan',20);

```
## 作用域

JS没有块级作用域.只有函数和全局作用域.

```text
//无块级作用域
//尽量不要写在块找那个,不好读.

//块级作用域
if (true) {
    var name = 'zhangsan';//在大括号里面对变量没有约束力.
}
console.log(name);


//函数和全局作用域
var a = 100;

function f() {
    var a = 200;
    console.log('f', a);//定义在函数内部的值,并不会对外部的变量造成影响.从外面无法更改.
}

console.log('global', a);//global 100
f();//f 200
```
### 作用域链
```text
var a = 100;//a存在于全局作用域.
function f() {
    var b = 200;
    //当前作用域没有定义的变量.也就是一个"自由变量".
    //他就会从全局变量中获取值.
    console.log(a);
    console.log(b);
}

f();
var a = 100;
var b =50;
function F1() {
    var b = 200;
    function F2() {
        var c = 300;
        console.log("a: " + a);//a是自由变量,只能去全局变量中寻找
        console.log("b: " + b);//b是F2中的自由变量,只能去F2中去寻找,注意他遵循的是就近原则.
        console.log("c:" + c);//300
    }
    F2()
}
F1();
```

## 闭包

```text
function F() {
    var a = 100;
    //返回一个函数(让函数作为返回值)
    return function () {
        console.log("a: "+a);//它只能从它的父级作用域去寻找a的值.
    }
}
// 要明白F()最终返回的是一个函数.

var f  =F();//f是一个函数.
var a =666;
f();
```
### 闭包的使用场景
1.函数为作为返回值.(上面的例子)

2.函数作为参数传递.

```text
function F2() {
    var a = 100;
    //返回一个函数(让函数作为返回值)
    return function () {
        console.log("a: "+a);
    }
}

var f2 =F2();
var a =777;

//将函数当做参数传入
function F3(fn) {
    var a =888;
    console.log("F3中的a: "+a);//888
    fn();//100
}
F3(f2);
```

## 题目解答

### 变量提升的理解.

变量声明和函数表达式的区别.
```text
function F(){}; 

var f = f(){};

执行上下文:

在script中或者在构造函数中,变量的声明和定义以及构造函数的声明都会提前放在前面,这样就会造成变量提升.


```
### this的几种不同的使用场景.

1.this使用在函数当中执行.
2.this在普通函数中执行
3.this在对象属性中执行.
4.call apply bind

### 创建10个<a>标签,点击的时候弹出来对应的序号.(闭包和作用域问题)
```text
 var i;
    for (i = 0; i < 10; i++) {
        //我们需要将 i 的每个值传进来.从而声明10个函数.
        
        //函数的自执行.相当于list(index),此时相当于构造了一个局部函数.
        (function (i) {
            var a = document.createElement("a");
            a.innerHTML = i + "<br>";   // <a>i </>
            a.addEventListener("click", function (e) {
                e.preventDefault();//通知 Web 浏览器不要执行与事件关联的默认动作,比如阻止提交表单.
                alert(i); // 如果不加 一层funciton,那么i就是一个自由变量, i = 10 ,始终打印的就是10.所以这样就会出错.
            });
            // 往body里面插入子元素a
            document.body.appendChild(a);
        })(i);
    }
```

### 如何理解作用域.

自由变量.

作用域链,即自由变量的查找.

闭包的两个场景: 函数作为返回值. 函数作为参数去传递.

### 实际开发中闭包的作用.

主要用在封装变量,收敛权限.

好处: 把数源封装起来,其他函数拿不到这个数.
```text
function isFirstLoad(){
    var _list =[]; //将变量封装起来.
    return function(id){
    //返回某个指定的字符串值在字符串中首次出现的位置
        if(_list.indexOf(id) >= 0){
            return false;
        }else{
            _list.push(id);//证明它已经获取了权限.
            return true;
        }
    }
}
var use= isFirstLoad();
use(10);//true
use(10);//false

```
