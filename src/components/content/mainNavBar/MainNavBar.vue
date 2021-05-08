<template>
  <nav-bar>
    <div slot="topbar-nav">
      <nav-bar-item path="/home">
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
      <nav-bar-item path="/cart">
        <a slot="item-text">
          <a-icon type="shopping-cart" />
          Cart
        </a>
      </nav-bar-item>
    </div>
    <div v-if="$store.state.isLogin == false" slot="topbar-info">
      <nav-bar-item path="/login">
        <a slot="item-text">Login</a>
      </nav-bar-item>
      <nav-bar-item path="/register">
        <a slot="item-text">Register</a>
      </nav-bar-item>
      <nav-bar-item>
        <a slot="item-text">Message</a>
      </nav-bar-item>
    </div>
    <div v-else slot="topbar-info">
      <nav-bar-item>
        <a slot="item-text">
          <a-icon type="reddit" />
          {{$store.state.userId}}
        </a>
      </nav-bar-item>
      <nav-bar-item path="/login">
        <a slot="item-text" @click="logoutClick">
          Logout
        </a>
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
          <a-input-search
              placeholder="input search text"
              enter-button="Search"
              size="large"
              @search="onSearch"
          />
        </div>
      </nav-bar-item2>
    </div>
  </nav-bar>
</template>

<script>
  import NavBar from "components/common/navbar/NavBar";
  import NavBarItem from "components/common/navbar/NavBarItem";
  import NavBarItem2 from "components/common/navbar/NavBarItem2";
  import { Input, Button, Icon } from 'ant-design-vue'

  export default {
    name: "MainNavBar",
    components: {
      NavBar,
      NavBarItem,
      NavBarItem2,
      'a-input-search': Input.Search,
      'a-button': Button,
      'a-icon': Icon
    },
    methods: {
      onSearch(value) {
        console.log(value)
      },
      logoutClick() {
        this.$store.state.userId = ''
        this.$store.state.isLogin = false
        // this.$router.push('/login')
      }
    }
  }
</script>

<style scoped>
  div {
    display: inline;
  }
</style>
