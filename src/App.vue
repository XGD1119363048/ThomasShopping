<template>
  <div id="app">
    <main-nav-bar/>
    <router-view/>
  </div>
</template>

<script>
  import MainNavBar from "components/content/mainNavBar/MainNavBar";

  export default {
    name: 'App',
    components:{
      MainNavBar
    }
  }
</script>

<style>
body {
  margin-top: 0;
  margin-left: 0;
  background: #f5f5f5;
}
</style>
