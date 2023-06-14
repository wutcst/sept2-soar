import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    component: () => import(/* webpackChunkName: "home" */ '../views/Home.vue')
  },
  {
    path: '/game',
    component: () => import(/* webpackChunkName: "game" */ '../views/Game.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
