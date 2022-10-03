<template>
  <div>
    <div class="container mt-3">
      <div class="row">
        <div class="col">
          <p class="h3 text-success fw-bold">Update Blog</p>
        </div>
      </div>
    </div>
    <div class="container mt-3">
      <div class="row">
        <div class="col-md-3">
          <form>
            <div class="mb-2">
              <input v-model="blog.title" type="text" name="title" class="form-control" placeholder="Title">
            </div>
            <div class="mb-2">
              <input v-model="blog.content" name="content" type="text" class="form-control" placeholder="Content">
            </div>
            <div class="mb-2">
              <select v-model="blog.authors" class="form-control" name="authors">
                <option :value="blog.authors">{{ blog.authors.authorName }}</option>
                <option :value="a.id" v-for="a of authors" :key="a.id">{{a.authorName}}</option>
              </select>
            </div>
            <div class="mb-2">
              <select v-model="blog.categorys" class="form-control" name="categorys">
                <option :value="blog.categorys">{{ blog.categorys.name }}</option>
                <option :value="cate.id" v-for="cate of categorys" :key="cate.id">{{cate.name}}</option>
              </select>
            </div>
            <div class="mb-2">
              <button class="btn btn-success" @click.prevent="update">Update</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BlogService from '../services/BlogService'
export default {
  name: 'EditBlog',
  data: function () {
    return {
      blogId: this.$route.params.blogId,
      blog: {
        title: '',
        categorys: '',
        content: '',
        authors: ''
      },
      errorMessage: null,
      categorys: [],
      authors: []
    }
  },
  created: async function () {
    try {
      let response = await BlogService.getBlog(this.blogId)
      let responseCate = await BlogService.getCategory()
      let responseAuth = await BlogService.getAuthor()
      this.categorys = responseCate.data
      this.blog = response.data
      this.authors = responseAuth.data
    } catch (error) {
      this.errorMessage = error
    }
  },
  methods: {
    update () {
      BlogService.update2(this.blogId, this.blog)
        .then(response => {
          console.log(response.data)
          this.message = 'The tutorial was updated successfully!'
          return this.$router.push('/')
        })
        .catch(e => {
          console.log(e)
        })
      // var data = {
      //   id: this.blog.id,
      //   content: this.blog.content,
      //   title: this.blog.title,
      //   authors: this.blog.authors,
      //   categorys: this.blog.categorys
      // }
      // let form = document.querySelector('#form-blog')
      // console.log(form)
      // BlogService.update2(this.blogId, form)
      // console.log(this.blogId)
    //  return this.$router.push('/')
    }
  }
}
</script>
