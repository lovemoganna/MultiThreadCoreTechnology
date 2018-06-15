var path = require('path')
var webpack = require('webpack')

module.exports = {
    // 定义src目录
    context: path.resolve(__dirname, './src'),
    entry: {
        //定义入口文件
        app: './app.js'
    },
    output: {
        //定义输出目录
        path: path.resolve(__dirname, './dist'),
        //定义输出文件
        filename: 'bundle.js'
    }
}