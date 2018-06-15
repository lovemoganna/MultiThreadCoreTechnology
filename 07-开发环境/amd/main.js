//mainjs是一个入口函数
require(['./a.js'],function (a) {
    var date =new Date();
    a.printDate(date);
});