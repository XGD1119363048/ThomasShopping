<template>
  <nav-bar>
    <div slot="topbar-nav">
      <nav-bar-item>
        <a slot="item-text">TMSshopping</a>
      </nav-bar-item>
      <nav-bar-item>
        <a slot="item-text">good2</a>
      </nav-bar-item>
      <nav-bar-item>
        <a slot="item-text">good3</a>
      </nav-bar-item>
      <nav-bar-item>
        <a slot="item-text">good4</a>
      </nav-bar-item>
      <nav-bar-item>
        <a slot="item-text">good5</a>
      </nav-bar-item>
      <nav-bar-item>
        <a slot="item-text">good6</a>
      </nav-bar-item>
    </div>
    <div slot="topbar-cart">
      <nav-bar-item>
<!--        <img slot="item-icon" src="~assets/img/navbar/Cart.svg">-->
        <a slot="item-text">Cart</a>
      </nav-bar-item>
    </div>
    <div slot="topbar-info">
      <nav-bar-item>
        <a slot="item-text">Login</a>
      </nav-bar-item>
      <nav-bar-item>
        <a slot="item-text">Register</a>
      </nav-bar-item>
      <nav-bar-item>
        <a slot="item-text">Message</a>
      </nav-bar-item>
    </div>
  </nav-bar>
</template>

<script>
  import NavBar from "components/common/navbar/NavBar";
  import NavBarItem from "components/common/navbar/NavBarItem";

  export default {
    name: "MainNavBar",
    components: {
      NavBar,
      NavBarItem
    }
  }
</script>

<style scoped>

</style>
