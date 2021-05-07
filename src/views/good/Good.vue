<template>
  <div id="good">
    <a-layout>
      <a-layout-header>{{good.name}}</a-layout-header>
      <a-layout-content>
        <div class="good-box">
          <div class="good-img">
            <img :src="good.imageAddress" style="width: 50%"/>
          </div>
          <div class="good-info">
            <div class="info-item">
              Good Select
            </div>
            <a-button type="primary">Add To Cart</a-button>
          </div>
        </div>
      </a-layout-content>
      <a-layout-footer>{{good.description}}</a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
  import { Layout, Button } from 'ant-design-vue'
  import { getProductById } from "network/good";

  export default {
    name: "Good",
    components: {
      'a-layout': Layout,
      'a-layout-header': Layout.Header,
      'a-layout-content': Layout.Content,
      'a-layout-footer': Layout.Footer,
      'a-button': Button
    },
    data() {
      return {
        good: {}
      }
    },
    methods: {
      getProductById(id) {
        getProductById(id).then(res => {
          console.log(res);
          this.good = res.product
        })
      }
    },
    mounted() {
      this.getProductById(this.$route.query.goodId)
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

  .good-box {
    display: flex;
  }

  .good-img {
    flex: 1;
  }

  .good-info {
    flex: 1;
  }

  .good-info .info-item {
    display: block;
  }
</style>
