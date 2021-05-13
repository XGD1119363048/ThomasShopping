<template>
  <div class="nav-bar-item2" @click="item2Click">
    <slot name="item-icon"></slot>
    <slot name="item-box"></slot>
    <slot name="item-text"></slot>
  </div>
</template>

<script>
  export default {
    name: "NavBarItem2",
    props: {
      path: String
    },
    methods: {
      item2Click() {
        this.$router.push(this.path)
      }
    }
  }
</script>

<style scoped>
  .nav-bar-item2 {
    flex: 1;
    text-align: center;
    height: 49px;
    font-size: 14px;
  }

  .nav-bar-item2 img {
    width: 50px;
    height: 50px;
    vertical-align: center;
  }
</style>
