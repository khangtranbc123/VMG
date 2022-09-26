<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by text"
          v-model="text"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchText"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Blogs List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(x, index) in blogs"
          :key="index"
          @click="setActiveTutorial(x, index)"
        >
          {{ x.title }}
        </li>
      </ul>
      <button class="m-3 btn btn-sm btn-danger" @click="removeAllTutorials">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentTutorial">
        <h4>Blog</h4>
        <div>
          <div>
            <label><strong>id:</strong></label> {{ currentTutorial.id }}
          </div>
          <div>
            <label><strong>content:</strong></label> {{ currentTutorial.content }}
          </div>
          <div>
            <label><strong>category:</strong></label> {{ currentTutorial.categorys.name}}
          </div>
          <div>
            <label><strong>author:</strong></label> {{ currentTutorial.authors.authorName}}
          </div>
          <div>
            <a class="badge badge-warning "
              :href="'/companyList/' + currentTutorial.id ">
              Edit
            </a>
          </div>
        </div>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Blog...</p>
      </div>
    </div>
  </div>
</template>
<script>

import BlogService from '../service/BlogService'

export default {
  name: 'blogs-list',
  data () {
    return {
      blogs: [],
      currentBlog: null,
      currentIndex: -1,
      text: ''
    }
  },
  methods: {
    retrieveTutorials () {
      BlogService.getAll()
        .then(response => {
          this.blogs = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    refreshList () {
      this.retrieveTutorials()
      this.currentBlog = null
      this.currentIndex = -1
    },

    setActiveTutorial (blog, index) {
      this.currentBlog = blog
      this.currentIndex = index
    },

    removeAllTutorials () {
      BlogService.deleteAll()
        .then(response => {
          console.log(response.data)
          this.refreshList()
        })
        .catch(e => {
          console.log(e)
        })
    },

    searchText () {
      BlogService.findByText(this.name)
        .then(response => {
          this.name = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    }
  },
  mounted () {
    this.retrieveTutorials()
  }
}

</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
