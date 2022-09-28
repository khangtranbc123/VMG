<template>
  <div id="app">
    <div class="nav-bar">
      <!-- <span>Home</span>
      <span>Blog</span>
      <span>Service</span>
      <span>Contact</span> -->
      <li class="nav-item">
      <router-link class="nav" to="/blog">Blog</router-link>
      </li>
      <li class="nav-item">
      <router-link class="nav-link" to="/services">Services</router-link>
      </li>
      <li class="nav-item">
       <router-link class="nav-link" to="/contact">contact</router-link>
      </li>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.nav-bar {
  display: flex;
}
.nav-bar span {
  margin-right: 5px;
}
</style>
