import Vue from 'vue'
import Vuex from 'vuex'

import mutations from "./mutations";
import actions from './actions'
import getters from './getters'

Vue.use(Vuex)

const state = {
<<<<<<< HEAD
  isLogin: false,
  userId: '',
  userInfo: {}
=======
  isLogin: window.sessionStorage.getItem('isLogin'),
  userId: window.sessionStorage.getItem('userId'),
  userInfo: window.sessionStorage.getItem('userInfo')
  // isLogin: false,
  // userId: ''
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
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
