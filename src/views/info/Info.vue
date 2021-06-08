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
                  <p style="display: inline">Balance:{{remainBalance}}</p>
                  <div style="display: inline; margin-left: 10px">
                    <a-button @click="showModal">
                      charge
                    </a-button>
                    <a-modal
                        title="Input"
                        :visible="visible"
                        :confirm-loading="confirmLoading"
                        @ok="handleOk"
                        @cancel="handleCancel"
                    >
<!--                      <p>{{ ModalText }}</p>-->
                      <a-input prefix="￥" suffix="RMB" v-model="balance" />
                    </a-modal>
                  </div>
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
              </a-row>
              <a-row>
                <order-list :orders="getTypeOrders(orderNowIndex)"/>
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
  import { Layout, Row, Col, Icon, Button, Modal, Input } from 'ant-design-vue'
  import { queryUser, addBalance } from "network/user"
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
      'a-icon': Icon,
      'a-button': Button,
      'a-modal': Modal,
      'a-input': Input
    },
    data() {
      return {
        userInfo: {},
        userOrders: [],
        paidOrders: [],
        canceledOrders: [],
        cartList: [],
        nowIndex: 1,
        orderNowIndex: 1,

        ModalText: 'Content of the modal',
        visible: false,
        confirmLoading: false,

        balance: 100
      }
    },
    computed: {
      remainBalance() {
        // console.log(this.userInfo.balance);
        // if(this.userInfo.balance == undefined) {
        //   return this.userInfo.balance
        // } else {
        //   return this.userInfo.balance.toFixed(2)
        // }
        return this.userInfo.balance
      }
    },
    methods: {
      changeIndex(index) {
        this.nowIndex = index
      },
      changeOrderIndex(index) {
        this.orderNowIndex = index
        // console.log(this.userOrders)
        // console.log(this.orderNowIndex);
        // console.log(this.getTypeOrders(this.orderNowIndex));
      },
      getTypeOrders(orderNowIndex) {
        switch (orderNowIndex) {
          case 1:
            return this.userOrders
          case 2:
            return this.paidOrders
          case 3:
            return this.canceledOrders
        }
      },
      queryUser(username) {
        queryUser(username).then(res => {
          if(res.error == '') {
            // console.log(res.user);
            this.userInfo = res.user
            // console.log(this.userInfo);
          }
        })
      },
      getOrderByUser(username) {
        getOrderByUser(username).then(res => {
          if(res.error == '') {
            // console.log(res.orders);
            this.userOrders = res.orders
            this.userOrders.pop()
            for(let i = 0; i < this.userOrders.length; i++) {
              if(this.userOrders[i].status == 1) {
                this.paidOrders.push(this.userOrders[i])
              } else if(this.userOrders[i].status == 2) {
                this.canceledOrders.push(this.userOrders[i])
              } else {}
            }
            // console.log(this.userOrders);
            // console.log(this.paidOrders);
            // console.log(this.canceledOrders);
          }
        })
      },
      addBalance(username, balance) {
        addBalance(username, balance).then(res => {
          if(res.error == '') {
            console.log(this.userInfo.balance);
            this.userInfo.balance += parseInt(balance)
            console.log(this.userInfo.balance);
          }
        })
      },
      showModal() {
        this.visible = true;
      },
      handleOk(e) {
        this.ModalText = 'The modal will be closed after two seconds';
        this.confirmLoading = true;
        this.addBalance(this.$store.state.userId, this.balance)
        setTimeout(() => {
          this.visible = false;
          this.confirmLoading = false;
        }, 2000);
      },
      handleCancel(e) {
        console.log('Clicked cancel button');
        this.visible = false;
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
