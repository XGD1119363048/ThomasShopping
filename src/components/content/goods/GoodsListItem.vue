<template>
  <div class="goods-item" @click="goodClick">
      <img :src="goodsItem.imageAddress" alt="">
<!--    <img src="~assets/img/navbar/cart.svg">-->
    <div class="goods-info">
      <p>{{goodsItem.name}}</p>
      <span class="price">￥{{goodsItem.price}}</span>
    </div>
  </div>
</template>

<script>
  export default {
    name: "GoodsListItem",
    props: {
      goodsItem: {
        type: Object,
        default() {
          return {}
        }
      }
    },
    methods: {
      goodClick() {
        if (this.$store.state.isLogin == true) {
          this.$router.push({
            path: '/goodDetail',
            query: {
              goodId: this.goodsItem.id
            }
          })
        } else {
          this.$router.push('/login')
        }
      }
    }
  }
</script>

<style scoped>
  .goods-item {
    padding-top: 40px;
    padding-bottom: 40px;
    position: relative;
    width: 24%;
    cursor: pointer;
  }

  .goods-item img {
    width: 100%;
    border-radius: 5px;
  }

  .goods-info {
    font-size: 20px;
    color: black;
    position: absolute;
    bottom: -22px;
    left: 0;
    right: 0;
    overflow: hidden;
    text-align: center;
  }

  .goods-info p {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-bottom: 3px;
  }

  .goods-info .price {
    color: red;
    /*color: var(--color-high-text);*/
    margin-right: 20px;
  }

  /*.goods-info .collect {*/
  /*  position: relative;*/
  /*}*/

  /*.goods-info .collect::before {*/
  /*  content: '';*/
  /*  position: absolute;*/
  /*  left: -15px;*/
  /*  top: -1px;*/
  /*  width: 14px;*/
  /*  height: 14px;*/
  /*  background: url("~assets/img/navbar/cart.svg") 0 0/14px 14px;*/
  /*}*/
</style>
