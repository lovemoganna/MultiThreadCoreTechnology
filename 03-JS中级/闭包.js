function F() {
    var a = 100;
    //返回一个函数(让函数作为返回值)
    return function () {
        console.log("a: " + a);
    }
}

// 要明白F()最终返回的是一个函数.

var f = F();
var a = 666;
f();

function F2() {
    var a = 100;
    //返回一个函数(让函数作为返回值)
    return function () {
        console.log("a: " + a);
    }
}

var f2 = F2();
var a = 777;

//将函数当做参数传入
function F3(fn) {
    var a = 888;
    console.log("F3中的a: " + a);//888
    fn();//100
}

F3(f2);


