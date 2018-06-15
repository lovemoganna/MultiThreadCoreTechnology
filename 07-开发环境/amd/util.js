define(function () {
    //1.直接返回也行
    // return {
    //     getFormatDate: function (type, date) {
    //         if (type == 1) {
    //             return "2018-6-14";
    //         }
    //         if (type == 2) {
    //             return "2018年6月14日";
    //         }
    //     }
    // }
    //2.先定义一个对象,再返回也行.

    var util = {
        getFormatDate: function (type, date) {
            if (type == 1) {
                return "2018-6-14";
            }
            if (type == 2) {
                return "2018年6月14日";
            }
        }
    }
    return util;
});

//本质上就是定义一个define函数,函数内部就是返回一个对象.