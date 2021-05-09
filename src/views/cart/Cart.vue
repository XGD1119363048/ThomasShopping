<template>
  <div id="cart">
    <cart-list :goods="showGoods"
               @minus-click="minusClick"
               @plus-click="plusClick"
               @delete-click="deleteClick"
    />
    <div v-if="cartGoods.length > 0" class="total-price" style="font-size: 20px">￥{{showTotalPrice}}</div>
  </div>
</template>

<script>
  import CartList from "components/content/cart/CartList";

  export default {
    name: "Cart",
    components: {
      CartList
    },
    data() {
      return {
        cartGoods:
            [
              {
                name: 'test1',
                count: 2,
                pricePerCount: 10
              },
              {
                name: 'test2',
                count: 4,
                pricePerCount: 8
              },
            ],
        totalPrice: 0
      }
    },
    computed: {
      showGoods() {
        return this.cartGoods
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
        // console.log(item);
      },
      plusClick(item) {
        this.totalPrice = 0
        item.count++
      },
      deleteClick(item) {
        for(let i = 0; i < this.cartGoods.length; i++) {
          if(this.cartGoods[i] == item) {
            this.totalPrice = 0
            this.cartGoods.splice(i, 1)
            break
          }
        }
      }
    },
    mounted() {

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
