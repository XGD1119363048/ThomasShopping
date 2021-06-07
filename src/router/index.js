import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";
import {SETLOGIN} from "@/store/mutations-types";

const Cart = () => import('../views/cart/Cart')
const Home = () => import('../views/home/Home')
const Login = () => import('../views/login/Login')
const Register = () => import('../views/register/Register')
const Good = () => import('../views/good/Good')
const Info = () => import('../views/info/Info')

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
  {
    path: '',
    redirect: '/login'
  },
  {
    path: '/cart',
    component: Cart,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/home',
    component: Home
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/goodDetail',
    component: Good,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/info',
    component: Info
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach(((to, from, next) => {
  // console.log(window.sessionStorage.getItem('isLogin'))
  // console.log(window.sessionStorage.getItem('userId'))
  const payload = {
    userId: window.sessionStorage.getItem('userId'),
    isLogin: window.sessionStorage.getItem('isLogin')
  }
  // console.log(payload);
  store.commit(SETLOGIN, payload)
  if(to.meta.requireAuth && !window.sessionStorage.getItem('isLogin')) {
    // console.log('test')
    router.push('/login')
  } else {
    next()
  }
}))

export default router
