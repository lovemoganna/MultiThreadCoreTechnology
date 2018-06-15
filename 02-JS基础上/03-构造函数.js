// 构造函数名称首字母一定要大写
function Foo(name, age) {
    this.name = name;
    this.age = age;
    this.class = 'class-1';
    //return this;//默认是有这一行的.
}

var p = new Foo("小明", 12);
// new函数执行的时候,this先变为一个空对象,然后进行this.xxx赋值,最终默认把this再return回来.此时p就具备了p.name p.age的特性.
var p2 = new Foo("小红", 13);
console.log(p);
console.log(p2);

