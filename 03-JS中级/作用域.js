//无块级作用域
//尽量不要写在块找那个,不好读.
if (true) {
    var name = 'zhangsan';
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