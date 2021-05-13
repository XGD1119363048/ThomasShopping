<<<<<<< HEAD
import {SETUSERID} from "./mutations-types";

export default {
  [SETUSERID](state, payload) {
    state.userId = payload.userId
    state.isLogin = payload.isLogin
=======
import {SETLOGIN} from "./mutations-types";

export default {
  [SETLOGIN](state, payload) {
    state.userId = payload.userId
    state.isLogin = payload.isLogin
    window.sessionStorage.setItem('userId', payload.userId)
    window.sessionStorage.setItem('isLogin', payload.isLogin)
>>>>>>> 7905b19445d052cbba97498d8991c4d5c81af553
  }
}
