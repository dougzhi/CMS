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

  css: {
    loaderOptions: {
      sass: {
        prependData: `
          @import "src/statics/css/vars.scss";
        `
      }
    }
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
      '/banner': {
        // target: 'http://localhost:3000/mock/11'
        target: 'http://127.0.0.1:7777'

      },
      '/api/cms': {
        target: 'http://localhost:31001/api/cms',
        changeOrigin: true,
        pathRewrite: {
          '^/api/cms': ''
        }
        //target: 'http://127.0.0.1:50201'
      },

      /*,
      '/ucenter': {
        // target: 'http://localhost:3000/mock/11'
        //target: 'http://127.0.0.1:31200'
        target: 'http://127.0.0.1:50201'

      },
      '/auth': {
        // target: 'http://localhost:3000/mock/11'
        //target: 'http://127.0.0.1:31200'
        target: 'http://127.0.0.1:50201/api'

      },
      '/course': {
        // target: 'http://localhost:3000/mock/11'
        //target: 'http://127.0.0.1:31200'
        target: 'http://127.0.0.1:50201/api'

      },
      '/media': {//媒资管理
        //target: 'http://127.0.0.1:31400'
        target: 'http://127.0.0.1:50201'
      },

      '/cms/!*': {//cms管理
        //target: 'http://127.0.0.1:31001'
        target: 'http://127.0.0.1:50201'

      },


      '/filesystem/!*': {//文件系统管理
        //target: 'http://127.0.0.1:22100'
        target: 'http://127.0.0.1:50201'

      },
      '/category/!*': {//分类管理
        // target: 'http://127.0.0.1:3000/mock/11'
        //target: 'http://127.0.0.1:31200'
        target: 'http://127.0.0.1:50201'

      },
      '/sys/!*': {//系统管理
        //target: 'http://127.0.0.1:31001'
        target: 'http://127.0.0.1:50201'

      },*/
      /*'/static/!*': {//系统管理
        //target: 'http://127.0.0.1:31001'
        target: 'http://127.0.0.1'

      },
      '/group1/*': {//系统管理
        // target: 'http://127.0.0.1:3000/mock/11'
        target: 'http://192.168.101.64'

      }*/
    }
  }
  // before: app => {}
}

function resolve(dir) {
  return path.join(__dirname, dir)
}
