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
function f1(name,age) {
    alert(name);
    console.log(this);//{x:100}就是this.
    this.age = age;
}
f1.call({x:100},'xiaoming',24);

//函数表达式中使用bind
var fn2=function(name,age) {
    alert(name);
    console.log(this);//{x:100}就是this.
}.bind({x:2});
fn2('KHan',20);
