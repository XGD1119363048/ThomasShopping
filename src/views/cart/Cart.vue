<template>
  <div id="cart">
    <cart-list :goods="showGoods"
               :status="getStatus"
               @minus-click="minusClick"
               @plus-click="plusClick"
               @delete-click="deleteClick"
    />
    <div v-if="status == 0 && cartGoods.length > 0" class="total-price" style="font-size: 20px">
      <div>
        <p style="display: inline">总价：</p>
        <div style="display: inline" v-if="userCoins >= 10">
          <del style="display: inline; color: grey">￥{{showTotalPrice.toFixed(2)}}</del>
          <p style="display: inline">￥{{(showTotalPrice - (userCoins - (userCoins % 10)) / 1000).toFixed(2)}}</p>
          <p style="display: inline; font-size: 10px">(使用积分: {{(userCoins - (userCoins % 10))}})</p>
        </div>
        <div style="display: inline" v-else>
          <p style="display: inline;">￥{{showTotalPrice.toFixed(2)}}</p>
        </div>
      </div>
      <a-button type="primary" style="margin-left: 30px; margin-top: 0" @click="payOrder">Pay</a-button>
    </div>
  </div>
</template>

<script>
  import CartList from "components/content/cart/CartList";
  import { getOrderByUser, deleteProductInOrder, addProductInOrder, payOrder } from "@/network/order";
  import { Button } from 'ant-design-vue'

  export default {
    name: "Cart",
    components: {
      CartList,
      'a-button': Button
    },
    data() {
      return {
        cartGoods: [],
        totalPrice: 0,
        status: 0,
        userCoins: 0
      }
    },
    computed: {
      showGoods() {
        return this.cartGoods
      },
      getStatus() {
        return this.status
      },
      showTotalPrice() {
        for(let i = 0; i < this.cartGoods.length; i++) {
          this.totalPrice += this.cartGoods[i].count * this.cartGoods[i].pricePerCount
          // console.log(this.totalPrice);
        }
        return this.totalPrice
      }
    },
    methods: {
      minusClick(item) {
        this.totalPrice = 0
        item.count--
        deleteProductInOrder(this.$store.state.userId, item.id, 1)
        // console.log(item);
      },
      plusClick(item) {
        this.totalPrice = 0
        item.count++
        addProductInOrder(this.$store.state.userId, item.id, 1)
      },
      deleteClick(item) {
        for(let i = 0; i < this.cartGoods.length; i++) {
          if(this.cartGoods[i] == item) {
            this.totalPrice = 0
            this.cartGoods.splice(i, 1)
            deleteProductInOrder(this.$store.state.userId, item.id, item.count)
            // console.log(item);
            break
          }
        }
      },
      getOrderByUser(username) {
        getOrderByUser(username).then(res => {
          // console.log(res);
          const order = res.orders[res.orders.length - 1]
          this.status = order.status
          // console.log(order);
          for(let i = 0; i < order.products.length; i++) {
            this.cartGoods.push({
              id: order.products[i].id,
              name: order.products[i].name,
              count: order.quantity[i],
              pricePerCount: order.products[i].price,
              imageAddress: order.products[i].imageAddress,
              stock: order.products[i].stock
            })
          }
          this.userCoins = order.user.coin
          console.log(this.userCoins);
        })
      },
      payOrder() {
        // console.log(this.$store.state.userId)
        payOrder(this.userCoins, this.$store.state.userId).then(res => {
          if(res.error == '') {
            alert('Pay successfully!')
            this.status = 1
          } else {
            alert('Error!')
          }
        })
        // alert('Pay successfully!')
      }
    },
    mounted() {
      this.getOrderByUser(this.$store.state.userId)
    }
  }
</script>

<style scoped>
  .total-price {
    margin: 1px 350px;
    float: right;
    color: red;
  }
</style>
