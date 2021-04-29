<template>
  <div id="home">
    <goods-list :goods="showGoods" />
  </div>
</template>

<script>
  import GoodsList from "components/content/goods/GoodsList";

  export default {
    name: "Home",
    components: {
      GoodsList
    },
    data() {
      return {
        goods: {
          'good1': {
            page: 0,
            list: [
              {
                show: {img: '/img/navbar/Cart_Active.svg'},
                title: 'title1',
                price: 0,
                id: 101201
              },
              {
                show: {img: ''},
                title: 'title2',
                price: 1,
                id: 101202
              },
              {
                show: {img: ''},
                title: 'title3',
                price: 1,
                id: 101203
              },
              {
                show: {img: ''},
                title: 'title4',
                price: 1,
                id: 101204
              },
              {
                show: {img: ''},
                title: 'title5',
                price: 1,
                id: 101205
              },
              {
                show: {img: ''},
                title: 'title6',
                price: 1,
                id: 101206
              },
              {
                show: {img: ''},
                title: 'title7',
                price: 1,
                id: 101207
              },
              {
                show: {img: ''},
                title: 'title8',
                price: 1,
                id: 101208
              },
            ]
          }
        },
        currentType: 'good1'
      }
    },
    computed: {
      showGoods() {
        return this.goods[this.currentType].list
      }
    },
    created() {
    }
  }
</script>

<style scoped>
</style>
