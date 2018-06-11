console.log(a);//undefined
var a =100;
//1.把a拿出来,此时 a = undefined,占位.
//2.把100赋给a.


fn("xiaoming");//xiaoming 20
//函数声明,先把整个函数拿出来.
function fn(name) {
    age = 20;
    console.log(name,age);
    console.log(this);
    console.log(arguments);
    var age;
}

// 在整个script执行之前,已经把fn这个函数拿出来了.
//然后进入fn函数之中,把变量声明提前.也就是把var age;提前

//fn执行的时候,this要提前拿出来,arguments这个参数也就是name也要提前拿出来.