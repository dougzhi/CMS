const path = require('path')

const BASE_URL = process.env.NODE_ENV === 'production'
  ? '/'
  : '/'

const CompressionWebpackPlugin = require('compression-webpack-plugin')

module.exports = {
  // 别名 alias
  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src'))
      .set('assets', resolve('src/assets'))
      .set('components', resolve('src/components'))
  },

  // api请求基础路径
  publicPath: './',
  // 将js、css压缩
  configureWebpack: config => {
    let plugins = [
      new CompressionWebpackPlugin({
        filename: '[path].gz[query]',
        algorithm: 'gzip',
        test: new RegExp(
          '\\.(' +
          ['js', 'css'].join('|') +
          ')$',
        ),
        threshold: 10240,
        minRatio: 0.8,
      }),
    ]
    if (process.env.NODE_ENV !== 'development') {
      config.plugins = [...config.plugins, ...plugins]
    }
  },

  // 保存时检查
  lintOnSave: true,

  // 代理设置
  devServer: {
    disableHostCheck: true,
    host: '0.0.0.0',
    port: '8080', // 代理端口
    open: true, // 项目启动时是否自动打开浏览器
    proxy: {
      '/api': {
            target: 'http://localhost:8099',
        changeOrigin: true,
        ws: true, // proxy websockets
        secure: true // 设置支持https协议的代理
        // pathRewrite: {      //重写路径
        //     '^/travelPortal': '/travelPortal'
        // }
      }
    }
  }
  // before: app => {}
}

function resolve(dir) {
  return path.join(__dirname, dir)
}
