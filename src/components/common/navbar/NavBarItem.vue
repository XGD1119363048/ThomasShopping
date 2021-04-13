<template>
  <div class="nav-bar-item">
    <span class="sep"></span>
    <slot name="item-icon"></slot>
    <slot name="item-text"></slot>
    <span class="sep"></span>
  </div>
</template>

<script>
  export default {
    name: "NavBarItem"
  }
</script>

<style scoped>
  .nav-bar-item {
    flex: 1;
    float: left;
    text-align: center;
    height: 40px;
    font-size: 14px;
  }

  .nav-bar-item text {
    color: #b0b0b0;
    line-height: 40px;
    display: inline-block;
  }

  .nav-bar-item .sep {
    margin: 0.3em;
    color: #424242;
  }
</style>
