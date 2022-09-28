<template>
  <div class="row">
    <div>
      <el-table
    @click="setBlog(blogs ,blogs.id)"
    :data="blogs"
    style="width: 100%"
    :row-class-name="tableRowClassName">
    <el-table-column
      prop="id"
      label="ID"
      width="180">
    </el-table-column>
    <el-table-column
      prop="title"
      label="Title"
      width="180">
    </el-table-column>
    <el-table-column
      prop="content"
      label="Content">
    </el-table-column>
    <el-table-column
      prop="categorys.name"
      label="Category">
    </el-table-column>``
    <el-table-column
      prop="authors.authorName"
      label="Author">
    </el-table-column>
    <el-table-column  v-slot="scope"
      label="IMG">
      <template>
        <img v-for="(img, index) in scope.row.covers"
        :key="index"
         v-bind:src="'http://localhost:8080/image/' + img.name" width="50px">
      </template>
    </el-table-column>
  </el-table>
    </div>
  </div>

</template>

<script>
import BlogService from '../services/BlogService'

export default {
  name: 'blog-list',
  data () {
    return {
      blogs: [],
      currentBlog: null,
      currentIndex: -1
    }
  },
  methods: {
    retrieveBlog () {
      BlogService.getAll()
        .then(response => {
          this.blogs = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    tableRowClassName ({rowIndex}) {
      if (rowIndex % 2 === 0) {
        return 'warning-row'
      }
      return ''
    },
    refreshList () {
      this.retrieveBlog()
      this.currentBlog = null
      this.currentIndex = -1
    },
    setBlog (blog, index) {
      this.currentBlog = blog
      this.currentIndex = index
    }
  },
  mounted () {
    this.retrieveBlog()
  }
}
</script>
