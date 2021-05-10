<template>
  <div id="home">
    <goods-list :goods="showGoods" />
  </div>
</template>

<script>
  import GoodsList from "components/content/goods/GoodsList";
  import {getHomeGoods} from "network/good";

  export default {
    name: "Home",
    components: {
      GoodsList
    },
    data() {
      return {
        goods: {
          'homeGoods': {
            page: 1,
            list: []
          },
          'good1': {
            page: 1,
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
              {
                show: {img: ''},
                title: 'title9',
                price: 1,
                id: 101209
              },
              {
                show: {img: ''},
                title: 'title10',
                price: 1,
                id: 101210
              },
              {
                show: {img: ''},
                title: 'title11',
                price: 1,
                id: 101211
              },
              {
                show: {img: ''},
                title: 'title12',
                price: 1,
                id: 101212
              },
            ]
          }
        },
        currentType: 'homeGoods'
      }
    },
    computed: {
      showGoods() {
        return this.goods[this.currentType].list
      }
    },
    created() {
      this.getHomeGoods(this.goods[this.currentType].page * 64)
      // console.log(window.sessionStorage.getItem('isLogin'));
    },
    methods: {
      getHomeGoods(count) {
        getHomeGoods(count).then(res => {
          this.goods[this.currentType].list = res.product
          // console.log(this.goods[this.currentType].list);
        })
      }
    }
  }
</script>

<style scoped>
</style>
