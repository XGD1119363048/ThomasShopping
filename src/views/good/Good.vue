<template>
  <div id="good">
    <a-layout>
      <a-layout-header>
        <div style="color: black; font-size: 25px">
          {{good.name}}
        </div>
      </a-layout-header>
      <a-layout-content>
        <div class="good-box">
          <div class="good-img">
            <img :src="good.imageAddress" style="width: 30%"/>
          </div>
          <div class="good-info" style="font-size: 22px; color: black; line-height: 80px">
            {{good.description}}
          </div>
          <div class="good-info" style="line-height: 60px">
<!--            <div class="info-item">-->
<!--              Good Select-->
<!--            </div>-->
            <div style="color: red; font-size: 25px">￥{{good.price}}/件</div>
            <a-space size="middle">
              <a-button icon="minus" shape="circle" @click="countMinus" size="large"></a-button>
              <div style="font-size: 22px; color: black">{{count}}</div>
              <a-button icon="plus" shape="circle" @click="countPlus" size="large"></a-button>
              <a-button type="primary" size="large" style="background-color: #ff0036; border: 2px solid #ff0036">Add To Cart</a-button>
            </a-space>
          </div>
        </div>
      </a-layout-content>
      <a-layout-footer></a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
  import { Layout, Button, Space } from 'ant-design-vue'
  import { getProductById } from "network/good";

  export default {
    name: "Good",
    components: {
      'a-layout': Layout,
      'a-layout-header': Layout.Header,
      'a-layout-content': Layout.Content,
      'a-layout-footer': Layout.Footer,
      'a-button': Button,
      'a-space': Space
    },
    data() {
      return {
        good: {},
        count: 1
      }
    },
    methods: {
      countMinus() {
        if(this.count > 1) {
          this.count --
        }
      },
      countPlus() {
        if(this.count < 100) {
          this.count ++
        }
      },
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
    /*display: flex;*/
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
