//动物
function Anima() {
    this.eat = function () {
        console.log("animal eat");
    }
}
//狗
function Dog() {
    this.bark = function () {
        console.log("dog bark");
    }
}
Dog.prototype = new Anima();
// Dog构造函数的显式原型 = 对象(具有eat属性).
//此时Dog就具有了eat属性.

var hashiqi = new Dog();
hashiqi.eat();
hashiqi.bark();


//封装一个DOM查询的例子

//根据id去查询当前元素
function Elem(id) {
    this.elem = document.getElementById(id)
}
