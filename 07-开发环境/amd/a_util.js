//来到具体业务当中.此时的业务是a业务.

// a_util.js依赖于util.js,所以此时还需要传入参数
define(['./util.js'], function (util) {
    var aUtil = {
        aGetFormatDate: function (date) {
            return util.getFormatDate(2,date);
        }
    }
    return aUtil;
});

//我们优先使用的内容
define(function () {
    return{
        printName: function () {
            console.log("xiaoming");
        }
    }
})