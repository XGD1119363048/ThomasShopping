import {SETLOGIN} from "./mutations-types";

export default {
  [SETLOGIN](state, payload) {
    state.userId = payload.userId
    state.isLogin = payload.isLogin
    window.sessionStorage.setItem('userId', payload.userId)
    window.sessionStorage.setItem('isLogin', payload.isLogin)
  }
}
