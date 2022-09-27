import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/',
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
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('../components/Profile.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      // lazy-loaded
      component: () => import('../components/BoarAdmin.vue')
    },
    {
      path: '/mod',
      name: 'modertor',
      // lazy-loaded
      component: () => import('../components/BoardUser.vue')
    },
    {
      path: '/user',
      name: 'user',
      // lazy-loaded
      component: () => import('../components/BoardUser.vue')
    },
    {
      path: '/add',
      name: 'add',
      component: () => import('../components/Add')

    },
    {
      path: '/blog/:id',
      name: 'blogDetail',
      component: () => import('../components/Blog.vue')
    }
  ]
})
