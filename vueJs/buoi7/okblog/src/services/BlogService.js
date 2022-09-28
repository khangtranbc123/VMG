import http from '../http-common'

class BlogService {
  getAll () {
    return http.get('/list')
  }
  createBlog (blog) {
    let blogForm = new FormData(blog)
    return http.post('/blog/create', blogForm)
  }
  update (id, data) {
    return http.put(`/blog/update/${id}`, data)
  }
  delete (id) {
    return http.delete(`/blog/delete/${id}`)
  }
  getCategory () {
    return http.get('/categorys')
  }
  getAuthor () {
    return http.get('/authors')
  }
}
export default new BlogService()
