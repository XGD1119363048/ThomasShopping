<template>
  <div class="goods-item" @click="goodClick">
<!--      <img :src="goodsItem.show.img" alt="">-->
    <img src="~assets/img/navbar/Cart.svg">
    <div class="goods-info">
      <p>{{goodsItem.title}}</p>
      <span class="price">{{goodsItem.price}}元</span>
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
        this.$router.push({
          path: '/goodDetail',
          query: {
            goodId: this.goodsItem.id
          }
        })
      }
    }
  }
</script>

<style scoped>
  .goods-item {
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
    font-size: 12px;
    position: absolute;
    bottom: 5px;
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
    color: var(--color-high-text);
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
  /*  background: url("~assets/img/navbar/Cart.svg") 0 0/14px 14px;*/
  /*}*/
</style>
