import Vue from 'vue'
import { Button, Input } from "ant-design-vue";
import App from './App.vue'
import router from './router'
import store from './store'

Vue.component(Button.name, Button)
Vue.component(Input.name, Input)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
