import {SETUSERID} from "./mutations-types";

export default {
  [SETUSERID](state, payload) {
    state.userId = payload.userId
    state.isLogin = payload.isLogin
  }
}
