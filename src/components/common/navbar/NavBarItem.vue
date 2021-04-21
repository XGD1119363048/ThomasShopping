<template>
  <div class="nav-bar-item" @click="itemClick">
    <span class="sep"></span>
    <slot name="item-icon"></slot>
    <slot name="item-text"></slot>
    <span class="sep"></span>
  </div>
</template>

<script>
  export default {
    name: "NavBarItem",
    props: {
      path: String
    },
    methods: {
      itemClick() {
        this.$router.replace(this.path)
      }
    }
  }
</script>

<style scoped>
  .nav-bar-item {
    flex: 1;
    text-align: center;
    height: 49px;
    font-size: 14px;
  }

  .nav-bar-item img {
    width: 24px;
    height: 24px;
    /*margin-top: 3px;*/
    vertical-align: middle;
    /*margin-bottom: 2px;*/
  }

  .nav-bar-item .sep {
    margin: 0.3em;
    color: #424242;
  }
</style>
