'use strict';

var webpack = require('webpack'),
    jsPath  = 'frontend',
    path = require('path'),
    srcPath = path.join(__dirname, jsPath);

var vue = require('vue-loader')
var ExtractTextPlugin = require("extract-text-webpack-plugin")

var cssLoader = ExtractTextPlugin.extract({fallback:"style-loader", use:"css-loader"})

function resolve (dir) {
    return path.join(__dirname, '..', dir)
}


module.exports = {
    target: "web",
    context: __dirname,
    entry: './src/app.js',
    resolve: {
        modules: ['node_modules', jsPath]
    },
    output: {
        path:__dirname + "/../public/javascripts/",
        publicPath: '/assets/javascripts/',
        filename: 'app.js'
        // filename: '[name].js',
        //pathInfo: false
    },

    module: {
        rules: [
            {
                test: /\.vue$/,
                exclude: /node_modules/,
                loader: 'vue-loader'
            },
            {
                test: /\.js$/,
                // excluding some local linked packages.
                // for normal use cases only node_modules is needed.
                exclude: /node_modules/,
                loader: 'babel-loader'
            },
            {
                test: /\.css$/,
                loader: cssLoader
            },
            {
                test: /\.(js|vue)$/,
                loader: 'eslint-loader',
                enforce: 'pre',
                include: [resolve('src'), resolve('test')],
                options: {
                    formatter: require('eslint-friendly-formatter')
                }
            }
        ]
    },
    performance: {
        hints: false
    },
    devtool: '#source-map'
/*
    eslint: {
        formatter: require('eslint-friendly-formatter')
    },
*/
    /*babel: {
        presets: ['es2015'],
        plugins: ['transform-runtime']
    },*/
};


if (process.env.NODE_ENV === 'production') {
    module.exports.output.filename = module.exports.output.filename.replace(/\.js$/, '.min.js')

    module.exports.devtool = '#source-map'
    // http://vue-loader.vuejs.org/en/workflow/production.html
    module.exports.plugins = (module.exports.plugins || []).concat([
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            sourceMap: true,
            compress: {
                warnings: false
            }
        }),
        new webpack.LoaderOptionsPlugin({
            minimize: true
        })
    ])
}
/*
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
}*/