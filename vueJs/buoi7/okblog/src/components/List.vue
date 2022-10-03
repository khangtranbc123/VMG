<template>
  <div class="row">
    <div class="col-7">
      <el-table
        :data="blogs.content.filter(data => !search || data.categorys.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%"
        :row-class-name="tableRowClassName">
        <el-table-column
          prop="title"
          label="Title"
          >
        </el-table-column>
        <el-table-column  v-slot="scope"
          label="IMG">
          <template>
            <img v-for="(img, index) in scope.row.covers"
            :key="index"
            v-bind:src="'http://localhost:8080/image/' + img.name" width="50px" style="margin: 4px;">
          </template>
        </el-table-column>
        <el-table-column>
          <template slot="header" slot-scope="scope" >
            <el-input
              v-model="search"
              size="mini"
              placeholder="Type to search"/>
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">
              details
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
          </template>
        </el-table-column>
       </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="itemCount"
          :page-count="count"
          :page-size="pageSize"
          :page-sizes="pageSizes"
          @current-change="handlePageChange"
          hide-on-single-page>
      </el-pagination>
    </div>
    <div class="col-5">
      <div v-if="!status">
        <div class="col-5" >
          <div>
          <label>
            <h3>Title: {{blog.title}}</h3>
          </label>
        </div>
        <div>
          <label>
            <h6>Author: {{blog.authors.authorName}}</h6>
          </label>
          <br>
        </div>
        <div>
          <label>
            <h6>Category: {{blog.categorys.name}}</h6>
          </label>
          <br>
        </div>
        <div>
          <label>
            <h9>Content: {{blog.content}}</h9>
          </label>
        </div>
      </div>
      <router-link :to="`/blog/${blog.id}`" class="btn btn-warning"><i class="fa fa-pen">edit</i></router-link>
    </div>
    <div v-else>
      <p>Please click on a Blog....</p>
    </div>
    </div>
  </div>

</template>

<script>
import BlogService from '../services/BlogService'
import UserSerVice from '../services/user-service'
export default {
  name: 'blog-list',
  data () {
    return {
      blogs: [],
      blogId: '',
      currentBlog: null,
      currentIndex: -1,
      content: '',
      page: 1,
      cuont: 0,
      pageSize: 5,
      itemCount: 6,
      pageSizes: [2, 4, 6],
      multipleSelection: [],
      search: '',
      blog: {
        id: '',
        title: '',
        content: '',
        categorys: '',
        authors: ''
      },
      status: true,
      categories: [],
      authories: []
    }
  },
  methods: {
    getRequestParams (page, pageSize) {
      let params = {}
      if (page) {
        params[ 'page' ] = page - 1
      }
      if (pageSize) {
        params['size'] = pageSize
      }
      return params
    },
    retrieveBlog () {
      const params = this.getRequestParams(
        this.page,
        this.pageSize
      )
      BlogService.getAll(params)
        .then(response => {
          this.blogs = response.data
          this.count = response.data.totalPages
          this.itemCount = response.data.totalElements
          console.log(response.data)
          console.log(response.data.content)
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
      this.handleEdit()
      this.currentBlog = null
      this.currentIndex = -1
    },
    setBlog (blog, index) {
      this.currentBlog = blog
      this.currentIndex = index
    },
    handleEdit (index, row) {
      this.blogId = row.id
      console.log(row.id)
      console.log(index)
      BlogService.getBlog(row.id)
        .then(response => {
          this.blog = response.data
        })
      BlogService.getCategory()
        .then(Response => {
          this.categories = Response.data
          console.log(Response.data)
        })
      BlogService.getAuthor()
        .then(Response => {
          this.authories = Response.data
          console.log(Response.data)
        })
      this.status = false
    },
    updateBlog () {
      let form = document.querySelector('#form-blog')
      console.log(form)
      BlogService.update2(form)
    },
    handleDelete (row) {
      if (confirm('Bạn muốn xóa à?')) {
        try {
          BlogService.delete(row.id)
          location.reload(true)
        } catch (error) {
          this.errorMessage = error
        }
      }
    },
    handlePageChange (value) {
      this.page = value
      console.log(this.page)
      this.retrieveBlog()
    },
    handleSelectionChange (val) {
      let ids = []
      val.forEach(v => {
        ids.push(v.id)
      })
      this.multipleSelection = [...ids]
      console.log(ids)
      console.log(this.multipleSelection)
    }
  },
  mounted () {
    this.retrieveBlog()
    UserSerVice.getUserBoard().then(
      response => {
        this.content = response.data
      },
      error => {
        this.content =
        (error.response && error.response.data) ||
        error.message ||
        error.toString()
      }
    )
  }
}
</script>
