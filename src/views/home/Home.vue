<template>
  <div id="home">
    <goods-list :goods="showGoods" />
  </div>
</template>

<script>
  import GoodsList from "components/content/goods/GoodsList";
  import {getHomeGoods, getProductByCategoryId} from "network/good";

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
          'spicyStripsGoods': {
            page: 1,
            list: []
          },
          'instanceNoodleGoods': {
            page: 1,
            list: []
          },
          'nutGoods': {
            page: 1,
            list: []
          },
          'drinkGoods': {
            page: 1,
            list: []
          },
          'breadGoods': {
            page: 1,
            list: []
          },
          'riverSnailsRiceNoodleGoods': {
            page: 1,
            list: []
          }
        },
        currentType: 'homeGoods'
      }
    },
    computed: {
      showGoods() {
        const categoryId = this.$route.query.categoryId
        switch (categoryId) {
          case '1':
            this.currentType = 'spicyStripsGoods'
            break
          case '2':
            this.currentType = 'instanceNoodleGoods'
            break
          case '3':
            this.currentType = 'nutGoods'
            break
          case '4':
            this.currentType = 'drinkGoods'
            break
          case '5':
            this.currentType = 'breadGoods'
            break
          case '6':
            this.currentType = 'riverSnailsRiceNoodleGoods'
            break
          default:
            this.currentType = 'homeGoods'
            break
        }
        // console.log(this.goods[this.currentType].list);
        return this.goods[this.currentType].list
      }
    },
    created() {
      this.getHomeGoods(this.goods['homeGoods'].page * 64)
      for(let i = 1; i <= 6; i++) {
        this.getProductByCategoryId(i)
      }
    },
    methods: {
      getHomeGoods(count) {
        getHomeGoods(count).then(res => {
          this.goods['homeGoods'].list = res.product
          // console.log(this.goods[this.currentType].list);
        })
      },
      getProductByCategoryId(categoryId) {
        getProductByCategoryId(categoryId).then(res => {
          if(res.error == '') {
            // console.log(categoryId);
            // console.log(res);
            this.goods[this.getCategoryByCategoryId(categoryId)].list = res.product
          } else {
            alert('Error!')
          }
        })
      },
      getCategoryByCategoryId(categoryId) {
        switch (categoryId) {
          case 1:
            return 'spicyStripsGoods'
          case 2:
            return 'instanceNoodleGoods'
          case 3:
            return 'nutGoods'
          case 4:
            return 'drinkGoods'
          case 5:
            return 'breadGoods'
          case 6:
            return 'riverSnailsRiceNoodleGoods'
          default:
            return 'homeGoods'
        }
      }
    }
  }
</script>

<style scoped>
</style>
