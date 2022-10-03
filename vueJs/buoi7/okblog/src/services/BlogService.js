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
  getBlogById (id) {
    return http.get(`/blog/${id}`)
  }
  update (id, blog) {
    this.getBlogById(id)
    let blogForm = new FormData(blog)
    return http.put(`/blog/${id}`, blogForm, {headers: authHeader()})
  }
  update2 (id, data) {
    this.getBlogById(id)
    return http.put(`/blog/${id}`, data, {headers: authHeader()})
  }
  delete (id) {
    return http.delete(`/blog/delete/${id}`, {headers: authHeader()})
  }
  getBlog (id) {
    return http.get(`/blog/${id}`)
  }
  getCategory () {
    return http.get('/categorys')
  }
  getAuthor () {
    return http.get('/authors')
  }
  findByText (text) {
    return http.get(`/blog/find?text=${text}`)
  }
}
export default new BlogService()
