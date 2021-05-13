<template>
  <div id="home">
    <div > <home-nav-bar /></div>

    <div class="main">
      <div class="left">
        <home-left-nav-bar />
        </div>
        <div class="right">
          <home-right />
        </div>
    </div>
    </div>
         

</template>

<<<<<<< HEAD
<style type="text/css" >
*{
padding:0;
margin:0;
}

.left{
  width:150px;
  float:left;
  margin-right:10px;
}

.right{
   width:1000px;  
   background-color: #f5f5f5;
   float:right;
}

.main{
  width:1200px;
  margin:20px auto;
}
.left,.right{
  height: 1200px;
}

</style>


<script>  
  import HomeNavBar from "components/content/mainNavBar/HomeNavBar";
  import HomeLeftNavBar from "components/common/navbar/HomeLeftNavBar";
  import HomeRight from '../../components/HomeRight';
=======
<script>
  import GoodsList from "components/content/goods/GoodsList";
  import {getHomeGoods, getProductByCategoryId} from "network/good";
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553

  export default {
    name: "Home",
    components: {
    HomeNavBar,
    HomeLeftNavBar,
    HomeRight
    },
       
    data() {
<<<<<<< HEAD

     },
    computed: {

     
=======
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
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
    },

    created() {
<<<<<<< HEAD
      this.getHomeGoods(this.goods[this.currentType].page * 64)
=======
      this.getHomeGoods(this.goods['homeGoods'].page * 64)
      for(let i = 1; i <= 6; i++) {
        this.getProductByCategoryId(i)
      }
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
    },
    methods: {
      getHomeGoods(count) {
        getHomeGoods(count).then(res => {
<<<<<<< HEAD
          this.goods[this.currentType].list = res.product
          // console.log(this.goods[this.currentType].list);
        })
=======
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
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
      }
    }
  }
</script>

