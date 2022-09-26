import http from '../http-common'

class BlogService {
  getAll () {
    return http.get('/list')
  }
  create (data) {
    return http.post('/blog/create', data)
  }
  get (id) {
    return http.get(`/blog/${id}`)
  }
  update (id, data) {
    return http.put(`/blog/${id}`, data)
  }

  delete (id) {
    return http.delete(`/blog/delete/${id}`)
  }

  deleteAll () {
    return http.delete(`/blog`)
  }

  findByText (text) {
    return http.get(`/blog/find?text=${text}`)
  }
}
export default new BlogService()
