//值类型
var a = 100;
var b = a;
a = 200;
console.log(b);//100
var c = b;
b = 300;
console.log(c);
// 值类型改变新开辟一块内存空间.值类型赋值不会相互干预.
//引用类型

var a = {age: 20};
var b = a;
b.age = 200;
console.log(a.age);//200
var c = b;
c.age = 25;
console.log(c);
console.log(b);
//先复制一个指针,但是2个指针指向的是同一个内存空间.也就是说引用类型共用一块内存空间.


var arr = [1, 2, 3, 4, {age: 22, name: "王小明"}];
console.log(arr.length);//5

