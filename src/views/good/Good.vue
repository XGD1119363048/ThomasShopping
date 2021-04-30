<template>
  <div id="good">
    <a-layout>
      <a-layout-header>{{good.title}}</a-layout-header>
      <a-layout-content>Content</a-layout-content>
      <a-layout-footer>Footer</a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
  import { Layout } from 'ant-design-vue'

  export default {
    name: "Good",
    components: {
      'a-layout': Layout,
      'a-layout-header': Layout.Header,
      'a-layout-content': Layout.Content,
      'a-layout-footer': Layout.Footer
    },
    props: {
      good: {
        type: Object,
        default() {
          return {title: 'Test'}
        }
      }
    }
  }
</script>

<style scoped>
  #good {
    text-align: center;
  }

  #good .ant-layout-header,
  #good .ant-layout-footer {
    background: #fff;
    color: #b0b0b0;
  }

  #good .ant-layout-footer {
    line-height: 1.5;
  }

  #good .ant-layout-sider {
    background: #fff;
    color: #fff;
    line-height: 120px;
  }

  #good .ant-layout-content {
    /*background: rgba(16, 142, 233, 1);*/
    background: #fff;
    color: #b0b0b0;
    min-height: 120px;
    line-height: 120px;
  }

  #good > .ant-layout {
    margin-bottom: 48px;
  }

  #good > .ant-layout:last-child {
    margin: 0;
  }
</style>
