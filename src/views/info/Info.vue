<template>
  <div id="info">
    <a-layout>
      <a-layout-header>User Info</a-layout-header>
      <a-layout>
        <a-layout-sider style="background: white">
          <div style="margin-left: 50px">
            <div style="font-size: 18px; line-height: 50px; margin-top: 30px">个人中心</div>
            <div class="payment">
              <a @click="changeIndex(1)" :class="{active: nowIndex === 1}">个人信息</a>
              <br/>
              <br/>
              <a @click="changeIndex(2)" :class="{active: nowIndex === 2}">我的订单</a>
            </div>
          </div>
        </a-layout-sider>
        <a-layout-content>
          <div style="margin-left: 20px; background: white">
            <div v-if="nowIndex == 1">
              <a-row style="text-align: center; vertical-align: center">
                <a-col :span="12">
                  <div style="line-height: 50px">
                    <a-icon type="reddit" />
                    {{userInfo.userName}}
                  </div>
                </a-col>
                <a-col :span="12" style="text-align: left">
                  <p>Address:{{userInfo.address}}</p>
                  <p>Age:{{userInfo.age}}</p>
                  <p>Balance:{{userInfo.balance}}</p>
                  <p>Birthday:{{userInfo.birthday}}</p>
                  <p>Coin:{{userInfo.coin}}</p>
                  <p>Email:{{userInfo.email}}</p>
                  <p>Gender:{{userInfo.gender}}</p>
                  <p>PhoneNumber:{{userInfo.phoneNumber}}</p>
                </a-col>
              </a-row>
            </div>
            <div v-else-if="nowIndex == 2">
              <a-row>
                <p style="font-size: 50px; margin-left: 50px; margin-top: 20px; margin-bottom: 20px">我的订单</p>
              </a-row>
              <a-row style="text-align: center; font-size: 22px">
                <a-col :span="4">
                  <a @click="changeOrderIndex(1)" :class="{active: orderNowIndex === 1}">全部有效订单</a>
                </a-col>
                <a-col :span="4">
                  <a @click="changeOrderIndex(2)" :class="{active: orderNowIndex === 2}">已支付</a>
                </a-col>
                <a-col :span="4">
                  <a @click="changeOrderIndex(3)" :class="{active: orderNowIndex === 3}">已取消</a>
                </a-col>
                <a-col :span="4">
                  <a @click="changeOrderIndex(4)" :class="{active: orderNowIndex === 4}">购物车</a>
                </a-col>
              </a-row>
              <a-row>
                <order-list :orders="userOrders"/>
              </a-row>
            </div>
          </div>
        </a-layout-content>
      </a-layout>
      <a-layout-footer></a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
  import { Layout, Row, Col, Icon } from 'ant-design-vue'
  import { queryUser } from "@/network/user"
  import { getOrderByUser } from "@/network/order"
  import OrderList from "views/info/infoChild/OrderList";

  export default {
    name: "Info",
    components: {
      OrderList,

      'a-layout': Layout,
      'a-layout-header': Layout.Header,
      'a-layout-content': Layout.Content,
      'a-layout-footer': Layout.Footer,
      'a-layout-sider': Layout.Sider,
      'a-row': Row,
      'a-col': Col,
      'a-icon': Icon
    },
    data() {
      return {
        userInfo: {},
        userOrders: [],
        // paidOrders: [],
        // canceledOrders: [],
        // cartList: [],
        nowIndex: 1,
        orderNowIndex: 1
      }
    },
    methods: {
      changeIndex(index) {
        this.nowIndex = index
      },
      changeOrderIndex(index) {
        this.orderNowIndex = index
      },
      queryUser(username) {
        queryUser(username).then(res => {
          if(res.error == '') {
            // console.log(res.user);
            this.userInfo = res.user
          }
        })
      },
      getOrderByUser(username) {
        getOrderByUser(username).then(res => {
          if(res.error == '') {
            console.log(res.orders);
            this.userOrders = res.orders
          }
        })
      }
    },
    mounted() {
      this.queryUser(this.$store.state.userId)
      this.getOrderByUser(this.$store.state.userId)
    }
  }
</script>

<style scoped>
  #info {
    margin: 50px 300px;
    background: #f5f5f5;
  }

  .ant-layout-header, .ant-layout-sider, .ant-layout-content, .ant-layout-footer {
    background: #f5f5f5;
  }

  .ant-layout-sider {
    height: 1000px;
  }

  .payment {
    height: 40px;
    vertical-align: center;
    margin-top: 10px
    /*margin: 0 auto;*/
    /*position: relative;*/
    /*top: 50%;*/
    /*transform: translateY(-50%);*/
  }

  .active {
    color: orange;
  }
</style>
