// var i;
// for (i = 0; i < 10; i++) {
//     //定义一个自执行的函数.
//     (function f() {
//         //寻找父级作用域
//     })(i);
// }
//
// for (i = 0; i < 10; i++) {
//     (function (i) {
//         var a = document.createElement('a');
//         a.innerHTML = i + '<br>';
//         a.addEventListener("click", function (e) {
//             e.preventDefault();
//             alert(i); // i 是一个自由变量,要去父级作用域去获取值.
//         });
//         document.body.appendChild(a);
//     })(i);
// }

function isFirstLoad() {
    var _list = [];
    return function (id) {
        if (_list.indexOf() >= 0) {
            return false;
        } else {
            _list.push(id);
            return true;
        }
    }
}

var firstLoad = isFirstLoad();
firstLoad(12);