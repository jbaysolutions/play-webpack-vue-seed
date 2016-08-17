'use strict';

var webpack = require('webpack'),
    jsPath  = 'frontend',
    path = require('path'),
    srcPath = path.join(__dirname, jsPath);

var vue = require('vue-loader')
var ExtractTextPlugin = require("extract-text-webpack-plugin")

var cssLoader = ExtractTextPlugin.extract("style-loader", "css-loader")

module.exports = {
    target: "web",
    entry: [
        path.join(srcPath, 'vue-app.js')
    ],
    resolve: {
        alias: {},
        root: srcPath,
        extensions: ['', '.js'],
        modulesDirectories: ['node_modules', jsPath]
    },
    output: {
        path:__dirname + "/public/javascripts/",
        publicPath: '/assets/javascripts/',
        filename: 'vue-app.js',
        // filename: '[name].js',
        pathInfo: false
    },

    module: {
        noParse: [],
        preLoaders: [
            /*{
                test: /\.vue$/,
                loader: 'eslint',
                // include: '/app/javascripts/',
                exclude: /node_modules/
            },
            {
                test: /\.js$/,
                loader: 'eslint',
                // include: '/app/javascripts/',
                exclude: /node_modules/
            }*/
        ],
        loaders: [
            {
                test: /\.vue$/,
                exclude: /node_modules/,
                loader: 'vue'
            },
            {
                test: /\.js$/,
                // excluding some local linked packages.
                // for normal use cases only node_modules is needed.
                exclude: /node_modules/,
                loader: 'babel'
            },
            {
                test: /\.css$/,
                loader: cssLoader
            }
        ]
    },
    eslint: {
        formatter: require('eslint-friendly-formatter')
    },
    babel: {
        presets: ['es2015'],
        plugins: ['transform-runtime']
    },
};


if (process.env.NODE_ENV === 'production') {
    module.exports.plugins = [
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false
            }
        }),
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.NoErrorsPlugin()
    ]
} else {
    module.exports.devtool = '#source-map'
}