<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">bezKoder</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/blog" class="nav-link">Blogs</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link">Add</router-link>
        </li>
      </div>
      <div class="navbar-nav mr-auto" v-if="!loggedIn">
        <li>
          <router-link to="/login" class="nav-link">Login</router-link>
        </li>
      </div>
      <div class="navbar-nav mr-auto" v-if="!loggedIn">
        <li>
          <router-link to="/Register" class="nav-link">Register</router-link>
        </li>
      </div>
      <div class="navbar-nav mr-auto" v-if="loggedIn">
        <li>
            <button class="btn btn-danger nav-link" @click="logOut">logOut</button>
        </li>
      </div>
    </nav>
    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>
import login from '../src/components/Login'
export default {
  name: 'app',
  components: login,
  computed: {
    loggedIn () {
      return this.$store.state.auth.status.loggedIn
    }
  },
  methods: {
    logOut () {
      this.$store.dispatch('auth/logout')
      this.$router.push('/login')
    }
  }
}
</script>
