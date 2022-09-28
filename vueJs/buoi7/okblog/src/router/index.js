import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      alias: '/blog',
      name: 'blog',
      component: () => import('../components/List.vue')
    },
    {
      path: '/add',
      name: 'add',
      component: () => import('../components/create.vue')
    }
  ]
})
