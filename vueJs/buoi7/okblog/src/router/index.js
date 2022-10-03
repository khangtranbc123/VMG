import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

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
    },
    {
      path: '/blog/:blogId',
      name: 'EditBlog',
      component: () => import('../components/Edit.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../components/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../components/Register.vue')
    },
    {
      path: '/logout',
      name: 'logout',
      beforeEnter (to, from, next) {
        store.dispatch('logout').then(() => {
          if (this.$store.state.auth.status.loggedIn) {
            return next('/')
          }
          location.reload()
        }).catch(reason => {
          console.log(reason)
        })
      }
    }
  ]
})
