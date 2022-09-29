import http from '../http-common'
import authHeader from './auth-hreader'

class BlogService {
  getAll (params) {
    return http.get('/list', {params, headers: authHeader()})
  }
  createBlog (blog) {
    let blogForm = new FormData(blog)
    return http.post('/blog/create', blogForm, {headers: authHeader()})
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
