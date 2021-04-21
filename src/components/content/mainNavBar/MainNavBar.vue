<template>
  <nav-bar>
    <div slot="topbar-nav">
      <nav-bar-item path="/home">
        <div slot="item-text">TMSshopping</div>
      </nav-bar-item>
      <nav-bar-item>
        <div slot="item-text">good2</div>
      </nav-bar-item>
      <nav-bar-item>
        <div slot="item-text">good3</div>
      </nav-bar-item>
      <nav-bar-item>
        <div slot="item-text">good4</div>
      </nav-bar-item>
      <nav-bar-item>
        <div slot="item-text">good5</div>
      </nav-bar-item>
      <nav-bar-item>
        <div slot="item-text">good6</div>
      </nav-bar-item>
    </div>
    <div slot="topbar-cart">
      <nav-bar-item>
        <img slot="item-icon" src="~assets/img/navbar/Cart.svg">
        <div slot="item-text">Cart</div>
      </nav-bar-item>
    </div>
    <div slot="topbar-info">
      <nav-bar-item path="/login">
        <div slot="item-text">Login</div>
      </nav-bar-item>
      <nav-bar-item path="/register">
        <div slot="item-text">Register</div>
      </nav-bar-item>
      <nav-bar-item>
        <div slot="item-text">Message</div>
      </nav-bar-item>
    </div>
    <div slot="header-logo">
      <nav-bar-item2>
        <img slot="item-icon" src="~assets/img/navbar/Cart_Active.svg">
      </nav-bar-item2>
    </div>
    <div slot="header-search">
      <nav-bar-item2>
        <div slot="item-box">
          <input placeholder="Basic usage" />
          <input type="submit"></input>
        </div>
      </nav-bar-item2>
    </div>
  </nav-bar>
</template>

<script>
import NavBar from "components/common/navbar/NavBar";
import NavBarItem from "components/common/navbar/NavBarItem";
import NavBarItem2 from "components/common/navbar/NavBarItem2";

export default {
  name: "MainNavBar",
  components: {
    NavBar,
    NavBarItem,
    NavBarItem2
  }
}
</script>

<style scoped>
  div {
    display: inline;
  }
</style>
