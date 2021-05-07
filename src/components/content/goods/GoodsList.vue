<template>
  <div class="goods">
    <goods-list-item v-for="item in goods" :goods-item="item" :key="item.id" />
  </div>
</template>

<script>
  import GoodsListItem from "./GoodsListItem";

  export default {
    name: "GoodsList",
     components: {
      GoodsListItem
    },
      data(){
      var _this =this;
      var url="";
      if(_this.goodId==1){
          url='json/guazi.json';
        }else if(_this.goodId==2){
          url='json/latiao.json';
        }else{
          url='json/guazi.json';
        }
      this.$axios.get(url).then(function(res){
        _this.goods=res.data;
      })
      return{
        goods:[]
      }
    },
    props: {
      goods: {
        type: Array,
        default() {
          return []
        }
      },
      goodId:Number
    },
    watch:{
      goodId(){
        var _this =this;
        var url ="";
        if(_this.goodId==1){
          url='json/guazi.json';
        }else if(_this.goodId==2){
          url='json/latiao.json';
        }

        this.$axios.get(url).then(function(res){
        _this.goods=res.data;
      })
      return{
        goods:[]
      }
      }
    }
  }
</script>

<style scoped>
  .goods {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    padding: 2px;
    margin: 2px 200px;
  }
</style>
