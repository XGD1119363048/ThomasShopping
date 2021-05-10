import Vue from 'vue'
import Vuex from 'vuex'

import mutations from "./mutations";
import actions from './actions'
import getters from './getters'

Vue.use(Vuex)

const state = {
  isLogin: window.sessionStorage.getItem('isLogin'),
  userId: window.sessionStorage.getItem('userId'),
  userInfo: window.sessionStorage.getItem('userInfo')
  // isLogin: false,
  // userId: ''
}

const store = new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules: {
  }
})

export default store
