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

function F1() {
    var b = 200;
    function F2() {
        var c = 300;
        console.log("a: " + a);//a是自由变量,F2找不到a变量,就去它的父级作用域F1()中去寻找.
        console.log("b: " + b);//b是自由变量
        console.log("c:" + c);//300
    }
    F2()
}
F1();