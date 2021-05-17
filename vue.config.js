module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        'assets': '@/assets',
        'common': '@/common',
        'components': '@/components',
        'network': '@/network',
        'views': '@/views'
      }
    }
  },
  css: {
    loaderOptions: {
      less: {
        modifyVars: {
          'btn-primary-bg': '#000000',
          'checkbox-color': '#333333',
          'link-color': '#b0b0b0'
        },
        javascriptEnabled: 'true'
      }
    }
  },
  publicPath: './',
  outputDir: 'dist',
  assetsDir: 'static',
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8888',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}
