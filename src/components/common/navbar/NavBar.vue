<template>
  <div id="nav-bar">
    <div class="site-topbar">
      <div class="container">
        <div class="topbar-nav">
          <slot name="topbar-nav"></slot>
        </div>
        <div id="J_miniCartTrigger" class="topbar-cart">
          <slot name="topbar-cart"></slot>
        </div>
        <div id="J_siteUserInfo" class="topbar-info">
          <slot name="topbar-info"></slot>
        </div>
      </div>
    </div>
    <div class="site-header"></div>
  </div>
</template>

<script>
  export default {
    name: "NavBar"
  }
</script>

<style scoped>
  .site-topbar {
    position: relative;
    z-index: 30;
    height: 40px;
    font-size: 12px;
    color: #b0b0b0;
    background: #333;
  }

  .container {
    width: 1226px;
    margin-right: auto;
    margin-left: auto;
  }

  .site-topbar .topbar-nav {
    float: left;
    height: 40px;
    line-height: 40px;
  }

  .site-topbar a {
    color: #b0b0b0;
    line-height: 40px;
    display: inline-block;
  }

  .site-topbar .topbar-cart {
    position: relative;
    float: right;
    /*width: 120px;*/
    line-height: 40px;
    margin-left: 15px;
    -webkit-transition: all .2s;
    transition: all .2s;
    font-size: 12px;
    background: #424242
  }

  .site-topbar .topbar-info {
    position: relative;
    float: right;
    height: 40px;
    line-height: 40px;
  }

  .site-header {
    z-index: 20;
    height: 100px;
  }
</style>
