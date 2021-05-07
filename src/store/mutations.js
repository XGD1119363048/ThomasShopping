import {SETUSERID} from "./mutations-types";

export default {
  [SETUSERID](state, payload) {
    sessionStorage.setItem('userId', payload.userId)
    sessionStorage.setItem('isLogin', payload.isLogin)
    state.userId = payload.userId
    state.isLogin = payload.isLogin
  }
}
