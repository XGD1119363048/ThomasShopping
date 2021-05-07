<template>
<div>
    <div v-if="idx==1">
 <!-- <goods-list :goods="showGoods" /> -->
    <goods-list :goodId="1" /></div>
    <div v-else-if="idx==2"><goods-list :goodId="2" /></div>
    <div v-else><goods-list :goodId="0" /></div>

</div>
</template>

<script>
  
import GoodsList from './content/goods/GoodsList.vue';
import Msg from './msg.js'
  


  export default {
    name: "Home",
    components: {
        GoodsList
   
    },
       
    data() {
      return {
        idx:0,
        // goods: {
        //   'good1': {
        //     page: 0,
        //     list: [
        //       {
        //         show: {img: 'assets/img/navbar/Cart_Active.svg'},
        //         title: 'title1',
        //         price: 0,
        //         id: 101201
        //       },
        //       {
        //         show: {img: ''},
        //         title: 'title2',
        //         price: 1,
        //         id: 101202
        //       },
        //       {
        //         show: {img: ''},
        //         title: 'title3',
        //         price: 1,
        //         id: 101203
        //       },
        //       {
        //         show: {img: ''},
        //         title: 'title4',
        //         price: 1,
        //         id: 101204
        //       },
        //       {
        //         show: {img: ''},
        //         title: 'title5',
        //         price: 1,
        //         id: 101205
        //       },
        //       {
        //         show: {img: ''},
        //         title: 'title6',
        //         price: 1,
        //         id: 101206
        //       },
        //       {
        //         show: {img: ''},
        //         title: 'title7',
        //         price: 1,
        //         id: 101207
        //       },
        //       {
        //         show: {img: ''},
        //         title: 'title8',
        //         price: 1,
        //         id: 101208
        //       },
        //     ]
        //   }
        // // },
        // currentType: 'good1'
      }
    },
    mounted: function(){
        var _this = this
        Msg.$on('val',function(m){
            _this.idx=m;
        })
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