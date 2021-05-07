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

<style type="text/css" >
*{
padding:0;
margin:0;
}

.left{
  width:100px;
  float:left;
  margin-right:10px;
}

.right{
  width:1000px;
  float:right;
}

.main{
  width:1200px;
  margin:20px auto;
}
.left,.right{
  background-color: #f5f5f5;
  height: 500px;
}

</style>

<script>
  
  import HomeNavBar from "components/content/mainNavBar/HomeNavBar";
  import HomeLeftNavBar from "components/common/navbar/HomeLeftNavBar";
  import HomeRight from '../../components/HomeRight';
  


  export default {
    name: "Home",
    components: {
    HomeNavBar,
    HomeLeftNavBar,
    HomeRight
    },
       
    data() {
        },
    computed: {
     
    },
    created() {
    }
  }
</script>

