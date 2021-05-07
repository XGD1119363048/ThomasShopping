import Vue from 'vue'
import Vuex from 'vuex'

import mutations from "./mutations";
import actions from './actions'
import getters from './getters'

Vue.use(Vuex)

const state = {
  isLogin: false,
  userId: '',
  userInfo: {}
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
