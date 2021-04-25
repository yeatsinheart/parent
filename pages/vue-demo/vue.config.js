// ide中查找配置路径
// <projectRoot>/node_modules/@vue/cli-service/webpack.config.js
/*
开发环境：npx vue-cli-service inspect --mode development
生产环境：npx vue-cli-service inspect --mode production
运行命令，将输出导入到 js 文件：
开发环境：npx vue-cli-service inspect --mode development >> webpack.config.development.js
生产环境：npx vue-cli-service inspect --mode production >> webpack.config.production.js
*/
const path = require('path')
const resolve = dir => path.join(__dirname, dir)
const TerserPlugin = require('terser-webpack-plugin')
const webpack = require('webpack')
const CompressionWebpackPlugin = require('compression-webpack-plugin')
// 定义压缩文件类型
const productionGzipExtensions = ['js', 'css', 'json', 'txt', 'html', 'ico', 'svg']

module.exports = {
  productionSourceMap: false,
  // 统一配置打包插件
  configureWebpack: {
    // 左边代表要引入资源包的名字，右边代表该模块在外面使用引用的名字
    externals: {
      vue: 'Vue',
      vuex: 'Vuex',
      'vue-router': 'VueRouter',
      'element-plus': 'ElementPlus',
      axios: 'axios',
      'vue-i18n': 'VueI18n',
      jquery: '$'
    },
    plugins: [
      new CompressionWebpackPlugin({
        filename: '[path].gz[query]',
        algorithm: 'gzip',
        test: new RegExp('\\.(' + productionGzipExtensions.join('|') + ')$'), // 匹配文件名
        threshold: 10 * 1024, // 对10K以上的数据进行压缩
        minRatio: 0.8,
        deleteOriginalAssets: false// 是否删除源文件
      }),
      new webpack.ProvidePlugin({
        $: 'jquery',
        jQuery: 'jquery',
        'windows.jQuery': 'jquery'
      }),
      new TerserPlugin({
        terserOptions: {
          warnings: false,
          output: {
            comments: false
          },
          compress: {
            drop_console: true,
            drop_debugger: false,
            pure_funcs: ['console.log']
          }
        },
        extractComments: false
      })
    ]
  },
  parallel: true,
  css: {
    // vue 文件中修改css 不生效 注释掉  extract:true
    requireModuleExtension: true,
    extract: {
      extract: true,
      filename: 'style/[name].css?v=[hash]',
      chunkFilename: 'style/[name].css?v=[hash]'
    },
    sourceMap: false,
    loaderOptions: {
      css: {},
      sass: {
        prependData: `@import "src/views/frame/style/_variables${process.env.ACTIVE_VIEW_PACKAGE}.scss";`
      },
      less: {}
    }
  },

  chainWebpack: config => {
    // 添加别名
    config.resolve.alias
      .set('@', resolve('src'))
    config.module
      .rule('images')
      .use('image-webpack-loader')
      .loader('image-webpack-loader')
      .options({
        mozjpeg: { progressive: true, quality: 65 },
        optipng: { enabled: false },
        pngquant: { quality: [0.65, 0.90], speed: 4 },
        gifsicle: { interlaced: false }
        /* webp: { quality: 75 } */
      })
    const cdn = {
      js: [
        // 可以写成动态版本号
        'https://unpkg.com/vue@next',
        'https://unpkg.com/vue-router@4',
        'https://unpkg.com/vuex@4',
        'https://unpkg.com/element-plus/lib/index.full.js',
        'https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.2/axios.min.js',
        'https://cdn.bootcss.com/vue-i18n/8.17.0/vue-i18n.min.js',
        '//code.jquery.com/jquery-3.5.1.slim.min.js'
      ],
      css: []
    }
    config.plugin('html-index')
      .tap(args => {
        args[0].cdn = cdn
        return args
      })

    // 删除默认的splitChunk
    config.optimization.delete('splitChunks')
    config.optimization.minimize(true)

    config.optimization.splitChunks({
      chunks: 'all',
      minSize: 30 * 1024,
      minChunks: 2,
      cacheGroups: {
        common: {
          // 抽取所有入口页面都需要的公共chunk
          name: 'chunk-common',
          chunks: 'initial',
          minChunks: 2,
          maxInitialRequests: 5,
          minSize: 0,
          priority: 1,
          reuseExistingChunk: true,
          enforce: true
        }
      }
    })

    config.output.filename('js/[name].js?v=[hash]').end()
    config.output.chunkFilename('js/[name].js?v=[hash]').end()
    // 取消预加载 首屏加速

    config.plugins.delete('preload-index')
    config.plugins.delete('prefetch-index')
  }, // 静态资源打包路径
  assetsDir: 'static', // 打包后的启动文件
  indexPath: 'index.html', // baseUrl 从 Vue CLI 3.3 起已弃用，请使用publicPath。
  pages: {
    index: {
      // page 的入口
      entry: './src/main.js', // 模板来源
      template: `public/${process.env.ACTIVE_VIEW_PACKAGE}.html`, // 在 dist/h5.html 的输出
      filename: 'index.html', // 打开时的访问路径，
      // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
      title: process.env.TITLE,
      // 在这个页面中包含的块，默认情况下会包含
      // 提取出来的通用 chunk 和 vendor chunk。
      chunks: ['chunk-vendors', 'chunk-common', 'index'],
      favicon: `./src/${process.env.ACTIVE_VIEW_PACKAGE}/favicon.ico`
    }
  },
  devServer: {
    open: true,
    proxy: {
      '/gate': {
        target: 'http://' + process.env.VUE_APP_GATE + '/', // 设置你调用的接口域名和端口号
        changeOrigin: true, // 跨域
        secure: false,
        pathRewrite: {
          '^/gate': '/gate' // 匹配以/gate为开头的请求地址，并使用/gate替换
        }
      }
    },
    compress: true,
    hot: true,
    port: 9000
  }
}
