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
            <div style="display: inline; color: red; font-size: 25px">￥{{good.price}}/件</div>
            <div style="display: inline; color: gray; font-size: 15px; margin-left: 300px">库存：{{good.stock}}</div>
            <br/>
            <a-space size="middle">
              <a-button icon="minus" shape="circle" @click="countMinus" size="large"></a-button>
              <div style="font-size: 22px; color: black">{{count}}</div>
              <a-button icon="plus" shape="circle" @click="countPlus" size="large"></a-button>
              <a-button type="primary" size="large" style="background-color: #ff0036; border: 2px solid #ff0036" @click="addProductInOrder">Add To Cart</a-button>
            </a-space>
          </div>
        </div>
      </a-layout-content>
      <a-layout-footer>
        <div class="footer">
          <p>
            Comments
            <a-button type="primary" style="float: right" @click="addComment">Send</a-button>
          </p>
          <a-textarea placeholder="有什么想法说出来吧！" :rows="4" style="resize: none" v-model="inputMessage"></a-textarea>
          <br/>
          <br/>
          <a-row v-for="item in comments" :key="item.id" class="comments">
            <a-col :span="6">
              <a-icon type="reddit" />
              {{item.nickname}}
            </a-col>
            <a-col :span="10" style="text-align: left">{{item.content}}</a-col>
            <a-col :span="8">test3</a-col>
          </a-row>
        </div>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
  import { Layout, Button, Space, Input, Row, Col, Icon } from 'ant-design-vue'
  import { getProductById } from 'network/good'
  import { addProductInOrder } from 'network/order'
  import { getCommentsByProduct, addComment } from "network/comments";

  export default {
    name: "Good",
    components: {
      'a-layout': Layout,
      'a-layout-header': Layout.Header,
      'a-layout-content': Layout.Content,
      'a-layout-footer': Layout.Footer,
      'a-button': Button,
      'a-space': Space,
      'a-textarea': Input.TextArea,
      'a-row': Row,
      'a-col': Col,
      'a-icon': Icon
    },
    data() {
      return {
        good: {},
        count: 1,
        comments: [],
        inputMessage: ''
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
          // console.log(res);
          this.good = res.product
        })
      },
      addProductInOrder() {
        addProductInOrder(this.$store.state.userId, this.$route.query.goodId, this.count).then(res => {
          if(res.error == "") {
            alert('Add to cart successfully!')
          } else {
            alert('Error!')
          }
        })
      },
      getCommentsByProduct(productId) {
        // console.log(productId);
        getCommentsByProduct(productId).then(res => {
          console.log(res);
          this.comments = res.comments
        })
      },
      addComment() {
        addComment(this.inputMessage, this.$store.state.userId, this.$route.query.goodId).then(res => {
          if(res.error == '') {
            // console.log(res);
            const obj = {
              content: this.inputMessage,
              nickname: this.$store.state.userId
            }
            this.comments.push(obj)
            this.inputMessage = ''
          } else {
            alert('Error')
          }
        })
      }
    },
    mounted() {
      this.getProductById(this.$route.query.goodId)
      this.getCommentsByProduct(this.$route.query.goodId)
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

  .footer {
    width: 40%;
    margin: 0 auto;
  }

  .footer p {
    text-align: left;
    margin-left: 10px;
    color: black;
    /*text-decoration: underline;*/
    /*text-decoration: none;*/
    /*padding-bottom: 5px;*/
    /*border-bottom: 1px solid #000;*/
  }

  .comments {
    border: 1px solid gray;
    height: 60px;
    line-height: 60px;
    color: black;
  }
</style>
