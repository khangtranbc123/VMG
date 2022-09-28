<template>
    <div v-if="!submitted" class="form-add">
      <form id="form-blog">
      <div>
        <label for="title">Title</label>
        <input
          type="text"
          class="form-control"
          id="title"
          v-model="blog.title"
          name="title"
        />
      </div>
      <div>
        <label for="content">Content</label>
        <br>
        <input
          class="form-control"
          id="content"
          v-model="blog.content"
          name="content"
        />
      </div>
      <div>
        <label for="categorys">Category</label>
        <br>
        <select v-model="blog.categorys" name="categorys">
          <option v-for="cate in categorys" :value="cate.id" :key="cate.id">
            {{cate.name}}
          </option>
        </select>
      </div>
      <br>
      <div>
        <label for="authors">Author</label>
        <br>
        <select v-model="blog.authors" name="authors">
          <option v-for="auth in authors" :value="auth.id" :key="auth.id">
            {{auth.authorName}}
          </option>
        </select>
      </div>
      <br>
      <div class="mb-2">
              <input type="file" name="files" multiple class="form-control"  placeholder="Cover">
      </div>
      <br>
      <div>
        <button @click.prevent ="create" class="btn btn-success">Submit</button>
      </div>
      </form>
    </div>
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newBlog">Add</button>
    </div>
</template>

<script>
import BlogService from '../services/BlogService'
export default {
  name: 'add-blog',
  data: function () {
    return {
      blog: {
        title: '',
        content: '',
        categorys: '',
        authors: '',
        file: []
      },
      categorys: [],
      authors: [],
      submitted: false
    }
  },
  methods: {
    getCategorys () {
      BlogService.getCategory()
        .then(Response => {
          this.categorys = Response.data
          console.log(Response.data)
        })
    },
    getAuthors () {
      BlogService.getAuthor()
        .then(Response => {
          this.authors = Response.data
          console.log(Response.data)
        })
    },
    create () {
      let form = document.querySelector('#form-blog')
      console.log(form)
      BlogService.createBlog(form)
        .then(response => {
          console.log(response.data)
          this.submitted = true
        }).catch(e => {
          console.log(e)
          alert('loi!')
        })
      // console.log(form)
      // let response = BlogService.createBlog(form)
      // if (response) {
      //   alert('ok!')
      //   return this.$router.push('/')
      // } else {
      //   return this.$router.push('/add')
      // }
    },
    newBlog () {
      this.submitted = false
      this.blog = {}
    }
  },
  mounted () {
    this.getCategorys()
    this.getAuthors()
  }
}
</script>
<style scoped>
  .submit-form {
    max-width: 300px;
    margin: auto;
  }
  .form-add {
    width: 459px;
    margin: auto;
  }
</style>
