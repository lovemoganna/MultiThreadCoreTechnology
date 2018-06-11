//定义一个函数Elem.并定义一个通过id来获得元素的方法.
function Elem(id){
    this.elem = document.getElementById(id);
}
    // 给此构造函数添加一个替换HTML的方法
    Elem.prototype.html = function (val) {
    //先获得此元素
        var elem = this.elem;
        if (val){
            //插入新的HTML
            elem.innerHTML = val;
            //链式操作的关键.
            return this;
        }
        else {
            //返回原来的HTML内容.
            return elem.innerHTML;
        }
    };

    //再给元素扩展一个事件绑定的方法(事件类型,回调函数)
    Elem.prototype.on =function (type,fn) {
        //获得此元素
        var elem = this.elem;
        //绑定事件
        elem.addEventListener(type,fn);
        return this;//链式操作的关键在于此.
    };

    //获取此节点的所有HTML
    var div1 = new Elem("Main-Article-QQ");
    console.log(div1.html());

    //替换文本!
    // div1.html("<p>hello world</p>");
    // div1.on("keyup",function () {
    //     alert("_keyup!")
    // })
    //链式操作
    div1.html("<a href='123'>小明</a>").on("click",function () {
        console.log("xiaoming").html("<p>小红</p>");
    });