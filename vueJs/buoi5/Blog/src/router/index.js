import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      alias: '/list',
      name: 'list',
      component: () => import('../components/List.vue')
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
