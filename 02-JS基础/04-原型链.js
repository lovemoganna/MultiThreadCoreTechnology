//构造函数
function Foo(name, age) {
    this.name  =name;
}

//每个构造函数都有显式原型,显式原型是一个__proto__对象.
// 那么Foo.prototype也就是指向了Foo的显式原型,显式原型是一个__proto__对象.完全可以对这个对象进行扩展属性alterName.

Foo.prototype.alterName = function () {
    console.log(this.name);
};

var f = new Foo("zhangsan");
f.printName = function () {
    console.log(this.name);
}
// f是一个对象,现在f只有一个name属性.
f.printName();//zhangsan

// f的构造函数是Foo函数,也就是f的隐式原型指向Foo的显式原型.
//
// f本身并没有alterName的属性.只有printName的属性.
//     但是他会去f的隐式原型目录中去寻找.f的隐式原型执行的就是它的构造函数Foo的显式原型.
//     Foo的显示原型已经被扩展了一个属性.所以会执行到了Foo.prototype.alterName.
f.alterName();//张三

var item;
for(item in f){
    //我们只希望拿到f本身的属性
    if(f.hasOwnProperty(item)){
        console.log("属性有:"+item+"\n");
    }
}
f.toString();//"[object Object]"

//当对象没有这个属性的时候,就会去自身的隐式原型中去寻找,也就是指向它的构造函数的显式原型.
//它的构造函数Foo中并没有toString这个东西.
//但是f.__proto__是他的隐式原型,也就是指向构造函数Foo的显式原型Foo.prototype.
//也就指向他Foo.prototype的隐式原型__proto__.也就是去Object的显式原型中去找toString.寻找的结果为 null

// 也就是f._proto__.__proto__去寻找.
//     Foo的显式原型,也是一个对象(f._proto__),然后再去他的隐式原型中去寻找.
//     也就相当于去他的构造函数Object的prototype去寻找toString.
